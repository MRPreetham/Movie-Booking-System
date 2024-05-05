package BookMyShow.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketResponseDto {
    private String auditoriumName;
    private List<Long> seatNumber;
    private int price;
    private String status;
    private String message;
}
