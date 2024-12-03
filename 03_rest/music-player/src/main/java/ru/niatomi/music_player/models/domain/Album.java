/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.music_player.models.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 *
 * @author nia
 */
@Entity
@Data
@RequiredArgsConstructor
@ToString
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "album_name")
    private String albumName;
    
    
    @OneToMany(mappedBy = "album")
    @JsonManagedReference
    private List<Song> songs;
    
    @Override
    public int hashCode() {
        return id;
    }
    
}
