package com.newklearz;

import static com.newklearz.controllers.Utils.getAlphaNumericString;
import static com.newklearz.controllers.Utils.getRandomDate;
import static com.newklearz.controllers.Utils.getRandomEmail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    protected List<TicketDTO> ticketDTOS = new ArrayList<>();

    @BeforeEach
    public void setUp()
    {
        createTicket();
        createUser();
    }

    private void createTicket()
    {
        ticketDetailsDTO = new TicketDetailsDTO(1, getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString());
        ticketDTO = new TicketDTO(1, getAlphaNumericString(), getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(),
            ticketDetailsDTO);
        this.ticketDTOS.add(ticketController.createTicket(ticketDTO).getBody());

        ticketDetailsDTO = new TicketDetailsDTO(2, getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString());
        ticketDTO = new TicketDTO(2, getAlphaNumericString(), getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(),
            ticketDetailsDTO);
        this.ticketDTOS.add(ticketController.createTicket(ticketDTO).getBody());
    }

    private void createUser()
    {
        this.usersDTO = new UsersDTO();
        usersDTO.setId(7);
        usersDTO.setUserName(getAlphaNumericString());
        usersDTO.setEmail(getRandomEmail());
        usersDTO.setPassword("admiN123$");
        usersDTO.setAppUserRole(AppUserRole.ADMIN);
        usersDTO.setTicketList(ticketDTOS);
        usersDTO.setActive(true);
        userController.createUser(usersDTO);
    }
}