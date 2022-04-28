package mk.ukim.finki.vp.proekt.vpproekt.web.controller;

import mk.ukim.finki.vp.proekt.vpproekt.model.ShoppingCart;
import mk.ukim.finki.vp.proekt.vpproekt.model.User;
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

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
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

//    @PostMapping
//    public String reserveTicket(@RequestParam )
}
