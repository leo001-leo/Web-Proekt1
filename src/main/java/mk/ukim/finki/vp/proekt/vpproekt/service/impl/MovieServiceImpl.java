package mk.ukim.finki.vp.proekt.vpproekt.service.impl;

import mk.ukim.finki.vp.proekt.vpproekt.model.Cast;
import mk.ukim.finki.vp.proekt.vpproekt.model.Genre;
import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;
import mk.ukim.finki.vp.proekt.vpproekt.model.dto.MovieDto;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.GenreNotFoundException;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.GenreRepository;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.MovieRepository;
import mk.ukim.finki.vp.proekt.vpproekt.service.CastService;
import mk.ukim.finki.vp.proekt.vpproekt.service.MovieService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CastService castService;

    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository, CastService castService) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.castService = castService;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> findByName(String name) {
        return this.movieRepository.findByName(name);
    }

    @Override
    public Optional<Movie> save(String name, LocalDateTime date, String description, Long genreId, String price, String quantity, Duration duration, String ratingImdb, List<Cast> cast) {
        Genre genre = this.genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
        //this is not implemented
        //todo

        return Optional.empty();
    }

    @Override
    public Optional<Movie> save(MovieDto movieDto) {
        //todo
        return Optional.empty();
    }

    @Override
    public Optional<Movie> edit(Long id, String name, LocalDateTime date, String description, Long genreId, String price, String quantity, Duration duration, String ratingImdb, List<Cast> cast) {

       //todo
        return Optional.empty();
    }

    @Override
    public Optional<Movie> edit(MovieDto movieDto) {
        //todo
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }
}
