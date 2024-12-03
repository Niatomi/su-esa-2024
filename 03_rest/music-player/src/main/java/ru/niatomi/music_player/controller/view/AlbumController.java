package ru.niatomi.music_player.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.niatomi.music_player.models.domain.Album;
import ru.niatomi.music_player.models.domain.Artist;
import ru.niatomi.music_player.models.domain.Song;
import ru.niatomi.music_player.service.AlbumService;
import ru.niatomi.music_player.service.SongService;

@Controller
public class AlbumController {

    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService;

    @GetMapping("/album")
    public String getArtists(@RequestParam Integer id, Model model) {
        Album album = albumService.getAlbumById(id);
        model.addAttribute("album", album);
        model.addAttribute("totalCount", albumService.countTotalListenCount(album));
        return "album";
    }

    @PostMapping("/album")
    public String addListenCount(
            @RequestParam Integer songId,
            RedirectAttributes redirectAttributes
    ) {
        if (songId != null) {
            Album album = albumService.getAlbumBySongId(songId);
            Song song = songService.getSong(songId);
            songService.playSong(song);
            redirectAttributes.addAttribute("id", album.getId());
        }
        return "redirect:/album";
    }
}
