/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.music_player.models.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ru.niatomi.music_player.models.AnnotationInfo;

import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author nia
 */
@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "artists")
public class Artist implements AnnotationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    @OneToMany(mappedBy="artist", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Song> songs;

    @Override
    public String getTableName() {
        Table annotation = this.getClass().getAnnotation(Table.class);
        return annotation.name();
    }

    @Override
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
