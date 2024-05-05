package BookMyShow.BookMyShow.models;

import BookMyShow.BookMyShow.enums.Language;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends Base_model{
    private String name;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Language> languages;
}
