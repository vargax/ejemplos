package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JList;

import estructurasDatos.IListaEncadenada;

import mundo.CupiPeliculas;
import mundo.ICupiPeliculas;
import mundo.Pelicula;

public class InterfazSustentacion extends JFrame implements ActionListener {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El mundo del problema
	 */
	private ICupiPeliculas mundo;
	/**
	 * La lista que contendrá las películas
	 */
	private JList listaPeliculas;
	/**
	 * El panel principal
	 */
	private JPanel contentPane;
	//-----------------------------------------------------------------
    // Método MAIN
    //-----------------------------------------------------------------
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazSustentacion frame = new InterfazSustentacion();
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
	public InterfazSustentacion() {
		cargarPeliculas();
		setTitle("n14 - CupiPeliculas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnPorNombre = new JButton("Por Nombre");
		btnPorNombre.setActionCommand("porNombre");
		btnPorNombre.addActionListener(this);
		panel.add(btnPorNombre);
		
		JButton btnPorAo = new JButton("Por Año");
		btnPorAo.setActionCommand("porAo");
		btnPorAo.addActionListener(this);
		panel.add(btnPorAo);
		
		JButton btnRangoAos = new JButton("Rango Años");
		btnRangoAos.setActionCommand("rangoAos");
		btnRangoAos.addActionListener(this);
		panel.add(btnRangoAos);
		
		listaPeliculas = new JList();
		listaPeliculas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					Pelicula seleccionada = (Pelicula) listaPeliculas.getModel().getElementAt(listaPeliculas.locationToIndex(e.getPoint()));
					if(seleccionada != null) {
						new InfoPelicula(seleccionada).setVisible(true);
					}
				}
			}
		});
		contentPane.add(new JScrollPane(listaPeliculas), BorderLayout.CENTER);
		actualizarLista(mundo.darPeliculas());
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Carga las películas desde el archivo de entrada
	 */
	private void cargarPeliculas() {
		JFileChooser fc = new JFileChooser("./data");
		fc.setDialogTitle("Cargar peliculas");
		int respuesta = fc.showOpenDialog(this);
		if(respuesta == JFileChooser.APPROVE_OPTION && fc.getSelectedFile() != null) {
			mundo = new CupiPeliculas();
			try	{
				String resultado = mundo.cargarPeliculas(fc.getSelectedFile());
				JOptionPane.showMessageDialog(this, resultado);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Imposible cargar desde archivo "+ e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo");
		}
	}
	/**
	 * Actualiza la lista con los resultados de la búsqueda
	 */
	private void actualizarLista(IListaEncadenada<Pelicula> resultado) {
		listaPeliculas.setListData(resultado.toArray());
	}
	//-----------------------------------------------------------------
    // Manejo de eventos
    //-----------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("porNombre")) {
			String nombre = JOptionPane.showInputDialog("Buscar por nombre:");
			if (nombre != null && !nombre.equals("")) actualizarLista(mundo.buscarPeliculasPorNombre(nombre));
			else actualizarLista(mundo.darPeliculas());
		} else if (comando.equals("porAo")) {
			String año = JOptionPane.showInputDialog("Buscar por año:");
			try {
				int añO = Integer.parseInt(año);
				actualizarLista(mundo.buscarPeliculasPorAño(añO));
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Año inválido");
			}
		} else if (comando.equals("rangoAos")) {
			String añoI = JOptionPane.showInputDialog("El año inicial:");
			String añoF = JOptionPane.showInputDialog("El año final:");
			try {
				int añoi = Integer.parseInt(añoI);
				int añof = Integer.parseInt(añoF);
				actualizarLista(mundo.buscarPeliculasPorRangoAños(añoi, añof));
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Años inválidos");
			}
		}
	}
}