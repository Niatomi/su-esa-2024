package ru.niatomi.music_player.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.niatomi.music_player.service.ApiService;

@RestController("/api")
public class ApiController {

    @Autowired
    ApiService apiService;

    @PutMapping("/songs/{songId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateSongMeta(@PathVariable Integer songId, @RequestBody Integer newListenCount) {
        apiService.updateSong(songId, newListenCount);
    }

    @PostMapping("/releaseFakeAlbum")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void releaseFakeAlbum(@RequestBody Integer artistId) {
        apiService.fakeRelease(artistId);
    }


}
