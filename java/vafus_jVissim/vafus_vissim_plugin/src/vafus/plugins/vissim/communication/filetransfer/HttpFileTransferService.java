package vafus.plugins.vissim.communication.filetransfer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import vafus.plugins.vissim.exceptions.CommunicationException;
import vafus.plugins.vissim.exceptions.PersistenceException;

import com.sun.net.httpserver.HttpServer;

public class HttpFileTransferService {	
	// ------------------------
	// Constants
	// ------------------------
	private final static String DOWNLOADS_FOLDER_NAME = "downloads";
	private final static String UPLOADS_FOLDER_NAME = "uploads";
	
	// ------------------------
	// Attributes
	// ------------------------
	private String logId;
	private File httpRoot;
	private File downloadFolder;
	private File uploadFolder;
	
	private HttpServer server;
	private DownloadHandler downloadHandler;
	private UploadHandler uploadHandler;
	
	// ------------------------
	// Constructor
	// ------------------------
	public HttpFileTransferService(int port, String httpRoot) throws CommunicationException, PersistenceException {
		this.logId = "[HttpFileTransferServer (port:"+port+")]";
		System.out.println(logId+": Preparing "+httpRoot+" workspace...");
		File workspace = new File(httpRoot);
		if (workspace.exists()) delete(workspace);

		this.httpRoot = new File(httpRoot);
		this.httpRoot.mkdir();
		
		this.downloadFolder = new File(httpRoot+"/"+DOWNLOADS_FOLDER_NAME);
		this.downloadFolder.mkdir();
		
		this.uploadFolder = new File(httpRoot+"/"+UPLOADS_FOLDER_NAME);
		this.uploadFolder.mkdir();
		
		if (!this.downloadFolder.exists() || !this.uploadFolder.exists())
			throw new PersistenceException(this.uploadFolder.getAbsolutePath()+" and "
					+this.downloadFolder.getAbsolutePath()+" can not be created.");
		
		try {
			this.server = HttpServer.create(new InetSocketAddress(port), 0);
		} catch (IOException e) {
			throw new CommunicationException(e.getMessage(), e.getStackTrace());
		}
		
		this.downloadHandler = new DownloadHandler(this.downloadFolder);
		this.uploadHandler = new UploadHandler();
		
		this.server.createContext("/download",this.downloadHandler);
		this.server.createContext("/upload",this.uploadHandler);
		
		this.server.setExecutor(null);
		System.out.println(logId+": Starting upload/download HTTP server...");
		this.server.start();
	}
	
	// ------------------------
	// Methods
	// ------------------------
	public void makeFileAvailable(File file) {
		
	}
	
	
	
	
	public void shutDown() {
		System.out.println(logId+": Shutting down upload/download HTTP server...");
		this.server.stop(0);		
	}
	
	// ------------------------
	// Auxiliary methods
	// ------------------------
	public static void delete(File file) {
		if (file.isDirectory() && file.list().length != 0) {
			String files[] = file.list();
			for(String tmpFilePath : files) {
				File tmpFile = new File(tmpFilePath);
				delete(tmpFile);
			}
		}
		file.delete();
	}
}
