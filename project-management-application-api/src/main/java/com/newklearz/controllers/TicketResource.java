package com.newklearz.controllers;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketDetailsDTO;

@Api("TicketResource")
public interface TicketResource
{
    String TICKET_COMMON_PREFIX = "/api/v1/tickets";

    /**
     * retrives all tickets
     * @return {@link  ResponseEntity<List<TicketDTO>>}
     */
    @ApiOperation("Retrieve all tickets")
    @ResponseBody
  //  @GetMapping(TICKET_COMMON_PREFIX)
    @RequestMapping(method = RequestMethod.GET, value = TICKET_COMMON_PREFIX)
    ResponseEntity<List<TicketDTO>> getTickets();

    @ApiOperation("Retrieve all tickets 21")
    @ResponseBody
    @GetMapping(TICKET_COMMON_PREFIX + "/{id}")
    ResponseEntity<TicketDTO> getTicket(@PathVariable("id") Integer id);

    @ApiOperation("Retrieve all tickets 22")
    @ResponseBody
    @PostMapping(TICKET_COMMON_PREFIX)
    ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO);

    @ApiOperation("Retrieve all tickets 23 ")
    @ResponseBody
    @PutMapping(TICKET_COMMON_PREFIX + "/{id}")
    ResponseEntity<TicketDTO> updateTicket(@PathVariable("id") Integer id, @RequestBody TicketDTO ticketDTO);

    @ApiOperation("Retrieve all tickets 24")
    @ResponseBody
    @DeleteMapping(TICKET_COMMON_PREFIX + "/{id}")
    ResponseEntity<Object> deleteTicket(@PathVariable("id") Integer id);

    @ApiOperation("Retrieve all tickets 25")
    @ResponseBody
    @GetMapping(TICKET_COMMON_PREFIX + "/{id}/details")
    ResponseEntity<TicketDetailsDTO> getTicketDetailsForTicket(@PathVariable("id") Integer id);

    @ApiOperation("Retrieve all tickets 26")
    @ResponseBody
    @PutMapping(TICKET_COMMON_PREFIX + "/{id}/details")
    ResponseEntity<TicketDetailsDTO> updateTicketDetails(@PathVariable("id") Integer id,
        @RequestBody TicketDetailsDTO ticketDetailsDTO);

    @ApiOperation("Retrieve all tickets 27")
    @ResponseBody
    @PostMapping(TICKET_COMMON_PREFIX + "/{id}/clone")
    ResponseEntity<TicketDTO> cloneTicket(@PathVariable("id") Integer id) throws CloneNotSupportedException;
}
