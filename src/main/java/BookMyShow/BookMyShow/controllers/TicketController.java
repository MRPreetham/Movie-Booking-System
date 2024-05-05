package BookMyShow.BookMyShow.controllers;

import BookMyShow.BookMyShow.dtos.BookTicketRequestDto;
import BookMyShow.BookMyShow.dtos.BookTicketResponseDto;
import BookMyShow.BookMyShow.exceptions.InvalidArgumentsException;
import BookMyShow.BookMyShow.exceptions.SeatNotAvailableException;
import BookMyShow.BookMyShow.models.Ticket;
import BookMyShow.BookMyShow.services.BookTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TicketController {
    private final BookTicketService bookTicketService;
    @Autowired
    public TicketController(BookTicketService bookTicketService){
        this.bookTicketService = bookTicketService;
    }
    BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto){
        List<Long> seatIds = requestDto.getSeatIds();
        Long userId = requestDto.getUserId();
        Long showId = requestDto.getShowId();
        Ticket ticket;
        ticket = bookTicketService.bookTicket(seatIds,userId,showId);
        BookTicketResponseDto responseDto =  new BookTicketResponseDto();
        responseDto.setPrice(ticket.getAmount());

        return responseDto;
    }
}
