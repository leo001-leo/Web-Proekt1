//package mk.ukim.finki.vp.proekt.vpproekt.security;
//
//import mk.ukim.finki.vp.proekt.vpproekt.model.User;
//import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//public class MyUserDetailService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    public void MyUserDetailsService() {
//        //super();
//    }
//
//    // API
//
//    @Override
//    public UserDetails loadUserByUsername(final String username) {
//        final Optional<User> user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new org.springframework.security.core.userdetails.User(username, user.toString(), true, true, true, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
//    }
//}