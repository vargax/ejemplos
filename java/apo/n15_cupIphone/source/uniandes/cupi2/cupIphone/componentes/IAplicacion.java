package uniandes.cupi2.cupIphone.componentes;

import javax.swing.JPanel;

import uniandes.cupi2.cupIphone.core.ICore;

/**
 * Interfaz que define el punto de entrada de una aplicación que se
 * instala en el CupIphone
 * @author Yeisson Oviedo
 */
public interface IAplicacion{

	/**
	 * Retorna el panel principal de la aplicación
	 * @return Panel principal 
	 */
	public JPanel darPanelPrincipal();

	/**
	 * Método que es invocado al momento de inicializar la aplicación
	 * @param c Referencia al núcleo de la aplicación
	 */
	public void cambiarCore(ICore c);
	
	/**
	 * Le indica a la aplicación que su ejecución ha comenzado
	 */
	public void iniciarEjecucion();
	
	/**
	 * Método invocado justo antes de cerrar el componente
	 */
	public void terminarEjecucion();
	
	/**
	 * Retorna la instancia del modelo de esta aplicación
	 * @return La instancia del modelo. La clase a la que se debe 
	 * hacer cast depende de cada componente
	 */
	public Object darInstanciaModelo();
		
}
