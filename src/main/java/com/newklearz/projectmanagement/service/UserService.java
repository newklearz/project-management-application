package com.newklearz.projectmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.newklearz.projectmanagement.repository.ticket.Ticket;
import com.newklearz.projectmanagement.repository.users.Users;
import com.newklearz.projectmanagement.repository.users.UsersRepository;

@Service
public class UserService
{

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }

    public List<Ticket> findAllTicketsByUser(Integer userId)
    {
        Optional<Users> optional = usersRepository.findById(userId);

        if (optional.isPresent())
        {
            return optional.get().getTicketList();
        }

        throw new EntityNotFoundException("User with id " + userId + " not found");
    }

    public List<Users> findAll()
    {
        return usersRepository.findAll();
    }

    public Users findById(Integer id)
    {
        return getUserById(id);
    }

    public Users createUser(Users user)
    {
        return usersRepository.save(user);
    }

    public Users updateUser(Integer id, Users user)
    {
        Users theUser = getUserById(id);

        if (!theUser.getId().equals(user.getId()))
        {
            throw new RuntimeException("Id of entity not the same with path id");
        }
        return usersRepository.save(theUser);

    }

    public void deleteUser(Integer id)
    {
        usersRepository.deleteById(id);
    }

    private Users getUserById(Integer id)
    {
        Optional<Users> optional = usersRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }
}
