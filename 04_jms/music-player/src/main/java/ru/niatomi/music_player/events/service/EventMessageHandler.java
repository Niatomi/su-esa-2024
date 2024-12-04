package ru.niatomi.music_player.events.service;

import ru.niatomi.music_player.events.models.dto.InstanceChangeEvent;

public interface EventMessageHandler {

    void handleMessage(InstanceChangeEvent instanceChangeEvent);

}
