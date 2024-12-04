package ru.niatomi.music_player.events.repository;

import org.springframework.data.repository.CrudRepository;
import ru.niatomi.music_player.events.models.domain.EventSubscriber;

import java.util.List;

public interface EventSubscriberRepository extends CrudRepository<EventSubscriber, Integer> {
    List<EventSubscriber> findByEventIdAndCondition(Integer eventId, String condition);
}
