package mk.ukim.finki.vp.proekt.vpproekt.service;

import mk.ukim.finki.vp.proekt.vpproekt.model.enumerations.Role;
import mk.ukim.finki.vp.proekt.vpproekt.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword,
                  String name, String surname, String email, Role role);


}
