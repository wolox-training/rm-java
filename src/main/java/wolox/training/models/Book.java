package wolox.training.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public @Data class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String genre;

	@NotNull
	private String author;

	@NotNull
	private String image;

	@NotNull
	private String title;

	@NotNull
	private String subtitle;

	@NotNull
	private String publisher;

	@NotNull
	private String year;

	@NotNull
	private int pages;

	@NotNull
	private String isbn;

	@JsonIgnore
	@ManyToMany(mappedBy = "books")
	private List<User> users;

	public Book(String genre, String author, String image, String title, String subtitle, String publisher, String year,
	        int pages, String isbn) {
		this.genre = genre;
		this.author = author;
		this.image = image;
		this.title = title;
		this.subtitle = subtitle;
		this.publisher = publisher;
		this.year = year;
		this.pages = pages;
		this.isbn = isbn;
	}
}
