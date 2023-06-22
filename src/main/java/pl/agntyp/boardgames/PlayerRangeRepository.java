package pl.agntyp.boardgames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRangeRepository extends JpaRepository<PlayerRange, Long> {
    PlayerRange findByMinPlayersAndMaxPlayers(int minPlayers, int maxPlayers);
}
