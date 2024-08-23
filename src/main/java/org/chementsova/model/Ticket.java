package org.chementsova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "type")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Basic(optional = false)
    private TicketType ticketType;

    @Column (name = "class")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Basic(optional = false)
    private TicketClass ticketClass;

    @Column (name = "start_date")
    private Date startDate;

    @Column (name = "price")
    private double price;

    public Ticket(TicketType ticketType, TicketClass ticketClass, String startDate, double price) {
        this.ticketType = ticketType;
        this.ticketClass = ticketClass;
        this.startDate = Date.valueOf(startDate);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket " +
                "\n id = " + id +
                "\n ticket type = " + ticketType +
                "\n ticket class = " + ticketClass +
                "\n start date = " + startDate +
                "\n price = " + price;
    }
}
