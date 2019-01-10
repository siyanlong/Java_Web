package siyl.cit.shop.model;

public class ShopException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7377109611983062933L;

	public ShopException() {
		super();
	}

	public ShopException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShopException(String message) {
		super(message);
	}

	public ShopException(Throwable cause) {
		super(cause);
	}
	
}
