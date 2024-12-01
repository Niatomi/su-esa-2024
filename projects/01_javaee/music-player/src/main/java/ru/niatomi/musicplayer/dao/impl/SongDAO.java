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
import ru.niatomi.musicplayer.models.domain.Song;

/**
 *
 * @author nia
 */
@Stateless
public class SongDAO implements GenericDAO<Integer, Song>{

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager entityManager;
    
    @Override
    public Song insert(Song item) {
        entityManager.persist(item);
        return item;
    }

    @Override
    public List<Song> select() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Song> criteria = cb.createQuery(Song.class);
        CriteriaQuery<Song> all = criteria.select(criteria.from(Song.class));
        List<Song> resultList = entityManager.createQuery(all).getResultList();
        return resultList;
    }

    @Override
    public Song findByKey(Integer key) {
        return entityManager.find(Song.class, key);
    }

    @Override
    public Song update(Integer key, Song item) {
        Song old = findByKey(key);
        old.setName(item.getName());
        entityManager.persist(old);
        return old;
    }

    @Override
    public void removeByKey(Integer key) {
        Song del = findByKey(key);
        entityManager.remove(del);
    }

    
    
}
