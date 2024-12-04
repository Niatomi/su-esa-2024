package ru.niatomi.music_player.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.niatomi.music_player.events.publisher.EventPublisher;
import ru.niatomi.music_player.exceptions.NotFoundException;
import ru.niatomi.music_player.models.domain.Album;
import ru.niatomi.music_player.models.domain.Artist;
import ru.niatomi.music_player.models.domain.Song;
import ru.niatomi.music_player.repository.ArtistRepository;
import ru.niatomi.music_player.repository.SongRepository;
import ru.niatomi.music_player.service.ArtistService;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistsServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist getArtist(Integer artistId) {
        Artist artist = artistRepository.findById(artistId);
        if (artist == null) {
            throw new NotFoundException();
        }
        return artist;
    }

    @Override
    public Artist getArtistBySongId(Integer songId) {
        Optional<Song> songOpt = songRepository.findById(songId);
        if (songOpt.isEmpty()) {
            throw new NotFoundException();
        }
        Song song = songOpt.get();
        return song.getArtist();
    }

    @Override
    public void playArtistSongs(Artist artist) {
        LinkedList<Song> songsCopy = new LinkedList<>(artist.getSongs());
        for (Song song: songsCopy) {
            song.setListenCount(song.getListenCount() + 1);
        }
        songRepository.saveAll(songsCopy);
        for (Song song: songsCopy) {
            eventPublisher.sendChange(
                song.getTableName(),
                song.getColumnName("listenCount"),
                song.getId(),
                    song.getListenCount() - 1,
                song.getListenCount()
            );
        }
    }
}
