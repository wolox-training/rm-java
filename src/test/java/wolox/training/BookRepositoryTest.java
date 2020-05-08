package wolox.training;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

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

		entityManager.flush();
	}

	@Test
	public void whenFindByAuthor_thenReturnBookJsonArray() throws Exception {

		// when
		Optional<Book> found = bookRepository.findFirstByAuthorOrderByIdAsc("Wulf Dorn");

		// then
		assertThat(found.get().getTitle()).isEqualTo("Phobia");

	}

	@Test
	public void whenCreateBook_thenBookIsPersisted() throws Exception {

		// when
		Optional<Book> found = bookRepository.findFirstByAuthorOrderByIdAsc("Edgar Allan Poe");

		// then
		assertThat(found.get().getAuthor()).isEqualTo("Edgar Allan Poe");
		assertThat(found.get().getGenre()).isEqualTo("Terror");
		assertThat(found.get().getImage()).isEqualTo("TheCrow.JPG");
		assertThat(found.get().getTitle()).isEqualTo("The Crow");
		assertThat(found.get().getSubtitle()).isEqualTo("N/A");
		assertThat(found.get().getPublisher()).isEqualTo("Madison Street");
		assertThat(found.get().getYear()).isEqualTo("1940");
		assertThat(found.get().getPages()).isEqualTo(304);
		assertThat(found.get().getIsbn()).isEqualTo("1122334455667789");

	}

	@Test
	public void whenFindAll_thenReturnBookJsonArray() throws Exception {

		// when
		List<Book> found = bookRepository.findAll();
		// then
		assertThat(found.size()).isEqualTo(4);

	}

	@Test(expected = NoSuchElementException.class)
	public void whenFindByAuthor_thenReturnNoElements() throws Exception {

		// when
		Optional<Book> found = bookRepository.findFirstByAuthorOrderByIdAsc("WulfqQ DornQ");

		// then
		assertThat(found.get().getTitle()).isEqualTo("Phobia");

	}

}
