package wolox.training.exceptions;
import java.lang.String;


public class BookAlreadyOwnedException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616176L;

	public BookAlreadyOwnedException(String message, Throwable cause) {
        super(message, cause);
    }

	public BookAlreadyOwnedException(String message) {
        super(message);
    }
	public BookAlreadyOwnedException() {
    }

}