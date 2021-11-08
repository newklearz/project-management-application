package com.newklearz.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.newklearz.DTO.UsersDTO;
import com.newklearz.repository.users.Users;

public class UserAdapter
{
    public static UsersDTO toDTO(Users user)
    {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(user.getId());
        usersDTO.setUserName(user.getUserName());
        usersDTO.setEmail(user.getEmail());
        usersDTO.setPassword(user.getPassword());
        usersDTO.setAppUserRole(user.getAppUserRole());
        usersDTO.setTicketList(TicketAdapter.toDTOList(user.getTicketList()));
        return usersDTO;
    }

    public static Users toEntity(UsersDTO usersDTO)
    {
        Users user = new Users();
        user.setId(usersDTO.getId());
        user.setUserName(usersDTO.getUserName());
        user.setEmail(usersDTO.getEmail());
        user.setPassword(usersDTO.getPassword());
        user.setAppUserRole(usersDTO.getAppUserRole());
        user.setTicketList(TicketAdapter.toEntityList(usersDTO.getTicketList()));
        return user;
    }

    public static List<UsersDTO> toDTOList(List<Users> users)
    {
        return users
            .stream().map(UserAdapter::toDTO)
            .collect(Collectors.toList());
    }

    public static List<Users> toEntityList(List<UsersDTO> userDTOList)
    {
        return userDTOList
            .stream()
            .map(UserAdapter::toEntity)
            .collect(Collectors.toList());
    }
}