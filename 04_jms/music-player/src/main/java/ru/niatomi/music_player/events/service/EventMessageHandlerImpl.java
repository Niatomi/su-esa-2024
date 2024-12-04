package ru.niatomi.music_player.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.niatomi.music_player.email.EmailDetails;
import ru.niatomi.music_player.email.EmailSender;
import ru.niatomi.music_player.events.models.domain.EventBacklogItem;
import ru.niatomi.music_player.events.models.domain.EventSubscriber;
import ru.niatomi.music_player.events.models.domain.EventType;
import ru.niatomi.music_player.events.models.dto.InstanceChangeEvent;
import ru.niatomi.music_player.events.repository.EventBacklogItemRepository;
import ru.niatomi.music_player.events.repository.EventSubscriberRepository;
import ru.niatomi.music_player.events.repository.EventTypeRepository;
import ru.niatomi.music_player.models.domain.Album;
import ru.niatomi.music_player.models.domain.Song;
import ru.niatomi.music_player.repository.AlbumRepository;
import ru.niatomi.music_player.repository.SongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventMessageHandlerImpl implements EventMessageHandler{

    @Autowired
    private EventBacklogItemRepository eventBacklogItemRepository;

    @Autowired
    private EventSubscriberRepository eventSubscriberRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private EmailSender emailSender;


    private static final Integer RELEASE = 1;
    private static final Integer WRONG_STATISTICS = 2;
    private static final Integer LISTEN_ANNIVERSARY = 3;

    private void notifyUsers(String msg, EventType eventType, String condition) {
        List<EventSubscriber> byEventIdAndCondition = eventSubscriberRepository.findByEventIdAndCondition(
                eventType.getId(),
                condition
        );
        for (EventSubscriber es: byEventIdAndCondition) {
            EmailDetails emailDetails = EmailDetails.builder()
                .recipient(es.getEmail())
                .msgBody(msg)
                .subject(eventType.getDescription())
                .build();
            emailSender.sendSimpleMail(emailDetails);
        }
    }

    private EventBacklogItem saveBacklogItem(InstanceChangeEvent instanceChangeEvent, Integer eventId) {
        EventType eventType = eventTypeRepository.findById(eventId).get();
        EventBacklogItem.EventBacklogItemBuilder eventBacklogItemBuilder = EventBacklogItem.builder()
                .whichTable(instanceChangeEvent.getInstanceName())
                .newValue(instanceChangeEvent.getNewValue().toString())
                .event(eventType)
                .entityId(instanceChangeEvent.getEntityId())
                .entityAttribute(instanceChangeEvent.getAttributeName());
        if (instanceChangeEvent.getOldValue() != null) {
            eventBacklogItemBuilder.oldValue(instanceChangeEvent.getOldValue().toString());
        } else {
            eventBacklogItemBuilder.oldValue(null);
        }
        EventBacklogItem eventBacklogItem = eventBacklogItemBuilder.build();
        EventBacklogItem saveItem = eventBacklogItemRepository.save(eventBacklogItem);
        return saveItem;
    }

    @Override
    public void handleMessage(InstanceChangeEvent instanceChangeEvent) {

        if (instanceChangeEvent.getInstanceName().equals("songs")) {
            if (instanceChangeEvent.getAttributeName().equals("listen_count")) {
//        Anniversary listens
                boolean isAnniversary = (Integer) instanceChangeEvent.getNewValue() % 10 == 0;
                if (isAnniversary) {
                    EventBacklogItem eventBacklogItem = saveBacklogItem(instanceChangeEvent, LISTEN_ANNIVERSARY);
                    String msg = "Wow!!! %s got it %d listens";
                    Song song = songRepository.findById(eventBacklogItem.getEntityId()).get();
                    msg = String.format(msg, song.getName(), song.getListenCount());
                    String condition = "songs.artist->%s";
                    condition = String.format(condition, song.getArtist().getName());
                    notifyUsers(msg, eventBacklogItem.getEvent(), condition);
                }
//        Wrong statistics event
                if (((Integer) instanceChangeEvent.getNewValue()) < ((Integer) instanceChangeEvent.getOldValue())) {
                    EventBacklogItem eventBacklogItem = saveBacklogItem(instanceChangeEvent, WRONG_STATISTICS);
                    String msg = "Something went wrong with statistics. Check id=%d at events_backlog";
                    msg = String.format(msg, eventBacklogItem.getId());
                    notifyUsers(msg, eventBacklogItem.getEvent(), "songs");
                }
            }
        }
//        New relaese
        if (instanceChangeEvent.getInstanceName().equals("albums")) {
            if (instanceChangeEvent.getOldValue() == null) {
                EventBacklogItem eventBacklogItem = saveBacklogItem(instanceChangeEvent, RELEASE);
                Album album = albumRepository.findById(instanceChangeEvent.getEntityId()).get();
                Song song0 = album.getSongs().get(0);
                String msg = "Artist %s released new album: %s!";
                msg = String.format(msg, song0.getArtist().getName(), album.getAlbumName());
                String condition = "artists.name->%s";
                condition = String.format(condition, song0.getArtist().getName());
                notifyUsers(msg, eventBacklogItem.getEvent(), condition);
            }
        }

    }
}
