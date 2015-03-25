/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ComponenteContactos.java 1132 2010-09-14 20:52:52Z carl-veg $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_cupiphoneComponenteContactos
 * Autor: Yeisson Oviedo - 23-feb-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.componenteContactos.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.cupIphone.core.ICore;

/**
 *  
 */
public class ComponenteContactos implements IComponenteContactos
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------



    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	private static final String ARCHIVO_DATOS = "datos.contactos";

	/**
	 * Listado de contactos
	 */
	private Lista<Contacto> contactos;
	
	/**
	 * Referencia al núcleo
	 */
	private ICore core;	
	
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    /**
     *  Constructor
     * @param core 
     */
    @SuppressWarnings("unchecked")
	public ComponenteContactos(ICore core )
    {
    	this.core = core;
    	//Tratar de cargar datos previos
    	File f = new File(core.darDirectorioDatos(), ARCHIVO_DATOS);
    	try{
    		contactos = (Lista<Contacto>) new ObjectInputStream(new FileInputStream(f)).readObject();
    		
    	}
    	catch (Exception e){
    		//No fue posible cargar, inicializar vacío
    		contactos = new Lista<Contacto>();
    	}
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /** Permite registrar un contacto
     * @param c
     * @return true si logra agregar e contacto
     */
    public boolean registrarContacto (Contacto c){
    	boolean existe = contactos.buscar(c) != -1;
    	if (!existe){
    		contactos.agregar(c);
    		return true;
    	}
    	return false;
    }
    
    /* (non-Javadoc)
	 * @see uniandes.cupi2.componenteContactos.mundo.IComponenteContactos#darContactos()
	 */
    public Iterador<Contacto> darContactos(){
    	return contactos.darIterador();
    }
    
    /* (non-Javadoc)
	 * @see uniandes.cupi2.componenteContactos.mundo.IComponenteContactos#buscarContacto(java.lang.String)
	 */
    public Contacto buscarContacto (String nombre)
    {
    	for (int i = 0; i < contactos.darLongitud(); i++)
    		if (contactos.darElemento(i).darNombre().equals (nombre))
    			return contactos.darElemento(i);
    	return null;
    }

    /**
     * Permite eliminar un contacto
     * @param nombre Nombre del contacto
     * @return true si logra eliminar el contacto
     */
    public boolean eliminarContacto (String nombre)
    {
    	Contacto c = buscarContacto(nombre);
    	if (c != null){
    		contactos.eliminar(c);
    		return true;
    	}
    	return false;	
    } 
    
    /**
     * Guarda los datos de la aplicación
     */
    public void guardar(){
    	File f = new File(core.darDirectorioDatos(), ARCHIVO_DATOS);
    	try{
    		new ObjectOutputStream(new FileOutputStream(f)).writeObject(contactos);    		
    	}
    	catch (Exception e) {
    		System.err.println("No se pudo guardar los datos");
			e.printStackTrace();
		}
    }

    /**
     * Retorna los contactos como un arreglo
     * @return Arreglo
     */
	public Object[] darContactosArreglo() {
		Object [] res = new Object[contactos.darLongitud()];
		for (int i = 0; i < contactos.darLongitud(); i++)
			res[i] = contactos.darElemento(i);
		return res;
	}
}