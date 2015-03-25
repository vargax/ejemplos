package uniandes.cupi2.procesadoraCafe.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Panel con la información general de la Procesadora de Café
 */
public class PanelGeneral extends JPanel 
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación
	 */
	private InterfazProcesadoraCafe principal;

	//-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

	private JTextField txtDinero;
	
	private JTextField txtInsumos;
	
	private JTextField txtClienteFiel;
	
	private JTextField txtProveedorBarato;
	
	private JTextField txtProductoDisponible;
	
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

	/**
	 * Método constructor del panel
	 * @param ventana Ventana principal
	 */
	public PanelGeneral(InterfazProcesadoraCafe ventana)
	{
		principal = ventana;
		
		setLayout( new BorderLayout() );
		
		ImageIcon icon = new ImageIcon("./data/imagenes/cafe.png");
		JLabel imagen = new JLabel(icon);
		imagen.setBackground(Color.WHITE);
		add(imagen, BorderLayout.WEST);
		
		JPanel aux = new JPanel();
		aux.setLayout(new BorderLayout());
		
		JPanel general = new JPanel();
		general.setLayout(new GridLayout(2, 2));
		general.setBorder(new TitledBorder(" Información General "));
		
		JLabel temp = new JLabel(" Dinero en caja: ");
		temp.setHorizontalAlignment(JLabel.CENTER);
		general.add(temp);
		txtDinero = new JTextField( "$ 0" );
		txtDinero.setEditable(false);
		general.add(txtDinero);
		
		JLabel temp2 = new JLabel(" Insumo disponible: ");
		temp2.setHorizontalAlignment(JLabel.CENTER);
		general.add(temp2);
		txtInsumos = new JTextField( "0 kilos" );
		txtInsumos.setEditable(false);
		general.add(txtInsumos);
		
		aux.add(general, BorderLayout.NORTH);
		
		JPanel estadisticas = new JPanel();
		estadisticas.setLayout(new GridLayout(3, 2));
		estadisticas.setBorder(new TitledBorder(" Estadísticas "));
		
		JLabel temp3 = new JLabel(" Producto mayor disponibilidad: ");
		temp3.setHorizontalAlignment(JLabel.CENTER);
		estadisticas.add(temp3);
		txtProductoDisponible = new JTextField( "No existen productos" );
		txtProductoDisponible.setEditable(false);
		estadisticas.add(txtProductoDisponible);
		
		JLabel temp4 = new JLabel(" Cliente fiel: ");
		temp4.setHorizontalAlignment(JLabel.CENTER);
		estadisticas.add(temp4);
		txtClienteFiel = new JTextField( "No existen clientes" );
		txtClienteFiel.setEditable(false);
		estadisticas.add(txtClienteFiel);
		
		JLabel temp5 = new JLabel(" Proveedor más económico: ");
		temp5.setHorizontalAlignment(JLabel.CENTER);
		estadisticas.add(temp5);
		txtProveedorBarato = new JTextField( "No existen proveedores" );
		txtProveedorBarato.setEditable(false);
		estadisticas.add(txtProveedorBarato);
				
		aux.add(estadisticas, BorderLayout.CENTER);
		
		add(aux, BorderLayout.CENTER);		
	}
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Actualiza la información de la procesadora
	 */
	public void actualizar()
	{
		txtClienteFiel.setText(principal.darClienteFiel());
		txtDinero.setText("$ " + formatearValorReal(principal.darDinero(), 2));
		txtInsumos.setText(principal.darInsumoDisponible() + " kilos");
		txtProductoDisponible.setText(principal.darProductoMayorDisponibilidad());
		txtProveedorBarato.setText(principal.darProveedorMasBarato());
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
}
