package mk.ukim.finki.vp.proekt.vpproekt.web.controller.rest;

import mk.ukim.finki.vp.proekt.vpproekt.model.Genre;
import mk.ukim.finki.vp.proekt.vpproekt.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/genres")
public class GenreRestController {

    private final GenreService genreService;

    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> findAll(){
        return this.genreService.listGenres();
    }

    @PostMapping("/add")
    public ResponseEntity<Genre> save(@RequestParam String name, @RequestParam String description){
        return this.genreService.create(name, description)
                .map(genre -> ResponseEntity.ok().body(genre))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Genre> edit(@PathVariable Long id, @RequestParam String name, @RequestParam String description){
        return this.genreService.update(id, name, description)
                .map(genre -> ResponseEntity.ok().body(genre))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.genreService.deleteById(id);
        if(this.genreService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
