package ir.maleki.sideprojects.timetally.application.user.repository;

import ir.maleki.sideprojects.timetally.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
