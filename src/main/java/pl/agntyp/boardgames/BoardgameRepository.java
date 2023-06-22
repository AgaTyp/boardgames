package pl.agntyp.boardgames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {
    Optional<Boardgame> findById(Long id);

    List<Boardgame> findBoardgameByCategory(Optional<Category> category);

    List<Boardgame> findBoardgamesByMyFavorite(boolean myFavorite);

    List<Boardgame> findBoardgamesByMyOwn(boolean myOwn);

    List<Boardgame> findAllByOrderByLastTimePlayedDesc();

    List<Boardgame> findAllByOrderByCreationDateDesc();

    Boardgame findByTitle(String title);

}
