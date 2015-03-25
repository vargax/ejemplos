/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Impuestos de Carros
 * Autor: Katalina Marcos - Abr 15, 2005
 * Autor: Diana Puentes - Jun 23, 2005
 * Autor: Jorge Villalobos - Jul 10, 2005
 * Autor: Pablo Barvo - 30-Ago-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.impuestosCarro.interfaz;

import java.awt.*;

import javax.swing.*;

import uniandes.cupi2.impuestosCarro.mundo.*;

/**
 * Interfaz de cálculo de impuestos de carros
 */
public class InterfazImpuestosCarro extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Calculador de impuestos */
    private CalculadorImpuestos calculador;

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /** Panel de vehículos */
    private PanelVehiculo panelVehiculo;
    /** Panel de descuentos */
    private PanelDescuentos panelDescuentos;
    /** Panel de resultados */
    private PanelResultados panelResultados;

    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Crea la interfaz de impuestos de carros. Lanza una excepción si no puede localizar los archivos con la información inicial.
     * @throws Exception Error al cargar los archivos
     */
    public InterfazImpuestosCarro( ) throws Exception
    {
        // Crea el calculador de impuestos
        calculador = new CalculadorImpuestos( );

        // Configura la información de la ventana
        setTitle( "Cálculo impuestos" );
        setSize( 290, 350 );
        setResizable( false );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLayout( new BorderLayout( ) );

        // Crea los paneles y los agrega a la ventana
        // Panel de vehículos: panel activo
        panelVehiculo = new PanelVehiculo( this );
        add( panelVehiculo, BorderLayout.NORTH );
        // Panel de descuentos: panel pasivo
        panelDescuentos = new PanelDescuentos( );
        add( panelDescuentos, BorderLayout.CENTER );
        // Panel de resultados: panel activo
        panelResultados = new PanelResultados( this );
        add( panelResultados, BorderLayout.SOUTH );
    }

    //-----------------------------------------------------------------
    // Requerimientos funcionales
    //-----------------------------------------------------------------

    /**
     * Busca el vehículo según los datos de marca, línea y modelo y si lo encuentra retorna su valor
     */
    public void calcularPrecioVehiculo( )
    {
        // Pide la información dada por el usuario al respectivo panel
        String unaMarca = panelVehiculo.darMarca( );
        String unaLinea = panelVehiculo.darLinea( );
        String unModelo = panelVehiculo.darModelo( );
        // Verifica que la información este completa
        if( unaMarca.equals( "" ) || unaLinea.equals( "" ) || unModelo.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Por favor llene todos los datos", "Cálculo de Impuestos", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                double precio = calculador.buscarAvaluoVehiculo( unaMarca, unaLinea, unModelo );
                // Pide al panel que refresque la información del precio
                panelVehiculo.refrescarPrecio( precio );
            }
            catch( Exception e )
            {
                // Presenta al usuario el mensaje de la excepción
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Cálculo de Impuestos", JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    /**
     * Calcula el pago del impuesto según el vehículo
     */
    public void calcularImpuestos( )
    {
        // Pide al respectivo panel la información del vehículo
        String unaMarca = panelVehiculo.darMarca( );
        String unaLinea = panelVehiculo.darLinea( );
        String unModelo = panelVehiculo.darModelo( );
        // Verifica que la información este completa
        if( unaMarca.equals( "" ) || unaLinea.equals( "" ) || unModelo.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Por favor llene todos los datos", "Cálculo de Impuestos", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            // Pide al panel respectivo la información de los descuentos
            boolean descProntoPago = panelDescuentos.hayDescuentoProntoPago( );
            boolean descServicioPublico = panelDescuentos.hayDescuentoServicioPublico( );
            boolean descTrasladoCuenta = panelDescuentos.hayDescuentoTrasladoCuenta( );
            try
            {
                //Calcula el valor de los impuestos
                double pago = calculador.calcularPago( unaMarca, unaLinea, unModelo, descProntoPago, descServicioPublico, descTrasladoCuenta );
                // Pide al panel que refresque la información del valor de los
                // impuestos
                panelResultados.refrescarPago( pago );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Cálculo de Impuestos", JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    /**
     * Limpia todos los paneles
     */
    public void limpiar( )
    {
        panelVehiculo.limpiar( );
        panelDescuentos.limpiar( );
        panelResultados.limpiar( );
    }

    //----------------------------------------------------------------
    // Puntos de Extensión
    //----------------------------------------------------------------

    /**
     * Llamado para realizar el método de extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String respuesta = calculador.metodo1( );
        JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Llamado para realizar el método de extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String respuesta = calculador.metodo2( );
        JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    //-----------------------------------------------------------------
    // Programa principal
    //-----------------------------------------------------------------
    /**
     * Método para la ejecución del programa
     * @param args - no hay argumentos para la ejecución.
     */
    public static void main( String[] args )
    {
        try
        {
            InterfazImpuestosCarro interfaz = new InterfazImpuestosCarro( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}