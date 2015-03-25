package uniandes.cupi2.cupIphone.core;

import java.io.File;
import java.io.Serializable;

import uniandes.cupi2.cupIphone.componentes.excepciones.EjecucionException;

/**
 * Interfaz que define la interacción que pueden tener los componentes
 * con el núcleo del sistema
 * @author Yeisson Oviedo
 *
 */
public interface ICore extends Serializable {

	/**
	 * Retorna la instancia de modelo de la aplicación
	 * correspondiente al id suministrado
	 * @param idAplicacion Id de la aplicación cuya instancia de modelo
	 * se quiere obtener
	 * @return Instancia del modelo. La clase a la que se debe hacer cast
	 * depende de cada componente
	 * @throws EjecucionException Si no se encuentra la aplicación con el ID indicado
	 */
	public Object darInstanciaModelo(String idAplicacion) throws EjecucionException;

	/**
	 * Le indica a la aplicación el directorio de datos que le corresponde
	 * @return Instancia de File apuntando al directorio de datos que le corresponde
	 */
	public File darDirectorioDatos();
}
