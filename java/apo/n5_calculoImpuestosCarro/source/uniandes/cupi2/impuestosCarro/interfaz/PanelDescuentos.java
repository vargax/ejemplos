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
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.impuestosCarro.interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel para presentar la información de descuentos
 */
public class PanelDescuentos extends JPanel
{
    //-----------------------------------------------------------------
    // Elementos de la interfaz
    //-----------------------------------------------------------------

    /** CheckBox para elegir si se quiere liquidar con pronto pago */
    private JCheckBox cbPPago;
    /** CheckBox para elegir si se quiere liquidar con descuento por servicio publico */
    private JCheckBox cbSPublico;
    /** CheckBox para elegir si se quiere liquidar con descuento por traslado de cuenta */
    private JCheckBox cbTCuenta;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel de descuentos
     */
    public PanelDescuentos( )
    {
        //Establece el layout como una grilla de 2 filas y 2 columnas
        setLayout( new GridLayout( 2, 2 ) );

        //Adiciona un marco con titulo
        TitledBorder border = BorderFactory.createTitledBorder( "Descuentos" );
        border.setTitleColor( Color.BLUE );
        setBorder( border );

        //Crea los objetos del panel
        cbPPago = new JCheckBox( "Pronto pago" );
        cbSPublico = new JCheckBox( "Servicio público" );
        cbTCuenta = new JCheckBox( "Traslado de cuenta" );

        //Adiciona los objetos del panel
        add( cbPPago );
        add( cbTCuenta );
        add( cbSPublico );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Indica si aplica el descuento pronto pago
     * @return true si aplica el descuento, false en caso contrario
     */
    public boolean hayDescuentoProntoPago( )
    {
        return cbPPago.isSelected( );
    }

    /**
     * Indica si aplica el descuento por servicio publico
     * @return true si aplica el descuento, false en caso contrario
     */
    public boolean hayDescuentoServicioPublico( )
    {
        return cbSPublico.isSelected( );
    }

    /**
     * Indica si aplica el descuento por traslado de cuenta
     * @return true si aplica el descuento, false en caso contrario
     */
    public boolean hayDescuentoTrasladoCuenta( )
    {
        return cbTCuenta.isSelected( );
    }

    /**
     * Limpia los campos del panel
     */
    public void limpiar( )
    {
        cbPPago.setSelected( false );
        cbSPublico.setSelected( false );
        cbTCuenta.setSelected( false );
    }
}