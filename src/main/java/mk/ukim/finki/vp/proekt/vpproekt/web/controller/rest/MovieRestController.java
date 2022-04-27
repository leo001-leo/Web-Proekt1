package mk.ukim.finki.vp.proekt.vpproekt.web.controller.rest;

import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;
import mk.ukim.finki.vp.proekt.vpproekt.model.dto.MovieDto;
import mk.ukim.finki.vp.proekt.vpproekt.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/movies_rest")
public class MovieRestController {

    private final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> findAll(){
        return this.movieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id){
        return this.movieService.findById(id)
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> save(@RequestBody MovieDto movieDto) {
        return this.movieService.save(movieDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Movie> save(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        return this.movieService.edit(id, movieDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.movieService.deleteById(id);
        if(this.movieService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
