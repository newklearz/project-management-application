package com.newklearz.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final ApplicationPasswordEncoder applicationPasswordEncoder;
    private final MyUserDetailsService myUserDetailsService;

    private final CorsFilter corsFilter;

    public WebSecurityConfig(ApplicationPasswordEncoder applicationPasswordEncoder,
        MyUserDetailsService myUserDetails, CorsFilter corsFilter)
    {

        this.applicationPasswordEncoder = applicationPasswordEncoder;
        this.myUserDetailsService = myUserDetails;
        this.corsFilter = corsFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
        http.httpBasic().and()
            .authorizeRequests()
            .antMatchers("/index.html", "/", "/login").permitAll()
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