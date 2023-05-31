package pl.agntyp.boardgames;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @OneToMany
    @JoinColumn(name = "player_id")
    private List<Stats> stats = new ArrayList<>();

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
