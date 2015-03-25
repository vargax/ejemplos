package vafus.plugins.vissim.communication.filetransfer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DownloadHandler implements HttpHandler {

	private File downloadFolder;
	
	public DownloadHandler(File downloadFolder) {
		this.downloadFolder = downloadFolder;
	}
	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		// Look for the requested file in downloadFolder
		String uri = httpExchange.getRequestURI().getQuery();
		String fileName = uri.split("=")[1];
		
		File requestedFile = new File(downloadFolder.getAbsolutePath()+"/"+fileName);
		
		if (requestedFile.exists()) {
			// Prepare file
			byte[] byteArray = new byte[(int) requestedFile.length()];
			FileInputStream fis = new FileInputStream(requestedFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(byteArray, 0, byteArray.length);

			// Add the required response header for a text file
			Headers h = httpExchange.getResponseHeaders();
			h.add("Content-Type", "text/xml");
			
			// Send the response
			httpExchange.sendResponseHeaders(200, requestedFile.length());
			OutputStream os = httpExchange.getResponseBody();
			os.write(byteArray, 0, byteArray.length);
			os.close();
		} else httpExchange.sendResponseHeaders(404, 0);
	}
}
