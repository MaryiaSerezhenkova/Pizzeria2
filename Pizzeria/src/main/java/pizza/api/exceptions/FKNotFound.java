package pizza.api.exceptions;
public class FKNotFound extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FKNotFound(String message) {
        super(message);
    }
}