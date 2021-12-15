package com.newklearz.repository.rank;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TicketRankForeignKey implements Serializable
{
    @Column(name = "TICKET_ID")
    private Integer ticketId;

    @Column(name = "BOARD_ID")
    private Integer boardId;

    public TicketRankForeignKey()
    {
    }

    public TicketRankForeignKey(Integer ticketId, Integer boardId)
    {
        this.ticketId = ticketId;
        this.boardId = boardId;
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

    @Override
    public int hashCode()
    {
        return (ticketId + boardId);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof TicketRankForeignKey)
        {
            TicketRankForeignKey otherId = (TicketRankForeignKey) object;
            return (otherId.ticketId == this.ticketId) && (otherId.boardId == this.boardId);
        }
        return false;
    }
}