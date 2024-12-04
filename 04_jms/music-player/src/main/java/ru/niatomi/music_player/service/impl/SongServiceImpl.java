package ru.niatomi.music_player.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.niatomi.music_player.events.publisher.EventPublisher;
import ru.niatomi.music_player.exceptions.NotFoundException;
import ru.niatomi.music_player.models.domain.Song;
import ru.niatomi.music_player.repository.SongRepository;
import ru.niatomi.music_player.service.SongService;

import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public Song getSong(Integer songId) {
        Optional<Song> song = songRepository.findById(songId);
        if (song.isEmpty())
            throw new NotFoundException();
        return song.get();
    }

    @Override
    public boolean playSong(Song song) {
        try {
            Integer old = song.getListenCount();
            song.setListenCount(song.getListenCount() + 1);
            songRepository.save(song);
            eventPublisher.sendChange(
                    song.getTableName(),
                    song.getColumnName("listenCount"),
                    song.getId(),
                    old,
                    song.getListenCount());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
