package BookMyShow.BookMyShow.models;

import BookMyShow.BookMyShow.enums.PaymentStatus;
import BookMyShow.BookMyShow.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
public class Payment extends Base_model{
    @ManyToOne
    private Ticket ticket;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private int amount;
    private Date time;
    private String refId;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
