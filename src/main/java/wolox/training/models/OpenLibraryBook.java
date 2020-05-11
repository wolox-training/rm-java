package wolox.training.models;

import java.util.List;

public class OpenLibraryBook {

	private String isbn;

	private String title;

	private String subtitle;

	private String publisher;

	private String year;

	private int pages;

	private List<String> authors;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public OpenLibraryBook(String isbn, String title, String subtitle, String publisher, String year, int pages,
	        List<String> authors) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.subtitle = subtitle;
		this.publisher = publisher;
		this.year = year;
		this.pages = pages;
		this.authors = authors;
	}

}
