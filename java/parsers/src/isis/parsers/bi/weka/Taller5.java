package isis.parsers.bi.weka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Taller5 {
	// ---------------------------------------------
	// Constantes
	// ---------------------------------------------
	private final static String archivoEntrada = "./data/bi/heart-h.arff";
	private final static String archivoSalida = "./data/bi/output-h.arff";
	
	//private final static String region = "CLEV";
	private final static String region = "HUNG";
	
	// ---------------------------------------------
	// Atributos
	// ---------------------------------------------

	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	private Taller5() throws Exception {
		// Inicializo los archivos de lectura, escritura y lineaActual
		BufferedReader bf = new BufferedReader(new FileReader(new File(archivoEntrada)));
		PrintWriter pw = new PrintWriter(new File(archivoSalida));
		String lineaActual;
		
		// Avanzo transcribiendo hasta encontrar @data
		do {
			lineaActual = bf.readLine();
			pw.println(lineaActual);
		} while (!lineaActual.equals("@data"));

		lineaActual = bf.readLine();
		
		while (lineaActual != null) {
			lineaActual = lineaActual+",'"+region+"'";
			System.out.println(lineaActual);
			pw.println(lineaActual);
			
			lineaActual = bf.readLine();
		}
		
		bf.close();
		pw.close();
	}
	
	// ---------------------------------------------
	// Métodos
	// ---------------------------------------------	

	// ---------------------------------------------
	// Método Main
	// ---------------------------------------------
	public static void main(String[] args) {
		try {
			new Taller5();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
