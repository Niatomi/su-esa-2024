/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.musicplayer.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.niatomi.musicplayer.dao.GenericDAO;
import ru.niatomi.musicplayer.dao.impl.ArtistDAO;
import ru.niatomi.musicplayer.dao.impl.SongDAO;
import ru.niatomi.musicplayer.models.domain.Artist;
import ru.niatomi.musicplayer.models.domain.Song;

/**
 *
 * @author nia
 */
@Stateless
public class StupidService {
    
    @EJB(beanInterface = GenericDAO.class, beanName = "SongDAO")
    private GenericDAO sdao;
    
    @EJB(beanInterface = GenericDAO.class, beanName = "ArtistDAO") 
    private GenericDAO adao;
    
    
    public List<String> getSomeObjs() {
        List<String> result = new ArrayList<String>();
        List<Song> songs = sdao.select();
        for (Song song : songs) {
            result.add(song.toString());
        }
//        List<Artist> artists = adao.select();
//        for (Artist artist : artists) {
//            for (Song s : artist.getSongs()) {
//                result.add("" + s.getId() + s.getName());
//            }
//        }
        return result;
    }
}
