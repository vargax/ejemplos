package uniandes.cupi2.sistemaLockers.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.sistemaLockers.mundo.Casillero;
import uniandes.cupi2.sistemaLockers.mundo.Locacion;

/**
 * Panel donde se muestra la información de la locación y sus casilleros
 */
public class PanelLocacion extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando Casillero Anterior
     */
	private final static String ANTERIOR = "<<";
	
	/**
	 * Comando Casillero Siguiente
	 */
	private final static String SIGUIENTE = ">>";
	
	/**
	 * Comando Asignar Casillero
	 */
	private final static String ASIGNAR = "Asignar";
	
	/**
	 * Comando Des-asignar Casillero
	 */
	private final static String DESASIGNAR = "Desasignar";
	
	/**
	 * Comando Crear Casilleros
	 */
	private final static String CREAR = "Crear";
	
	/**
	 * Comando Buscar Casilleros
	 */
	private final static String BUSCAR = "Buscar";
	
	/**
	 * Comandos Porcentaje Asignado
	 */
	private final static String PORCENTAJE = "Porcentaje";
	
	/**
	 * Comando Tipo Popular
	 */
	private final static String TIPO_POPULAR = "Popular";
    
	/**
	 * Comando Locación Anterior
	 */
	private static final String ANTERIOR_L = "Anterior";
    
	/**
	 * Comando Locación Siguiente
	 */
	private static final String SIGUIENTE_L = "Siguiente";
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * Ventana principal de la aplicación
	 */
	private InterfazSistemaLockers principal;
	
	/**
	 * Locación actual en el panel
	 */
	private Locacion locacionActual;
	
	/**
	 * Número del casillero en el panel
	 */
	private int casilleroActual;
		
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	
	/**
	 * Etiqueta con la imagen del casillero
	 */
	private JLabel etiqImagenCasillero;
	
	/**
	 * Etiqueta con el id del casillero
	 */
	private JTextField txtIdCasillero;
	
	/**
	 * Etiqueta con el tipo del casillero
	 */
	private JTextField txtTipoCasillero;
	
	/**
	 * Etiqueta con el estado del casillero
	 */
	private JTextField txtEstadoCasillero;
	
	/**
	 * Etiqueta con el número del casillero
	 */
	private JLabel etiqCasillero;
	
	/**
	 * Etiqueta con el nombre de la locación
	 */
	private JLabel etiqLocacion;
	
	/**
	 * Botón Casillero Anterior
	 */
	private JButton btnAnterior;
	
	/**
	 * Botón Casillero Siguiente
	 */
	private JButton btnSiguiente;
	
	/**
	 * Botón Asignar Casillero
	 */
	private JButton btnAsignar;
	
	/**
	 * Botón Des-asignar Casillero
	 */
	private JButton btnDesasignar;
	
	/**
	 * Botón Crear Casilleros
	 */
	private JButton btnCrearCasilleros;
	
	/**
	 * Botón Buscar Casilleros
	 */
	private JButton btnBuscarCasilleros;
	
	/**
	 * Botón Porcentaje Asignado
	 */
	private JButton btnPorcentajeAsignado;
	
	/**
	 * Botón Tipo Popular
	 */
	private JButton btnTipoPopular;	
	
	/**
	 * Botón Locación Anterior
	 */
	private JButton btnAnteriorL;
	
	/**
	 * Botón Locación Siguiente    
	 */
	private JButton btnSiguienteL;
	 
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
	/**
	 * Método constructor del panel. 
	 * Muestra la información de la locación y sus casilleros 
	 * @param principalP Venta principal de la aplicación
	 * @param l Locación que va a ser mostrada
	 */
	public PanelLocacion(InterfazSistemaLockers principalP, Locacion l)
	{
		principal = principalP;
		locacionActual = l;
		casilleroActual = 0;
		
		setBorder(new TitledBorder("Locación "));
		setLayout( new BorderLayout() );
		
		JPanel temp1 = new JPanel();
		temp1.setLayout( new GridLayout(7,1) );
		temp1.setBorder(new EmptyBorder(0,5,0,5));
		temp1.setPreferredSize(new Dimension(210, 0));
		
		temp1.add(new JLabel());
		
		etiqLocacion = new JLabel("Locación " + locacionActual.darNombre());
		etiqLocacion.setFont(new Font("Arial", Font.BOLD, 18));
		etiqLocacion.setHorizontalAlignment(JLabel.CENTER);
		temp1.add(etiqLocacion);
		
		temp1.add(new JLabel());
		
		btnCrearCasilleros = new JButton("Crear Casilleros");
		btnCrearCasilleros.setActionCommand(CREAR);
		btnCrearCasilleros.addActionListener(this);
		temp1.add(btnCrearCasilleros);
		
		btnBuscarCasilleros = new JButton("Buscar Casilleros");
		btnBuscarCasilleros.setActionCommand(BUSCAR);
		btnBuscarCasilleros.addActionListener(this);
		temp1.add(btnBuscarCasilleros);
		
		btnPorcentajeAsignado = new JButton("Casillero Asignados");
		btnPorcentajeAsignado.setActionCommand(PORCENTAJE);
		btnPorcentajeAsignado.addActionListener(this);
		temp1.add(btnPorcentajeAsignado);
		
		btnTipoPopular = new JButton("Casillero más Popular");
		btnTipoPopular.setActionCommand(TIPO_POPULAR);
		btnTipoPopular.addActionListener(this);
		temp1.add(btnTipoPopular);
				
		add(temp1, BorderLayout.WEST);
		
		JPanel temp2 = new JPanel();
		temp2.setLayout( new BorderLayout() );
		temp2.setBorder(new TitledBorder("Casillero") );
		
		etiqImagenCasillero = new JLabel();
		etiqImagenCasillero.setIcon(new ImageIcon("./data/imagenes/noDisponible.png"));
		etiqImagenCasillero.setBorder(new EmptyBorder(5, 10, 5, 10));
		temp2.add(etiqImagenCasillero, BorderLayout.EAST);
		
		JPanel temp3 = new JPanel();
		temp3.setLayout( new GridLayout(4,2) );
		temp3.setBorder( new EmptyBorder(10, 20, 10, 20) );
		
		etiqCasillero = new JLabel("Casillero 0 de 0");
		temp3.add(etiqCasillero);
		
		temp3.add(new JLabel());
		
		JLabel etiqId = new JLabel(" Id: ");
		JLabel etiqTipo = new JLabel(" Tipo: ");		
		JLabel etiqEstado = new JLabel(" Estado: ");
		txtIdCasillero = new JTextField();
		txtIdCasillero.setEditable(false);
		txtTipoCasillero = new JTextField();
		txtTipoCasillero.setEditable(false);
		txtEstadoCasillero = new JTextField();
		txtEstadoCasillero.setEditable(false);
		
		temp3.add(etiqId);
		temp3.add(txtIdCasillero);
		temp3.add(etiqTipo);
		temp3.add(txtTipoCasillero);
		temp3.add(etiqEstado);
		temp3.add(txtEstadoCasillero);
		
		JPanel temp4 = new JPanel();
		temp4.setLayout( new GridLayout(1, 4) );
		
		btnAnterior = new JButton(ANTERIOR);
		btnAnterior.setActionCommand(ANTERIOR);
		btnAnterior.addActionListener(this);
		btnAnterior.setEnabled(false);
		temp4.add(btnAnterior);
		
		btnAsignar = new JButton(ASIGNAR);
		btnAsignar.setActionCommand(ASIGNAR);
		btnAsignar.addActionListener(this);
		btnAsignar.setEnabled(false);
		temp4.add(btnAsignar);
		
		btnDesasignar = new JButton(DESASIGNAR);
		btnDesasignar.setActionCommand(DESASIGNAR);
		btnDesasignar.addActionListener(this);
		btnDesasignar.setEnabled(false);
		temp4.add(btnDesasignar);
		
		btnSiguiente = new JButton(SIGUIENTE);
		btnSiguiente.setActionCommand(SIGUIENTE);
		btnSiguiente.addActionListener(this);
		btnSiguiente.setEnabled(false);
		temp4.add(btnSiguiente);
		
		temp2.add(temp3, BorderLayout.CENTER);
		temp2.add(temp4, BorderLayout.SOUTH);
		
		add(temp2, BorderLayout.CENTER);
		
		JPanel temp5 = new JPanel();
		temp5.setLayout( new GridLayout(1, 2) );
		temp5.setBorder(new EmptyBorder(5,0,5,0));
		
		btnAnteriorL = new JButton( " Locación " + ANTERIOR_L);
		btnAnteriorL.setActionCommand(ANTERIOR_L);
		btnAnteriorL.addActionListener(this);
		btnAnteriorL.setEnabled(false);
		temp5.add(btnAnteriorL);
	        
		btnSiguienteL = new JButton( " Locación " + SIGUIENTE_L);
		btnSiguienteL.setActionCommand(SIGUIENTE_L);
		btnSiguienteL.addActionListener(this);
		temp5.add(btnSiguienteL);
	        
	    add(temp5, BorderLayout.SOUTH);  
	}

	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
  
	/**
	 * Actualiza la información mostrada de la locación
	 * @param l Locación a mostrar
	 */
	public void actualizarLocacion(Locacion l)
	{
		locacionActual = l;
		etiqLocacion.setText(" Locación " + l.darNombre());
		casilleroActual = -1;
		btnAnterior.setEnabled(false);
		
		if(l.darCasilleros().isEmpty())
		{
			etiqCasillero.setText("Casillero 0 de 0");
			txtIdCasillero.setText("");
			txtTipoCasillero.setText("");
			txtEstadoCasillero.setText("");
			etiqImagenCasillero.setIcon(new ImageIcon("./data/imagenes/noDisponible.png"));
			btnSiguiente.setEnabled(false);
			btnAsignar.setEnabled(false);
			btnDesasignar.setEnabled(false);
		}
		else
		{
			casilleroActual++;
			actualizarCasillero();
			
		}
	}
	
	/**
	 * Actualiza la información del casillero actual
	 */
	public void actualizarCasillero()
	{
		Casillero c = locacionActual.darCasillero(casilleroActual);
		
		etiqCasillero.setText("Casillero " + (casilleroActual+1) + " de " + locacionActual.darNumCasilleros());
		txtIdCasillero.setText(c.darId());
		txtTipoCasillero.setText(c.darTipo());
		
		if(c.estaAsignado())
		{
			etiqImagenCasillero.setIcon(new ImageIcon("./data/imagenes/asignado.png"));
			txtEstadoCasillero.setText("Asignado");
			btnAsignar.setEnabled(false);
			btnDesasignar.setEnabled(true);
		}
		else
		{
			etiqImagenCasillero.setIcon(new ImageIcon("./data/imagenes/desasignado.png"));
			txtEstadoCasillero.setText("No asignado");
			btnAsignar.setEnabled(true);
			btnDesasignar.setEnabled(false);
		}
		
		if(casilleroActual == locacionActual.darNumCasilleros()-1 )
			btnSiguiente.setEnabled(false);
		else
			btnSiguiente.setEnabled(true);
		
		if(casilleroActual == 0)
			btnAnterior.setEnabled(false);
		else
			btnAnterior.setEnabled(true);
	}
	
    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
	public void actionPerformed(ActionEvent e) 
	{
		String evento = e.getActionCommand();
		if( evento.equals( ANTERIOR ) )
		{
			casilleroActual--;
			actualizarCasillero();
		}
        else if(ANTERIOR_L.equals( e.getActionCommand() ))
        {
        	boolean primera = principal.locacionAnterior();
        	btnSiguienteL.setEnabled(true);
        	
        	if(primera)
        		btnAnteriorL.setEnabled(false);
        }
		else if( evento.equals( ASIGNAR ) )
		{
			String id = txtIdCasillero.getText();
			String locacion = locacionActual.darNombre();
			
			principal.asignarCasillero(locacion, id);	
			etiqImagenCasillero.setIcon(new ImageIcon("./data/imagenes/asignado.png"));
			txtEstadoCasillero.setText("Asignado");
			btnAsignar.setEnabled(false);
			btnDesasignar.setEnabled(true);
		}
		else if( evento.equals( BUSCAR ) )
		{
			String locacion = locacionActual.darNombre();
			principal.buscarCasilleros(locacion);
		}
		else if( evento.equals( CREAR ) )
		{
			String locacion = locacionActual.darNombre();
			boolean crear = principal.crearCasilleros(locacion);
			
			if(crear)
			{
				casilleroActual = 0;
				actualizarCasillero();
			}
		}
		else if( evento.equals( DESASIGNAR ) )
		{
			String id = txtIdCasillero.getText();
			String locacion = locacionActual.darNombre();
			
			principal.desasignarCasillero(locacion, id);
			etiqImagenCasillero.setIcon(new ImageIcon("./data/imagenes/desasignado.png"));
			txtEstadoCasillero.setText("No asignado");
			btnAsignar.setEnabled(true);
			btnDesasignar.setEnabled(false);
		}
		else if( evento.equals( PORCENTAJE ) )
		{
			String locacion = locacionActual.darNombre();
			principal.darPorcentajeAsignado(locacion);
		}
		else if( evento.equals( SIGUIENTE ) )
		{
			casilleroActual++;
			actualizarCasillero();
		}
		else if(SIGUIENTE_L.equals( e.getActionCommand() ))
        {
        	boolean ultima = principal.siguienteLocacion();
        	btnAnteriorL.setEnabled(true);
        	
        	if(ultima)
        		btnSiguienteL.setEnabled(false);
        } 
		else if( evento.equals( TIPO_POPULAR ) )
		{
			String locacion = locacionActual.darNombre();
			principal.darTipoPopular(locacion);
		}
	}
}