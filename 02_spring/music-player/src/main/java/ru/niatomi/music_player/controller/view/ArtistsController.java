package ru.niatomi.music_player.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.niatomi.music_player.models.domain.Artist;
import ru.niatomi.music_player.service.ArtistService;

import java.util.List;

@Controller
public class ArtistsController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists")
    public String getArtists(Model model) {
        List<Artist> allArtists = artistService.getAllArtists();
        model.addAttribute("artists", allArtists);
        return "artists";
    }

}
