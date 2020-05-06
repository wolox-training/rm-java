package wolox.training.exceptions;
import java.lang.String;


public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616174L;

	public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

	public UserNotFoundException(String message) {
        super(message);
    }
	public UserNotFoundException() {
    }

}