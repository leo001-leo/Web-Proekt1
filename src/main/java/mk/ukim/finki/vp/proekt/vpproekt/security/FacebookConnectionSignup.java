//package mk.ukim.finki.vp.proekt.vpproekt.security;
//
//import mk.ukim.finki.vp.proekt.vpproekt.model.User;
//import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.UserRepository;
//import net.bytebuddy.utility.RandomString;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionSignUp;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FacebookConnectionSignup implements ConnectionSignUp {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public String execute(Connection<?> connection) {
//        System.out.println("signup === ");
//        final User user = new User();
//        user.setUsername(connection.getDisplayName());
//        user.setPassword(RandomStringUtils.randomAlphabetic(8));
//        userRepository.save(user);
//        return user.getUsername();
//    }
//}
