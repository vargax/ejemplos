/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodríguez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.interfaz;

/**
 * Hilo de Ejecución que automatiza el ciclo de vida de los productos
 */
public class ThreadCicloVida extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCupiFinca principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el hilo de ejecución del manejo del ciclo con la ventana principal
     * @param ventana La ventana principal de la interfaz. ventana != null
     */
    public ThreadCicloVida( InterfazCupiFinca ventana )
    {
        principal = ventana;
    }

    /**
     * El método para las acciones del hilo de ejecución
     */
    public void run( )
    {
        try
        {
            while( true )
            {
                principal.simularEtapaCicloVidaTerrenos( );
                sleep( 1000 );
            }
        }
        catch( InterruptedException e )
        {
            e.printStackTrace( );
        }
    }
}
