package com.example.jwt.config;

import com.example.jwt.config.filter.JwtTokenFilter;
import com.example.jwt.config.util.JwtTokenUtil;
import com.example.jwt.entity.Role;
import com.example.jwt.service.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserManagement userManagement;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtTokenFilter)
//                .addFilter(new JwtTokenFilter( jwtTokenUtil,authenticationManager()))
//                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest()
                .authenticated();
//                .and()
//                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userManagement);
        return provider;
    }


    //    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//       UserDetails erfan = User.builder()
//                .username("erfan")
//                .password(passwordEncoder.encode("123"))
//                .roles("STUDENT")
////               .authorities()
//                .build();
//
//       UserDetails khadijeh = User.builder()
//                .username("khadijeh")
//                .password(passwordEncoder.encode("123"))
//                .roles("TEACHER")
////               .authorities()
//                .build();
//
//       return new InMemoryUserDetailsManager(erfan,khadijeh);
//    }
}
