package mk.ukim.finki.vp.proekt.vpproekt.web.controller;

import mk.ukim.finki.vp.proekt.vpproekt.model.Cast;
import mk.ukim.finki.vp.proekt.vpproekt.model.Genre;
import mk.ukim.finki.vp.proekt.vpproekt.service.CastService;
import mk.ukim.finki.vp.proekt.vpproekt.service.GenreService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/casts")
public class CastController {

    private final CastService castService;

    public CastController(CastService castService) {
        this.castService = castService;
    }

    @GetMapping
    public String getCast(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Cast> casts = this.castService.findAll();
        model.addAttribute("casts", casts);
        model.addAttribute("bodyContent", "casts");
        return "master-template";
    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCastPage(Model model){
        model.addAttribute("bodyContent", "casts");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveMovie( @RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String surname) {
        if (id != null) {
            this.castService.edit(id, name, surname);
        } else {
            this.castService.save(name, surname);
        }
        return "redirect:/casts";
    }
}