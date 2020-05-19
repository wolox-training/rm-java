package wolox.training.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findFirstByAuthorOrderByIdAsc(String author);

    @Query("SELECT b FROM Book b WHERE (:publisher is null or b.publisher = :publisher)"
        + " and (:genre is null or b.genre = :genre)"
        + " and (:year is null or b.year = :year)")
    Iterable<Book> findAllByPublisherAndGenreAndYear(@Param("publisher") String publisher, @Param("genre") String genre, @Param("year") String year);

    @Query("SELECT b FROM Book b WHERE (:genre is null or b.genre = :genre)"
        + " and (:publisher is null or b.publisher = :publisher)"
        + " and (:author is null or b.author = :author)"
        + " and (:image is null or b.image = :image)"
        + " and (:title is null or b.genre = :title)"
        + " and (:subtitle is null or b.genre = :subtitle)"
        + " and (:year is null or b.year = :year)"
        + " and (:pages is 0 or b.pages = :pages)"
        + " and (:isbn is null or b.isbn = :isbn)"
    )
    Iterable<Book> findAll(@Param("genre") String genre, @Param("author") String author,
        @Param("image") String image,@Param("title") String title,@Param("subtitle") String subtitle
        ,@Param("publisher") String publisher,@Param("year") String year,@Param("pages") int pages
        ,@Param("isbn") String isbn);


    Optional<Book> findFirstByIsbnOrderByIdAsc(String isbn);

}
