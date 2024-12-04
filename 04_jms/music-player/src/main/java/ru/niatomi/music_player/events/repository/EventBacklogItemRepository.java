package ru.niatomi.music_player.events.repository;

import org.springframework.data.repository.CrudRepository;
import ru.niatomi.music_player.events.models.domain.EventBacklogItem;

public interface EventBacklogItemRepository extends CrudRepository<EventBacklogItem, Integer> {
}
