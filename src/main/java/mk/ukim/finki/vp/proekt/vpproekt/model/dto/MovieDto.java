package mk.ukim.finki.vp.proekt.vpproekt.model.dto;

import lombok.Data;
import mk.ukim.finki.vp.proekt.vpproekt.model.Cast;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MovieDto {

    private String name;

    private String price;

    private String description;

    private LocalDateTime date;

    private Double quantity;

    private String duration;

    private Double ratingImdb;

    private Long genre;

    private List<Long> cast;

    public MovieDto() {
    }

    public MovieDto(String price, String description, LocalDateTime date, Double quantity, String duration, Double ratingImdb, Long genre, List<Long> cast) {
        this.price = price;
        this.description = description;
        this.date = date;
        this.quantity = quantity;
        this.duration = duration;
        this.ratingImdb = ratingImdb;
        this.genre = genre;
        this.cast = cast;
    }
}
