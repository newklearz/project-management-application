package com.newklearz.DTO;

public class TicketRankDTO
{
    private TicketRankForeignKeyDTO ticketRankForeignKey = new TicketRankForeignKeyDTO();
    private TicketDTO assignedTicket;
    private BoardDTO assignedBoard;
    private Integer ticketRank;

    public TicketRankDTO()
    {
    }

    public TicketRankDTO(TicketRankForeignKeyDTO ticketRankForeignKey, TicketDTO assignedTicket,
        BoardDTO assignedBoard, Integer ticketRank)
    {
        this.ticketRankForeignKey = ticketRankForeignKey;
        this.assignedTicket = assignedTicket;
        this.assignedBoard = assignedBoard;
        this.ticketRank = ticketRank;
    }

    public Integer getTicketRank()
    {
        return ticketRank;
    }

    public void setTicketRank(Integer ticketRank)
    {
        this.ticketRank = ticketRank;
    }

    public TicketRankForeignKeyDTO getTicketRankForeignKey()
    {
        return ticketRankForeignKey;
    }

    public void setTicketRankForeignKey(TicketRankForeignKeyDTO ticketRankForeignKey)
    {
        this.ticketRankForeignKey = ticketRankForeignKey;
    }

    public BoardDTO getAssignedBoard()
    {
        return assignedBoard;
    }

    public void setAssignedBoard(BoardDTO assignedBoard)
    {
        this.assignedBoard = assignedBoard;
    }

    public TicketDTO getAssignedTicket()
    {
        return assignedTicket;
    }

    public void setAssignedTicket(TicketDTO assignedTicket)
    {
        this.assignedTicket = assignedTicket;
    }
}