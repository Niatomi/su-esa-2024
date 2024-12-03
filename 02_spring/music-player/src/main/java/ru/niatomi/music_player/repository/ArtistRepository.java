package ru.niatomi.music_player.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.niatomi.music_player.models.domain.Artist;

import java.util.List;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {
    List<Artist> findAll();
    Artist findById(Integer artistId);
}
