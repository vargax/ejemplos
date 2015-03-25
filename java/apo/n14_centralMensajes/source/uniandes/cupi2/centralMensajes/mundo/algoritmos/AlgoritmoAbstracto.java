/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n14_centralMensajes
 * Autor: Yeisson Oviedo - 10 de Mayo, 2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralMensajes.mundo.algoritmos;

/**
 * Algoritmo abstracto
 *
 */
public abstract class AlgoritmoAbstracto implements IAlgoritmoEncriptacion {

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/* (non-Javadoc)
	 * @see uniandes.cupi2.centralMensajes.mundo.algoritmos.IAlgoritmoEncriptacion#codificarMensaje(java.lang.String)
	 */
	public String codificarMensaje(String mensaje) {
		byte[] m = darArregloByte(mensaje);
		for (int i = 0; i < m.length; i++)
			m[i] = codificar(m[i]);
		return darString(m);
	}

	
	/**
	 * @param b
	 * @return
	 */
	protected abstract byte codificar(byte b);
	
	/**
	 * @param b
	 * @return
	 */
	protected abstract byte decodificar(byte b);

	/* (non-Javadoc)
	 * @see uniandes.cupi2.centralMensajes.mundo.algoritmos.IAlgoritmoEncriptacion#decodificarMensaje(java.lang.String)
	 */
	public String decodificarMensaje(String mensaje) {
		byte[] m = darArregloByte(mensaje);
		for (int i = 0; i < m.length; i++)
			m[i] = decodificar(m[i]);
		return darString(m);
	}

	/**
	 * Convierte a arreglo de bytes
	 * @param mensaje
	 * @return Arreglo de bytes
	 */
	public byte[] darArregloByte(String mensaje)
	{
		byte[] res = new byte[mensaje.length()]; 
        for( int i = 0; i < mensaje.length( ); i++ )
        {
            res[i] = ( byte ) ( ( int )mensaje.charAt( i ) );
        }
        return res;
	}
	
	/**
	 * Convierte a mensaje
	 * @param mensaje
	 * @return String con el mensaje
	 */
	public String darString(byte[] mensaje){
		return new String(mensaje);
	}
}
