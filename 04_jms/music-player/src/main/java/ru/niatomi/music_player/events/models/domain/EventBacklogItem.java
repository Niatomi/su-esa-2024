package ru.niatomi.music_player.events.models.domain;

import jakarta.persistence.*;
import lombok.*;
import ru.niatomi.music_player.models.domain.Album;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "events_backlog")
@Builder
public class EventBacklogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "which_table")
    private String whichTable;

    @Column(name = "old", nullable = true)
    private String oldValue;

    @Column(name = "new", nullable = true)
    private String newValue;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventType event;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "entity_attribute")
    private String entityAttribute;


}
