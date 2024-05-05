package BookMyShow.BookMyShow.services;

import BookMyShow.BookMyShow.enums.ShowSeatStatus;
import BookMyShow.BookMyShow.enums.TicketStatus;
import BookMyShow.BookMyShow.exceptions.InvalidArgumentsException;
import BookMyShow.BookMyShow.exceptions.SeatNotAvailableException;
import BookMyShow.BookMyShow.models.*;
import BookMyShow.BookMyShow.repositories.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookTicketService{
    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    @Autowired
    public BookTicketService(SeatRepository seatRepository,ShowSeatRepository showSeatRepository,
                             ShowRepository showRepository,UserRepository userRepository,
                             TicketRepository ticketRepository){
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }
    public Ticket bookTicket(List<Long> seatIds,Long userId,Long showId) throws InvalidArgumentsException, SeatNotAvailableException {

        List<Seat> seats = seatRepository.findAllById(seatIds);
        Optional<Show> showOptional = showRepository.findById(showId);

        if(showOptional.isEmpty()){
            throw new InvalidArgumentsException(
                    "Show by"+showId+"is not valid"
            );
        }
        //locking on showSeats
        getAndLockShowSeat(showOptional.get(), seats);

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new InvalidArgumentsException("User Id"+userId+"not valid");
        }

        Ticket ticket = new Ticket();
        ticket.setTicketStatus(TicketStatus.PROCESSING);
        ticket.setTimeOfBooking(new Date());
        ticket.setSeats(seats);
        ticket.setShow(showOptional.get());
        ticket.setBookedBy(userOptional.get());

        return ticketRepository.save(ticket);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
    public void getAndLockShowSeat(Show show,List<Seat> seats) throws SeatNotAvailableException {
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatAndShow(seats, show);

        for(ShowSeat showSeat:showSeats){
            if(showSeat.getSeatStatus().equals(ShowSeatStatus.LOCKED)||
            showSeat.getSeatStatus().equals(ShowSeatStatus.BOOKED)){
                throw new SeatNotAvailableException("No seat is available for show"+show.getMovie());
            }
            showSeat.setSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            showSeatRepository.save(showSeat);
        }
    }
}
