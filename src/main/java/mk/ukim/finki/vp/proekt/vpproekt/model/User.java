package mk.ukim.finki.vp.proekt.vpproekt.model;

import lombok.Data;
import mk.ukim.finki.vp.proekt.vpproekt.model.enumerations.Provider;
import mk.ukim.finki.vp.proekt.vpproekt.model.enumerations.Role;
import org.hibernate.mapping.Collection;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collections;
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

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public User() { }

    public User(String name, String surname, String username, String password, String email, Role role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

}
