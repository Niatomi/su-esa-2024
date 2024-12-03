package ru.niatomi.music_player.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArtistsController {

    @GetMapping("/artists")
    public String getArtists() {
        return "artists";
    }

}
