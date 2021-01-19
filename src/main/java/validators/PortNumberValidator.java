package validators;

public class PortNumberValidator {

	public static boolean validate(int portNumber) {
		
		if (String.valueOf(portNumber).isEmpty())
            return false;

        return String.valueOf(portNumber).matches("^[0-9]+$");
    }
}
