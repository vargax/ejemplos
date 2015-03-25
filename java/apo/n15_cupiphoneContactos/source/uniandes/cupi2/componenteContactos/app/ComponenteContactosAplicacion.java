package uniandes.cupi2.componenteContactos.app;

import javax.swing.JPanel;

import uniandes.cupi2.componenteContactos.interfaz.ComponenteContactosPanel;
import uniandes.cupi2.componenteContactos.mundo.ComponenteContactos;
import uniandes.cupi2.cupIphone.componentes.IAplicacion;
import uniandes.cupi2.cupIphone.core.ICore;

/**
 * Aplicación para el manejo de contactos del CupiPhone <br>
 * Esta aplicación esta compuesta por un panel principal y el modelo del mundo.
 */
public class ComponenteContactosAplicacion implements IAplicacion {
	 
	
	/**
	 * Referencia al core del cupiPhone, para localizar otros componentes y acceder al directorio de datos
	 */
	private ICore core;
	
	
	/**
	 * Panel principal del componente
	 */
	private ComponenteContactosPanel panel;
	
	
	/**
	 * Clase principal del mundo del componente
	 */
	private ComponenteContactos mundo;
	
	/**
	 * Esta aplicación se implementa como un singleton
	 */
	private static ComponenteContactosAplicacion instancia;
	
	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#darPanelPrincipal()
	 */
	public JPanel darPanelPrincipal() {
		return panel;
	}

	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#terminarEjecucion()
	 */
	public void terminarEjecucion() {
		panel.terminar();
	}

	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#darInstanciaModelo()
	 */
	public Object darInstanciaModelo() {
		return mundo;
	}

	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#cambiarCore(uniandes.cupi2.cupIphone.core.ICore)
	 */
	public void cambiarCore(ICore c) {
		core = c;
		
	}

	/* (non-Javadoc)
	 * @see uniandes.cupi2.cupIphone.componentes.IAplicacion#iniciarEjecucion()
	 */
	public void iniciarEjecucion() {
	    mundo = new ComponenteContactos(core);
		panel = new ComponenteContactosPanel( mundo);
	}

	/**
	 * Método necesario para interactuar con el core
	 * del teléfono
	 * @return La instancia de la clase
	 */
	public static IAplicacion darInstancia()
	{
		return instancia!=null? instancia: new ComponenteContactosAplicacion();
	}
}
