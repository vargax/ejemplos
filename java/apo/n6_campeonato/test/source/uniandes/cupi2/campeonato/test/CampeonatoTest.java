/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_campeonato 
 * Autor: Mario Sánchez - 21/07/2005 
 * Autor: J. Villalobos - 28/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.campeonato.test;

import java.io.*;
import java.util.*;

import junit.framework.*;
import uniandes.cupi2.campeonato.mundo.*;

/**
 * Esta clase sirve para probar la clase campeonato
 */
public class CampeonatoTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es el campeonato utilizado para las pruebas
     */
    private Campeonato campeonato;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Crea un campeonato sin equipos
     */
    private void setupEscenario1( )
    {
        try
        {
            campeonato = new Campeonato( new File( "./test/data/equiposVacio.prop" ) );
        }
        catch( Exception e )
        {
            fail( "No debería fallar: " + e.getMessage( ) );
        }
    }

    /**
     * Crea un campeonato inicializado con varios equipos
     */
    private void setupEscenario2( )
    {
        try
        {
            campeonato = new Campeonato( new File( "./test/data/equipos.prop" ) );

            campeonato.registrarResultado( 0, 1, 3, 1 );
            campeonato.registrarResultado( 0, 2, 2, 0 );
            campeonato.registrarResultado( 1, 2, 1, 1 );
            campeonato.registrarResultado( 2, 3, 1, 0 );
            campeonato.registrarResultado( 4, 3, 1, 0 );
        }
        catch( Exception e )
        {
            fail( "No debería fallar: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica el método darEquipos en un campeonato vacío
     */
    public void testDarEquipos1( )
    {
        setupEscenario1( );
        assertTrue( "El campeonato aún no debe tener equipos", campeonato.darNumeroEquipos( ) == 0 );
    }

    /**
     * Verifica el método darEquipos en un campeonato con equipos
     */
    public void testDarEquipos2( )
    {
        setupEscenario2( );
        assertEquals( "El campeonato debe tener 5 equipos", 5, campeonato.darNumeroEquipos( ) );
    }

    /**
     * Verifica el método darTablaGoles en un campeonato con goles
     */
    public void testDarTablaGoles( )
    {
        setupEscenario2( );

        assertEquals( "La tabla de goles ya debe tener 5 equipos", 5, campeonato.darNumeroEquipos( ) );

        assertEquals( "El número de goles es incorrecto", 3, campeonato.darGolesMarcados( 0, 1 ) );
        assertEquals( "El número de goles es incorrecto", 1, campeonato.darGolesMarcados( 1, 0 ) );
    }

    /**
     * Verifica la tabla de posiciones de un campeonato ya iniciado
     */
    public void testDarTablaPosiciones1( )
    {
        setupEscenario2( );
        ArrayList tablaPosiciones = new ArrayList( );
        for( int i = 0; i < campeonato.darNumeroEquipos( ); i++ )
        {
            // La información de cada equipo la almacena en un arreglo de 8 cadenas de caracteres
            Equipo e = campeonato.darEquipo( i );
            String[] calculosEquipo = new String[8];
            calculosEquipo[ 0 ] = e.darNombre( );
            calculosEquipo[ 1 ] = "" + campeonato.darTotalPuntos( i );
            calculosEquipo[ 2 ] = "" + campeonato.darPartidosJugados( i );
            calculosEquipo[ 3 ] = "" + campeonato.darPartidosGanados( i );
            calculosEquipo[ 4 ] = "" + campeonato.darPartidosEmpatados( i );
            calculosEquipo[ 5 ] = "" + campeonato.darPartidosPerdidos( i );
            calculosEquipo[ 6 ] = "" + campeonato.darGolesAFavor( i );
            calculosEquipo[ 7 ] = "" + campeonato.darGolesEnContra( i );
            tablaPosiciones.add( calculosEquipo );
        }
        assertEquals( "La tabla de posiciones debe tener 5 equipos", 5, tablaPosiciones.size( ) );

        String[] datos1 = ( String[] )tablaPosiciones.get( 0 );
        assertEquals( "El nombre del primer equipo está equivocado", "A.C. Milan", datos1[ 0 ] );
        assertEquals( "El número de puntos del primer equipo está equivocado", "6", datos1[ 1 ] );
        assertEquals( "El número de partidos jugados del primer equipo está equivocado", "2", datos1[ 2 ] );
        assertEquals( "El número de partidos ganados del primer equipo está equivocado", "2", datos1[ 3 ] );
        assertEquals( "El número de partidos empatados del primer equipo está equivocado", "0", datos1[ 4 ] );
        assertEquals( "El número de partidos perdidos del primer equipo está equivocado", "0", datos1[ 5 ] );
        assertEquals( "El número de goles a favor del primer equipo está equivocado", "5", datos1[ 6 ] );
        assertEquals( "El número de goles en contra del primer equipo está equivocado", "1", datos1[ 7 ] );
    }

    /**
     * Verifica la tabla de posiciones de un campeonato ya iniciado
     */
    public void testDarTablaPosiciones2( )
    {
        setupEscenario2( );
        ArrayList tablaPosiciones = new ArrayList( );
        for( int i = 0; i < campeonato.darNumeroEquipos( ); i++ )
        {
            // La información de cada equipo la almacena en un arreglo de 8 cadenas de caracteres
            Equipo e = campeonato.darEquipo( i );
            String[] calculosEquipo = new String[8];
            calculosEquipo[ 0 ] = e.darNombre( );
            calculosEquipo[ 1 ] = "" + campeonato.darTotalPuntos( i );
            calculosEquipo[ 2 ] = "" + campeonato.darPartidosJugados( i );
            calculosEquipo[ 3 ] = "" + campeonato.darPartidosGanados( i );
            calculosEquipo[ 4 ] = "" + campeonato.darPartidosEmpatados( i );
            calculosEquipo[ 5 ] = "" + campeonato.darPartidosPerdidos( i );
            calculosEquipo[ 6 ] = "" + campeonato.darGolesAFavor( i );
            calculosEquipo[ 7 ] = "" + campeonato.darGolesEnContra( i );
            tablaPosiciones.add( calculosEquipo );
        }
        assertEquals( "La tabla de posiciones debe tener 5 equipos", 5, tablaPosiciones.size( ) );
        String[] datos1 = ( String[] )tablaPosiciones.get( 3 );
        assertEquals( "El nombre del primer equipo está equivocado", "Roma", datos1[ 0 ] );
        assertEquals( "El número de puntos del cuarto equipo está equivocado", "0", datos1[ 1 ] );
        assertEquals( "El número de partidos jugados del cuarto equipo está equivocado", "2", datos1[ 2 ] );
        assertEquals( "El número de partidos ganados del cuarto equipo está equivocado", "0", datos1[ 3 ] );
        assertEquals( "El número de partidos empatados del cuarto equipo está equivocado", "0", datos1[ 4 ] );
        assertEquals( "El número de partidos perdidos del cuarto equipo está equivocado", "2", datos1[ 5 ] );
        assertEquals( "El número de goles a favor del cuarto equipo está equivocado", "0", datos1[ 6 ] );
        assertEquals( "El número de goles en contra del cuarto equipo está equivocado", "2", datos1[ 7 ] );
    }

    /**
     * Registrar un resultado sin problemas
     */
    public void testRegistrarResultado1( )
    {
        setupEscenario2( );

        try
        {
            campeonato.registrarResultado( 0, 3, 4, 0 );
        }
        catch( Exception e )
        {
            fail( "No debería haber problemas registrando este resultado" );
        }
        ArrayList tablaPosiciones = new ArrayList( );
        for( int i = 0; i < campeonato.darNumeroEquipos( ); i++ )
        {
            // La información de cada equipo la almacena en un arreglo de 8 cadenas de caracteres
            Equipo e = campeonato.darEquipo( i );
            String[] calculosEquipo = new String[8];
            calculosEquipo[ 0 ] = e.darNombre( );
            calculosEquipo[ 1 ] = "" + campeonato.darTotalPuntos( i );
            calculosEquipo[ 2 ] = "" + campeonato.darPartidosJugados( i );
            calculosEquipo[ 3 ] = "" + campeonato.darPartidosGanados( i );
            calculosEquipo[ 4 ] = "" + campeonato.darPartidosEmpatados( i );
            calculosEquipo[ 5 ] = "" + campeonato.darPartidosPerdidos( i );
            calculosEquipo[ 6 ] = "" + campeonato.darGolesAFavor( i );
            calculosEquipo[ 7 ] = "" + campeonato.darGolesEnContra( i );
            tablaPosiciones.add( calculosEquipo );
        }
        assertEquals( "La tabla de posiciones debe tener 5 equipos", 5, tablaPosiciones.size( ) );
        String[] datos1 = ( String[] )tablaPosiciones.get( 0 );
        assertEquals( "El nombre del primer equipo está equivocado", "A.C. Milan", datos1[ 0 ] );
        assertEquals( "El número de puntos del primer equipo está equivocado", "9", datos1[ 1 ] );
        assertEquals( "El número de partidos jugados del primer equipo está equivocado", "3", datos1[ 2 ] );
        assertEquals( "El número de partidos ganados del primer equipo está equivocado", "3", datos1[ 3 ] );
        assertEquals( "El número de partidos empatados del primer equipo está equivocado", "0", datos1[ 4 ] );
        assertEquals( "El número de partidos perdidos del primer equipo está equivocado", "0", datos1[ 5 ] );
        assertEquals( "El número de goles a favor del primer equipo está equivocado", "9", datos1[ 6 ] );
        assertEquals( "El número de goles en contra del primer equipo está equivocado", "1", datos1[ 7 ] );

        String[] datos5 = ( String[] )tablaPosiciones.get( 3 );
        assertEquals( "El nombre del cuarto equipo está equivocado", "Roma", datos5[ 0 ] );
        assertEquals( "El número de puntos del cuarto equipo está equivocado", "0", datos5[ 1 ] );
        assertEquals( "El número de partidos jugados del cuarto equipo está equivocado", "3", datos5[ 2 ] );
        assertEquals( "El número de partidos ganados del cuarto equipo está equivocado", "0", datos5[ 3 ] );
        assertEquals( "El número de partidos empatados del cuarto equipo está equivocado", "0", datos5[ 4 ] );
        assertEquals( "El número de partidos perdidos del cuarto equipo está equivocado", "3", datos5[ 5 ] );
        assertEquals( "El número de goles a favor del cuarto equipo está equivocado", "0", datos5[ 6 ] );
        assertEquals( "El número de goles en contra del cuarto equipo está equivocado", "6", datos5[ 7 ] );
    }

    /**
     * Intenta registrar nuevamente el resultado de un partido ya jugado
     */
    public void testRegistrarResultado2( )
    {
        setupEscenario2( );

        try
        {
            campeonato.registrarResultado( 0, 1, 2, 1 );
            fail( "Ese partido ya fue jugado" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepción era esperada", true );
        }
    }

    /**
     * Intenta registrar el resultado de un partido con equipos inválidos
     */
    public void testRegistrarResultado3( )
    {
        setupEscenario2( );

        try
        {
            campeonato.registrarResultado( 0, 5, 2, 1 );
            fail( "El equipo 5 no existe" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepción era esperada", true );
        }
    }
}
