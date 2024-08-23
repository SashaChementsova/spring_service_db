package org.chementsova.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketDto {

    private String ticketType;

    private String ticketClass;

    private String startDate;

    private String price;
}
