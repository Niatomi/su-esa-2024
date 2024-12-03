/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.music_player.models.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 *
 * @author nia
 */
@Entity
@Data
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    
    @Column(name = "listen_count")
    private Integer listenCount;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;
    
}
