package wolox.training.exceptions;
import java.lang.String;

public class BookIdMismatchException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616175L;

	public BookIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

	public BookIdMismatchException(String message) {
        super(message);
    }
	public BookIdMismatchException() {
	}

}