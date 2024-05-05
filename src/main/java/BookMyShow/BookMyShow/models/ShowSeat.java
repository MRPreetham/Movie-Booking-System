package BookMyShow.BookMyShow.models;

import BookMyShow.BookMyShow.enums.ShowSeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
public class ShowSeat extends Base_model{
    @ManyToOne
    private Show show;
    @ManyToOne
    private SeatType seatType;
    private Date LockedAt;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus seatStatus;
}
