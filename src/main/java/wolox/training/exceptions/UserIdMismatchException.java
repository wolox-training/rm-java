package wolox.training.exceptions;
import java.lang.String;

public class UserIdMismatchException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616175L;

	public UserIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

	public UserIdMismatchException(String message) {
        super(message);
    }
	public UserIdMismatchException() {
	}

}