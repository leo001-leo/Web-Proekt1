package mk.ukim.finki.vp.proekt.vpproekt.web.controller;

import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;
import mk.ukim.finki.vp.proekt.vpproekt.model.ShoppingCart;
import mk.ukim.finki.vp.proekt.vpproekt.model.User;
import mk.ukim.finki.vp.proekt.vpproekt.service.MovieService;
import mk.ukim.finki.vp.proekt.vpproekt.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final MovieService movieService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, MovieService movieService) {
        this.shoppingCartService = shoppingCartService;
        this.movieService = movieService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("movies", this.shoppingCartService.listAllMoviesInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-movie/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.addMovieToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @GetMapping("/reserve/{id}")
    public String getBuyTicketPage(@PathVariable Long id, Model model){
        Movie movie=this.movieService.findById(id).get();
        model.addAttribute("movie",movie);
        //treba da se implementira
        return "buy-ticket";
    }

    @PostMapping("/reserve/{id}")
    public String reserveTicket(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String address,
                                @RequestParam String quantity){
        this.shoppingCartService.reserveTicket(id, quantity);
        return "redirect:/movies";
    }
}
