package webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Response  {

 private String httpVersion;
 private int statusCode;
 private String contentType;
 private String content;
 
 public Response() {
	 
 }

 public static void sendResponse(Socket clientSocket, OutputStream clientOutput, String httpVersion, String statusCode, String contentType, byte[] content) throws IOException {
     clientOutput.write((httpVersion+" \r\n" + statusCode).getBytes());
     clientOutput.write(("ContentType: " + contentType + "\r\n").getBytes());
     clientOutput.write("\r\n".getBytes());
     clientOutput.write(content);
     clientOutput.write("\r\n\r\n".getBytes());
     clientOutput.flush();
     clientSocket.close();
 }
 /*
 public Response(OutputStream clientOutput)  {
	 this.clientOutput = clientOutput;
 }

 public void setResponseCode(int statusCode, String statusMessage)  {
	 this.statusCode = statusCode;
     this.statusMessage = statusMessage;
 }

 public void addHeader(String headerName, String headerValue)  {
     this.headers.put(headerName, headerValue);
 }

 public void addBody(String body)  {
     headers.put("Content-Length", Integer.toString(body.length()));
     this.body = body;
 }

 public void send() throws IOException  {
     headers.put("Connection", "Close");
     clientOutput.write(("HTTP/1.1 " + statusCode + " " + statusMessage + "\r\n").getBytes());
     for (String headerName : headers.keySet())  {
         clientOutput.write((headerName + ": " + headers.get(headerName) + "\r\n").getBytes());
     }
     clientOutput.write("\r\n".getBytes());
     if (body != null)  {
    	 clientOutput.write(body.getBytes());
     }
 }
*/
 
}