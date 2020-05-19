package wolox.training.controllers;

import java.util.Optional;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wolox.training.exceptions.BookIdMismatchException;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;
import wolox.training.service.OpenLibraryService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OpenLibraryService openLibraryService;

    @GetMapping
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping
    @RequestMapping(params = "author")
    public Optional<Book> findByAuthor(@RequestParam(required = true) String author) {
        return bookRepository.findFirstByAuthorOrderByIdAsc(author);
    }

    @GetMapping("/find")
    public Iterable<Book> findByPublisherGenreYear(@RequestParam(required = false) String publisher,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String year) {
        return bookRepository.findAllByPublisherAndGenreAndYear(publisher, genre, year);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No hay libro para el id " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No hay libro para el id " + id));
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException("No hay libro para el id " + id);
        }
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No hay libro para el id " + id));
        return bookRepository.save(book);
    }

    @GetMapping
    @RequestMapping(params = "isbn")
    public ResponseEntity<Book> findByIsbn(@RequestParam(required = true) String isbn) {

        Optional<Book> bookOptional = bookRepository.findFirstByIsbnOrderByIdAsc(isbn);
        Book book = new Book();

        if (bookOptional.isPresent()) {
            book = bookOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } else {
            book = openLibraryService.bookInfo(isbn).orElseThrow(
                    () -> new BookNotFoundException("No hay libro para el isbn " + isbn));
            return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));
        }
    }

    @GetMapping("/all")
    public Iterable<Book> findByAll(
        @RequestParam(required = false) String genre,
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String image,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String subtitle,
        @RequestParam(required = false) String publisher,
        @RequestParam(required = false) String year,
        @RequestParam(required = false, defaultValue = "") String pages,
        @RequestParam(required = false) String isbn

    ) {

        int iPages = (StringUtils.isNumber(pages)) ? Integer.decode(pages) : 0;
        return bookRepository.findAll(genre,author,image,title,subtitle,publisher,year,iPages,isbn);
    }

}
