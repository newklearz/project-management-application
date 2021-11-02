package com.newklearz.projectmanagement.security;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.newklearz.projectmanagement.repository.users.Users;
import com.newklearz.projectmanagement.repository.users.UsersRepository;
import com.newklearz.projectmanagement.service.exceptions.UserCredentialsException;

@Service
public class MyUserDetailsService implements UserDetailsService
{

    private final UsersRepository usersRepository;
    private final ApplicationPasswordEncoder applicationPasswordEncoder;
    private final String PASSWORD_PATTERN =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private final String INVALID_CHARACTERS = "^[^<>'\"/;:+=&`%{}\\]\\[\\\\?]*$";
    private final Pattern invalidPattern = Pattern.compile(INVALID_CHARACTERS);

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
            throw new UserCredentialsException("USERNAME_EXISTS");
        }
        validatePassword(user);

        String encodedPassword = applicationPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        usersRepository.save(user);

        return "User Sign-Up successfully!";
    }

    private boolean invalidCharacters(String password)
    {
        Matcher matcher = invalidPattern.matcher(password);
        return matcher.matches();

    }

    private boolean isValid(String password)
    {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String changePassword(Users user)
    {
        validatePassword(user);
        String password = user.getPassword();
        user.setPassword(applicationPasswordEncoder.encode(password));

        usersRepository.save(user);

        return "Password changed successfully";
    }

    private void validatePassword(Users user)
    {

        if (user.getPassword().trim().isEmpty())
        {
            throw new UserCredentialsException("PASSWORD_EMPTY");
        }

        if (!invalidCharacters(user.getPassword()))
        {
            throw new UserCredentialsException(
                "CONTAINS_INVALID_CHARACTER [';', '<', '>', '{', '}', '[', ']', '+', '=', '?', '&', ':', '\\','`']");
        }
        if (!isValid(user.getPassword()))
        {
            throw new UserCredentialsException("Password must contain at least one digit [0-9]." +
                "Password must contain at least one lowercase Latin character [a-z]." +
                "Password must contain at least one uppercase Latin character [A-Z]." +
                "Password must contain at least one special character like ! @ # & ( )." +
                "Password must contain a length of at least 8 characters and a maximum of 20 characters.");
        }

    }

}
