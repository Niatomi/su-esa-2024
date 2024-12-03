package ru.niatomi.music_player.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.niatomi.music_player.exceptions.NotFoundException;
import ru.niatomi.music_player.models.domain.Album;
import ru.niatomi.music_player.repository.AlbumRepository;
import ru.niatomi.music_player.repository.ArtistRepository;
import ru.niatomi.music_player.repository.SongRepository;
import ru.niatomi.music_player.service.AccessingService;
import ru.niatomi.music_player.service.AlbumService;
import ru.niatomi.music_player.service.ArtistService;
import ru.niatomi.music_player.service.SongService;
import ru.niatomi.music_player.utils.IterableToListObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessingServiceImpl implements AccessingService {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Object> getObjectsByClassName(String className) {
//      Возможно не самый оптимальный вариант,
//      но учитывая что инстансов всего 3
//      можно руками выделить нужный из всех репозиторий сущностей
        if (className.equals("Album")) {
            return IterableToListObject.getCollectionFromIterable(albumRepository.findAll());
        } else if (className.equals("Artist")) {
            return IterableToListObject.getCollectionFromIterable(artistRepository.findAll());
        } else if (className.equals("Song")) {
            return IterableToListObject.getCollectionFromIterable(songRepository.findAll());
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void isClassNameExists(String className) throws NotFoundException {
        try {
            Class<?> act = Class.forName("ru.niatomi.music_player.models.domain." + className);
        } catch (ClassNotFoundException e) {
            throw new NotFoundException();
        }
    }

}
