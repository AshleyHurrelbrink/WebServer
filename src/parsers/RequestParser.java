package parsers;

import exceptions.parsers_exceptions.InvalidRequestParserException;
import validators.RequestParserValidator;

public class RequestParser {
	
	private String content;
	
	public RequestParser(String content) {
		this.content=content;
	}

	public String getResource() throws InvalidRequestParserException {
		if(!RequestParserValidator.validateResource(this.content)) {
			throw new InvalidRequestParserException("Invalid resource request");
		}
		return null;
	}
	
	public String getHost() throws InvalidRequestParserException {
		if(!RequestParserValidator.validateHost(this.content)) {
			throw new InvalidRequestParserException("Invalid host request");
		}
		return null;
	}
}
