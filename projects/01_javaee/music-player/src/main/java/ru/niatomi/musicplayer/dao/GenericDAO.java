/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.niatomi.musicplayer.dao;

import java.util.List;

/**
 *
 * @author nia
 */
public interface GenericDAO<Integer, Item> {
    
    
    Item insert(Item item);

    List<Item> select();
    Item findByKey(Integer key);
    
    Item update(Integer key, Item item);
    
    void removeByKey(Integer key);

}

