package day0117.JavaTest.src.day0117.subject1.src.day0106.filedirectory.exception;

public class CanNotReadFileException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CanNotReadFileException() {
		super();
	}

	public CanNotReadFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public CanNotReadFileException(String message) {
		super(message);
	}

	public CanNotReadFileException(Throwable cause) {
		super(cause);
	}
	
	
}
