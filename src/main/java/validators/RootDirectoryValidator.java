package validators;

import config.DirectoryManager;
import webserver.DefaultValues;

public class RootDirectoryValidator {
	
	public static boolean validate(String rootDirectory) {
		if (DirectoryManager.isDirectory(rootDirectory)) {
			boolean hasHomePage = DirectoryManager.directoryContainsFile(rootDirectory, DefaultValues.getHomePage());
			boolean hasPageNotFound = DirectoryManager.directoryContainsFile(rootDirectory, DefaultValues.getpageNotFound());
            return hasHomePage && hasPageNotFound;
		}
		return false;
	}
}
