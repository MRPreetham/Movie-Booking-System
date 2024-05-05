package BookMyShow.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Theater extends Base_model{
    private String name;
    private String address;
    @OneToMany
    private List<Auditorium> auditoriumList;
    @ManyToOne
    private City city;
}
