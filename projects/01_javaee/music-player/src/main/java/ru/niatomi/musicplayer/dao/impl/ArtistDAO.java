/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.musicplayer.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import ru.niatomi.musicplayer.dao.GenericDAO;
import ru.niatomi.musicplayer.models.domain.Artist;

/**
 *
 * @author nia
 */
@Stateless
public class ArtistDAO implements GenericDAO<Integer, Artist>{

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager entityManager;
    
    @Override
    public Artist insert(Artist item) {
        entityManager.persist(item);
        return item;
    }

    
    @Override
    public List<Artist> select() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Artist> criteria = cb.createQuery(Artist.class);
        CriteriaQuery<Artist> all = criteria.select(criteria.from(Artist.class));
        List<Artist> resultList = entityManager.createQuery(all).getResultList();
        return resultList;
    }

    @Override
    public Artist findByKey(Integer key) {
        return entityManager.find(Artist.class, key);
    }

    @Override
    public Artist update(Integer key, Artist item) {
        Artist oldArtist = findByKey(key);
        oldArtist.setName(item.getName());
        entityManager.persist(oldArtist);
        return oldArtist;
    }

    @Override
    public void removeByKey(Integer key) {
        Artist deleteArtist = findByKey(key);
        entityManager.remove(deleteArtist);
    }
    
    
    
}
