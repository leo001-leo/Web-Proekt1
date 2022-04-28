package mk.ukim.finki.vp.proekt.vpproekt.service;

import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;
import mk.ukim.finki.vp.proekt.vpproekt.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Movie> listAllMoviesInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addMovieToShoppingCart(String username, Long movieId);
}
