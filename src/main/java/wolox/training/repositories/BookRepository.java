package wolox.training.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	  List<Book> findFirstByOrderByIdgitAsc(String author);

	}
