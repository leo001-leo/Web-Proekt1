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

    @Column(length = 4000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private String price;

    private Double quantity;

    private String duration;

    private Double ratingImdb;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Cast> cast;

    public Movie() { }

    public Movie(String name, LocalDateTime date, String description, Genre genre, String price, Double quantity, String duration, Double ratingImdb, List<Cast> cast) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getRatingImdb() {
        return ratingImdb;
    }

    public void setRatingImdb(Double ratingImdb) {
        this.ratingImdb = ratingImdb;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
