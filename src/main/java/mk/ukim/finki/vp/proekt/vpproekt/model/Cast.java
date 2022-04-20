package mk.ukim.finki.vp.proekt.vpproekt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cast_movie")
public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    private Movie movieId;

    public Cast() {
    }

    public Cast(String name, String surname, Movie movieId) {
        this.name = name;
        this.surname = surname;
        this.movieId = movieId;
    }
}
