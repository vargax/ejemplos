/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n14_centralMensajes
 * Autor: Pablo Marquez - 13 Jun, 2007
 * Autor: Juan Erasmo Gómez - 6 Ago, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralMensajes.test;

import junit.framework.TestCase;
import uniandes.cupi2.centralMensajes.mundo.Central;
import uniandes.cupi2.centralMensajes.mundo.ICentral;
import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Clase de pruebas de la clase uniandes.cupi2.centralMensajes.mundo.implementacion2.Central.
 */
public class CentralTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia de la central de mensajes para probar.
     */
    private ICentral central;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Instanciación de la central
     */
    public void setupEscenario1( )
    {
        try
        {
            central = new Central();
            /*
             * central.nuevoCliente( "101838" ); central.nuevoCliente( "101916" ); central.nuevoCliente( "102007" ); central.nuevoCliente( "102020" ); central.nuevoCliente(
             * "102035" ); central.nuevoCliente( "102044" ); central.nuevoCliente( "102058" );
             */
        }
        catch( Exception e )
        {
            fail( "Problemas instanciando la central..." );
        }
    }

    /**
     * Prueba que la central se instancie correctamente
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertNotNull( "La central no debería ser nula", central );
    }

    /**
     * Prueba que la central haya cargado correctamente los clientes y que lo retorne sin problema.
     */
    public void testDarClientes( )
    {
        setupEscenario1( );
        Iterador<String> clientes = central.darClientes( );
        assertNotNull( "La lista de clientes no debería ser nulo", clientes );
        assertEquals( "El cliente debe iniciar con 7 clientes", 7, contarIterador( clientes ) );
    }

    /**
     * Prueba que se envíe un mensaje de un usuario a otro correctamente.
     */
    public void testEnviarMensaje( )
    {
        setupEscenario1( );
        String mensaje = "esto_es un mensaje!";
        String destinatario = "Fernando";
        String remitente = "Julio";
        String encriptacion = "Negación";

        central.enviarMensaje( remitente, destinatario, mensaje, encriptacion );
        assertEquals( "La llave le ha debido llegar al destinatario", 1, contarIterador( central.darLlavesMensajes( destinatario ) ) );
    }

    /**
     * Prueba que un cliente recupere un mensaje correctamente.
     */
    public void testRecuperarMensaje( )
    {
        setupEscenario1( );
        String mensaje = "esto_es un mensaje!";
        String destinatario = "Fernando";
        String remitente = "Julio";
        String encriptacion = "Negación";

        central.enviarMensaje( remitente, destinatario, mensaje, encriptacion );

        String llaveMensaje = ( String )central.darLlavesMensajes( destinatario ).darSiguiente( );
        String mensajeRecuperado = central.recuperarMensaje( destinatario, llaveMensaje, encriptacion );
        assertEquals( "Los mensajes deberían ser iguales", mensaje, mensajeRecuperado );
    }

    /**
     * Prueba que se elimine un mensaje de la central y de la lista del destinatario correctamente.
     */
    public void testEliminarMensaje( )
    {
        setupEscenario1( );
        String mensaje = "esto_es un mensaje!";
        String destinatario = "Fernando";
        String remitente = "Julio";
        String encriptacion = "Negación";

        central.enviarMensaje( remitente, destinatario, mensaje, encriptacion );

        String llaveMensaje = ( String )central.darLlavesMensajes( destinatario ).darSiguiente( );
        central.eliminarMensaje( llaveMensaje, destinatario );
        assertEquals( "El cliente destinatario ha debido quedar sin mensajes", 0, contarIterador( central.darLlavesMensajes( destinatario ) ) );
    }

    // -----------------------------------------------------------------
    // Métodos Auxiliares
    // -----------------------------------------------------------------

    /**
     * Cuenta cuantos elementos vienen en un iterador.
     * @param iter Iterador del cual se quiere saber el número de elementos - iter != null.
     * @return El número de elementos contenidos en el iterador.
     */
    private int contarIterador( Iterador<?> iter )
    {
        int i;
        iter.reiniciar( );
        for( i = 0; iter.haySiguiente( ); i++ )
            iter.darSiguiente( );
        iter.reiniciar( );
        return i;
    }

}
