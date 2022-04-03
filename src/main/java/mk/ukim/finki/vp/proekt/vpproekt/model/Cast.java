package mk.ukim.finki.vp.proekt.vpproekt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Movie_cast")
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private Long movieId;

    public Cast() {
    }

    public Cast(String name, String surname, Long movieId) {
        this.name = name;
        this.surname = surname;
        this.movieId = movieId;
    }
}
