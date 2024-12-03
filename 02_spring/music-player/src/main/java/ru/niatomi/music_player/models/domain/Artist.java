/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.music_player.models.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 *
 * @author nia
 */
@Entity
@Data
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    @OneToMany(mappedBy="artist", fetch = FetchType.LAZY)
    private List<Song> songs;

}
