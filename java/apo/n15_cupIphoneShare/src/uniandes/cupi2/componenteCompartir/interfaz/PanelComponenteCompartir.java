package uniandes.cupi2.componenteCompartir.interfaz;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.componenteCompartir.mundo.IComponenteCompartir;
import javax.swing.JTabbedPane;

import estructurasDatos.IListaEncadenada;
import estructurasDatos.ListaEncadenada;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class PanelComponenteCompartir extends JPanel implements Observer, ActionListener {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El mundo de la aplicaciÃ³n
	 */
	public IComponenteCompartir mundo;
	/**
	 * El panel para desplegar los archivos
	 */
	private PanelArchivos panelArchivos;
	/**
	 * El panel de configuraciÃ³n de la aplicaciÃ³n
	 */
	private PanelConfiguracion panelConfiguracion;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public PanelComponenteCompartir(IComponenteCompartir mundoP) {
		mundo = mundoP;
		panelArchivos = new PanelArchivos(this);
		panelConfiguracion = new PanelConfiguracion(this);
		
		setLayout(new GridLayout(0, 1, 0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		tabbedPane.addTab("Archivos", panelArchivos);
		tabbedPane.addTab("Configuración", panelConfiguracion);
		
		actualizarLocales();
		actualizarRemotos();
		actualizarUsuarios();
		actualizarDescargas();
	}
	//-----------------------------------------------------------------
    // MÃ©todos observador
    //-----------------------------------------------------------------
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 == null) {
			System.out.println("INTERFAZ: Notificación de actualización general...");
			actualizarLocales();
			actualizarRemotos();
			actualizarUsuarios();
			actualizarDescargas();
		} else {
			System.out.println("INTERFAZ: Notificación de actualización para "+arg1);
			if (((String)arg1).equals("locales")) actualizarLocales();
			if (((String)arg1).equals("remotos")) actualizarRemotos();
			if (((String)arg1).equals("usuarios")) actualizarUsuarios();
			if (((String)arg1).equals("estadisticas")) actualizarLocales();
			if (((String)arg1).equals("descargando")) actualizarDescargas();
		}
	}
	/**
	 * Actualiza la lista de archivos locales
	 * @param nuevaLista
	 */
	private void actualizarLocales() {
		IListaEncadenada<String> nuevaLista = mundo.getLocales();
		panelArchivos.listaArchivosLocales.setListData(nuevaLista.toArray());
		panelConfiguracion.listaArchivos.setListData(nuevaLista.toArray());
	}
	/**
	 * Actualiza la lista de archivos remotos
	 * @param nuevaLista
	 */
	private void actualizarRemotos() {
		IListaEncadenada<String> nuevaLista = mundo.getRemotos();
		panelArchivos.listaArchivosRemotos.setListData(nuevaLista.toArray());	
	}
	/**
	 * Actualiza la lista de usuarios conectados
	 */
	private void actualizarUsuarios() {
		IListaEncadenada<String> nuevaLista = mundo.usuariosConectados();
		panelConfiguracion.listaUsuarios.setListData(nuevaLista.toArray());
	}
	/**
	 * Actualiza en número y tamaño de los archivos que se están descargando actualmente 
	 */
	private void actualizarDescargas() {
		float[] descargas = mundo.getDescargas();
		panelArchivos.lblDescargando.setText("Enviando: "+descargas[0]);
		panelArchivos.lblTamao.setText("Tamaño: "+descargas[1]);
		System.out.println("INTERFAZ: Enviando = "+descargas[0]+" Tamaño = "+descargas[1]);
	}
	/**
	 * Carga las estadísticas del archivo seleccionado
	 */
	private void cargarEstadisticas(String nombreArchivo) {
		IListaEncadenada<String> nuevaLista = mundo.estadisticasArchivo(nombreArchivo);
		if (nuevaLista == null) {
			nuevaLista = new ListaEncadenada<String>();
			nuevaLista.agregar("Ninguna estadÃ­stica disponible");
		}
		panelConfiguracion.listaDescargas.setListData(nuevaLista.toArray());
	}
	//-----------------------------------------------------------------
    // Manejo de eventos
    //-----------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		if (comando.equals("aplicar")) {
			mundo.definirConfiguracion(panelConfiguracion.txtNombreCliente.getText(), panelConfiguracion.txtPuerto.getText());
		} else if (comando.equals("conectar")) {
			try {
				new JOptionPane();
				String parametro = JOptionPane.showInputDialog(null,"Conectar con:","localhost:9876");
				if (parametro != null) {
					String[] parametros = parametro.split(":");
					String servidor = parametros[0];
					int puerto = Integer.parseInt(parametros[1]);
					mundo.conectar(servidor, puerto);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Parametros incorrectos","Conectar con cupiPhone", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} else if (comando.equals("buscar")) {
			String criterioBusqueda = JOptionPane.showInputDialog(null,"Buscar archivo:");
			if (criterioBusqueda != null) {
				Object[] resultados = mundo.buscarArchivo(criterioBusqueda).toArray();
				if (resultados.length > 0) panelArchivos.listaArchivosRemotos.setListData(resultados);
				else JOptionPane.showMessageDialog(null, "No se encontró ningún archivo que coincida con "+criterioBusqueda,"Buscar en Remotos",JOptionPane.INFORMATION_MESSAGE);
			} else actualizarRemotos();
		}
	}
}
