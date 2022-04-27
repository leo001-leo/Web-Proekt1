package mk.ukim.finki.vp.proekt.vpproekt.web.controller;

import mk.ukim.finki.vp.proekt.vpproekt.model.Cast;
import mk.ukim.finki.vp.proekt.vpproekt.model.Genre;
import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;
import mk.ukim.finki.vp.proekt.vpproekt.service.CastService;
import mk.ukim.finki.vp.proekt.vpproekt.service.GenreService;
import mk.ukim.finki.vp.proekt.vpproekt.service.MovieService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final CastService castService;
    private final GenreService genreService;

    public MovieController(MovieService movieService, CastService castService, GenreService genreService) {
        this.movieService = movieService;
        this.castService = castService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getMoviePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Movie> movies = this.movieService.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("bodyContent", "movies");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/edit-form/{id}")
    public String editMoviePage(@PathVariable Long id, Model model){
        if (this.movieService.findById(id).isPresent()){
            Movie movie = this.movieService.findById(id).get();
            List<Cast> cast = this.castService.findAll();
            List<Genre> genres = this.genreService.listGenres();
            model.addAttribute("cast", cast);
            model.addAttribute("genres", genres);
            model.addAttribute("movie", movie);
            model.addAttribute("bodyContent", "add-movie");
            return "master-template";
        }
        return "redirect:/movies?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addMoviePage(Model model){
        List<Cast> cast = this.castService.findAll();
        List<Genre> genres = this.genreService.listGenres();
        model.addAttribute("cast", cast);
        model.addAttribute("genres", genres);
        model.addAttribute("bodyContent", "add-movie");
            return "master-template";
    }

    @PostMapping("/add")
    public String saveMovie( @RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam LocalDateTime date,
                             @RequestParam String description,
                             @RequestParam Long genre,
                             @RequestParam String price,
                             @RequestParam Double quantity,
                             @RequestParam String duration,
                             @RequestParam Double ratingImdb,
                             @RequestParam List<Long> castIds) {

        if (id != null) {
            this.movieService.edit(id, name, date, description, genre, price, quantity, duration, ratingImdb, castIds);
        } else {
            this.movieService.save(name, date, description, genre, price, quantity, duration, ratingImdb, castIds);
        }
        return "redirect:/movies";
    }
}
