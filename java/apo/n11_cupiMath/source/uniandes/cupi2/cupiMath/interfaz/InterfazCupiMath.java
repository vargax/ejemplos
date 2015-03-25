/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_cupiMath
 * Autor: cupi2 - 15-mar-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiMath.interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import uniandes.cupi2.cupiMath.mundo.CupiMath;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazCupiMath extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private CupiMath cupiMath;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel con los números de la calculadora
     */
    private PanelDigitosCalculadora panelNumeros;

    /**
     * Panel con los operadores de la calculadora
     */
    private PanelOperadores panelOperadores;

    /**
     * Panel con la expresión aritmética
     */
    private PanelExpresionAritmetica panelExpresion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal de la aplicación
     */
    public InterfazCupiMath( )
    {
        // Crea la clase principal
        cupiMath = new CupiMath( );
        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 540, 530 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Calculadora Expresiones Aritméticas" );
        setResizable( false );

        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        panelExtension = new PanelExtension( this );
        panelExpresion = new PanelExpresionAritmetica( );

        // Panel que contiene al panel con el banner
        //y al banner con la expresión aritmética
        JPanel panelTemp = new JPanel( );
        panelTemp.setLayout( new BorderLayout( ) );
        panelTemp.add( panelImagen, BorderLayout.NORTH );
        panelTemp.add( panelExpresion, BorderLayout.CENTER );

        add( panelTemp, BorderLayout.NORTH );

        panelNumeros = new PanelDigitosCalculadora( this );
        add( panelNumeros, BorderLayout.CENTER );

        panelOperadores = new PanelOperadores( this );
        
        //Panel que contiene a los panales de los operadores y de las extensiones
        JPanel panelTemp2 = new JPanel( );
        panelTemp2.setLayout( new BorderLayout( ) );
        panelTemp2.add( panelOperadores, BorderLayout.NORTH );
        panelTemp2.add( panelExtension, BorderLayout.CENTER );
        add( panelTemp2, BorderLayout.SOUTH );
        // Centrar la ventana
        setLocationRelativeTo( null );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Verifica que sí hay paréntesis en la expresión, únicamente estén anidados, valida que no estén al mismo nivel.
     * Invoca al método principal del mundo para que evalué la expresión.
     */
    public void evaluarExpresionAritmetica( )
    {
        try
        {
            String expresion = panelExpresion.darExpresionAritmetica( );
            // Validar que no hayan parentesis que no estén anidados
            ArrayList posicionesParentesisCerrar = new ArrayList( );
            for( int i = 0; i < expresion.length( ); i++ )
            {
                if( expresion.charAt( i ) == ')' )
                    posicionesParentesisCerrar.add( new Integer( i ) );
            }

            for( int i = 0; i < expresion.length( ); i++ )
            {
                if( expresion.charAt( i ) == '(' )
                {

                    for( int j = 0; j < posicionesParentesisCerrar.size( ); j++ )
                    {
                        if( new Integer( i ) > ( Integer )posicionesParentesisCerrar.get( j ) )
                        {
                            JOptionPane.showMessageDialog( this, "No deben haber parentesis que no estén anidados", "ERROR", JOptionPane.INFORMATION_MESSAGE );
                            return;
                        }
                    }
                }
            }
            
            double valorExpresion = 0;
            cupiMath.construirArbol( expresion );
            valorExpresion = cupiMath.evaluarExpresion( expresion );
            JOptionPane.showMessageDialog( this, "Resultado evaluación: " + panelExpresion.darExpresionAritmetica( ) + " = " + valorExpresion, "RESPUESTA", JOptionPane.INFORMATION_MESSAGE );

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Presenta un mensaje al usuario con la expresión aritmética expresada en notación polaca.
     */
    public void convertirEnNotacionPolaca( )
    {
        String notacionPolaca;
        try
        {
            notacionPolaca = cupiMath.convertirEnNotacionPolaca(panelExpresion.darExpresionAritmetica( ) );
            JOptionPane.showMessageDialog( this, notacionPolaca, "NOTACIÓN POLACA", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
        }
        

    }

    /**
     * Actualiza el panel de la expresión aritmética, visualizando al comando que oprime el usuario.
     * @param item El ítem que agrega el usuario a la expresión. item != null
     */
    public void actualizarExpresionAritmetica( String item )
    {
        panelExpresion.actualizarExpresionAritmetica( item );
    }

    /**
     * Borra el último ítem de la expresión aritmética del panel expresión
     */
    public void borrarItemExpresionAritmetica( )
    {
        panelExpresion.borrarItem( );
    }

    /**
     * Borra toda la expresión aritmética del panel expresión
     */
    public void borrarExpresionAritmetica( )
    {
        panelExpresion.borrarExpresion( );

    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
    	try {
    		String expresion = panelExpresion.darExpresionAritmetica( );
			cupiMath.construirArbol( expresion );
			String tempNumero = JOptionPane.showInputDialog(this, "Ingrese el número a buscar");
	    	double numero = Double.parseDouble(tempNumero);
	        String resultado = cupiMath.buscarNumero(numero);
	        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
		}
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
    	try {
    		String expresion = panelExpresion.darExpresionAritmetica( );
			cupiMath.construirArbol( expresion );
			String tempNumero = JOptionPane.showInputDialog(this, "Ingrese el número a buscar");
	    	double numero = Double.parseDouble(tempNumero);
	        String resultado = cupiMath.elementosRama(numero);
	        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
		}
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Los argumentos de ejecución de la aplicación. args != null
     */
    public static void main( String[] args )
    {
        InterfazCupiMath interfaz = new InterfazCupiMath( );
        interfaz.setVisible( true );
    }

}