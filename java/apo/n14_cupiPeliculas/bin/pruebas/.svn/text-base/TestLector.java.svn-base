package pruebas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.stream.FileImageInputStream;

import mundo.Pelicula;
import estructurasDatos.ColeccionPeliculas;
import estructurasDatos.IColeccionPeliculas;
import estructurasDatos.IListaEncadenada;
import estructurasDatos.Lector;
import junit.framework.TestCase;

public class TestLector extends TestCase {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private IColeccionPeliculas coleccion;
	private Lector lector;
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Construye una nueva colección e inicializa el lector con ella
	 */
	public void setupEscenario1() {
		coleccion = new ColeccionPeliculas();
		lector = new Lector("./data/plot.list", coleccion);
	}
	/**
	 * Construye una nueva colección y carga las películas en ella
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void setupEscenario2() throws IOException, ClassNotFoundException {
//		File coleccionSerializado = new File("./data/cupiPeliculas.data");
//		if (coleccionSerializado.exists()) {
//			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(coleccionSerializado));
//			coleccion = (ColeccionPeliculas) ois.readObject();
//			ois.close();
//		} else {
			setupEscenario1();
			lector.cargarPeliculas();
//			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/cupiPeliculas.data"));
//			oos.writeObject(coleccion);
//			oos.close();
//		}
	}
	/**
	 * Carga el archivo de películas en la colección y lo serializa
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void testCargarPeliculas() throws FileNotFoundException, IOException {
		setupEscenario1();
		try {
			lector.cargarPeliculas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(0, lector.darNumeroErrores());
	}
	/**
	 * Realiza una búsqueda por título
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void testBuscarPeliculas() throws IOException, ClassNotFoundException {
		setupEscenario2();
		IListaEncadenada<Pelicula> resultado = coleccion.buscarPeliculas("zor");
		for(Pelicula pelicula : resultado) System.out.println(pelicula.darNombre());
	}
}
