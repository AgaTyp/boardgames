package pl.agntyp.boardgames;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findAllByStats(Stats stats);

    List<Player> findAllByStatsIn(List<Stats> stats);

    Player findByStats(Stats stats);

    boolean findByNameAndBirthday(String name, LocalDate birthday);
}
