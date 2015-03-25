package vrgx.infracomp.sync;

public class T extends Thread {
	private int id;
	private int filaProcesar;
	
	public T ( int n ) {
		this.id = n;
		this.filaProcesar = -1;
	}
	
	public void run () {
		while (maxSincronico != null && maxSincronico.getContinuar()) {
			int nElementos = M[0].length;
			int max = 0;
			int columna = 0;
			
			for ( int j = 0; j < nElementos; j++) {
				if ( M[this.filaProcesar][j] > max ) {
					max = M[this.filaProcesar][j];
					columna = j;
				}
			}
			
			maxSincronico.anotar( max, id, columna );
			this.filaProcesar = maxSincronico.filaActual;
		}
	}

	private static int [][] M;
	private static Max maxSincronico;

	private static void crearMatriz ( int n ) {
		
		M = new int [n][n];
		int base = 26;
		int max = n*n/4;
		
		for ( int i = 0; i < n; i++ ) {
			for ( int j = 0; j < n; j++ ) {
				M[i][j] = base % max;
				base++;
			}
		}
		
	}
	
	public void setFilaProcesar(int nuevaFila) {
		this.filaProcesar = nuevaFila;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int nThreads = 6;
		int nFilas = 200; 
		
		crearMatriz ( nFilas );
		maxSincronico = new Max (nThreads, nFilas);		
	}
}
