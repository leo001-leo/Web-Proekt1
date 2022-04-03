package mk.ukim.finki.vp.proekt.vpproekt.service;

import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;

import java.util.Optional;

public interface MovieService {
    Optional<Movie> findById(Long id);
}
