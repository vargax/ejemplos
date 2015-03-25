package pruebas;

import estructurasDatos.IIdentificable;

class Identificable implements IIdentificable {
	private String id;

	public Identificable(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return this.id;
	}
}