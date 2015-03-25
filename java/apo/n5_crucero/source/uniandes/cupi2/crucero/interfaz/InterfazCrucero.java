package uniandes.cupi2.crucero.interfaz;

import java.awt.*;
import javax.swing.*;
import uniandes.cupi2.crucero.mundo.*;

public class InterfazCrucero extends JFrame {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private Crucero crucero;
	
	//-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	private PanelDestino panelDestino;
	private PanelOpciones panelOpciones;
	
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public InterfazCrucero() {
		// Crea el mundo del problema
		crucero = new Crucero();
		
		// Configura la ventana
		setTitle("CUPI Crucero");
		setSize(565,536);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Genera los páneles
		PanelImagen panelImagen = new PanelImagen();
		add(panelImagen, BorderLayout.NORTH);
		
		panelDestino = new PanelDestino(this);
		add(panelDestino, BorderLayout.CENTER);
		
		panelOpciones = new PanelOpciones(this);
		add(panelOpciones, BorderLayout.SOUTH);
		
		// Carga el primer destino
		panelDestino.cambiarDestino(crucero.darDestinoActual().darCiudad()+" - "+crucero.darDestinoActual().darPais());
		try {
			Foto tempFoto = crucero.darImagenDestinoActual();
			panelDestino.cambiarImagen(tempFoto.darImagen(), tempFoto.darTitulo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//-----------------------------------------------------------------
    // Requerimientos Funcionales
    //-----------------------------------------------------------------
	private void refrescarDestino() {
		try {
			panelDestino.cambiarDestino(crucero.darDestinoActual().darCiudad()+" - "+crucero.darDestinoActual().darPais());
			Foto tempFoto = crucero.darImagenDestinoActual();
			panelDestino.cambiarImagen(tempFoto.darImagen(), tempFoto.darTitulo());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Cupi Crucero", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void imagenAnterior() {
		try {
			Foto tempFoto = crucero.darImagenAnteriorDelDestinoActual();
			panelDestino.cambiarImagen(tempFoto.darImagen(), tempFoto.darTitulo());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Cupi Crucero", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void imagenSiguiente() {
		try {
			Foto tempFoto = crucero.darImagenSiguienteDelDestinoActual();
			panelDestino.cambiarImagen(tempFoto.darImagen(), tempFoto.darTitulo());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Cupi Crucero", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void destinoAnterior() {
		try {
			crucero.darAnteriorDestino();
			refrescarDestino();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Cupi Crucero", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void destinoSiguiente() {
		try {
			crucero.darSiguienteDestino();
			refrescarDestino();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Cupi Crucero", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void agregar(String nombrePais, String nombreCiudad) {
		try {
			crucero.agregarDestino(nombreCiudad, nombrePais);
			JOptionPane.showMessageDialog(this, "El destino ha sido agregado", "Agregar Destino", JOptionPane.INFORMATION_MESSAGE);
			refrescarDestino();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Cupi Crucero", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void buscar(String nombrePais, String nombreCiudad) {
		crucero.buscarDestino(nombreCiudad, nombrePais);
		refrescarDestino();
	}
	
	public void eliminar() {
		try {
			crucero.eliminarDestinoActual();
			JOptionPane.showMessageDialog(this, "El destino ha sido eliminado", "Eliminar Destino", JOptionPane.INFORMATION_MESSAGE);
			refrescarDestino();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Cupi Crucero", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void opcion1() {
		String mensaje = crucero.metodo1();
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void opcion2() {
		String mensaje = crucero.metodo2();
		JOptionPane.showMessageDialog(this, mensaje);
	}
	//-----------------------------------------------------------------
    // Programa Principal
    //-----------------------------------------------------------------
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InterfazCrucero interfaz = new InterfazCrucero();
			interfaz.setVisible(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
