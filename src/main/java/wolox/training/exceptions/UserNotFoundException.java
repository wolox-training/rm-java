package wolox.training.exceptions;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616174L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
