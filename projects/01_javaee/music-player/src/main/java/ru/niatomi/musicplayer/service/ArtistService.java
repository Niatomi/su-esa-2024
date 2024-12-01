/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.musicplayer.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;
import ru.niatomi.musicplayer.dao.GenericDAO;
import ru.niatomi.musicplayer.models.domain.Artist;

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
    
    public Artist getArtist(Integer key) throws NotFoundException{
        Artist found = (Artist) adao.findByKey(key);
        if (found != null)
            return found;
        throw new NotFoundException("Artist not found");
    }
}
