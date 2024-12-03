package ru.niatomi.music_player.service;

import ru.niatomi.music_player.exceptions.NotFoundException;

import java.util.List;

public interface AccessingService {

    List<Object> getObjectsByClassName(String className);
    void isClassNameExists(String className) throws NotFoundException;

}
