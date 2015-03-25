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

package uniandes.cupi2.cupiFinca.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import junit.framework.TestCase;
import uniandes.cupi2.cupiFinca.mundo.Cebolla;
import uniandes.cupi2.cupiFinca.mundo.Cultivable;
import uniandes.cupi2.cupiFinca.mundo.excepciones.PersistenciaException;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Cultivable
 */
public class CultivableTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Cultivable cultivo;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Producto Cultivable
     * 
     */
    private void setupEscenario1( )
    {
        try
        {
            cultivo = new Cebolla( 0, 0, 30, 0 );
        }
        catch( PersistenciaException e )
        {
            fail( "No debe haber error. " + e.getMessage( ) );
        }
    }

    /**
     * Prueba del constructor de un elemento recolectable
     */
    public void testCultivable( )
    {
        setupEscenario1( );

        // Código de la prueba de valores iniciales
        assertEquals( "La finca debe tener el nombre definido por defecto para el terreno", cultivo.darNombre( ), Cebolla.NOMBRE_CULTIVO );
        assertEquals( "La finca debe tener el costo definido por defecto para el terreno", cultivo.darCosto( ), Cebolla.COSTO_CULTIVO );
        assertEquals( "El tiempo inicial es el definido por parámetro", cultivo.darTiempo( ), 0 );
        assertTrue( "El estado de recolección del producto que se cultiva en el terreno debe ser verdadero", cultivo.esRecolectable( ) );
    }

    /**
     * Prueba que devuelve la ganancia dependiendo el estado en el que se encuentra.<br>
     * 1. En caso de que no esté en un estado previo al de recolección.<br>
     * 2. En caso de que esté en un estado de recolección<br>
     * 2. En caso de que no esté en un estado posterior al de recolección.
     */
    public void testDarGanancia( )
    {
        setupEscenario1( );
        
        // 1. En el caso de que no sea requerido cambiar del estado
        int ganancia = cultivo.darGanancia( );

        assertEquals( "La ganancia es invalida", ganancia, -1 );

        // 2. en caso de que se requiera el cambio de estado
        for( int i = cultivo.darTiempo( ); i <= Cultivable.TIEMPO_CRECIMIENTO; i++ )
        {
            cultivo.avanzarCiclo( );
        }
        ganancia = cultivo.darGanancia( );
        assertEquals( "La ganancia debe ser la asignada", ganancia, Cebolla.GANANCIA_CULTIVO );
        
        // 3. en caso de que se requiera el cambio de estado
        for( int i = cultivo.darTiempo( ); i <= Cultivable.TIEMPO_COSECHA; i++ )
        {
            cultivo.avanzarCiclo( );
        }
        ganancia = cultivo.darGanancia( );
        assertEquals( "La ganancia debe ser la asignada", ganancia, 0 );
    }
    
    /**
     * Prueba que el terreno cultivable se guarda con el formato correspondiente
     */
    public void testGuardarTerreno(){
        setupEscenario1( );
        
        File file = new File("./test/data/testCultivable.dat");
        PrintWriter pw;
        
        //1. Guarda el archivo
        try
        {
            pw = new PrintWriter( new FileOutputStream( file ), true );
            cultivo.guardarTerreno( pw );
            pw.close( );
        }
        catch( FileNotFoundException e )
        {
            fail("No debe haber error guardando el archivo.");
        }
        
        //2. Carga el archivo
        BufferedReader br;
        try
        {
            br = new BufferedReader( new FileReader( file ) );
            String linea = br.readLine( );
            if(linea == null){
                fail("La línea no debería estar vacía");
            }
            String[] terminos = linea.split( ";" );
            assertEquals( "El nombre del producto debe ser el mismo", terminos[0], cultivo.darNombre( ) );
            assertEquals( "El nombre del producto debe ser el mismo", Integer.parseInt( terminos[3] ), cultivo.darTiempo( ) );
        }
        catch( FileNotFoundException e )
        {
            fail("No debe haber error abriendo el archivo.");
        }
        catch( IOException e )
        {
            fail("No debe haber error leyendo el archivo.");
        }
    }
}