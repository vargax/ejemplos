package pruebas;

import estructurasDatos.IIdentificableComparable;

class IdentificableComparable implements IIdentificableComparable<IdentificableComparable> {
	private String id;
	private int peso;
	public IdentificableComparable(String id, int peso) {
		this.id = id;
		this.peso = peso;
	}
	
	public int getPeso() {
		return this.peso;
	}
	@Override
	public String getId() {
		return this.id;
	}

	// Negativo si YO soy menor que el elemento pasado como parámetro
	// Positivo si YO soy mayor que el elemento pasado como parámetro
	@Override
	public int compareTo(IdentificableComparable arg0) {
		return this.peso - arg0.getPeso();
	}

	@Override
	public long darCosto() {
		return peso;
	}
	
	@Override
	public String toString() {
		return this.id;
	}
}