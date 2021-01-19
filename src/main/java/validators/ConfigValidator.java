package validators;

public class ConfigValidator {
	
	public static boolean validateGetSetting(String value){
        return value != null;
    }
	
	public static boolean validateSetSetting(String expectedValue, String NewValue){
        return expectedValue.equals(NewValue);
    }
}
