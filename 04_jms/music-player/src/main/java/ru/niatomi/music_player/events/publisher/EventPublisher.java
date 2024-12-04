package ru.niatomi.music_player.events.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.niatomi.music_player.events.models.dto.InstanceChangeEvent;

@EnableJms
@Component
@Slf4j
public class EventPublisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendChange(String tableName, String attributeName, Integer objectId, Object oldObject, Object newObject) {
        InstanceChangeEvent instanceChangeEvent = new InstanceChangeEvent(tableName, attributeName, objectId, oldObject, newObject);
        jmsTemplate.convertAndSend(
                "event",
                instanceChangeEvent);
        log.info("Sent object: " + instanceChangeEvent.toString());
    }
}
