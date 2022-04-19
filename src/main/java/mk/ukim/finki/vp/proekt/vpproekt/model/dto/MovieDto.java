package mk.ukim.finki.vp.proekt.vpproekt.model.dto;

import lombok.Data;

@Data
public class MovieDto {

    private String name;

    private Double price;

    private Integer quantity;

    private String duration;

    private String ratingImdb;

    public MovieDto(String name, Double price, Integer quantity, String duration, String ratingImdb) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.duration = duration;
        this.ratingImdb = ratingImdb;
    }
}
