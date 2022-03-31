package mk.ukim.finki.vp.proekt.vpproekt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime date;

    private String description;

    private Long genreId;

    private String price;

    private String quantity;

    private String duration;

    private String ratingImdb;

    private Long castId;

    public Movie() { }

    public Movie(String name, LocalDateTime date, String description, Long genreId, String price, String quantity, String duration, String ratingImdb, Long castId) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.genreId = genreId;
        this.price = price;
        this.quantity = quantity;
        this.duration = duration;
        this.ratingImdb = ratingImdb;
        this.castId = castId;
    }
}
