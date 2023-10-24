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
            .requestMatchers(AntPathRequestMatcher.antMatcher("/janken*/**")).authenticated()
            .requestMatchers(AntPathRequestMatcher.antMatcher("/match/**")).authenticated()
            .requestMatchers(AntPathRequestMatcher.antMatcher("/fight/**")).authenticated()
            .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()) // それ以外は全員アクセス可能
            .csrf((csrf) -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/*")))// h2-consoleはCSRF対策を無効化
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); // h2-consoleはX-Frame-Optionsを無効化

        return http.build();
  }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("ほんだ")
            .password("{bcrypt}$2y$10$BNhpo4r5fAh5txCMnLJ37utoacKm8IyAF7XkhOtWQTBPHyZX0TR4G").roles("USER").build();
        UserDetails user2 = User.withUsername("いがき")
                .password("{bcrypt}$2y$10$Fx/Fd68TFzaAvEnTigBoAuEQaaS5rvb3qTsTW5K0ZdwSr5b7SjkTW").roles("USER").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
