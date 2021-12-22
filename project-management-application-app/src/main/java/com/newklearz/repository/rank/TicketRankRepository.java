package com.newklearz.repository.rank;

import com.newklearz.repository.board.Board;
import com.newklearz.repository.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TicketRankRepository extends JpaRepository<TicketRank, Integer>
{
    String BOARDID = "boardId";
    String TICKETID = "ticketId";

    @Query("SELECT ticket"
        + " FROM Ticket ticket"
        + " INNER JOIN TicketRank ticketRank"
        + " on ticket.id = ticketRank.assignedTicket.id"
        + " WHERE ticketRank.assignedBoard.id = :" + BOARDID
        + " ORDER BY ticketRank.ticketRank")
    List<Ticket> findTicketsForBoard(@Param(BOARDID) Integer boardId);

    @Query("SELECT board"
        + " FROM Board board"
        + " INNER JOIN TicketRank ticketRank"
        + " on board.id = ticketRank.assignedBoard.id"
        + " WHERE ticketRank.assignedTicket.id = :" + TICKETID)
    List<Board> findBoardsForTicket(@Param(TICKETID) Integer ticketId);

    @Query("SELECT ticketRank"
            + " FROM TicketRank ticketRank"
            + " WHERE ticketRank.assignedBoard.id = :" + BOARDID
            + " ORDER BY ticketRank.ticketRank")
    List<TicketRank> findTicketRanksForBoard(@Param(BOARDID) Integer boardId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TicketRank ticketRank"
            + " WHERE ticketRank.ticketRankForeignKey.ticketId = :" + TICKETID
            + " AND ticketRank.ticketRankForeignKey.boardId = :" + BOARDID)
    void removeTicketFromBoard(@Param(TICKETID) Integer ticketId, @Param(BOARDID) Integer boardId);
}