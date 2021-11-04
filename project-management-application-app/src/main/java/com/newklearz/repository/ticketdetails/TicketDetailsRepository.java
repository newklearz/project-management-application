package com.newklearz.repository.ticketdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDetailsRepository extends JpaRepository<TicketDetails, Integer>
{

}
