package uniandes.cupi2.componenteCompartir.app;

import javax.swing.JPanel;

import uniandes.cupi2.componenteCompartir.interfaz.PanelComponenteCompartir;
import uniandes.cupi2.componenteCompartir.mundo.ComponenteCompartir;
import uniandes.cupi2.cupIphone.componentes.IAplicacion;
import uniandes.cupi2.cupIphone.core.Core;
import uniandes.cupi2.cupIphone.core.ICore;

/**
 * Aplicación para compartir archivos en el CupiPhone
 * Esta aplicación está compuesta por un panel principal y el modelo del mundo.
 */
public class ComponenteCompartirAplicacion implements IAplicacion {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * Referencia al core del cupiPhone
	 */
	private ICore core;
	/**
	 * Panel principal del componente
	 */
	private PanelComponenteCompartir panel;
	/**
	 * Clase principal del mundo del componente
	 */
	private ComponenteCompartir mundo;
	/**
	 * ??
	 */
	private static ComponenteCompartirAplicacion instancia;
	//-----------------------------------------------------------------
    // Métodos Interfaz
    //-----------------------------------------------------------------
	@Override
	public JPanel darPanelPrincipal() {
		return panel;
	}

	@Override
	public void cambiarCore(ICore c) {
		core = c;
	}

	@Override
	public void iniciarEjecucion() {
		System.out.println("Inicializando el mundo...");
		mundo = new ComponenteCompartir(core);
		System.out.println("Inicializando la interfaz gráfica...");
		panel = new PanelComponenteCompartir(mundo);
		mundo.addObserver(panel);
		System.out.println("Inicializando al servidor...");
		mundo.esperarConexiones();
	}

	@Override
	public void terminarEjecucion() {
		mundo.terminarEjecucion();
	}

	@Override
	public Object darInstanciaModelo() {
		return mundo;
	}

	/**
	 * Método necesario para interactuar con el core del teléfono
	 * @return La instancia de la clase
	 */
	public static IAplicacion darInstancia() {
		return instancia != null ? instancia : new ComponenteCompartirAplicacion();
	}
}
