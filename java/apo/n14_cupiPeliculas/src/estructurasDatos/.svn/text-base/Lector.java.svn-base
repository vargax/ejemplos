package estructurasDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import mundo.Pelicula;

/**
 * Clase encargada de la lectura del archivo de texto
 * @author cvargasc
 */
public class Lector {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El archivo que contiene las películas
	 */
	private File origen;
	/**
	 * La estructura de datos para almacenar las películas
	 */
	private IColeccionPeliculas coleccion;
	/**
	 * Entero para calcular el número de peliculas agregadas
	 */
	private int peliculasAgregadas;
	/**
	 * Entero para llevar la cuenta de los errores
	 */
	private int errores;
	/**
	 * Entero para llevar la cuenta de peliculas encontradas
	 */
	private int peliculasEncontradas;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public Lector(String ubicacionArchivo, IColeccionPeliculas coleccionP) {
		origen = new File(ubicacionArchivo);
		coleccion = coleccionP;
		peliculasAgregadas = 0;
		errores = 0;
		peliculasEncontradas = 0;
	}
	public Lector(File origenP, IColeccionPeliculas coleccionP) {
		origen = origenP;
		coleccion = coleccionP;
		peliculasAgregadas = 0;
		errores = 0;
		peliculasEncontradas = 0;
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Carga las películas a partir del archivo de origen
	 * @throws IOException 
	 */
	public void cargarPeliculas() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(origen));
		String linea = "";
		while (linea != null) {
			try {
				linea = br.readLine();
				boolean unaPelicula = linea.startsWith("----");
				if (unaPelicula) {
					// Una película
					String titulo = "";
					int año = -1;
					String descripcion = "";
					String autor = "";
					peliculasEncontradas++;
					while (unaPelicula) {
						linea = br.readLine();
						// El titulo y el año
						if (linea.startsWith("MV")) {
							linea = linea.substring(4);
							String[] palabras = linea.split(" ");
							
							int i = 0; // El titulo
							while (palabras[i].charAt(0) != '(') {
								titulo = titulo + palabras[i] + ' ';
								i++;
								titulo = titulo.replaceAll("[\"]|&","");
							}
							
							// El año
							palabras[i] = palabras[i].substring(1, 5);
							año = Integer.parseInt(palabras[i]);
						}
						
						// La descripción
						while (linea.startsWith("PL")) {
							linea = linea.substring(4);
							descripcion = descripcion + " " + linea;
							linea = br.readLine();
						}
						
						// El autor
						if (linea.startsWith("BY"))	{
							autor = linea.substring(4);
							unaPelicula = false;
						}
					}
					System.out.println((peliculasAgregadas+1) + " INFO: Agregando ("+año+") "+titulo+"(por: "+autor+") a la colección");
					coleccion.agregarPelicula(new Pelicula(titulo, año, descripcion, autor));
					peliculasAgregadas++;
				}
			} catch (Exception e) {
				System.out.println("ERROR: No fue posible agregar la película: "+e.getMessage());
				errores++;
			}
		}
	}
	/**
	 * Entrega el número de peliculas agregadas
	 */
	public int darNumeroPeliculasAgregadas() {
		return peliculasAgregadas;
	}
	/**
	 * Entrega el número de errores de importación
	 */
	public int darNumeroErrores() {
		return errores;
	}
	/**
	 * Entrega el número de peliculas encontradas
	 */
	public int darNumeroPeliculasEncontradas() {
		return peliculasEncontradas;
	}
}
