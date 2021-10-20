package com.newklearz.projectmanagement.service;

import com.newklearz.projectmanagement.repository.ticket.Ticket;
import com.newklearz.projectmanagement.repository.users.Users;
import com.newklearz.projectmanagement.repository.users.UsersRepository;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Ticket> findAllTicketsByUser(Integer userId) {
        Optional<Users> optional = usersRepository.findById(userId);

        if(optional.isPresent()) {
            return optional.get().getTicketList();
        }

        throw new EntityNotFoundException("User with id " + userId + " not found");


    }

}
