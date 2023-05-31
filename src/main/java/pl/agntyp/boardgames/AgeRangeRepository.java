package pl.agntyp.boardgames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgeRangeRepository extends JpaRepository<AgeRange, Long> {
    List<AgeRange> findAllByMinAgeGreaterThanEqual(int minAge);

    List<AgeRange> findAllByMinAgeGreaterThanEqualOrderByMinAgeAsc(int minAge);

    AgeRange findByMinAge(int minAge);
}
