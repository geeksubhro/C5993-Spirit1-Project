package com.railway.management.dao;

import com.railway.management.entity.Ticket;
import com.railway.management.entity.User;

import java.util.List;

public interface TicketDAO {

    void saveTicket(Ticket ticket);

    Ticket getTicketById(int id);

    List<Ticket> getAllTickets();

    void updateTicket(Ticket ticket);

    void deleteTicket(int id);

	List<Ticket> getTicketsByUser(User user);
}