package ru.niatomi.music_player.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.niatomi.music_player.exceptions.NotFoundException;
import ru.niatomi.music_player.models.domain.Song;
import ru.niatomi.music_player.repository.SongRepository;
import ru.niatomi.music_player.service.SongService;

import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

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
            song.setListenCount(song.getListenCount() + 1);
            songRepository.save(song);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
