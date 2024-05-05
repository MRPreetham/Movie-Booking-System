package BookMyShow.BookMyShow.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends Base_model{
    @ManyToOne
    private SeatType seatType;
    @Column(name = "row")
    private int row;
    @Column(name = "col")
    private int col;
    private String seatNumber;
}
