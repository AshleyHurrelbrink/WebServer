package exceptions.parsers_exceptions;

public class InvalidRequestException extends Exception{

	public InvalidRequestException(String message) {
		super(message);
	}
	
	public InvalidRequestException() {
		super("Invalid Request Parser Exception");
	}
	
}
