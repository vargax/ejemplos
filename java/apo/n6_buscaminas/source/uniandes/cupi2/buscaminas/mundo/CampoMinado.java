/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 
 * Ejercicio: n6_buscaminas 
 * Autor Inicial: Mario Sánchez - 15/07/2005 
 * Autor: Milena Vela - 21/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.buscaminas.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * Esta clase representa el campo minado y conoce las casillas que ha jugado el usuario.
 */
public class CampoMinado
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que el juego debe continuar. <br>
     * No se han destapado todas las casillas que no tienen bomba y no se ha destapado una casilla con bomba
     */
    public static final int CONTINUA_JUEGO = 0;

    /**
     * Indica que el juego fue ganado porque se destaparon todas las casillas que no están minadas.
     */
    public static final int JUEGO_GANADO = 1;

    /**
     * Indica que el juego fue perdido porque se destapó una casilla que estaba minada
     */
    public static final int JUEGO_PERDIDO = 2;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el campo minado. <br>
     * Cada elemento de la matriz es una casilla que puede estar VACIA o MINADA
     */
    private Casilla[][] casillasCampoMinado;

    /**
     * Es la matriz donde se marcan las casillas que se han jugado. <br>
     * Cada casilla puede estar TAPADA, DESTAPADA o MARCADA.
     */
    private Casilla[][] casillasJugadas;

    /**
     * Número de columnas del campo minado
     */
    private int columnas;

    /**
     * Número de filas del campo minado
     */
    private int filas;

    /**
     * Indica si ya el juego se terminó y el resultado
     */
    private int estadoJuego;

    /**
     * Es la hora en la que empezó el juego
     */
    private long tiempoInicio;

    /**
     * Es la hora en la que terminó el juego
     */
    private long tiempoFinal;

    /**
     * Es el número de minas que hay en el campo minado
     */
    private int numeroMinas;

    /**
     * Es el número de marcas que el jugador ha puesto
     */
    private int numeroMarcas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo juego de buscaminas con el tamaño del campo minado y el número de minas especificados en el archivo de entrada.<br>
     * La distribución de las minas se realiza de manera aleatoria.<br>
     * <b>post: </b> Se construyó un nuevo campo minado del tamaño y número de minas especificados en el archivo.<br>
     * Todas las casillas están tapadas y desmarcadas.<br>
     * La distribución de las minas se realizó de manera aleatoria.<br>
     * El tiempo de inicio se inicializó en cero.<br>
     * @param arch Es el archivo que contiene la información (filas, columnas y número de minas) del campo minado. arch != null.
     * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo.
     */
    public CampoMinado( File arch ) throws Exception
    {
        Properties datos = cargarInfoJuego( arch );
        String strNumeroMinas = datos.getProperty( "buscaminas.minas" );
        numeroMinas = Integer.parseInt( strNumeroMinas );
        String strFilas = datos.getProperty( "buscaminas.filas" );
        filas = Integer.parseInt( strFilas );
        String strColumnas = datos.getProperty( "buscaminas.columnas" );
        columnas = Integer.parseInt( strColumnas );

        inicializarJuego( );
    }

    /**
     * Carga la información del campo minado en un objeto de tipo Properties.
     * @param arch Es el archivo que contiene la información del campo minado
     * @return un objeto de la clase Properties con la información del archivo.
     * @throws Exception Se lanza esta excepción si el archivo no existe o si el formato del archivo no es válido y no puede ser leído.
     */
    private Properties cargarInfoJuego( File arch ) throws Exception
    {
        Properties datos = new Properties( );
        FileInputStream in = new FileInputStream( arch );
        try
        {
            datos.load( in );
            in.close( );
        }
        catch( Exception e )
        {
            throw new Exception( "Formato inválido" );
        }
        return datos;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el número de filas del campo minado
     * @return filas
     */
    public int darFilas( )
    {
        return filas;
    }

    /**
     * Retorna el número de columnas del campo minado
     * @return columnas
     */
    public int darColumnas( )
    {
        return columnas;
    }
    
    /**
     * Retorna el número de minas del campo minado
     * @return minas
     */
    public int darNumeroMinas( )
    {
        return numeroMinas;
    }


    /**
     * Devuelve la casilla en la posición especificada.
     * @param fila Fila de la casilla.
     * @param columna Columna de la casilla.
     * @return Casilla en la posición especificada (fila, columna).
     */
    public Casilla darCasilla( int fila, int columna )
    {
        return casillasCampoMinado[ fila ][ columna ];
    }

    /**
     * Retorna el número de minas que quedan por descubrir según el número de marcas que se han puesto.
     * @return numeroMinas Número de minas por descubrir
     */
    public int darNumeroMinasRestantes( )
    {
        return numeroMinas - numeroMarcas;
    }

    /**
     * Retorna una representación matricial de las casillas del campo minado <br>
     * Si el juego no ha terminado se devuelven las casillas con los siguientes estados: <br>
     * Las casillas que están tapadas se muestran tapadas a menos que estén marcadas. <br>
     * De las casillas que están destapadas se muestra el número de minas cercanas. Si el juego ya terminó se devuelven las casillas con los siguientes estados: <br>
     * Si el jugador ganó, entonces se muestran las casillas igual que como se mostrarían si el juego no hubiera terminado. <br>
     * Si el juego terminó y el jugador perdió entonces las casillas tapadas sin marcar se muestran tapadas a menos que tengan una bomba, en cuyo caso esta se debe mostrar;
     * las casillas marcadas que tenían una bomba se muestran marcadas, mientras que las marcadas que no tenían bomba se muestran como marcadas equivocadas; para las casillas
     * destapadas se muestra el número de bombas cercanas excepto para la casilla que contenía la bomba, en la cual se muestra la bomba estallada.
     * @return Matriz de objetos de tipo Casilla
     */
    public Casilla[][] darCasillas( )
    {
        if( estadoJuego == CONTINUA_JUEGO )
            return darCasillasEnJuego( );
        else
            return darCasillasFinalJuego( );
    }

    /**
     * Retorna las casillas como se deben ver durante el juego. <br>
     * Las posiciones que están tapadas se muestran tapadas, a menos que estén marcadas. <br>
     * De las posiciones que están destapadas se muestra el número de minas cercanas.
     * @return Matriz de objetos de tipo Casilla
     */
    private Casilla[][] darCasillasEnJuego( )
    {
        Casilla[][] casillas = new Casilla[filas][columnas];

        for( int i = 0; i < filas; i++ )
        {
            for( int j = 0; j < columnas; j++ )
            {
                if( casillasJugadas[ i ][ j ].darEstado( ) == Casilla.TAPADA )
                    casillas[ i ][ j ] = new Casilla( Casilla.TAPADA, i, j );
                else if( casillasJugadas[ i ][ j ].darEstado( ) == Casilla.MARCADA )
                    casillas[ i ][ j ] = new Casilla( Casilla.MARCADA, i, j );
                else
                // Casilla.DESTAPADA
                {
                    int minasCercanas = calcularMinasCercanas( i, j );
                    casillas[ i ][ j ] = darCasillaSegunMinas( minasCercanas, i, j );
                }
            }
        }
        return casillas;
    }

    /**
     * Retorna las casillas como se deben ver al finalizar el juego <br>
     * Si el juego terminó y el jugador ganó, entonces se muestran las casillas igual que como se mostrarían si el juego no hubiera terminado. <br>
     * Si el juego terminó y el jugador perdió entonces las casillas tapadas sin marcar se muestran tapadas a menos que tengan una bomba, en cuyo caso esta se debe mostrar;
     * las casillas marcadas marcadas que tenían una bomba se muestran marcadas, mientras que las marcadas que no tenían bomba se muestran como marcadas equivocadas; para las
     * casillas destapadas se muestra el número de bombas cercanas menos para la casilla que contenía la bomba, en la cual se muestra la bomba estallada.
     * @return Matriz de objetos de tipo Casilla
     */
    private Casilla[][] darCasillasFinalJuego( )
    {
        Casilla[][] casillas = new Casilla[filas][columnas];
        for( int i = 0; i < filas; i++ )
        {
            for( int j = 0; j < columnas; j++ )
            {
                if( casillasJugadas[ i ][ j ].darEstado( ) == Casilla.TAPADA )
                {
                    if( casillasCampoMinado[ i ][ j ].darEstado( ) == Casilla.MINADA )
                        casillas[ i ][ j ] = new Casilla( Casilla.MINADA, i, j );
                    else
                        casillas[ i ][ j ] = new Casilla( Casilla.TAPADA, i, j );
                }
                else if( casillasJugadas[ i ][ j ].darEstado( ) == Casilla.MARCADA )
                {
                    if( casillasCampoMinado[ i ][ j ].darEstado( ) == Casilla.MINADA )
                        casillas[ i ][ j ] = new Casilla( Casilla.MARCADA, i, j );
                    else
                        casillas[ i ][ j ] = new Casilla( Casilla.MARCADA_EQUIVOCADA, i, j );
                }
                else
                {
                    if( casillasCampoMinado[ i ][ j ].darEstado( ) == Casilla.MINADA )
                    {
                        casillas[ i ][ j ] = new Casilla( Casilla.BOMBA_ESTALLADA, i, j );
                    }
                    else
                    {
                        int minasCercanas = calcularMinasCercanas( i, j );
                        casillas[ i ][ j ] = darCasillaSegunMinas( minasCercanas, i, j );
                    }
                }
            }
        }
        return casillas;
    }

    /**
     * Este método sirve para obtener el tipo correcto de una casilla según el número de minas que haya cerca de ella.
     * @param nMinas Es el número de minas que hay cerca de la casilla. 0 <= nMinas <= 8
     * @param laFila Número de fila de la casilla.
     * @param laColumna Número de columna de la casilla.
     * @return Retorna una casilla del tipo correspondiente al número de minas cercanas
     */
    public Casilla darCasillaSegunMinas( int nMinas, int laFila, int laColumna )
    {
        int tipo = Casilla.CERCA_0;
        switch( nMinas )
        {
            case 0:
                tipo = Casilla.CERCA_0;
                break;
            case 1:
                tipo = Casilla.CERCA_1;
                break;
            case 2:
                tipo = Casilla.CERCA_2;
                break;
            case 3:
                tipo = Casilla.CERCA_3;
                break;
            case 4:
                tipo = Casilla.CERCA_4;
                break;
            case 5:
                tipo = Casilla.CERCA_5;
                break;
            case 6:
                tipo = Casilla.CERCA_6;
                break;
            case 7:
                tipo = Casilla.CERCA_7;
                break;
            case 8:
                tipo = Casilla.CERCA_8;
                break;
        }
        return new Casilla( tipo, laFila, laColumna );
    }

    /**
     * Este método sirve para saber cuantas minas hay alrededor de una casilla
     * @param i Coordenada x de la casilla
     * @param j Coordenada y de la casilla
     * @return El número de minas que hay cerca de la casilla. 0<= número <= 8.
     */
    private int calcularMinasCercanas( int i, int j )
    {
        int cercanas = 0;
        int delta = 1;
        for( int r = i - delta; r <= i + delta; r++ )
        {
            for( int s = j - delta; s <= j + delta; s++ )
            {
                if( r >= 0 && r < filas && s >= 0 && s < columnas )
                {
                    if( casillasCampoMinado[ r ][ s ].darEstado( ) == Casilla.MINADA )
                        cercanas++;
                }
            }
        }
        return cercanas;
    }

    /**
     * Destapa una casilla del campo minado. <br>
     * Si la casilla contenía una bomba entonces la bomba estalla y termina el juego. <br>
     * Si la casilla no tenía una bomba, pero estaba cerca de una, entonces la casilla correspondiente ahora deberá indicar el número de bombas cercanas. <br>
     * <b>post: </b> La casilla está destapada y se actualizó el estado del juego: número de minas restantes y el tiempo de juego.
     * @param x La coordenada x de la casilla que se quiere destapar
     * @param y La coordenada y de la casilla que se quiere destapar
     * @return Retorna un código indicando el resultado de la jugada. <br>
     *         El resultado puede ser CONTINUA_JUEGO, si con la jugada el juego no termina. <br>
     *         Puede ser también JUEGO_GANADO o JUEGO_PERDIDO si con la jugada el juego termina.
     * @throws Exception Si la casilla ya está destapada o el juego ya terminó.
     */
    public int destapar( int x, int y ) throws Exception
    {

        if( casillasJugadas[ x ][ y ].darEstado( ) == Casilla.DESTAPADA )
            throw new Exception( "La casilla indicada ya está destapada" );

        if( estadoJuego != CONTINUA_JUEGO )
            throw new Exception( "El juego ya terminó y no pueden realizarse más jugadas" );

        int resultadoJugada = CONTINUA_JUEGO;

        if( calcularMinasCercanas( x, y ) == 0 )
            expandir( x, y );

        casillasJugadas[ x ][ y ].cambiarEstado( Casilla.DESTAPADA );
        if( casillasCampoMinado[ x ][ y ].darEstado( ) == Casilla.MINADA )
        {
            resultadoJugada = JUEGO_PERDIDO;
        }
        else
        {
            resultadoJugada = verificarFinal( );
        }
        estadoJuego = resultadoJugada;

        calcularTiempoDeJuego( );

        return resultadoJugada;
    }

    /**
     * Calcula el tiempo actual cada vez que se hace una jugada. Para ello utiliza un objeto de la clase Date.
     * 
     */
    private void calcularTiempoDeJuego( )
    {
        if( tiempoInicio == 0 )
        {
            tiempoInicio = ( new Date( ) ).getTime( );
        }
        tiempoFinal = ( new Date( ) ).getTime( );
    }

    /**
     * Este método hace que todas las casillas que están cercanas a una casilla sin minas cercanas queden destapadas. <br>
     * Una casilla está cercana a otra si entre ellas es posible establecer un camino que pasa únicamente por casillas sin minas cercanas.
     * @param fila de una casilla sin minas cercanas
     * @param columna de una casilla sin minas cercanas
     */
    private void expandir( int fila, int columna )
    {
        ArrayList cercanas = new ArrayList( );
        Casilla c = darCasilla( fila, columna );
        cercanas.add( c );

        while( !cercanas.isEmpty( ) )
        {
            Casilla d = ( Casilla )cercanas.remove( 0 );
            int i = d.darFila( );
            int j = d.darColumna( );
            casillasJugadas[ i ][ j ].cambiarEstado( Casilla.DESTAPADA );

            int delta = 1;
            for( int r = i - delta; r <= i + delta; r++ )
            {
                for( int s = j - delta; s <= j + delta; s++ )
                {
                    if( r >= 0 && r < filas && s >= 0 && s < columnas && ( r != i || s != j ) )
                    {
                        if( casillasJugadas[ r ][ s ].darEstado( ) == Casilla.TAPADA )
                        {
                            casillasJugadas[ r ][ s ].cambiarEstado( Casilla.DESTAPADA );

                            if( calcularMinasCercanas( r, s ) == 0 )
                            {
                                d = casillasJugadas[ r ][ s ];
                                cercanas.add( d );
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Verifica si ya el juego terminó. <br>
     * El juego termina cuando todas las casillas sin minas están destapadas o cuando hay una casilla minada que está destapada.
     * @return Retorna un código indicando el estado del juego. <br>
     *         El resultado puede ser CONTINUA_JUEGO, si el juego no ha terminado. <br>
     *         Puede ser también JUEGO_GANADO o JUEGO_PERDIDO si el juego ya terminó y el jugador ganó o perdió, respectivamente.
     */
    private int verificarFinal( )
    {
        boolean sigueJuego = false;

        for( int i = 0; i < filas && !sigueJuego; i++ )
        {
            for( int j = 0; j < columnas && !sigueJuego; j++ )
            {
                if( casillasCampoMinado[ i ][ j ].darEstado( ) == Casilla.VACIA && ( casillasJugadas[ i ][ j ].darEstado( ) == Casilla.TAPADA || casillasJugadas[ i ][ j ].darEstado( ) == Casilla.MARCADA ) )
                {
                    sigueJuego = true;
                }
            }
        }

        int estadoJuegoi = CONTINUA_JUEGO;

        if( !sigueJuego )
        {
            estadoJuegoi = JUEGO_GANADO;

            boolean finJuego = false;
            for( int i = 0; i < filas && !finJuego; i++ )
            {
                for( int j = 0; j < columnas && !finJuego; j++ )
                {
                    if( casillasCampoMinado[ i ][ j ].darEstado( ) == Casilla.MINADA && casillasJugadas[ i ][ j ].darEstado( ) == Casilla.DESTAPADA )
                    {
                        finJuego = true; // Estalló una bomba
                        estadoJuegoi = JUEGO_PERDIDO;
                    }
                }
            }
        }

        return estadoJuegoi;
    }

    /**
     * Sirve para poner una marca en una casilla <br>
     * <b>pre: </b> La casilla no está marcada ni está destapada y el juego no ha terminado <br>
     * <b>post: </b> Se marcó la casilla
     * @param x La coordenada x de la casilla que se quiere marcar
     * @param y La coordenada y de la casilla que se quiere marcar
     * @throws Exception Si la casilla ya está marcada o está destapada o el juego ya terminó.
     */
    public void marcar( int x, int y ) throws Exception
    {
        if( casillasJugadas[ x ][ y ].darEstado( ) == Casilla.MARCADA || casillasJugadas[ x ][ y ].darEstado( ) == Casilla.DESTAPADA )
            throw new Exception( "La casilla indicada ya está marcada o destapada" );

        if( estadoJuego != CONTINUA_JUEGO )
            throw new Exception( "El juego ya terminó y no pueden realizarse más jugadas" );

        casillasJugadas[ x ][ y ].cambiarEstado( Casilla.MARCADA );
        numeroMarcas++;
    }

    /**
     * Quita una marca de una casilla. <br>
     * <b>post: </b> Se desmarcó la casilla.
     * @param x La coordenada x de la casilla que se quiere desmarcar
     * @param y La coordenada y de la casilla que se quiere desmarcar
     * @throws Exception Si la casilla no está marcada o el juego ya terminó.
     */
    public void desmarcar( int x, int y ) throws Exception
    {
        if( casillasJugadas[ x ][ y ].darEstado( ) == Casilla.TAPADA || casillasJugadas[ x ][ y ].darEstado( ) == Casilla.DESTAPADA )
            throw new Exception( "La casilla indicada no está marcada" );

        if( estadoJuego != CONTINUA_JUEGO )
            throw new Exception( "El juego ya terminó y no pueden realizarse más jugadas" );

        casillasJugadas[ x ][ y ].cambiarEstado( Casilla.TAPADA );
        numeroMarcas--;
    }

    /**
     * Crea un nuevo campo minado de juego, tapando todas las casillas y redistribuyendo las minas. El tiempo de inicio del juego se inicializa en 0 <br>
     * <b>post: </b>El campo minado quedó inicializado, sin marcas, con todas las casillas tapadas, con las minas distribuidas de forma aleatoria y con el tiempo de inicio en
     * 0. El número de minas destapadas también se actualizó en cero.
     */
    public void inicializarJuego( )
    {
        ArrayList listaPosiciones = new ArrayList( );
        // Se construye un nuevo campo minado
        casillasCampoMinado = new Casilla[filas][columnas];
        casillasJugadas = new Casilla[filas][columnas];

        // recorre el campo minado inicializando las casillas

        for( int i = 0; i < filas; i++ )
        {
            for( int j = 0; j < columnas; j++ )
            {
                casillasCampoMinado[ i ][ j ] = new Casilla( Casilla.VACIA, i, j );
                casillasJugadas[ i ][ j ] = new Casilla( Casilla.TAPADA, i, j );
                Casilla c = darCasilla( i, j );
                listaPosiciones.add( c );

            }
        }

        // Se distribuyen de manera aleatoria las minas en las casillas del campo minado
        int numeroMinasRestantes = numeroMinas;

        while( numeroMinasRestantes > 0 )
        {
            // Se genera aleatoriamente la fila
            int x = generarNumeroAleatorioEnRango( filas );

            // Se genera aleatoriamente la columna
            int y = generarNumeroAleatorioEnRango( columnas );

            Casilla[][] campo = darCasillas( );
            Casilla d = campo[ x ][ y ];
            listaPosiciones.remove( d );

            casillasCampoMinado[ x ][ y ].cambiarEstado( Casilla.MINADA );

            numeroMinasRestantes--;
        }

        // Se inicializa el estado del juego
        estadoJuego = CONTINUA_JUEGO;
        // Se inicializa el tiempo de inicio en cero
        tiempoInicio = 0;

    }

    /**
     * Genera un número entero aleatorio entre 0 y tamRango - 1
     * @param tamRango Tamaño del rango
     * @return Número entero entre 0 y tamRango - 1
     */
    public int generarNumeroAleatorioEnRango( int tamRango )
    {
        return ( int ) ( Math.random( ) * tamRango );
    }

    /**
     * Retorna el tiempo total en segundos que fué necesario para resolver el juego
     * @return tiempo
     */
    public int darTiempoTotal( )
    {
        if( tiempoInicio == 0 )
            return 0;
        else
            return ( int ) ( ( tiempoFinal - tiempoInicio ) / 1000 );
    }

    /**
     * Indica si el juego ya está terminado e informa el resultado
     * @return Retorna un código indicando el estado del juego. <br>
     *         El resultado puede ser CONTINUA_JUEGO, si el juego no ha terminado. <br>
     *         Puede ser también JUEGO_GANADO o JUEGO_PERDIDO si el juego ya terminó y el jugador ganó o perdió, respectivamente.
     */
    public int darEstadoJuego( )
    {
        return estadoJuego;
    }

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Método 1 de extensión al ejemplo
     * @return respuesta
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método 2 de extensión al ejemplo
     * @return respuesta
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}
