package mk.ukim.finki.vp.proekt.vpproekt.service.impl;

import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;
import mk.ukim.finki.vp.proekt.vpproekt.model.ShoppingCart;
import mk.ukim.finki.vp.proekt.vpproekt.model.User;
import mk.ukim.finki.vp.proekt.vpproekt.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.MovieAlreadyInShoppingCartException;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.UserNotFoundException;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.UserRepository;
import mk.ukim.finki.vp.proekt.vpproekt.service.MovieService;
import mk.ukim.finki.vp.proekt.vpproekt.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final MovieService movieService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, MovieService movieService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.movieService = movieService;
    }

    @Override
    public List<Movie> listAllMoviesInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getMovies();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user=this.userRepository.findByUsername(username).
                orElseThrow(()->new UserNotFoundException(username));
        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(()->{
                    ShoppingCart shoppingCart=new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addMovieToShoppingCart(String username, Long movieId) {
        ShoppingCart shoppingCart=this.getActiveShoppingCart(username);
        Movie movie = this.movieService.findById(movieId)
                .orElseThrow(()->new MovieNotFoundException(movieId));
        if(shoppingCart.getMovies()
                .stream().filter(i->i.getId().equals(movieId))
                .collect(Collectors.toList()).size() > 0)
            throw new MovieAlreadyInShoppingCartException(movieId,username);
        shoppingCart.getMovies().add(movie);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void reserveTicket(Long id) {
        Movie movie = this.movieService.findById(id).get();
        Double quant=movie.getQuantity();
        quant--;
        movie.setQuantity(quant);
    }
}
