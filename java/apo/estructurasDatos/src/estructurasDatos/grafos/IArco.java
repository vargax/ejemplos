package estructurasDatos.grafos;

import estructurasDatos.IIdentificable;
import estructurasDatos.IIdentificableComparable;
/**
 * 
 * @author cvargasc
 *
 * @param <T> El tipo de los vertices
 * @param <U> El tipo de los arcos
 */
public interface IArco<T extends IIdentificable, U extends IIdentificableComparable<U>> extends IIdentificableComparable<U> {
	/**
	 * Entrega el objeto almacenado en el arco
	 * @return El objeto almacenado en el arco
	 */
	public U darObjeto();
	/**
	 * Entrega el destino del arco
	 * @return Una referencia al vertice sucesor del arco
	 */
	public IVertice<T,U> darDestino();
	
}
