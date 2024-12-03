package ru.niatomi.music_player.service;

import ru.niatomi.music_player.models.domain.Album;
import ru.niatomi.music_player.models.domain.Artist;
import ru.niatomi.music_player.models.domain.Song;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtists();
    Artist getArtist(Integer artistId);
    Artist getArtistBySongId(Integer songId);
    void playArtistSongs(Artist artist);

}
