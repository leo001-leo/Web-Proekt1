package mk.ukim.finki.vp.proekt.vpproekt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 4000)
    private String description;

    public Genre() { }

    public Genre(String name, String description) {
        this.name = name;
    }
}
