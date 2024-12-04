package ru.niatomi.music_player.events.models.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "event_types")
@Builder
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @OneToMany(mappedBy = "event")
    List<EventBacklogItem> eventBacklogItems;
}
