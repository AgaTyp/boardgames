package pl.agntyp.boardgames;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TimeRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int minTime;
    private int maxTime;
    @OneToMany
    @JoinColumn(name = "time_range_id")
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

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
}
