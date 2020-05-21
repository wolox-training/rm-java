package wolox.training.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;
import wolox.training.exceptions.BookNotFoundException;

@Entity
@NoArgsConstructor
public @Data class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String username;

	@NotNull
	private String name;

	@NotNull
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate birthdate;

	@ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinTable(name = "user_book", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
	private List<Book> books = new ArrayList<Book>();


	public User(@NotNull String username, @NotNull String name, @NotNull LocalDate birthdate) {
		super();
		this.username = username;
		this.name = name;
		this.birthdate = birthdate;
	}

	public List<Book> getBooks() {
		return (List<Book>) Collections.unmodifiableList(books);
	}

	public void addBook(Book book) {
		if (!this.books.contains(book)) {
			this.books.add(book);
		} else {
			throw new BookNotFoundException(
			        "Libro " + book.getTitle() + " ya existente para el usuario " + this.getId());
		}
	}

	public void removeBook(Book book) {
		if (!this.books.remove(book)) {
			throw new BookNotFoundException("No hay libro con id " + book.getId() + " para el usuario " + this.getId());
		}
	}

}
