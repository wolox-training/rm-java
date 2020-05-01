package wolox.training.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import wolox.training.exceptions.BookNotFoundException;
import wolox.training.exceptions.UserIdMismatchException;
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;
 
	@GetMapping
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
	
	@GetMapping("/")
	@RequestMapping(params = "username")
    public Optional<User> findByAuthor(@RequestParam(required = true) String username) {
        return userRepository.findFirstByUsernameOrderByIdAsc(username);
    }
 
    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id)
          .orElseThrow( () ->new UserNotFoundException("No hay usuario para el id " + id) );
    }
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
    	System.out.println(user);
        return userRepository.save(user);
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.findById(id)
          .orElseThrow( () ->new UserNotFoundException("No hay usuario para el id " + id));
        userRepository.deleteById(id);
    }
 
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        if (user.getId() != id) {
          throw new UserIdMismatchException("No hay usuario para el id " + id);
        }
        userRepository.findById(id)
          .orElseThrow( () ->new UserNotFoundException("No hay usuario para el id " + id));
        return userRepository.save(user);
    }

    @PostMapping("/{id}/addbook")
    public User addBook(@RequestBody Book book, @PathVariable Long id) {

    	User user = new User();
    	user = userRepository.findById(id)
    	    	  .orElseThrow( () ->new UserNotFoundException("No hay usuario para el id " + id));
    		
    	book = bookRepository.findById(book.getId())
  	    	  .orElseThrow( () ->new BookNotFoundException("No hay libro para el id " + id));
    	
        user.addBook(book);
        
        return userRepository.save(user);
    }
    
    @PostMapping("/{id}/removebook")
    public User removeBook(@RequestBody Book book, @PathVariable Long id) {

    	User user = new User();
    	user = userRepository.findById(id)
    	    	  .orElseThrow( () ->new UserNotFoundException("No hay usuario para el id " + id));
    	
    	book = bookRepository.findById(book.getId())
    	    	  .orElseThrow( () ->new BookNotFoundException("No hay libro para el id " + id));
        
    	user.removeBook(book);
        
        return userRepository.save(user);
    }
    
}