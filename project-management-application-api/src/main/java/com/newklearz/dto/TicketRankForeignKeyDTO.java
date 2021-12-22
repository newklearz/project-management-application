package com.newklearz.dto;

public class TicketRankForeignKeyDTO
{
    private Integer ticketId;
    private Integer boardId;

    public TicketRankForeignKeyDTO()
    {
    }

    public Integer getTicketId()
    {
        return ticketId;
    }

    public void setTicketId(Integer ticketId)
    {
        this.ticketId = ticketId;
    }

    public Integer getBoardId()
    {
        return boardId;
    }

    public void setBoardId(Integer boardId)
    {
        this.boardId = boardId;
    }
}