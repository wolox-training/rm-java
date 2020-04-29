package wolox.training.exceptions;
import java.lang.String;


public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616174L;

	public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

	public BookNotFoundException(String message) {
        super(message);
    }
	public BookNotFoundException() {
    }

}