package ru.niatomi.musicplayer.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.niatomi.musicplayer.dao.GenericDAO;
import ru.niatomi.musicplayer.models.domain.Artist;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nia
 */
@Stateless
public class ArtistsService {
    
    @EJB(beanInterface = GenericDAO.class, beanName = "ArtistDAO") 
    private GenericDAO adao;
    
    public List<Artist> getArtists() {
        return adao.select();
    }
    
}
