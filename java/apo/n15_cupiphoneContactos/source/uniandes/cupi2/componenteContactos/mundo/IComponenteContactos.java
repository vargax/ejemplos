package uniandes.cupi2.componenteContactos.mundo;

import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Interface que define los servicios básicos del componente de contactos
 * @author Yeisson Oviedo
 *
 */
public interface IComponenteContactos {

	/**
	 * Retorna un iterador sobre el listado de contactos
	 * @return Iterador sobre el listado de contactos
	 */
	public Iterador<Contacto> darContactos();

	/**
	 * Busca el contacto identificado con el nombre suministrado
	 * @param nombre Nombre a buscar
	 * @return El contacto que corresponde al nombre, null si no lo encuentra
	 */
	public Contacto buscarContacto(String nombre);

}