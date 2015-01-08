package springboot;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.*;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;
import springboot.data.UserManager;
import springboot.data.entities.User;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] NONAUTHORISED = new String[] {
        "/css/**",
        "/js/**",
        "/fonts/**",
        "/",
        "/users/register",
        "/users/confirmemail/*/*",
        "/auth/facebook"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers(NONAUTHORISED).permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/loginSuccess").failureUrl("/loginFailure")
                .permitAll()
                .and().rememberMe().and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccess")
                .permitAll().and().apply(new SpringSocialConfigurer());
    }

    @Configuration
    protected static class AuthenticationConfiguration extends
            GlobalAuthenticationConfigurerAdapter implements ApplicationContextAware {

        ApplicationContext context;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService((UserDetailsService) context.getBean("userDetailsService")).passwordEncoder(User.encoder);
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            context = applicationContext;
        }
    }

    @Autowired
    UserManager userDetailsService;

    @Bean
    protected SocialUserDetailsService socialUserDetailsService() {
        return new SocialUserDetailsService() {

            private SocialUserDetails map(UserDetails details) {
                if (details == null) {
                    return null;
                }
                return new SocialUser(details.getUsername(), details.getPassword(), details.getAuthorities());
            }

            @Override
            public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
                return map(userDetailsService.loadUserByUsername(userId));
            }
        };
    }

    @Configuration
    @EnableSocial
    public static class SocialConfig implements SocialConfigurer {

        @Autowired
        DataSource dataSource;

        @Autowired
        UserManager userDetailsService;

        @Override
        public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        }

        @Override
        public UserIdSource getUserIdSource() {
            return null;
        }

        @Override
        public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
            JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
            repository.setConnectionSignUp(new ConnectionSignUp() {
                @Override
                public String execute(Connection<?> connection) {

                    UserProfile profile = connection.fetchUserProfile();
                    if (profile != null) {
                        User user = new User();
                        user.setUsername(profile.getEmail());
                        user.generatePassword();
                        if (!userDetailsService.userExists(user.getUsername()))
                            userDetailsService.createUser(user);
                        return user.getUsername();
                    }
                    return null;
                }
            });
            return repository;
        }
    }
}
