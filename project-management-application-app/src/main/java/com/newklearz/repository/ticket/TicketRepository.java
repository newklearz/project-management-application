package com.newklearz.repository.ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>
{

    String USERID = "userId";
    String BOARDID = "boardId";

    @Query("SELECT ticket"
            + " FROM Ticket ticket"
            + " WHERE ticket.createdBy.id = :" + USERID)
    List<Ticket> findTicketsCreatedByUser(@Param(USERID) Integer userId);

    @Query("SELECT ticket"
            + " FROM Ticket ticket"
            + " WHERE ticket.assignedTo.id = :" + USERID)
    List<Ticket> findTicketsAssignedToUser (@Param(USERID) Integer userId);

    @Query("SELECT ticket"
                  + " FROM Ticket ticket"
                  + " WHERE ticket.assignedBoard.id = :" + BOARDID)
    List<Ticket> findTicketsAssignedToBoard (@Param(BOARDID) Integer boardId);
}