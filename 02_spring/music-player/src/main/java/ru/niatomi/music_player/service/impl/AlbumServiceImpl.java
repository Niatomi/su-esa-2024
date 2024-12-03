package ru.niatomi.music_player.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.niatomi.music_player.exceptions.NotFoundException;
import ru.niatomi.music_player.models.domain.Album;
import ru.niatomi.music_player.models.domain.Artist;
import ru.niatomi.music_player.models.domain.Song;
import ru.niatomi.music_player.repository.AlbumRepository;
import ru.niatomi.music_player.repository.SongRepository;
import ru.niatomi.music_player.service.AlbumService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @Override
    public Album getAlbumById(Integer albumId) {
        Optional<Album> album = albumRepository.findById(albumId);
        if (album.isEmpty()) {
            throw new NotFoundException();
        }
        return album.get();
    }

    @Override
    public Integer countTotalListenCount(Album album) {
        Integer totalCount = 0;
        for (Song song : album.getSongs()) {
            totalCount += song.getListenCount();
        }
        return totalCount;
    }

    @Override
    public Album getAlbumBySongId(Integer songId) {
        Optional<Song> song = songRepository.findById(songId);
        if (song.isEmpty())
            throw new NotFoundException();
        return song.get().getAlbum();
    }

    @Override
    public List<Album> getArtistAlbums(Artist artist) {
        HashSet<Album> albums = new HashSet<Album>();
        for (Song song: artist.getSongs())
            albums.add(song.getAlbum());
        return albums.stream().toList();
    }
}
