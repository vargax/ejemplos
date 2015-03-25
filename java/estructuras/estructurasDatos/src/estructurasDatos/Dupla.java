package estructurasDatos;

public class Dupla<T,U> {
	protected T izq;
	protected U der;
	
	public Dupla(T t, U u) {
		this.izq = t;
		this.der = u;
	}
	
	public Dupla() {
		
	}

	public T getIzq() {
		return izq;
	}

	public void setIzq(T izq) {
		this.izq = izq;
	}

	public U getDer() {
		return der;
	}

	public void setDer(U der) {
		this.der = der;
	}
	
	
}
