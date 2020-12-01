package handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ResponseHandler {

	public static void sendResponse(Socket clientSocket, OutputStream clientOutput, String httpVersion, String status, String contentType, byte[] content) throws IOException {
        clientOutput.write((httpVersion+" \r\n" + status).getBytes());
        clientOutput.write(("ContentType: " + contentType + "\r\n").getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write(content);
        clientOutput.write("\r\n\r\n".getBytes());
        clientOutput.flush();
        clientSocket.close();
    }

}
