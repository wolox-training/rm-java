package wolox.training.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.http.HttpStatus;

import wolox.training.exceptions.BookIdMismatchException;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.repositories.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@Autowired
    private BookRepository bookRepository;
 
	@GetMapping
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
	
	@GetMapping("/author/{bookAuthor}")
    public Optional<Book> findByAuthor(@PathVariable String bookAuthor) {
        return bookRepository.findFirstByAuthorOrderByIdAsc(bookAuthor);
    }
 
    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
          .orElseThrow( () ->new BookNotFoundException("No hay libro para el id " + id) );
    }
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
          .orElseThrow( () ->new BookNotFoundException("No hay libro para el id " + id));
        bookRepository.deleteById(id);
    }
 
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
          throw new BookIdMismatchException("No hay libro para el id " + id);
        }
        bookRepository.findById(id)
          .orElseThrow( () ->new BookNotFoundException("No hay libro para el id " + id));
        return bookRepository.save(book);
    }

}