package uniandes.cupi2.estacionServicio.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.cupi2.estacionServicio.mundo.Surtidor;

/**
 * Panel donde se muestra la información del Surtidor de combustible
 */
public class PanelSurtidor extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
     * Comando Registrar Venta.
     */
    private static final String REGISTRAR_VENTA = "Registrar Venta";
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazEstacionServicio principal;

    /**
     * Número del surtidor.
     */
    private int numSurtidor;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Etiqueta tipo de combustible.
     */
    private JLabel etiquetaTipoCombustible;

    /**
     * Etiqueta costo del galón de combustible.
     */
    private JLabel etiquetaCostoGalon;

    /**
     * Etiqueta Número de galones vendidos
     */
    private JLabel etiquetaNumGalones;

    /**
     * Etiqueta Dinero total recaudado por ventas.
     */
    private JLabel etiquetaDineroRecaudado;

    /**
     * Botón registrar oferta.
     */
    private JButton botonRegistrarVenta;
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param laPrincipal Ventana principal. laPrincipal != null.
     * @param nSurtidor Número del Surtidor.
     */
    public PanelSurtidor( InterfazEstacionServicio laPrincipal, int nSurtidor )
    {
        principal = laPrincipal;
        numSurtidor = nSurtidor;

        JPanel info = new JPanel();
        info.setLayout( new GridLayout(5,1));
        
        botonRegistrarVenta = new JButton( REGISTRAR_VENTA );
        botonRegistrarVenta.setPreferredSize( new Dimension( 160, 20 ) );
        botonRegistrarVenta.setActionCommand( REGISTRAR_VENTA );
        botonRegistrarVenta.addActionListener( this );

        etiquetaTipoCombustible = new JLabel( " Tipo: " );
        etiquetaTipoCombustible.setHorizontalAlignment( JLabel.LEFT );

        etiquetaCostoGalon = new JLabel( " Costo por Galón: $ " );
        etiquetaCostoGalon.setHorizontalAlignment( JLabel.LEFT );

        etiquetaNumGalones = new JLabel( " Galones Vendidos: " );
        etiquetaNumGalones.setHorizontalAlignment( JLabel.LEFT );

        etiquetaDineroRecaudado = new JLabel( " Dinero Recaudado: $ " );
        etiquetaDineroRecaudado.setHorizontalAlignment( JLabel.LEFT );
        
        info.add(etiquetaTipoCombustible);
        info.add(etiquetaCostoGalon);
        info.add(etiquetaNumGalones);
        info.add(etiquetaDineroRecaudado);
        info.add(botonRegistrarVenta);
        
        setLayout( new BorderLayout() );
        setBorder( BorderFactory.createTitledBorder( "Surtidor " + numSurtidor ) );

        JLabel imagen = new JLabel( );
        imagen.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon icono = new ImageIcon( "data/imagenes/gasStation.jpeg" );
        imagen.setIcon( icono );
        add( imagen, BorderLayout.NORTH );
        
        add( info, BorderLayout.CENTER );
        
    }
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Actualiza los campos de interfaz con la información del surtidor
     * @param s Surtidor 
     */
    public void actualizar(Surtidor s)
    {
    	etiquetaTipoCombustible.setText(" Tipo: " + s.darTipoCombustible());
    	etiquetaCostoGalon.setText(" Costo por Galón: $ " + s.darCostoGalon());
    	etiquetaNumGalones.setText(" Galones Vendidos: " + formatearValorReal(s.darNumeroGalonesVendidos(), 2));
    	etiquetaDineroRecaudado.setText(" Dinero Recaudado: $ " + formatearValorReal(s.darDineroRecaudado(), 2));
    }
    
    /**
     * Formatea un valor numérico real para presentar en la interfaz <br>
     * @param valor El valor numérico a ser formateado
     * @param numDigitos El número de decimales deseados
     * @return Cadena con el valor formateado con puntos y signos.
     */
    private String formatearValorReal( double valor, int numDigitos )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( " ###,###.##" );
        df.setMinimumFractionDigits( 0 );
        return df.format( valor );
    }
    
    /**
     * Manejo de eventos del usuario
     * @param e Evento de usuario. e != null.
     */
    public void actionPerformed(ActionEvent e)
    {
		String comando = e.getActionCommand();
		if(comando.equals(REGISTRAR_VENTA))
		{
			principal.registarVenta(numSurtidor);
		}
		
	}
}
