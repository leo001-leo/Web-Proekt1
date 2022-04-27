package mk.ukim.finki.vp.proekt.vpproekt.config;

//import mk.ukim.finki.vp.proekt.vpproekt.security.FacebookConnectionSignup;
//import mk.ukim.finki.vp.proekt.vpproekt.security.FacebookSignInAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.UsersConnectionRepository;
//import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
//import org.springframework.social.connect.support.ConnectionFactoryRegistry;
//import org.springframework.social.connect.web.ProviderSignInController;
//import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder,
                             CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
    }


    // @Autowired
   // private UserDetailsService userDetailsService;

//    @Autowired
//    private FacebookConnectionSignup facebookConnectionSignup;
//
//    @Value("${spring.social.facebook.appId}")
//    String appId;
//
//    @Value("${spring.social.facebook.appSecret}")
//    String appSecret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/assets/**", "/register", "/movies", "/api/**","/login*","/signin/**","/signup/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/movies", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

//    @Bean
//    public ProviderSignInController providerSignInController() {
//        ConnectionFactoryLocator connectionFactoryLocator =
//                connectionFactoryLocator();
//        UsersConnectionRepository usersConnectionRepository =
//                getUsersConnectionRepository(connectionFactoryLocator);
//        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
//                .setConnectionSignUp(facebookConnectionSignup);
//        return new ProviderSignInController(connectionFactoryLocator,
//                usersConnectionRepository, new FacebookSignInAdapter());
//    }
//
//    private ConnectionFactoryLocator connectionFactoryLocator() {
//        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//        registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
//        return registry;
//    }
//
//    private UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator
//                                                                           connectionFactoryLocator) {
//        return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(userDetailsService);
//        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authenticationProvider);
    }
}
