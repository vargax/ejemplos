package estructurasDatos.arboles;

import estructurasDatos.IIdentificable;
import estructurasDatos.listas.IListaEncadenada;
import excepciones.CriterioOrdenamientoInvalidoException;
import excepciones.ElementoExisteException;

public class IndiceLexicograficoUnico<T extends IIdentificable> extends IndiceLexicografico<T> implements IIndiceLexicograficoUnico<T> {
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	/**
	 * Crea un nuevo indice lexicográfico único vacio
	 */
	public IndiceLexicograficoUnico() {
		super();
	}
	/**
	 * Crea un nuevo indice lexicográfico único con su primer elemento
	 * @throws CriterioOrdenamientoInvalidoException En caso que el identificador del primer objeto
	 * no genere un criterio de ordenamiento válido para el índice.
	 */
	public IndiceLexicograficoUnico(T primerElemento) throws CriterioOrdenamientoInvalidoException {
		super();
		agregarObjeto(primerElemento.getId(), primerElemento);
	}
	//-----------------------------------------------------------------
    // Métodos Interfaz
    //-----------------------------------------------------------------
	@Override
	public void agregarObjeto(T objetoGuardar) throws ElementoExisteException, CriterioOrdenamientoInvalidoException {
		String criterioOrden = objetoGuardar.getId();
		T candidato = recuperarObjeto(criterioOrden);
		if (candidato != null) throw new ElementoExisteException("Ya existe un elemento con id "+criterioOrden+" almacenado en el índice", candidato);
		agregarObjeto(criterioOrden, objetoGuardar);
	}
	
	@Override
	public T recuperarObjeto(String id) throws CriterioOrdenamientoInvalidoException {
		IListaEncadenada<T> resultados = super.buscarObjeto(id);
		return resultados.iterator().next();
	}
}
