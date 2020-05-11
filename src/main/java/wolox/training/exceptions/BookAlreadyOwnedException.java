package wolox.training.exceptions;

public class BookAlreadyOwnedException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616176L;

	public BookAlreadyOwnedException(String message) {
		super(message);
	}
}
