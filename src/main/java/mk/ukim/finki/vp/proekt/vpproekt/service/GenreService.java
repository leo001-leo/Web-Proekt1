package mk.ukim.finki.vp.proekt.vpproekt.service;

import mk.ukim.finki.vp.proekt.vpproekt.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> findById(Long id);

    Optional<Genre> findByName(String name);

    Optional<Genre> create(String name, String description);

    Optional<Genre> update(Long id , String name, String description);



    void deleteById(Long id);

    void delete(String name);

    List<Genre> listGenres();

    List<Genre> searchGenres(String searchText);

}
