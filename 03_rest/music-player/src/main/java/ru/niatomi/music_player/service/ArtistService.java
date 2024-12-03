package ru.niatomi.music_player.service;

import ru.niatomi.music_player.formats.RequestDataFormat;
import ru.niatomi.music_player.models.domain.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtists();
    Artist getArtist(Integer artistId);
    Artist getArtistBySongId(Integer songId);
    void playArtistSongs(Artist artist);

    interface AccessingService<Object, Integer> {

        List<Object> getAllData(RequestDataFormat rdf);
        Object getSingleById(RequestDataFormat rdf, Integer id);

    }
}
