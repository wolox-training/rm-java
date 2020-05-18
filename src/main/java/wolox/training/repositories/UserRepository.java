package wolox.training.repositories;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wolox.training.models.Book;
import wolox.training.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsernameOrderByIdAsc(String username);

    @Query("SELECT u FROM User u WHERE (cast(:bi as date) is null or u.birthdate >= :bi) and "
        + "(cast(:bf as date) is null or u.birthdate <= :bf) and (:na <= '' or upper(u.name) = upper(:na))")
    Iterable<User> findAllByBirthdateBetweenAndNameIgnoreCaseContaining(@Param("bi") LocalDate birthdateIni,
        @Param("bf") LocalDate birthdateEnd, @Param("na") String name);
}
