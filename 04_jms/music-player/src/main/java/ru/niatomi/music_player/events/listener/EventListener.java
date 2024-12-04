package ru.niatomi.music_player.events.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.niatomi.music_player.events.models.dto.InstanceChangeEvent;
import ru.niatomi.music_player.events.service.EventMessageHandler;

@Component
@Slf4j
public class EventListener {

    @Autowired
    private EventMessageHandler eventMessageHandler;

    @JmsListener(destination = "event", containerFactory = "eventConnectionFactory")
    public void receiveMessage(InstanceChangeEvent instanceChangeEvent) {
        log.info("Recv object: " + instanceChangeEvent.toString());
        eventMessageHandler.handleMessage(instanceChangeEvent);
    }

}

