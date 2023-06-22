package pl.agntyp.boardgames;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AgeRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int minAge;

    @OneToMany
    @JoinColumn(name = "age_range_id")
    private List<Boardgame> boardgames = new ArrayList<>();

    public void addGame(Boardgame boardgame) {
        boardgames.add(boardgame);
    }

    public List<Boardgame> getBoardgames() {
        return boardgames;
    }

    public void setBoardgames(List<Boardgame> boardgames) {
        this.boardgames = boardgames;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

}
