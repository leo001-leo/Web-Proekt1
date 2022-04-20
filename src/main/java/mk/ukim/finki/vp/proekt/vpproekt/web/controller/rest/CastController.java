package mk.ukim.finki.vp.proekt.vpproekt.web.controller.rest;

import mk.ukim.finki.vp.proekt.vpproekt.model.Cast;
import mk.ukim.finki.vp.proekt.vpproekt.model.dto.CastDto;
import mk.ukim.finki.vp.proekt.vpproekt.service.CastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/cast")
public class CastController {

    private final CastService castService;

    public CastController(CastService castService) {
        this.castService = castService;
    }

    @GetMapping
    private List<Cast> findAll(){
        return this.castService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Cast> save(@RequestParam CastDto castDto) {
        return this.castService.save(castDto)
                .map(cast -> ResponseEntity.ok().body(cast))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Cast> edit(@PathVariable Long id, @RequestBody CastDto castDto){
        return this.castService.edit(id, castDto)
                .map(cast -> ResponseEntity.ok().body(cast))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.castService.deleteById(id);
        if(this.castService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
