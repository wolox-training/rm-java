package wolox.training.exceptions;

public class UserIdMismatchException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616175L;

	public UserIdMismatchException(String message) {
		super(message);
	}
}
