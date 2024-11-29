/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.musicplayer.models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.niatomi.musicplayer.models.domain.Artist;

/**
 *
 * @author nia
 */
@Stateless
public class PersistanceObject {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager entityManager;

    public void saveUser(Artist artist){
        entityManager.persist(artist);
    }
    
}
