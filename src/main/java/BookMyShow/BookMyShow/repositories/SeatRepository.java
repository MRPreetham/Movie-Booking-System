package BookMyShow.BookMyShow.repositories;

import BookMyShow.BookMyShow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> findAllById(List<Long> seatId);
}
