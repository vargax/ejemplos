package lab5infracom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lab5infracom.server.UdpServer;

public class Main {

	public final static String[] paths = {"a-ha", "metallica", "rollingStones", "shakira"};
	
	public static void main(final String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		UdpServer udpServer = UdpServer.getInstance();
		for (String path : paths) {
			path = "data/"+path+".mp4";
			udpServer.publicarVideo(path);
		}
		
		System.err.println("Presione cualquier tecla para salir...");
		br.readLine();
		udpServer.detener();
	}
}
