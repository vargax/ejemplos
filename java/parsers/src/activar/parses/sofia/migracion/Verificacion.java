package activar.parses.sofia.migracion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import econ.parsers.pabis.codensa.Codensa;

public class Verificacion {
	// ---------------------------------------------
	// Constantes
	// ---------------------------------------------
	private final int ULTIMO_CONTRATO_MIGRADO = 3554;
	
	private final static String archivoEntrada = "./data/activar/input.csv";
	private final static String archivoSalida = "./data/activar/output.csv";
	
	// ---------------------------------------------
	// Atributos
	// ---------------------------------------------
	private ArrayList<String> reales; // Los contratos realmente registrados en ActWeb
	private ArrayList<String> migrados; // Los contratos migrados a Sofia
	private ArrayList<String> faltantes; // Los contratos que estaban en ActWeb y no se migraron a Sofia

	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	private Verificacion() throws Exception {
		this.reales = new ArrayList<String>();
		this.migrados = new ArrayList<String>();	
		this.faltantes = new ArrayList<String>();
		
		lector();
		comparardor();
		escritor();
	}
	
	// ---------------------------------------------
	// Métodos
	// ---------------------------------------------	
	private void lector() throws Exception {
		BufferedReader bf = new BufferedReader(new FileReader(new File(archivoEntrada)));
		
		String lineaActual = bf.readLine();
		while (lineaActual != null) {
			// Los archivos actualmente en ActWeb están a la izquierda, los originalmente migrados están a la derecha
			String[] datos = lineaActual.split(",");
			if (Integer.parseInt(datos[0]) <= ULTIMO_CONTRATO_MIGRADO) reales.add(datos[0]);
			if (datos.length == 2) migrados.add(datos[1]);
			
			lineaActual = bf.readLine();
		}
	}
	
	private void comparardor() {
		// Los contratos que me interesan son aquellos que están en ActWeb y no aparecen en el listado de migrados
		for (String actual : reales) {
			boolean fueMigrado = false;
			for (String migrado : migrados) {
				if (actual.equals(migrado)) {
					fueMigrado = true;
					break;
				}
			}
			if (!fueMigrado) {
				System.out.println("El contrato "+actual+" no fue migrado a SOFIA");
				faltantes.add(actual);
			}
		}
		System.err.println("Se identificaron "+faltantes.size()+" contratos no migrados...");
	}
	
	private void escritor() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(archivoSalida));
		for (String faltante : faltantes) pw.println(faltante);
		
		String sql = "(activos.ID_Contrato)=";
		for (String faltante : faltantes) sql = sql+faltante+" or (activos.ID_Contrato)=";
		System.out.println(sql);
	}
	
	// ---------------------------------------------
	// Método Main
	// ---------------------------------------------
	public static void main(String[] args) {
		try {
			new Verificacion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
