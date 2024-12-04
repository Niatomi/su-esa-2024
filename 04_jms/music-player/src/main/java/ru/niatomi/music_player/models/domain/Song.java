/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.music_player.models.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ru.niatomi.music_player.models.AnnotationInfo;

import java.lang.reflect.Field;

/**
 *
 * @author nia
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@Table(name = "songs")
public class Song implements AnnotationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    @Column(name = "listen_count")
    private Integer listenCount;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    public String getTableName() {
        Table annotation = this.getClass().getAnnotation(Table.class);
        return annotation.name();
    }

    public String getColumnName(String pojoAttributeName) {
        try {
            Field declaredField = this.getClass().getDeclaredField(pojoAttributeName);
            Column annotation = declaredField.getAnnotation(Column.class);
            return annotation.name();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}
