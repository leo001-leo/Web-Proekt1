package mk.ukim.finki.vp.proekt.vpproekt.service;

import mk.ukim.finki.vp.proekt.vpproekt.model.Role;
import mk.ukim.finki.vp.proekt.vpproekt.model.User;

public interface UserService {
    User register(String username, String password, String repeatPassword,
                  String name, String surname, String email, Role role);
}
