package BookMyShow.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class City extends Base_model{
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Theater> theaters;
}
