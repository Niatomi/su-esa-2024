package ru.niatomi.music_player.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.niatomi.music_player.events.publisher.EventPublisher;
import ru.niatomi.music_player.exceptions.NotFoundException;
import ru.niatomi.music_player.models.domain.Album;
import ru.niatomi.music_player.models.domain.Artist;
import ru.niatomi.music_player.models.domain.Song;
import ru.niatomi.music_player.repository.AlbumRepository;
import ru.niatomi.music_player.repository.ArtistRepository;
import ru.niatomi.music_player.repository.SongRepository;
import ru.niatomi.music_player.service.ApiService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private EventPublisher eventPublisher;

    private static final Integer TRACKS_IN_ALBUM = 10;


    @Override
    public void updateSong(Integer songId, Integer newListenCount) {
        Optional<Song> songOpt = songRepository.findById(songId);
        if (songOpt.isEmpty())
                throw new NotFoundException();
        Song song = songOpt.get();
        Integer oldListenCount = song.getListenCount();
        song.setListenCount(newListenCount);
        songRepository.save(song);
        eventPublisher.sendChange(
                song.getTableName(),
                song.getColumnName("listenCount"),
                song.getId(),
                oldListenCount,
                newListenCount);
    }

    @Override
    public void fakeRelease(Integer artistId) {
        Artist artist = artistRepository.findById(artistId);
        if (artist == null) {
            throw new NotFoundException();
        }
        Album newAlbum = new Album();
        newAlbum.setAlbumName(UUID.randomUUID().toString());
        newAlbum.setSongs(List.of());
        newAlbum = albumRepository.save(newAlbum);



        LinkedList<Song> newSongs = new LinkedList<>();
        Song newSong;
        for (int i = 0; i < TRACKS_IN_ALBUM; i++) {
            newSong = new Song();
            newSong.setArtist(artist);
            newSong.setName(UUID.randomUUID().toString());
            newSong.setListenCount(0);
            newSong.setAlbum(newAlbum);
            newSongs.add(newSong);
        }
        songRepository.saveAll(newSongs);
        eventPublisher.sendChange(
        newAlbum.getTableName(),
        "id",
        newAlbum.getId(),
        null,
            newAlbum.getId());

    }
}
