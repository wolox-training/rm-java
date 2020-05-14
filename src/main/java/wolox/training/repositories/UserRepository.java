package wolox.training.repositories;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import wolox.training.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsernameOrderByIdAsc(String username);

    Iterable<User> findAllByBirthdateBetweenAndNameIgnoreCaseContaining(LocalDate birthdateIni,
            LocalDate birthdateEnd, String name);

}
