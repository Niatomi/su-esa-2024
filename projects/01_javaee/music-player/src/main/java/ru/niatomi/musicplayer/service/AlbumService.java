/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.musicplayer.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.niatomi.musicplayer.dao.GenericDAO;
import ru.niatomi.musicplayer.models.domain.Album;
import ru.niatomi.musicplayer.models.domain.Song;

/**
 *
 * @author nia
 */
@Stateless
public class AlbumService {
    
    @EJB(beanInterface = GenericDAO.class, beanName = "SongDAO")
    private GenericDAO sdao;
    
    @EJB(beanInterface = GenericDAO.class, beanName = "ArtistDAO") 
    private GenericDAO adao;
    
    @EJB(beanInterface = GenericDAO.class, beanName = "AlbumDAO") 
    private GenericDAO aldao;
    
    public Album getAlbum(Integer album_id) {
        Album album = (Album) aldao.findByKey(album_id);
        return album;
    }
    
    public void playAlbumSong(Integer song_id) {
        Song song = (Song) sdao.findByKey(song_id);
        song.setListenCount(song.getListenCount() + 1);
        sdao.update(song_id, song);
    }
    
    public Integer countTotalListenCount (Album album) {
        Integer totalCount = 0;
        for (Song song : album.getSongs()) {
            totalCount += song.getListenCount();
        }
        return totalCount;
    }
    
}
