package mk.ukim.finki.vp.proekt.vpproekt.service.impl;

import mk.ukim.finki.vp.proekt.vpproekt.model.Cast;
import mk.ukim.finki.vp.proekt.vpproekt.model.Genre;
import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;
import mk.ukim.finki.vp.proekt.vpproekt.model.dto.MovieDto;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.GenreNotFoundException;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.CastRepository;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.GenreRepository;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.MovieRepository;
import mk.ukim.finki.vp.proekt.vpproekt.service.MovieService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CastRepository castRepository;


    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository, CastRepository castRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;

        this.castRepository = castRepository;
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
    @Transactional
    public Optional<Movie> save(String name, LocalDateTime date, String description, Long genreId, String price, Double quantity, String duration, Double ratingImdb, List<Long> castIds) {
        List<Cast> casts = this.castRepository.findAllById(castIds);
        Genre genre = this.genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));

        this.movieRepository.deleteByName(name);
        Movie movie = new Movie(name, date, description, genre, price, quantity, duration, ratingImdb, casts);
        this.movieRepository.save(movie);
        return Optional.of(movie);
    }

    @Override
    public Optional<Movie> save(MovieDto movieDto) {
        List<Cast> casts = this.castRepository.findAllById(movieDto.getCast());
        Genre genre = this.genreRepository.findById(movieDto.getGenre())
                .orElseThrow(() -> new GenreNotFoundException(movieDto.getGenre()));
        this.movieRepository.findByName(movieDto.getName());
        Movie movie = new Movie(movieDto.getName(), movieDto.getDate(), movieDto.getDescription(), genre, movieDto.getPrice(), movieDto.getQuantity(), movieDto.getDuration(), movieDto.getRatingImdb(), casts);
        this.movieRepository.save(movie);
        return Optional.of(movie);
    }

    @Override
    @Transactional
    public Optional<Movie> edit(Long id, String name, LocalDateTime date, String description, Long genreId, String price, Double quantity, String duration, Double ratingImdb, List<Long> castIds) {
        Movie movie = this.movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        movie.setName(name);
        movie.setDate(date);
        movie.setDescription(description);
        Genre genre = this.genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
        movie.setGenre(genre);
        movie.setPrice(price);
        movie.setQuantity(quantity);
        movie.setDuration(duration);
        movie.setRatingImdb(ratingImdb);

        List<Cast> casts = this.castRepository.findAllById(castIds);
        movie.setCast(casts);
        this.movieRepository.save(movie);
        return Optional.of(movie);
    }


    @Override
    public Optional<Movie> edit(Long id, MovieDto movieDto) {
        Movie movie = this.movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        movie.setName(movieDto.getName());
        movie.setDate(movieDto.getDate());
        movie.setDescription(movieDto.getDescription());
        Genre genre = this.genreRepository.findById(movieDto.getGenre())
                .orElseThrow(() -> new GenreNotFoundException(movieDto.getGenre()));
        movie.setGenre(genre);
        movie.setPrice(movieDto.getPrice());
        movie.setQuantity(movieDto.getQuantity());
        movie.setDuration(movieDto.getDuration());
        movie.setRatingImdb(movieDto.getRatingImdb());

        List<Cast> casts = this.castRepository.findAllById(movieDto.getCast());
        movie.setCast(casts);
        return Optional.of(movie);
    }

    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }
}