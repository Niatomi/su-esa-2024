package ru.niatomi.music_player.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.niatomi.music_player.models.domain.Artist;
import ru.niatomi.music_player.models.domain.Song;
import ru.niatomi.music_player.service.AlbumService;
import ru.niatomi.music_player.service.ArtistService;
import ru.niatomi.music_player.service.SongService;

import java.util.List;

@Controller()
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService;

    @GetMapping("/artist")
    public String getArtists(@RequestParam Integer id, Model model) {
        Artist artist = artistService.getArtist(id);
        model.addAttribute("artist", artist);
        model.addAttribute("albums", albumService.getArtistAlbums(artist));
        return "artist";
    }

    @PostMapping("/artist")
    public String addListenCount(
            @RequestParam(required = false) Integer artistId,
            @RequestParam(required = false) Integer songId,
            RedirectAttributes redirectAttributes
        ) {
        if (artistId != null) {
            Artist artist = artistService.getArtist(artistId);
            artistService.playArtistSongs(artist);
            redirectAttributes.addAttribute("id", artist.getId());
        }
        if (songId != null) {
            Artist artist = artistService.getArtistBySongId(songId);
            Song song = songService.getSong(songId);
            songService.playSong(song);
            redirectAttributes.addAttribute("id", artist.getId());
        }
        return "redirect:/artist";
    }
}
