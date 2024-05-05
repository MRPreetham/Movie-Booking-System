package BookMyShow.BookMyShow.repositories;

import BookMyShow.BookMyShow.models.Seat;
import BookMyShow.BookMyShow.models.Show;
import BookMyShow.BookMyShow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    List<ShowSeat> findAllBySeatAndShow(List<Seat> seats, Show show);

    ShowSeat save(ShowSeat showSeat);
}
