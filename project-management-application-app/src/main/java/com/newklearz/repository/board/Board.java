package com.newklearz.repository.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.newklearz.repository.rank.TicketRank;

@Entity
@Table(name = "pma_board")
public class Board implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "assignedBoard")
    private List<TicketRank> ticketRankList = new ArrayList<>();

    public Board()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<TicketRank> getTicketRankList()
    {
        return ticketRankList;
    }

    public void setTicketRankList(List<TicketRank> ticketRankList)
    {
        this.ticketRankList = ticketRankList;
    }
}