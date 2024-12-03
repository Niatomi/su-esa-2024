package ru.niatomi.music_player.service;

import ru.niatomi.music_player.models.domain.Album;
import ru.niatomi.music_player.models.domain.Artist;

import java.util.List;

public interface AlbumService {

    Album getAlbumById(Integer albumId);
    Integer countTotalListenCount(Album album);
    Album getAlbumBySongId(Integer songId);
    List<Album> getArtistAlbums(Artist artist);
}
