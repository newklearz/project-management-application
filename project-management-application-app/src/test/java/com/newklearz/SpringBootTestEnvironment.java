package com.newklearz;

import static com.newklearz.controllers.Utils.COMPLEX_PASSWORD;
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

import com.newklearz.controllers.BoardController;
import com.newklearz.controllers.RegistrationController;
import com.newklearz.controllers.TicketController;
import com.newklearz.controllers.TicketRankController;
import com.newklearz.controllers.UserController;
import com.newklearz.dto.AppUserRole;
import com.newklearz.dto.BoardDTO;
import com.newklearz.dto.TicketDTO;
import com.newklearz.dto.TicketDetailsDTO;
import com.newklearz.dto.TicketRankDTO;
import com.newklearz.dto.UsersDTO;

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
    @Autowired
    protected BoardController boardController;
    @Autowired
    protected TicketRankController ticketRankController;
    protected UsersDTO usersDTO;
    protected TicketDTO ticketDTO;
    protected TicketDetailsDTO ticketDetailsDTO;
    protected List<TicketDTO> ticketDTOS = new ArrayList<>();
    protected BoardDTO boardDTO;
    protected TicketRankDTO ticketRankDTO;
    protected List<TicketRankDTO> ticketRankDTOS = new ArrayList<>();

    @BeforeEach
    public void setUp()
    {
        createUser();
    }

    private void createTwoTickets()
    {
        ticketDetailsDTO = new TicketDetailsDTO(null, getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString());
        ticketDTO = new TicketDTO(null, getAlphaNumericString(), getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, ticketDetailsDTO);
        this.ticketDTOS.add(ticketController.createTicket(ticketDTO).getBody());

        ticketDetailsDTO = new TicketDetailsDTO(null, getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString());
        ticketDTO = new TicketDTO(null, getAlphaNumericString(), getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, ticketDetailsDTO);
        this.ticketDTOS.add(ticketController.createTicket(ticketDTO).getBody());

        ticketDetailsDTO = new TicketDetailsDTO(null, getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString());
        ticketDTO = new TicketDTO(null, getAlphaNumericString(), getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, ticketDetailsDTO);
        this.ticketDTOS.add(ticketController.createTicket(ticketDTO).getBody());
    }

    private void addTicketToBoard()
    {
        this.ticketRankDTO = new TicketRankDTO();
        this.ticketRankDTO.setAssignedTicket(ticketDTOS.get(1));
        this.ticketRankDTO.setAssignedBoard(boardDTO);
        this.ticketRankDTOS
            .add(ticketRankDTO = ticketRankController.addTicketToBoard(boardDTO.getId(), ticketRankDTO).getBody());
    }

    private void createUser()
    {
        this.usersDTO = new UsersDTO();
        usersDTO.setUserName(getAlphaNumericString());
        usersDTO.setEmail(getRandomEmail());
        usersDTO.setPassword(COMPLEX_PASSWORD);
        usersDTO.setAppUserRole(AppUserRole.ADMIN);
        usersDTO.setActive(true);
        usersDTO = userController.createUser(this.usersDTO).getBody();
        createBoard();
        createTwoTickets();
        addTicketToBoard();
    }

    private void createBoard()
    {
        this.boardDTO = new BoardDTO();
        boardDTO.setName(getAlphaNumericString());
        boardDTO = boardController.createBoard(this.boardDTO).getBody();
    }
}