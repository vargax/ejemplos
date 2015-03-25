/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ComponenteContactosPanel.java 1139 2011-03-09 16:04:20Z n-calder $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_cupiphoneComponenteContactos
 * Autor: Yeisson Oviedo - 23-feb-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.componenteContactos.interfaz;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.componenteContactos.mundo.ComponenteContactos;
import uniandes.cupi2.componenteContactos.mundo.Contacto;
import uniandes.cupi2.cupIphone.core.ICore;


/**
 * Esta es la ventana principal de la aplicación.
 */
public class ComponenteContactosPanel extends JPanel
{
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Clase principal del mundo
	 */
	private ComponenteContactos componenteContactos;

	//-----------------------------------------------------------------
	// Atributos de la interfaz
	//-----------------------------------------------------------------

	/**
	 * Panel con las extensiones
	 */
	private PanelListaContactos panelListaContactos;

	/**
	 * Panel con la imagen del encabezado
	 */
	private PanelVer panelVer;
	
	
	//-----------------------------------------------------------------
	// Constructores
	//-----------------------------------------------------------------

	/**
	 * Crea el panel principal del componente <br>
	 * @param mundo Clase principal del mundo de la aplicación
	 */
	public ComponenteContactosPanel( ComponenteContactos mundo)
	{    	
		componenteContactos = mundo;

		// Construye la forma
		setLayout( new BorderLayout( ) );
		setBorder(new TitledBorder("Componente Contactos"));

		//Creación de los paneles aquí

		panelListaContactos = new PanelListaContactos( this );
		add( panelListaContactos, BorderLayout.CENTER);								
	}

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------

	/**
	 * Indica al panel que se debe terminar la ejecución
	 */
	public void terminar() {
		componenteContactos.guardar();		
	}

	/**
	 * Agrega un contacto
	 */
	public void mostrarAgregar() {	
		remove(panelListaContactos);
		add(panelVer = new PanelVer(this, null), BorderLayout.CENTER);
	}

	/**
	 * Elimina un contacto
	 * @param nombre 
	 */
	public void eliminarContacto(String nombre) {
		componenteContactos.eliminarContacto(nombre);
		panelListaContactos.actualizar();
	}

	/**
	 * @param contacto
	 */
	public void agregar(Contacto contacto) {
		componenteContactos.registrarContacto(contacto);
		volver();
	}

	/**
	 * Vuelve a mostrar el contenido del panel principal
	 */
	public void volver() {		
		remove(panelVer);
		panelVer = null;
		add(panelListaContactos = new PanelListaContactos( this ), BorderLayout.CENTER);
		panelListaContactos.actualizar();
		
	}

	/**
	 * Retorna un iterador sobre el listado de contactos 
	 * @return Listado de contactos
	 */
	public Object[] darContactosArreglo() {
		return componenteContactos.darContactosArreglo();
	}

	/**
	 * Muestra el contacto
	 * @param c Contacto a mostrar
	 */
	public void ver(Contacto c) {		
		panelVer = new PanelVer(this, c);
		remove(panelListaContactos);
		add(panelVer, BorderLayout.CENTER);
	}

	/**
	 * Busca un contacto
	 * @param nombreABuscar Nombre
	 */
	public void buscar(String nombreABuscar) {
		Contacto c = componenteContactos.buscarContacto(nombreABuscar);
		if (c != null)
			ver(c);
		else
		{
			//Mostrar error			
		}
	}
}