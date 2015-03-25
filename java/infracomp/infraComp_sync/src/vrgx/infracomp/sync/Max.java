package vrgx.infracomp.sync;

public class Max {
	
	int nFilas;
	int nThreads;
	int filaActual;
	
	boolean continuar;
	
	int max;
	int columna;
	int numThread;
	
	public Max(int nThreads, int nFilas) {
		this.nFilas = nFilas - 1;
		this.nThreads = nThreads;
		
		this.continuar = true;
		
		this.filaActual = 0;
		
		this.max = -1;
		this.numThread = 0;
		this.columna = -1;
		
		for (int i = 0; i < this.nThreads; i++) {
			T t = new T (i);
			t.setFilaProcesar(this.filaActual);
			t.start();
			this.filaActual++;
		}
	}
	
	public synchronized void anotar(int max, int id, int columna) {
		if (this.max <= max) {
			this.max = max;
			this.numThread = id;
			this.columna = columna;
		}
		
		this.filaActual++;
		if (nFilas == filaActual) {
			this.continuar = false;
			System.out.println("El máximo es "+this.max+" y está en la coordenada ("+this.numThread+","+this.columna+")");
		}
	}
	
	public synchronized boolean getContinuar() {
		return this.continuar;
	}

	public int getMax() {
		return max;
	}
}
