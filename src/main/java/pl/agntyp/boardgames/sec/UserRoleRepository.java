package pl.agntyp.boardgames.sec;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
