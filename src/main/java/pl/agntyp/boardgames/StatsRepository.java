package pl.agntyp.boardgames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatsRepository  extends JpaRepository<Stats, Long> {
    List<Stats> findAllByBoardgameOrderByWinCounterDesc(Optional<Boardgame> boardgame);

    List<Stats> findAllByBoardgameOrderByPlayCounterDesc(Optional<Boardgame> boardgame);

    List<Stats> findAllByBoardgameOrderByMaxPointsDesc(Optional<Boardgame> boardgame);

}
