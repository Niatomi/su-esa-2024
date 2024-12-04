package ru.niatomi.music_player.service;

public interface ApiService {

    void updateSong(Integer songId, Integer newListenCount);
    void fakeRelease(Integer artistId);
}
