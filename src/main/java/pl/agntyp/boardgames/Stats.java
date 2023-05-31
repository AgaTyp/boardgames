package pl.agntyp.boardgames;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @OneToMany
//    @JoinColumn(name = "s_id")
//    private List<Boardgame> boardgames = new ArrayList<>();
//    @OneToMany
//    @JoinColumn(name = "st_id")
//    private List<Player> players = new ArrayList<>();
    private int playCounter;
    private int winCounter = 0;
    private int maxPoints;
    @ManyToOne
    private Boardgame boardgame;

    @ManyToOne
    private Player player;

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPlayCounter() {
        return playCounter;
    }

    public void setPlayCounter(int playCounter) {
        this.playCounter = playCounter;
    }

    public int getWinCounter() {
        return winCounter;
    }

    public void setWinCounter(int winCounter) {
        this.winCounter = winCounter;
    }
}
