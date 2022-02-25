package by.itstep.pronovich.exception;

public class CarNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6017500164857599753L;

	public CarNotFoundException() {
		super();
	}

	public CarNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CarNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CarNotFoundException(String message) {
		super(message);
	}

	public CarNotFoundException(Throwable cause) {
		super(cause);
	}

	public CarNotFoundException(Long id) {
		super("Could not find car " + id);
	}
}
