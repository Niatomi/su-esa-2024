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
import ru.niatomi.musicplayer.models.domain.Album;

/**
 *
 * @author nia
 */
@Stateless
public class AlbumDAO implements GenericDAO<Integer, Album>{

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager entityManager;
    
    @Override
    public Album insert(Album item) {
        entityManager.persist(item);
        return item;
    }

    @Override
    public List<Album> select() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Album> criteria = cb.createQuery(Album.class);
        CriteriaQuery<Album> all = criteria.select(criteria.from(Album.class));
        List<Album> resultList = entityManager.createQuery(all).getResultList();
        return resultList;
    }

    @Override
    public Album findByKey(Integer key) {
        return entityManager.find(Album.class, key);
    }

    @Override
    public Album update(Integer key, Album item) {
        Album old = findByKey(key);
        old.setAlbumName(item.getAlbumName());
        entityManager.persist(old);
        return old;
    }

    @Override
    public void removeByKey(Integer key) {
        Album del = findByKey(key);
        entityManager.remove(del);
    }
    
}
