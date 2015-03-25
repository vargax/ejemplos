package mundo;

import java.io.Serializable;

public class Pelicula implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * El nombre de la película
     */
    private String nombre;
    /**
     * El año de la película
     */
    private int año;
    /**
     * La descripción de la película
     */
    private String descripcion;
    /**
     * El subtítulo de la película
     */
    private String autor;
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    public Pelicula( String nombreP, int añoP, String descripcionP, String autorP )
    {
        nombre = nombreP;
        año = añoP;
        descripcion = descripcionP;
        autor = autorP;
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Devuelve el nombre de la película
     * @return El nombre de la película
     */
    public String darNombre( )
    {
        return nombre;
    }
    /**
     * Devuelve el año de la película
     * @return El año de la película
     */
    public int darAño( )
    {
        return año;
    }
    /**
     * Devuelve la descripción de la película
     * @return La descripción de la película
     */
    public String darDescripcion( )
    {
        return descripcion;
    }
    /**
     * Devuelve el autor de la descripción
     * @return El autor de la descripción
     */
    public String darAutor() {
    	return autor;
    }
    /**
     * Crea una cadena que describe la película
     * @return Cadena que describe la película
     */
    public String toString() {
    	return año+" "+nombre+" (por: "+autor+")";
    }
}