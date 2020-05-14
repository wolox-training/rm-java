package wolox.training.service;

import java.util.Map;
import java.util.Optional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.training.models.Book;
import wolox.training.models.dto.OpenLibraryBook;

@Service
public class OpenLibraryService {

    public Optional<Book> bookInfo(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        String openlibraryResourceUrl =
                "https://openlibrary.org/api/books?bibkeys=ISBN:{isbn}&format=json&jscmd=data";
        Map<String, OpenLibraryBook> response = restTemplate
                .exchange(openlibraryResourceUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Map<String, OpenLibraryBook>>() {}, isbn)
                .getBody();
        Optional<OpenLibraryBook> openLibraryBook =
                Optional.ofNullable(response.get("ISBN:" + isbn));
        return bookBuildFromDto(openLibraryBook, isbn);
    }

    public Optional<Book> bookBuildFromDto(Optional<OpenLibraryBook> olb, String isbn) {

        if (olb.isPresent()) {

            Book book = new Book();

            OpenLibraryBook openLibraryBook = olb.get();

            book.setGenre(
                    Optional.ofNullable(openLibraryBook.getSubjects().get(0).getName()).orElse(""));

            book.setAuthor(
                    Optional.ofNullable(openLibraryBook.getAuthors().get(0).getName()).orElse(""));

            book.setImage(Optional.ofNullable(openLibraryBook.getCover().getSmall()).orElse(""));

            book.setPublisher(Optional.ofNullable(openLibraryBook.getPublishers().get(0).getName())
                    .orElse(""));

            book.setIsbn(isbn);
            book.setTitle(Optional.ofNullable(openLibraryBook.getTitle()).orElse(""));
            book.setSubtitle(Optional.ofNullable(openLibraryBook.getSubtitle()).orElse(""));
            book.setYear(Optional.ofNullable(openLibraryBook.getPublishDate()).orElse(""));
            book.setPages(Optional.ofNullable(openLibraryBook.getNumberOfPages()).orElse(0));
            return Optional.ofNullable(book);
        } else {
            return Optional.empty();
        }
    }

}
