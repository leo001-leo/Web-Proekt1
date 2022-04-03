package mk.ukim.finki.vp.proekt.vpproekt.service.impl;

import mk.ukim.finki.vp.proekt.vpproekt.model.Role;
import mk.ukim.finki.vp.proekt.vpproekt.model.User;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.UserRepository;
import mk.ukim.finki.vp.proekt.vpproekt.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, String email, Role role) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
        {
            throw new InvalidUsernameOrPasswordException();
        }
        if(!password.equals(repeatPassword))
        {
            throw new PasswordsDoNotMatchException();
        }
        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }
        User user=new User(name,surname,username,passwordEncoder.encode(password),email,role);
        return userRepository.save(user);
    }
}
