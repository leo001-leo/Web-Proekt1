package mk.ukim.finki.vp.proekt.vpproekt.service;

import mk.ukim.finki.vp.proekt.vpproekt.model.Cast;
import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;
import mk.ukim.finki.vp.proekt.vpproekt.model.dto.MovieDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> findAll();

    Optional<Movie> findById(Long id);

    Optional<Movie> findByName(String name);

    Optional<Movie> save(String name,
                         LocalDateTime date,
                         String description,
                         Long genreId,
                         String price,
                         Double quantity,
                         String duration,
                         Double ratingImdb,
                         List<Long> castIds);

    Optional<Movie> save(MovieDto movieDto);

    Optional<Movie> edit(Long id,
                         String name,
                         LocalDateTime date,
                         String description,
                         Long genreId,
                         String price,
                         Double quantity,
                         String duration,
                         Double ratingImdb,
                         List<Long> castIds);

    Optional<Movie> edit(Long id, MovieDto movieDto);

    void deleteById(Long id);

    List<Movie> getByKeyword(String keyword);


}
