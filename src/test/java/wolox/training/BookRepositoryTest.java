package wolox.training;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat ;

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
    
    @Test
    public void whenFindByAuthor_thenReturnBookJsonArray()
      throws Exception {
        
    	//given
        Book theCrow = new Book("Terror",
        	    "Edgar Allan Poe",
        	    "TheCrow.JPG",
        	    "The Crow",
        	    "N/A",
        	    "Madison Street",
        	    "1940",
        	    304,
        	    "1122334455667789");
        
        entityManager.persist(theCrow);
        
        Book seasonsOfStorm = new Book("SciFy",
        	    "Andrezej Sapkowsky",
        	    "TheCrow.JPG",
        	    "Seasons of Storms",
        	    "The Witcher",
        	    "??",
        	    "1970",
        	    200,
        	    "1122334455667780");
        
        entityManager.persist(seasonsOfStorm);
        entityManager.flush();

        //when        
        Optional<Book> found = bookRepository.findFirstByAuthorOrderByIdAsc(theCrow.getAuthor());
        
        //then
        assertThat(found.get().getAuthor()).isEqualTo(theCrow.getAuthor());        

    }
    @Test
    public void whenCreateBook_thenBookIsPersisted()
      throws Exception {
        
    	//given
        Book theCrow = new Book("Terror",
        	    "Edgar Allan Poe",
        	    "TheCrow.JPG",
        	    "The Crow",
        	    "N/A",
        	    "Madison Street",
        	    "1940",
        	    304,
        	    "1122334455667789");
        
        entityManager.persist(theCrow);
        entityManager.flush();

        //when        
        Optional<Book> found = bookRepository.findFirstByAuthorOrderByIdAsc(theCrow.getAuthor());
        
        //then
        assertThat(found.get().getAuthor()).isEqualTo(theCrow.getAuthor());        
        assertThat(found.get().getGenre()).isEqualTo(theCrow.getGenre());        
        assertThat(found.get().getImage()).isEqualTo(theCrow.getImage());
        assertThat(found.get().getTitle()).isEqualTo(theCrow.getTitle());
        assertThat(found.get().getSubtitle()).isEqualTo(theCrow.getSubtitle());
        assertThat(found.get().getPublisher()).isEqualTo(theCrow.getPublisher());
        assertThat(found.get().getYear()).isEqualTo(theCrow.getYear());
        assertThat(found.get().getPages()).isEqualTo(theCrow.getPages());
        assertThat(found.get().getIsbn()).isEqualTo(theCrow.getIsbn());
        assertThat(found.get().getId()).isEqualTo(1L);
    }
    
}
