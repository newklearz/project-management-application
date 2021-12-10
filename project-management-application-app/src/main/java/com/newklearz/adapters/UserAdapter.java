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
        usersDTO.setActive(user.isActive());
        usersDTO.setPassword(user.getPassword());
        usersDTO.setAppUserRole(user.getAppUserRole());
        return usersDTO;
    }

    public static Users toEntity(UsersDTO usersDTO)
    {
        Users user = new Users();
        if (usersDTO != null)
        {
            user.setId(usersDTO.getId());
            user.setUserName(usersDTO.getUserName());
            user.setEmail(usersDTO.getEmail());
            user.setActive(usersDTO.isActive());
            user.setPassword(usersDTO.getPassword());
            user.setAppUserRole(usersDTO.getAppUserRole());
        }
        else
        {
            return new Users();
        }
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
