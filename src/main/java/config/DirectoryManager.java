package config;

import java.io.File;

public class DirectoryManager {
	
	public static boolean isDirectory(String directoryPath) {
		File f = new File(directoryPath);
        return f.exists() && f.isDirectory() && !f.isFile();
    }
	
	public static boolean directoryContainsFile(String directoryPath, String fileName) {
		String filePath = directoryPath + "/" +fileName;
		File f = new File(filePath);
        return f.exists() && f.isFile() && !f.isDirectory();
    }
	
	public static boolean directoryContainsDirectory(String directoryPath, String directoryName) {
		String newDirectoryPath = directoryPath + "/" + directoryName;
		File f = new File(newDirectoryPath);
        return f.exists() && f.isDirectory() && !f.isFile();
    }
}
