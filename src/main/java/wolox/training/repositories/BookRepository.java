package wolox.training.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findFirstByAuthorOrderByIdAsc(String author);

    @Query("SELECT u FROM Book u WHERE (:pub <= '' or u.publisher = :pub) and (:gen <= '' or"
        + " u.genre = :gen) and (:yea <= '' or u.year = :yea)")
    Iterable<Book> findAllByPublisherAndGenreAndYear(@Param("pub") String publisher, @Param("gen") String genre, @Param("yea") String year);

    Optional<Book> findFirstByIsbnOrderByIdAsc(String isbn);

}
