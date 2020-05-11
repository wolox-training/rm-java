package wolox.training.exceptions;

public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616174L;

	public BookNotFoundException(String message) {
		super(message);
	}
}
