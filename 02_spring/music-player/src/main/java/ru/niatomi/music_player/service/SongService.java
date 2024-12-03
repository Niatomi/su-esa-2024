package ru.niatomi.music_player.service;

import ru.niatomi.music_player.models.domain.Song;

public interface SongService {

    Song getSong(Integer songId);
    boolean playSong(Song song);

    interface AlbumService {
    }
}
