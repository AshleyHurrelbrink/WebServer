package exceptions.parsers_exceptions;

public class InvalidRequestParserException extends Exception{

	public InvalidRequestParserException(String message) {
		super(message);
	}
	
	public InvalidRequestParserException() {
		super("Invalid Request Parser Exception");
	}
	
}
