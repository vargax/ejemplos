package generadorCarga;

import uniandes.gload.core.LoadGenerator;
import uniandes.gload.core.Task;

public class Generator {
	// --------------------------------------------------------------
	// ATRIBUTOS ESTÁTICOS
	// --------------------------------------------------------------
	/**
	 * El número de tareas a ejecutar
	 */
	private static int numeroDeTareas;
	/**
	 * El gap entre cada tarea
	 */
	private static int gapEntreTareas;
	// --------------------------------------------------------------
	// ATRIBUTOS
	// --------------------------------------------------------------
	/**
	 * La clase encargada de generar la carga
	 */
	private LoadGenerator generator;
	// --------------------------------------------------------------
	// CONSTRUCTOR
	// --------------------------------------------------------------
	public Generator() {
		Task tarea = new ClientServerTask();
		this.generator = new LoadGenerator("Caso 3 - Test 1", numeroDeTareas, tarea, gapEntreTareas);
		
		this.generator.generate();
	}
	
	public static void main(String[] args) {
		try {
			Generator.numeroDeTareas = Integer.parseInt(args[0]);
			Generator.gapEntreTareas = Integer.parseInt(args[1]);
			Generator gen = new Generator();	
		} catch (Exception e) {
			System.err.println("Error al parsear parámetros: "+e.getMessage());
		}
	}
}
