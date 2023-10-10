package oit.is.z1992.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(AntPathRequestMatcher.antMatcher("/janken*/**")).authenticated() // /sample3/以下は認証済みであること
            .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()); // それ以外は全員アクセス可能
        return http.build();
  }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
            .password("{bcrypt}$2y$10$034f2PjUvNwrYmPy2BuMi.TX6L1SF24RAR4Lu.aB.ZZzIaWC0q.Pm").roles("USER").build();
        UserDetails user2 = User.withUsername("user2")
            .password("{bcrypt}$2y$10$cTPHVC7w/hmBOWz2661VJO9xj3mQJS/NIwrjzA6BCPNE1zXmLgjIm").roles("USER").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
