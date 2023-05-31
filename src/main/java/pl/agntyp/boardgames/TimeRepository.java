package pl.agntyp.boardgames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<TimeRange, Long> {
    TimeRange findByMinTimeAndMaxTime(int minTime, int maxTime);
}
