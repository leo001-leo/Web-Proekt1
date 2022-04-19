package mk.ukim.finki.vp.proekt.vpproekt.model.dto;

import lombok.Data;

@Data
public class CastDto {

    private String name;

    private String surname;

    private Long movie;

    public CastDto(String name, String surname, Long movie) {
        this.name = name;
        this.surname = surname;
        this.movie = movie;
    }
}
