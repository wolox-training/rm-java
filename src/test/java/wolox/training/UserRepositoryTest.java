package wolox.training;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void setUp() {
		// given

		Book theCrow = new Book("Terror", "Edgar Allan Poe", "TheCrow.JPG", "The Crow", "N/A", "Madison Street", "1940",
		        304, "1122334455667789");

		entityManager.persist(theCrow);

		Book seasonsOfStorm = new Book("SciFy", "Andrezej Sapkowsky", "Seasons.JPG", "Seasons of Storms", "The Witcher",
		        "??", "1970", 200, "1122334455667780");

		entityManager.persist(seasonsOfStorm);

		Book phobia = new Book("Thriller", "Wulf Dorn", "Phobia.JPG", "Phobia", "--", "??", "1980", 300,
		        "1122334455667781");

		entityManager.persist(phobia);

		Book laPsiquiatra = new Book("Thriller", "Wulf Dorn", "laPsiquiatra.JPG", "La Psiquiatra", "--", "??", "1990",
		        400, "1122334455667782");

		entityManager.persist(laPsiquiatra);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2000-01-01", formatter);
		User juanCarlos = new User("JuanCarlos", "Juan Carlos", ld);
		entityManager.persist(juanCarlos);

		ld = LocalDate.parse("2012-12-31", formatter);
		User pepe = new User("pepe", "Pepe Biondi", ld);

		entityManager.persist(pepe);

		entityManager.flush();
	}

	@Test
	@Order(1)
	public void whenCreateUser_thenUserIsPersisted() throws Exception {

		// when
		Optional<User> found = userRepository.findFirstByUsernameOrderByIdAsc("pepe");

		// then
		assertThat(found.get().getName()).isEqualTo("Pepe Biondi");
		assertThat(found.get().getUsername()).isEqualTo("pepe");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2012-12-31", formatter);
		assertThat(found.get().getBirthdate()).isEqualTo(ld);

	}

	@Test
	public void whenFindByUsername_thenReturnBookJsonArray() throws Exception {

		// when
		Optional<User> found = userRepository.findFirstByUsernameOrderByIdAsc("JuanCarlos");

		// then
		assertThat(found.get().getName()).isEqualTo("Juan Carlos");

	}

	@Test
	public void whenFindAll_thenReturnUserJsonArray() throws Exception {

		// when
		List<User> found = userRepository.findAll();
		// then
		assertThat(found.size()).isEqualTo(2);

	}

	@Test
	public void whenFindByUsername_thenReturnNoElements() throws Exception {

		// when
		Optional<User> found = userRepository.findFirstByUsernameOrderByIdAsc("JuanCarlos");

		// then
		assertThat(!found.isPresent());

	}

}
