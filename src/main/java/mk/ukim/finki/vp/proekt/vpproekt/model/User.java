package mk.ukim.finki.vp.proekt.vpproekt.model;

import lombok.Data;
import mk.ukim.finki.vp.proekt.vpproekt.model.enumerations.Role;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "shop_users")
public class User {

    @Id
    private String username;

    private String name;

    private String surname;

    private String password;

    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() { }

    public User(String name, String surname, String username, String password, String email, Role role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role=role;
    }
}
