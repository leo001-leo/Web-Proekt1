package mk.ukim.finki.vp.proekt.vpproekt.service;

import mk.ukim.finki.vp.proekt.vpproekt.model.User;

public interface AuthService {
    User login(String username, String password);
}
