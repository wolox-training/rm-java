package wolox.training.models;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import wolox.training.exceptions.BookNotFoundException;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String username;

	@NotNull
	private String name;

	@NotNull
	private LocalDate birthdate;

	@ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinTable(name = "user_book", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
	private List<Book> books;

	public long getId() {
		return id;
	}

	public User(long id, @NotNull String username, @NotNull String name, @NotNull LocalDate birthdate,
	        @NotNull List<Book> books) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.birthdate = birthdate;
		this.books = Collections.emptyList();;
	}
	
	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public List<Book> getBooks() {
		return (List<Book>) Collections.unmodifiableList(books);
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		if (this.books.contains(book) == false) {
			this.books.add(book);
		} else {
			throw new BookNotFoundException("Libro " + book.getTitle() + " ya existente para el usuario");
		}
	}

	public void removeBook(Book book) {
		if (this.books.remove(book) == false) {
			throw new BookNotFoundException("No hay libro para el id " + book.getId());
		}
	}


}
