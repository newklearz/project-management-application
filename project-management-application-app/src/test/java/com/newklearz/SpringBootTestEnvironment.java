package com.newklearz;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.newklearz.DTO.AppUserRole;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketDetailsDTO;
import com.newklearz.DTO.UsersDTO;
import com.newklearz.controllers.RegistrationController;
import com.newklearz.controllers.TicketController;
import com.newklearz.controllers.UserController;

@SpringBootTest(classes = ProjectManagementApplication.class)
@AutoConfigureMockMvc
public class SpringBootTestEnvironment
{
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected UserController userController;
    @Autowired
    protected TicketController ticketController;
    @Autowired
    protected RegistrationController registrationController;
    protected UsersDTO usersDTO;
    protected TicketDTO ticketDTO;
    protected TicketDetailsDTO ticketDetailsDTO;

    @BeforeEach
    public void setUp()
    {
        ticketDetailsDTO = new TicketDetailsDTO(1, "Avem niste probleme urgente", "Dragonii albstri", "Ultimul");
        ticketDTO = new TicketDTO(1, "test", "bug", "01-12-2021", "02-12-2021", "inProgress", "unsolved", "assignee", ticketDetailsDTO);
        ResponseEntity<TicketDTO> ticketOne = ticketController.createTicket(ticketDTO);
        Assertions.assertNotNull(ticketOne);
        Assertions.assertEquals(ticketOne.getStatusCode(), HttpStatus.OK);
        TicketDTO ticketOne$ = ticketOne.getBody();
        ticketDetailsDTO = new TicketDetailsDTO(2, "Avem niste probleme urgente", "Dragonii albstri", "Ultimul");
        ticketDTO = new TicketDTO(2, "test2", "bug", "01-12-2021", "02-12-2021", "inProgress", "unsolved", "assignee", ticketDetailsDTO);
        ticketController.createTicket(ticketDTO);
        ResponseEntity<TicketDTO> ticketTwo = ticketController.createTicket(ticketDTO);
        Assertions.assertNotNull(ticketTwo);
        Assertions.assertEquals(ticketTwo.getStatusCode(), HttpStatus.OK);
        TicketDTO ticketTwo$ = ticketTwo.getBody();
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        ticketDTOS.add(ticketOne$);
        ticketDTOS.add(ticketTwo$);
        usersDTO = new UsersDTO();
        usersDTO.setId(7);
        usersDTO.setUserName("hero");
        usersDTO.setEmail("heroz@gmail.com");
        usersDTO.setPassword("admiN123$");
        usersDTO.setAppUserRole(AppUserRole.ADMIN);
        usersDTO.setTicketList(ticketDTOS);
        usersDTO.setActive(true);
        ResponseEntity<UsersDTO> responseEntity = userController.createUser(usersDTO);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}