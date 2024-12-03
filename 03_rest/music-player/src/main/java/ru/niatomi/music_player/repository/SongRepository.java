package ru.niatomi.music_player.repository;

import org.springframework.data.repository.CrudRepository;
import ru.niatomi.music_player.models.domain.Song;

public interface SongRepository extends CrudRepository<Song, Integer> {
}
