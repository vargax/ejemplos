package co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.dispositivos;

/**
 *
 * @author c.vargas124
 */
public interface IDispositivo {
    public String getNombre();
    public String recuperarDato() throws ConexionException;
    public String[] parserDato(String dato);
}
