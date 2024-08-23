package org.chementsova.repository;

import org.chementsova.model.Ticket;

import java.util.List;

public interface TicketRepository {
    void createTicket(Ticket ticket);

    List<Ticket> getTickets();
}
