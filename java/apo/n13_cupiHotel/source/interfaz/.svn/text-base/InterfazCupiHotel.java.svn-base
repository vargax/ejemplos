package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import mundo.CupiHotel;
import mundo.ICupiHotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazCupiHotel extends JFrame implements ActionListener {
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = -3149543507462038988L;
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	/**
	 * Ruta del archivo de serialización
	 */
	private final static String RUTA = "./data/CupiHotel.data";
	/**
	 * Corresponde a la ruta del reporte
	 */
	private final static String RUTA_REPORTE = "./data/reporte.txt";
	/**
	 * Columnas de la tabla que despliega la información de las habitaciones
	 */
	private final static String[] columnasHabitacion = {"id", "Número", "Tipo","Precio", "Huespedes", "Consumos","Reservas"};
	/**
	 * Columnas de la tabla que despliega la información de los huespedes
	 */
	private final static String[] columnasHuesped = {"Nombre", "Edad", "Id", "Dirección", "Teléfono", "Noches"};
	/**
	 * Columnas de la tabla que despliega la información de los consumos
	 */
	private final static String[] columnasConsumo = {"Descripción","Valor"};
	/**
	 * Columnas de la tabla que despliega la información de las reservas
	 */
	private final static String[] columnasReserva = {"Nombre", "Días Reservados", "Fecha Inicio"};
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El panel principal de la aplicación
	 */
	private JPanel panelPrincipal;
	/**
	 * La relacion con el mundo del problema
	 */
	private ICupiHotel hotel;
	/**
	 * Las tablas para desplegar la información del hotel
	 */
	private DefaultTableModel modeloTablaHabitaciones;
	private JTable tablaHabitaciones;
	private String[][] infoHabitaciones;
	
	private DefaultTableModel modeloTablaHuespedes;
	private JTable tablaHuespedes;
	private String[][] infoHuespedes;
	
	private DefaultTableModel modeloTablaConsumos;
	private JTable tablaConsumos;
	private String[][] infoConsumos;
	
	private DefaultTableModel modeloTablaReservas;
	private JTable tablaReservas;
	private String[][] infoReservas;
	/**
	 * El id de la habitación actual
	 */
	int habitacionActual;
	//-----------------------------------------------------------------
    // Método MAIN
    //-----------------------------------------------------------------
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazCupiHotel frame = new InterfazCupiHotel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	/**
	 * Create the frame.
	 */
	public InterfazCupiHotel() {
		setTitle("CupiHotel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		
		cargarHotel();
		
		modeloTablaHabitaciones = new DefaultTableModel();
		modeloTablaHuespedes = new DefaultTableModel();	
		modeloTablaConsumos = new DefaultTableModel();
		modeloTablaReservas = new DefaultTableModel();
		
		BarraMenu barraMenu = new BarraMenu(this);
		setJMenuBar(barraMenu);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panelPrincipal.add(splitPane);
		
		JPanel panelInfohabitaciones = new JPanel();
		panelInfohabitaciones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Informaci\u00F3n Habitaciones", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		splitPane.setLeftComponent(panelInfohabitaciones);
		
		tablaHabitaciones = new JTable(modeloTablaHabitaciones);
		JScrollPane scrollPane = new JScrollPane(tablaHabitaciones);
		tablaHabitaciones.setFillsViewportHeight(true);
		tablaHabitaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaHabitaciones.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						habitacionActual = Integer.parseInt((String) modeloTablaHabitaciones.getValueAt(tablaHabitaciones.getSelectedRow(), 0));
						cargarInfoHabitacion(habitacionActual);
						//System.out.println(habitacionActual);
					}
				}
				);
		panelInfohabitaciones.setLayout(new GridLayout(0, 1, 0, 0));
		panelInfohabitaciones.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Habitaci\u00F3n Actual", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		splitPane.setRightComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panelInfoHuespedes = new JPanel();
		panelInfoHuespedes.setBorder(new TitledBorder(null, "Huespedes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelInfoHuespedes = new GridBagConstraints();
		gbc_panelInfoHuespedes.gridwidth = 2;
		gbc_panelInfoHuespedes.insets = new Insets(0, 0, 5, 0);
		gbc_panelInfoHuespedes.fill = GridBagConstraints.BOTH;
		gbc_panelInfoHuespedes.gridx = 0;
		gbc_panelInfoHuespedes.gridy = 0;
		panel_1.add(panelInfoHuespedes, gbc_panelInfoHuespedes);
		panelInfoHuespedes.setLayout(new GridLayout(1, 0, 0, 0));
		
		tablaHuespedes = new JTable(modeloTablaHuespedes);
		tablaHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_1 = new JScrollPane(tablaHuespedes);
		panelInfoHuespedes.add(scrollPane_1);
		
		JPanel panelInfoConsumos = new JPanel();
		panelInfoConsumos.setBorder(new TitledBorder(null, "Consumos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelInfoConsumos = new GridBagConstraints();
		gbc_panelInfoConsumos.insets = new Insets(0, 0, 5, 5);
		gbc_panelInfoConsumos.fill = GridBagConstraints.BOTH;
		gbc_panelInfoConsumos.gridx = 0;
		gbc_panelInfoConsumos.gridy = 1;
		panel_1.add(panelInfoConsumos, gbc_panelInfoConsumos);
		panelInfoConsumos.setLayout(new GridLayout(0, 1, 0, 0));
		
		tablaConsumos = new JTable(modeloTablaConsumos);
		tablaConsumos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_2 = new JScrollPane(tablaConsumos);
		panelInfoConsumos.add(scrollPane_2);
		
		JPanel panelInfoReservas = new JPanel();
		panelInfoReservas.setBorder(new TitledBorder(null, "Reservas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelInfoReservas = new GridBagConstraints();
		gbc_panelInfoReservas.insets = new Insets(0, 0, 5, 0);
		gbc_panelInfoReservas.fill = GridBagConstraints.BOTH;
		gbc_panelInfoReservas.gridx = 1;
		gbc_panelInfoReservas.gridy = 1;
		panel_1.add(panelInfoReservas, gbc_panelInfoReservas);
		panelInfoReservas.setLayout(new GridLayout(0, 1, 0, 0));
		
		tablaReservas = new JTable(modeloTablaReservas);
		tablaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_3 = new JScrollPane(tablaReservas);
		panelInfoReservas.add(scrollPane_3);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50, 50));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		panel_1.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1, 5, 5, 5));
		
		JButton btnAgregarReserva = new JButton("Agregar Reserva");
		btnAgregarReserva.setActionCommand("agregarReserva");
		btnAgregarReserva.addActionListener(this);
		panel.add(btnAgregarReserva);
		
		JButton btnEliminarReserva = new JButton("Eliminar Reserva");
		btnEliminarReserva.setActionCommand("eliminarReserva");
		btnEliminarReserva.addActionListener(this);
		panel.add(btnEliminarReserva);
		
		JButton btnRegistrarHuesped = new JButton("Registrar Huesped");
		btnRegistrarHuesped.setActionCommand("registrarHuesped");
		btnRegistrarHuesped.addActionListener(this);
		panel.add(btnRegistrarHuesped);
		
		JButton btnRegistrarConsumo = new JButton("Registrar Consumo");
		btnRegistrarConsumo.setActionCommand("registrarConsumo");
		btnRegistrarConsumo.addActionListener(this);
		panel.add(btnRegistrarConsumo);
		
		JButton btnRealizarCheckout = new JButton("Realizar Check-Out");
		btnRealizarCheckout.setActionCommand("realizarCheckout");
		btnRealizarCheckout.addActionListener(this);
		panel.add(btnRealizarCheckout);
		
		cargarInfoHabitaciones();
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Restaura el mundo del problema
	 */
	public void cargarHotel()
	{
		File datos = new File(RUTA);
		if(datos.exists())
		{
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(new FileInputStream(datos));
				hotel = (ICupiHotel) ois.readObject();
				ois.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Imposible restaurar el mundo "+e.getMessage());
				e.printStackTrace();
			}
		}
		else cargarDesdeArchivoConf();
	}
	/**
	 * Restaura el hotel desde el archivo de configuración
	 */
	public void cargarDesdeArchivoConf() {
		JFileChooser fc = new JFileChooser("./data");
		fc.setDialogTitle("Cargar Hotel");
		File archivo = null;
		int respuesta = fc.showOpenDialog(this);
		if(respuesta == JFileChooser.APPROVE_OPTION)
		{
			archivo = fc.getSelectedFile();
			if(archivo !=null)
			{
				hotel = new CupiHotel();
				try
				{
					hotel.inicializarHotel(archivo);
					cargarInfoHabitaciones();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(this, "Imposible cargar desde archivo "+ e.getMessage());
				}
			}
			else
				JOptionPane.showMessageDialog(this,"Imposible restaurar mundo");
		}
	}
	/**
	 * Carga la información de la habitación seleccionada actualmente
	 */
	public void cargarInfoHabitacion(int idHabitacion) {
		cargarInfoHuespedes(idHabitacion);
		cargarInfoConsumos(idHabitacion);
		cargarInfoReservas(idHabitacion);
	}
	/**
	 * Carga la información de las habitaciones
	 */
	public void cargarInfoHabitaciones() {
		infoHabitaciones = hotel.darListaHabitaciones();
		modeloTablaHabitaciones.setDataVector(infoHabitaciones, columnasHabitacion);
		modeloTablaHabitaciones.fireTableDataChanged();
	}
	/**
	 * Carga la información de los huespedes
	 */
	public void cargarInfoHuespedes(int idHabitacion) {
		infoHuespedes = hotel.darListaHuespedes(idHabitacion);
		modeloTablaHuespedes.setDataVector(infoHuespedes, columnasHuesped);
		modeloTablaHuespedes.fireTableDataChanged();
	}
	/**
	 * Carga la información de los consumos
	 */
	public void cargarInfoConsumos(int idHabitacion) {
		infoConsumos = hotel.darListaConsumos(idHabitacion);
		modeloTablaConsumos.setDataVector(infoConsumos, columnasConsumo);
		modeloTablaConsumos.fireTableDataChanged();
	}
	/**
	 * Carga la información de las reservas
	 */
	public void cargarInfoReservas(int idHabitacion) {
		infoReservas = hotel.darListaReservas(idHabitacion);
		modeloTablaReservas.setDataVector(infoReservas, columnasReserva);
		modeloTablaReservas.fireTableDataChanged();
	}
	/**
	 * Registra una nueva reserva
	 */
	public void registrarReserva(String nombreP, String fechaInicioP, String diasReservadosP) {
		Date fechaInicio = new Date(Date.parse(fechaInicioP)); 
		int diasReservados = Integer.parseInt(diasReservadosP);
		hotel.registrarReserva(habitacionActual, nombreP, fechaInicio, diasReservados);
		cargarInfoReservas(habitacionActual);
	}
	/**
	 * Registra un nuevo huesped
	 */
	public void registrarHuesped(String nombreP, String edadP, String identificacionP, String direccionP, String telefonoP, String nochesP) {
		
		int edad = Integer.parseInt(edadP);
		int identificacion = Integer.parseInt(identificacionP);
		int telefono = Integer.parseInt(telefonoP);
		int noches = Integer.parseInt(nochesP);
		hotel.registrarHuesped(habitacionActual, nombreP, edad, identificacion, direccionP, telefono, noches);
		cargarInfoHuespedes(habitacionActual);
	}
	/**
	 * Registra un nuevo consumo
	 */
	public void registrarConsumo(String nombreP, String valorP) {
		float valor = Float.parseFloat(valorP);
		hotel.registrarConsumo(habitacionActual, nombreP, valor);
		cargarInfoConsumos(habitacionActual);
	}
	/**
	 * Finaliza la aplicación
	 */
	public void dispose() {
		try	{	
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA));
			oos.writeObject(hotel);
			oos.close();
			super.dispose();
		} catch(Exception e) {
			int a =JOptionPane.showConfirmDialog(this, "Error al guardar el estado del hotel: /n "+e.getMessage()+"/n ¿Desea cerrar el programa?","CupiHotel", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if(a == JOptionPane.YES_OPTION)	super.dispose();
		}
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("agregarReserva")) {
			DialogoRegistrarReserva dialogo = new DialogoRegistrarReserva(this);
			dialogo.setVisible(true);
		}
		else if (comando.equals("registrarHuesped")) {
			DialogoRegistrarHuesped dialogo = new DialogoRegistrarHuesped(this);
			dialogo.setVisible(true);
		}
		else if (comando.equals("registrarConsumo")) {
			DialogoRegistrarConsumo dialogo = new DialogoRegistrarConsumo(this);
			dialogo.setVisible(true);
		}
		else if (comando.equals("eliminarReserva")) {
			System.out.println(tablaHabitaciones.getSelectedRow());
		}
	}
	public void generarReporte()
	{
		try
		{
			File archivo = new File(RUTA_REPORTE);
			hotel.generarReporte(archivo);
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Imposible generar reporte "+e.getMessage());
		}
	}
}

	