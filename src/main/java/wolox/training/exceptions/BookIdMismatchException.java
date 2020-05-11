package wolox.training.exceptions;

public class BookIdMismatchException extends RuntimeException {
	private static final long serialVersionUID = 7713168262749616175L;

	public BookIdMismatchException(String message) {
		super(message);
	}
}
