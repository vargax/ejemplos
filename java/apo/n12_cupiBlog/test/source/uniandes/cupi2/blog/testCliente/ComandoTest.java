/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.testCliente;

import junit.framework.TestCase;
import uniandes.cupi2.blog.cliente.mundo.Comando;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Comando estén correctamente implementados
 */
public class ComandoTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Comando comando;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva LatinChatServidor vacía
     *  
     */
    private void setupEscenario1( )
    {
        String nombreComando = "Comando";
        String[] parametrosComando = new String[]{"parametro1", "parametro2", "parametro3"};
        comando = new Comando( nombreComando, parametrosComando );
    }

    /**
     * Método que prueba que el Comando se haya creado correctamente
     */
    public void testComando( )
    {
        setupEscenario1( );
        assertEquals( "El nombre del comando debe ser el mismo", comando.darNombre( ), "Comando" );
        for(int i = 0; i < comando.darParametros( ).length; i++){
            assertEquals( "El valor del parámetro debe ser igual", comando.darParametros( )[i], "parametro"+(i+1));
        }
    }

}