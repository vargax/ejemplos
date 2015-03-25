package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Principal;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarraMenu extends JMenuBar implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5385277933903595199L;
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	private static final String CARGAR = "cargar";
	private static final String REPORTE = "reporte";
	private static final String BUSCAR_RESERVA = "buscarReserva";
	private static final String BUSCAR_HABITACION = "buscarHabitacion";
	private static final String BUSCAR_DISPONIBLES = "buscarDisponibles";
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	//private InterfazCupiHotel interfaz;
	private InterfazCupiHotel ventana;
	
	private JMenu menuArchivo;
	private JMenuItem itemCargar;
	private JMenuItem itemReporte;
	
	private JMenu menuBuscar;
	private JMenuItem itemBuscarReserva;
	private JMenuItem itemBuscarHabitacion;
	private JMenuItem itemBuscarDisponibles;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	public BarraMenu(InterfazCupiHotel ventanaP) {
		ventana = ventanaP;
		
		menuArchivo = new JMenu("Archivo");
		add(menuArchivo);
		
		itemCargar = new JMenuItem("Cargar cupiHotel");
		itemCargar.setActionCommand(CARGAR);
		itemCargar.addActionListener(this);
		menuArchivo.add(itemCargar);
		
		itemReporte = new JMenuItem("Generar reporte");
		itemReporte.setActionCommand(REPORTE);
		itemReporte.addActionListener(this);
		menuArchivo.add(itemReporte);
		
		menuBuscar = new JMenu("Buscar");
		add(menuBuscar);
		
		itemBuscarReserva = new JMenuItem("Reservas");
		itemBuscarReserva.setActionCommand(BUSCAR_RESERVA);
		itemBuscarReserva.addActionListener(this);
		menuBuscar.add(itemBuscarReserva);
		
		itemBuscarHabitacion = new JMenuItem("Habitación por huesped");
		itemBuscarHabitacion.setActionCommand(BUSCAR_HABITACION);
		itemBuscarHabitacion.addActionListener(this);
		menuBuscar.add(itemBuscarHabitacion);
		
		itemBuscarDisponibles = new JMenuItem("Habitaciones disponibles");
		itemBuscarDisponibles.setActionCommand(BUSCAR_DISPONIBLES);
		itemBuscarDisponibles.addActionListener(this);
		menuBuscar.add(itemBuscarDisponibles);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(CARGAR.equals(comando))	ventana.cargarDesdeArchivoConf();
		else if (REPORTE.equals(comando)) ventana.generarReporte();
	}
}
