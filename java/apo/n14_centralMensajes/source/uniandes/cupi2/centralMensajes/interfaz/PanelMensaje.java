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
package uniandes.cupi2.centralMensajes.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Panel donde se va a desplegar la información de los mensaje.
 */
public class PanelMensaje extends JPanel implements ItemListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = 5607766705518757279L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Interfaz principal de la aplicación.
     */
    private InterfazCentral principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Scroll donde está el área de texto del mensaje.
     */
    private JScrollPane scrollTextoMensaje;

    /**
     * Área de texto del mensaje.
     */
    private JTextArea areaTextoMensaje;

    /**
     * Etiqueta "Remitente:".
     */
    private JLabel lblRemitente;

    /**
     * Etiqueta "Decodificador:"
     */
    private JLabel lblDecodificador;

    /**
     * Combo donde se selecciona el decodificador.
     */
    private JComboBox comboDecodificador = null;

    /**
     * Cuadro de texto donde se muestra el remitente.
     */
    private JTextField txtRemitente = null;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param nPrincipal Interfaz principal de la aplicación.
     * @param codificaciones Lista de codificaciones.
     */
    public PanelMensaje( InterfazCentral nPrincipal, Iterador<String> codificaciones )
    {
        super( );
        principal = nPrincipal;
        this.setSize( 600, 300 );
        this.setLayout( new GridBagLayout( ) );
        this.setBorder( BorderFactory.createTitledBorder( "Mensaje" ) );
        this.setPreferredSize( new Dimension( 600, 300 ) );

        // Construcción del panel.
        GridBagConstraints gridBagConstraints4 = new GridBagConstraints( );
        gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.gridx = 1;
        GridBagConstraints gridBagConstraints31 = new GridBagConstraints( );
        gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints31.gridy = 1;
        gridBagConstraints31.weightx = 1.0;
        gridBagConstraints31.insets = new Insets( 3, 0, 0, 0 );
        gridBagConstraints31.gridx = 1;
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints( );
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.anchor = GridBagConstraints.EAST;
        gridBagConstraints2.insets = new Insets( 3, 0, 0, 5 );
        gridBagConstraints2.gridy = 1;
        GridBagConstraints gridBagConstraints3 = new GridBagConstraints( );
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.anchor = GridBagConstraints.EAST;
        gridBagConstraints3.insets = new Insets( 0, 0, 0, 5 );
        gridBagConstraints3.gridy = 0;
        GridBagConstraints gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets( 5, 0, 0, 0 );
        gridBagConstraints.gridx = 0;

        // Inserción de los elementos.
        areaTextoMensaje = new JTextArea( );
        areaTextoMensaje.setEditable( false );
        areaTextoMensaje.setLineWrap( true );
        areaTextoMensaje.setWrapStyleWord( true );
        scrollTextoMensaje = new JScrollPane( );
        scrollTextoMensaje.setViewportView( areaTextoMensaje );
        add( scrollTextoMensaje, gridBagConstraints );

        lblRemitente = new JLabel( );
        lblRemitente.setText( "Enviado por:" );
        add( lblRemitente, gridBagConstraints3 );

        lblDecodificador = new JLabel( );
        lblDecodificador.setText( "Decodificación:" );
        add( lblDecodificador, gridBagConstraints2 );

        codificaciones.reiniciar( );
        Vector<String> encs = new Vector<String>( );
        while( codificaciones.haySiguiente( ) )
            encs.add( codificaciones.darSiguiente( ) );

        comboDecodificador = new JComboBox( encs.toArray( ) );
        comboDecodificador.setSelectedIndex( 0 );
        comboDecodificador.addItemListener( this );
        add( comboDecodificador, gridBagConstraints31 );

        txtRemitente = new JTextField( );
        txtRemitente.setEditable( false );
        this.add( txtRemitente, gridBagConstraints4 );
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el decodificador seleccionado.
     * @return El decodificador seleccionado.
     */
    public String darDecodificador( )
    {
        return comboDecodificador.getSelectedItem( ).toString( );
    }

    /**
     * Refresca el área de texto del mensaje.
     * @param remitente Remitente del mensaje.
     * @param mensaje Mensaje que se quiere mostrar.
     */
    public void refrescarMensaje( String remitente, String mensaje )
    {
        txtRemitente.setText( remitente );
        areaTextoMensaje.setText( mensaje );
    }

    /**
     * Manejo de los eventos sobre el combo de selección de decodificador.
     * @param e Acción que generó el evento.
     */
    public void itemStateChanged( ItemEvent e )
    {
        principal.cambiarDecodificador( );
    }

}
