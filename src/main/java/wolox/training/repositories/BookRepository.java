package wolox.training.repositories;

import java.util.Optional;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findFirstByAuthorOrderByIdAsc(String author);

    Iterable<Book> findAllByPublisherAndGenreAndYear(String publisher, String genere, String year);

  	Optional<Book> findFirstByIsbnOrderByIdAsc(String isbn);

}
