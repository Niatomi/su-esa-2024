package ru.niatomi.music_player.events.models.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Table(name = "event_subscribers")
public class EventSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventType event;

    private String condition;
}
