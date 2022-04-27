package mk.ukim.finki.vp.proekt.vpproekt.service.impl;

import mk.ukim.finki.vp.proekt.vpproekt.model.enumerations.Provider;
import mk.ukim.finki.vp.proekt.vpproekt.model.enumerations.Role;
import mk.ukim.finki.vp.proekt.vpproekt.model.User;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.vp.proekt.vpproekt.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.UserRepository;
import mk.ukim.finki.vp.proekt.vpproekt.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, String email, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(name, surname, username, passwordEncoder.encode(password), email, role);
        return userRepository.save(user);
    }

    @Override
    public void processOAuthPostLogin(String username) {
        User existUser = userRepository.getUserByUsername(username);
        if (existUser == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setProvider(Provider.FACEBOOK);
            newUser.setEnabled(true);

            userRepository.save(newUser);
        }

    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Stream.of(new SimpleGrantedAuthority("ROLE_"+user.getRole())).collect(Collectors.toList())
//        );
    // }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
