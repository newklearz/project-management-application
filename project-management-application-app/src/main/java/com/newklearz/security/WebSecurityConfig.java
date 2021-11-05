package com.newklearz.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final ApplicationPasswordEncoder applicationPasswordEncoder;
    private final MyUserDetailsService myUserDetailsService;

    public WebSecurityConfig(ApplicationPasswordEncoder applicationPasswordEncoder,
        MyUserDetailsService myUserDetails)
    {

        this.applicationPasswordEncoder = applicationPasswordEncoder;
        this.myUserDetailsService = myUserDetails;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic().and()
            .authorizeRequests()
            .antMatchers("/api/v1/registration").permitAll()
            .anyRequest().authenticated().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(applicationPasswordEncoder);
        return provider;
    }
}
