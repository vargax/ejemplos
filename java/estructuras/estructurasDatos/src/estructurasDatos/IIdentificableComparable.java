package estructurasDatos;


public interface IIdentificableComparable<T> extends Comparable<T>, IIdentificable {
	/**
	 * Entrega el costo del objeto T
	 * @return Long que representa el costo de T
	 */
	public long darCosto();
}
