package mk.ukim.finki.vp.proekt.vpproekt.service.impl;

import mk.ukim.finki.vp.proekt.vpproekt.model.User;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.UserRepository;
import mk.ukim.finki.vp.proekt.vpproekt.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password).
                orElseThrow(InvalidUserCredentialsException::new);
    }
}
