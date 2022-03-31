package mk.ukim.finki.vp.proekt.vpproekt.model;

import lombok.Data;
import mk.ukim.finki.vp.proekt.vpproekt.model.enumerations.ShoppingCartStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    private Long userId;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Movie> movies;

}
