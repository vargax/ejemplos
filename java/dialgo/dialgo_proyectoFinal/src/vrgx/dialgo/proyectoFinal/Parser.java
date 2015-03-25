package vrgx.dialgo.proyectoFinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import vrgx.dialgo.proyectoFinal.ecuacion.Ecuacion;
import vrgx.dialgo.proyectoFinal.excepciones.ArchivosEntradaExcepcion;

/**
 * Clase encargada de las operaciones de lectura y escritura
 * @author cvargasc
 *
 */
public class Parser {
	// ---------------------------------------------
	// CONSTANTES
	// ---------------------------------------------
	/**
	 * Prefijo para los archivos generados
	 */
	public final static String PREFIJO = "GP_11_";
	/**
	 * Cadena que representa la ruta del directorio que contiene los archivos de entrada
	 */
	public final static String RUTA_DIR_ENTRADA = "./data/entrada";
	/**
	 * Cadena que representa la ruta del directorio donde se generarán los archivos de salida
	 */
	public final static String RUTA_DIR_SALIDA = "./data/salida";
	/**
	 * Indica si se debe limpiar el directorio de salida antes de proceder
	 */
	public final static boolean ELIMINAR_ARCHIVOS = false;
	// ---------------------------------------------
	// ATRIBUTOS
	// ---------------------------------------------
	/**
	 * Representa el directorio de entrada
	 */
	private File dirEntrada;
	/**
	 * Representa el directorio de salida
	 */
	private File dirSalida;
	/**
	 * Contiene los archivos en el directorio de entrada
	 */
	private File[] archivosPorProcesar;
	// ---------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------
	@SuppressWarnings("unused")
	public Parser() throws ArchivosEntradaExcepcion {
		dirEntrada = new File(RUTA_DIR_ENTRADA);
		dirSalida = new File(RUTA_DIR_SALIDA);
		
		archivosPorProcesar = dirEntrada.listFiles();
		if (archivosPorProcesar == null) throw new ArchivosEntradaExcepcion("No se encontraron archivos en el directorio de entrada");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++ ISIS1105 Diseño y Análisis de Algoritmos +++");
		System.out.println("+++ Proyecto Final 201120 - Diciembre 1/2011 +++");
		System.out.println("+++ Camilo Vargas Cabrera (200612197)        +++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		File[] archivosEliminar = dirSalida.listFiles();
		if (ELIMINAR_ARCHIVOS && archivosEliminar != null) {
			System.out.println("Se han encontrado archivos en el directorio de salida.");
			System.out.println("Se eliminarán "+archivosEliminar.length+" archivos de "+RUTA_DIR_SALIDA);
			for (int i = 0; i < archivosEliminar.length; i++)
				archivosEliminar[i].delete();
		}
		procesarArchivos();
	}
	// ---------------------------------------------
	// MÉTODOS
	// ---------------------------------------------
	/**
	 * Ciclo que procesa todos los archivos en el directorio de entrada
	 */
	public void procesarArchivos() {
		System.out.println("Se encontraron "+archivosPorProcesar.length+" archivos para procesar.");
		for (int i = 0; i < archivosPorProcesar.length; i++) {
			try {
				procesarArchivo(archivosPorProcesar[i]);
			} catch (Exception e) {
				System.out.println("Se encontraron errores al procesar el archivo "+(i+1)+":"+e.getMessage()); 
			}
		}
		System.out.println(archivosPorProcesar.length+" archivos procesados.");
	}
	/**
	 * Método que precesa un archivo específico
	 * @param archivo
	 */
	public void procesarArchivo(File archivo) throws Exception {
		// Procesando el archivo de entrada
		BufferedReader entrada = new BufferedReader(new FileReader(archivo));
		String identificador = entrada.readLine();
		String ecuacion = entrada.readLine();
		String incognita = entrada.readLine();
		int maxPasos = Integer.parseInt(entrada.readLine());
		entrada.close();
		System.out.println("--------------------");
		System.out.println("Inciando proceso para < "+identificador+" >");
		// Despejando la ecuación
		Ecuacion ec = new Ecuacion(incognita, ecuacion);
		ec.despejarEcuacion();
		ArrayList<String> transformaciones = ec.darTransformaciones();
		ArrayList<String> resultados = ec.darResultados();
		// Generación del archivo de salida
		PrintWriter salida = new PrintWriter(new File(RUTA_DIR_SALIDA,PREFIJO+identificador+".out"));
		salida.println(identificador);
		salida.println(ecuacion);
		salida.println(incognita);
		salida.println(maxPasos);
		salida.println("--------------------");
		salida.println("[0] "+resultados.get(0));
		for (int i = 0; i < transformaciones.size(); i++) {
			salida.println("= <"+transformaciones.get(i)+">");
			salida.println("["+(i+1)+"] "+resultados.get(i+1));
		}
		salida.println("--------------------");
		salida.close();
		System.out.println("Finalizando proceso para < "+identificador+" >");
	}
	/**
	 * Método principal del programa
	 */
	public static void main(String[] args) {
		try {
			new Parser();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
