package wolox.training.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
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
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.exceptions.UserIdMismatchException;
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.BookRepository;
import wolox.training.repositories.UserRepository;

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

    @GetMapping
    @RequestMapping(params = "username")
    public User findByUsername(@RequestParam(required = true) String username) {
        return userRepository.findFirstByUsernameOrderByIdAsc(username).orElseThrow(
                () -> new UserNotFoundException("No hay usuario para el username " + username));

    }

    @GetMapping
    @RequestMapping(params = {"birthdateIni", "birthdateEnd", "name"})
    public Iterable<User> findByBirthdateAndName(
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate birthdateIni,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate birthdateEnd,
            @RequestParam(required = false) String name) {
        return userRepository.findAllByBirthdateBetweenAndNameIgnoreCaseContaining(birthdateIni,
                birthdateEnd, name);
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No hay usuario para el id " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No hay usuario para el id " + id));
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        if (user.getId() != id) {
            throw new UserIdMismatchException("No hay usuario para el id " + id);
        }
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No hay usuario para el id " + id));
        return userRepository.save(user);
    }

    @PostMapping("/{id}/books/{bookId}")
    public User addBook(@PathVariable Long id, @PathVariable Long bookId) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No hay usuario para el id " + id));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("No hay libro para el id " + id));

        user.addBook(book);

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}/books/{bookId}")
    public User removeBook(@PathVariable Long id, @PathVariable Long bookId) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No hay usuario para el id " + id));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("No hay libro para el id " + id));

        user.removeBook(book);

        return userRepository.save(user);
    }
}
