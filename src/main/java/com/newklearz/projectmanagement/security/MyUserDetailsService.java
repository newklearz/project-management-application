package com.newklearz.projectmanagement.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.newklearz.projectmanagement.repository.users.Users;
import com.newklearz.projectmanagement.repository.users.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService
{

    private final UsersRepository usersRepository;
    private final ApplicationPasswordEncoder applicationPasswordEncoder;

    public MyUserDetailsService(UsersRepository usersRepository, ApplicationPasswordEncoder applicationPasswordEncoder)
    {
        this.usersRepository = usersRepository;
        this.applicationPasswordEncoder = applicationPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        Optional<Users> usersOptional = usersRepository.findByUserName(userName);
        if (!usersOptional.isPresent())
        {
            throw new UsernameNotFoundException(userName);
        }
        return new MyUserDetails(usersOptional.get());
    }

    public String signUpUser(Users user)
    {
        boolean userExists = usersRepository.findByUserName(user.getUserName()).isPresent();
        if (userExists)
        {
            throw new IllegalStateException("username already taken");
        }

        String encodedPassword = applicationPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        usersRepository.save(user);

        return "it works";
    }
}
