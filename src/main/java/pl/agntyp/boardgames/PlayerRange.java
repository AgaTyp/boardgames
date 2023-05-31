package pl.agntyp.boardgames;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PlayerRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int minPlayers;
    private int maxPlayers;
    @OneToMany
    @JoinColumn(name = "player_range_id")
    private List<Boardgame> boardgames = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<Boardgame> getBoardgames() {
        return boardgames;
    }

    public void setBoardgames(List<Boardgame> boardgames) {
        this.boardgames = boardgames;
    }
}
