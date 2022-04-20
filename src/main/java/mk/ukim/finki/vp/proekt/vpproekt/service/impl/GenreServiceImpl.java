package mk.ukim.finki.vp.proekt.vpproekt.service.impl;

import mk.ukim.finki.vp.proekt.vpproekt.model.Genre;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.GenreNotFoundException;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.GenreRepository;
import mk.ukim.finki.vp.proekt.vpproekt.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    public Optional<Genre> findById(Long id) {
        return this.genreRepository.findById(id);
    }

    @Override
    public Optional<Genre> findByName(String name) {
        return this.genreRepository.findByName(name);
    }

    @Override
    public Optional<Genre> create(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Genre g = new Genre(name, description);
        genreRepository.save(g);
        return Optional.of(g);
    }

    @Override
    public Optional<Genre> update(Long id, String name, String description) {
        Genre genre = this.genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
        genre.setName(name);
        genre.setDescription(description);
        this.genreRepository.save(genre);
        return Optional.of(genre);
    }


    @Override
    public void deleteById(Long id) {
        this.genreRepository.deleteById(id);
    }

    @Override
    public void delete(String name) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        genreRepository.deleteByName(name);
    }

    @Override
    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> searchGenres(String searchText) {
        return genreRepository.findAllByNameLike(searchText);
    }
}
