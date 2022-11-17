package customExecptions;

public class ElementNotEnabledException extends RuntimeException{

	public ElementNotEnabledException (String message) {
		super(message);
	}
}
