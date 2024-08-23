package org.chementsova.repository.implementation;

import org.chementsova.model.Employee;
import org.chementsova.model.Ticket;
import org.chementsova.repository.TicketRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    private final SessionFactory sessionFactory;

    public TicketRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void createTicket(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    @Transactional (readOnly = true)
    public List<Ticket> getTickets() {
        try (Session session = sessionFactory.openSession()) {
            Query<Ticket> query = session.createQuery("from Ticket ", Ticket.class);
            return query.list();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
