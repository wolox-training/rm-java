package wolox.training;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import wolox.training.models.Book;
import wolox.training.models.OpenLibraryBook;

public class OpenLibraryService {

	public Optional<OpenLibraryBook> bookInfo(String isbn) {
		RestTemplate restTemplate = new RestTemplate();
		String openlibraryResourceUrl = "https://openlibrary.org/api/books?bibkeys=ISBN:{isbn}&format=json&jscmd=data";
		Map<String, OpenLibraryBook> response = restTemplate.exchange(openlibraryResourceUrl, HttpMethod.GET, null,
		        new ParameterizedTypeReference<Map<String, OpenLibraryBook>>() {
		        }, isbn).getBody();
		OpenLibraryBook openLibraryBook = response.get("ISBN:" + isbn);
		return Optional.ofNullable(openLibraryBook);
	}

	public Optional<OpenLibraryBook> mapBookInfo(Book b) {
		OpenLibraryBook ol = new OpenLibraryBook(b.getIsbn(), b.getTitle(), b.getSubtitle(), b.getPublisher(),
		        b.getYear(), b.getPages(), Arrays.asList(b.getAuthor()));
		return Optional.ofNullable(ol);
	}

}
