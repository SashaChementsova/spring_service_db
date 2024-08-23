package org.chementsova.service;

import org.chementsova.model.Ticket;

import java.util.List;

public interface TicketService {
    void save(Ticket ticket);

    List<Ticket> getAll();

    void initialize();
}
