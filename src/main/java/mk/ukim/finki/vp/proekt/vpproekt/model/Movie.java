package mk.ukim.finki.vp.proekt.vpproekt.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private Double price;

    private Double quantity;

    private Duration duration;

    private Double ratingImdb;

    @OneToMany
    private List<Cast> cast;

    public Movie() { }


    public Movie(String name, LocalDateTime date, String description, Genre genre, Double price, Double quantity, Duration duration, Double ratingImdb, List<Cast> cast) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.duration = duration;
        this.ratingImdb = ratingImdb;
        this.cast = cast;
    }
}
