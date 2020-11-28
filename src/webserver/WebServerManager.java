package webserver;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WebServerManager {
	
	public static String getRequest(BufferedReader in) throws IOException {
		String inputLine;
		StringBuilder request = new StringBuilder();
		
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			request.append(inputLine);
			
			if (inputLine.trim().equals(""))
				break;
		}
		return request.toString();
	}

    public static void sendResponse(Socket client, String status, String contentType, byte[] content) throws IOException {
        OutputStream clientOutput = client.getOutputStream();
        clientOutput.write(("HTTP/1.1 \r\n" + status).getBytes());
        clientOutput.write(("ContentType: " + contentType + "\r\n").getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write(content);
        clientOutput.write("\r\n\r\n".getBytes());
        clientOutput.flush();
        client.close();
    }

    public static Path getFilePath(String path) {
        if ("/".equals(path)) {
            path = "/index.html";
        }

        return Paths.get("/tmp/www", path);
    }

    public static String guessContentType(Path filePath) throws IOException {
        return Files.probeContentType(filePath);
    }
}
