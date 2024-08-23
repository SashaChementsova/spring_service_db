package org.chementsova.service.implementation;

import org.chementsova.dto.TicketDto;
import org.chementsova.model.Ticket;
import org.chementsova.model.TicketClass;
import org.chementsova.model.TicketType;
import org.chementsova.repository.TicketRepository;
import org.chementsova.service.TicketService;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    private final File file = new File("D:\\Java Beginners Andersen" +
            "\\TicketManager\\src\\main\\resources\\ticketData.txt");

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.createTicket(ticket);
    }

    @Override
    public List<Ticket> getAll(){
        return ticketRepository.getTickets();
    }

    @Override
    public void initialize() {
        try {
            List<TicketDto> ticketDtos = new ObjectMapper().reader()
                    .forType(new TypeReference<List<TicketDto>> () {})
                    .readValue(file);
            for (TicketDto ticketDto : ticketDtos) {
                if(checkPriceField(ticketDto)
                        && checkStartDateField(ticketDto)
                        && checkTicketTypeField(ticketDto)
                        && ticketDto.getTicketClass() != null) {
                    ticketRepository.createTicket(new Ticket(TicketType.valueOf(ticketDto.getTicketType()),
                            TicketClass.valueOf(ticketDto.getTicketClass()),
                            ticketDto.getStartDate(),
                            Double.parseDouble(ticketDto.getPrice())));
                }
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private boolean checkPriceField(TicketDto ticketDto) {
        return !Objects.equals(ticketDto.getPrice(), "0")
                && ticketDto.getPrice() != null;
    }

    private boolean checkStartDateField(TicketDto ticketDto) {
        if (ticketDto.getStartDate() == null || ticketDto.getStartDate().equals("")) {
            return false;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.
                ofPattern("yyyy-M-d", Locale.CANADA);
        return compareDates(LocalDate.parse(ticketDto.getStartDate(),
                dateTimeFormatter));
    }

    private boolean checkTicketTypeField(TicketDto ticketDto) {
        return Objects.equals(ticketDto.getTicketType(), TicketType.DAY.getValue())
                || Objects.equals(ticketDto.getTicketType(), TicketType.WEEK.getValue())
                || Objects.equals(ticketDto.getTicketType(), TicketType.MONTH.getValue())
                || Objects.equals(ticketDto.getTicketType(), TicketType.YEAR.getValue());
    }

    public boolean compareDates(LocalDate date) {
        LocalDate date1 = LocalDate.now();

        int diff = date1.compareTo(date);

        return diff >= 0;
    }
}

