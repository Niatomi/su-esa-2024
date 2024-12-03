/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.musicplayer.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;
import ru.niatomi.musicplayer.dao.GenericDAO;
import ru.niatomi.musicplayer.models.domain.Album;
import ru.niatomi.musicplayer.models.domain.Artist;
import ru.niatomi.musicplayer.models.domain.Song;

/**
 *
 * @author nia
 */
@Stateless
public class ArtistService {
    
    @EJB(beanInterface = GenericDAO.class, beanName = "SongDAO")
    private GenericDAO sdao;
    
    @EJB(beanInterface = GenericDAO.class, beanName = "ArtistDAO") 
    private GenericDAO adao;
    
    public Artist getArtist(Integer key) throws NotFoundException {
        Artist found = (Artist) adao.findByKey(key);
        if (found != null)
            return found;
        throw new NotFoundException("Artist not found");
    }
    
    public Set<Album> getArtistAlbums(Integer artist_id) throws NotFoundException {
        Artist artist = (Artist) getArtist(artist_id);
        HashSet<Album> albums = new HashSet<Album>();
        for (Song song : artist.getSongs()) {
            albums.add(song.getAlbum());
        }
        return albums;
    }
    
    public void playArtistSongs(Integer key) throws NotFoundException {
        Artist artist = getArtist(key);
        List<Song> songs = artist.getSongs();
        for (Song song : songs) {
            song.setListenCount(song.getListenCount() + 1);
            sdao.update(song.getId(), song);
        }
    }
    
    public void playArtistSong(Integer song_id) {
        Song song = (Song) sdao.findByKey(song_id);
        song.setListenCount(song.getListenCount() + 1);
        sdao.update(song_id, song);
    }
}
