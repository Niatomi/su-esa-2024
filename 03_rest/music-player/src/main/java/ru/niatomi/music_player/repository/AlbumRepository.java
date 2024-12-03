package ru.niatomi.music_player.repository;

import org.springframework.data.repository.CrudRepository;
import ru.niatomi.music_player.models.domain.Album;

public interface AlbumRepository extends CrudRepository<Album, Integer> {
}
