package uniandes.cupi2.bodyCupi2.excepciones;

import uniandes.cupi2.bodyCupi2.mundo.Fecha;

/**
 * Excepción en caso que para un registro de tiempo ya se haya registrado una hora de salida
 * y se intente agregar por segunda vez
 */
public class HoraSalidaExistenteParaRegistroException extends Exception
{

	// -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Construye una excepción con un mensaje explicando que el registro de tiempo ya existe
     * @param fechaRegistro La fecha del registro que ya existe
     */
    public HoraSalidaExistenteParaRegistroException( Fecha fechaRegistro)
    {
        super( "Ya existe una hora de salida para el usuario en la fecha: " + fechaRegistro.darFechaConFormato( ));
    }
	
}
