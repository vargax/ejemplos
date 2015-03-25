package uniandes.cupi2.calculadoraPunnett.mundo;

public class Genotipo {
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	private Gen[] genes;
	private Alelo[][] combinacionesPosiblesAlelos;
	private String[] combinacionesPosiblesStrings;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	public Genotipo(Gen[] genesP) throws Exception {
		for (int i = 0; i < genesP.length; i++)
			for (int j = 0; j < genesP.length; j++)
				if (i != j && genesP[i].darCaracteristica().equals(genesP[j].darCaracteristica()))
					throw new Exception("El genotipo especificado contiene dos genes que representan la misma característica.");
		genes = genesP;
		calcularCombinaciones();
		generarCombinacionesString();
		System.out.println(darStringGenotipo());
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	/**
	 * Método de calcula las combinaciones posibles dados los genes del individuo padre.
	 */
	private void calcularCombinaciones() {
		// Las combinaciones posibles son 2 por cada uno de los genes del arreglo
		int combinacionesPosibles = (int) Math.pow(2,genes.length);
		// Matriz de alelos que contiene todas las combinaciones posibles para los genes dados
		combinacionesPosiblesAlelos = new Alelo[combinacionesPosibles][genes.length];
		// Matriz de alelos que contiene los dos alelos de cada gen
		Alelo[][] tempGen = new Alelo[genes.length][2];
		for (int i = 0; i < genes.length; i++) {
			// Recuperando los alelos de cada gen
			tempGen[i] = genes[i].darAlelos();
		}
		// Calculando las combinaciones posibles
		for (int i = 0; i < combinacionesPosibles; i++) {
			String combinacionActual = Integer.toBinaryString(i);
			// Completando con ceros a la izquierda de ser necesario
			for (int j = 1; j < tempGen.length; j++)
				if (combinacionActual.length() < tempGen.length) combinacionActual = "0"+combinacionActual;
				else break;
			// Inicializando arreglo para los alelos
			Alelo[] tempAlelos = new Alelo[tempGen.length];
			for (int j = 0; j < tempAlelos.length; j++) {
				int a = Integer.parseInt(""+combinacionActual.charAt(j));
				tempAlelos[j] = tempGen[j][a];
			}
			combinacionesPosiblesAlelos[i] = tempAlelos;
		}
	}
	/**
	 * Método que genera los Strings que representan cada una de las combinaciones posibles
	 */
	private void generarCombinacionesString() {
		combinacionesPosiblesStrings = new String[darNumeroCombinacionesPosibles()];
		for (int i = 0; i < combinacionesPosiblesStrings.length; i++) {
			char[] caracteres = new char[combinacionesPosiblesAlelos[i].length];
			for (int j = 0; j < caracteres.length; j++) caracteres[j] = combinacionesPosiblesAlelos[i][j].darRepresentacion();
			combinacionesPosiblesStrings[i] = new String(caracteres);
			System.out.println(combinacionesPosiblesStrings[i]);
		}
	}
	/** Método que devuelve las combinaciones posibles como una matriz de alelos
	 * @return Retorna una matriz de Alelos que contiene todas las combinaciones posibles 
	 * (dados los genes del padre) para calcular los genotipos del individuo hijo.
	 * Esta matriz tendrá tantas filas como combinaciones posibles (2^#genes) y tantas
	 * columnas como #genes.
	 */
	public Alelo[][] darCombinacionesPosiblesAlelo() {
		return combinacionesPosiblesAlelos;
	}
	/** Método que devuelve las combinaciones posibles como un arreglo de Strings **/
	public String[] darCombinacionesPosiblesString() {
		return combinacionesPosiblesStrings;
	}
	/**
	 * Método que devuelve el string que representa el genotipo
	 */
	public String darStringGenotipo() {
		String respuesta = "";
		for (int i = 0; i < genes.length; i++)
			respuesta += genes[i].darStringGen();
		return respuesta;
	}
	/**
	 * Método que retorna el número de genes que conforman el genotipo actual
	 */
	public int darNumeroGenes() {
		return genes.length;
	}
	/**
	 * Método que retorna el número de combinaciones posibles para el genotipo actual	
	 */
	public int darNumeroCombinacionesPosibles() {
		return (int) Math.pow(2,genes.length);
	}
	/**
	 * Método que devuelve las características que representan los genes que conforman este genotipo
	 * @return Arreglo de Strings que contiene la característica que representa cada uno de los genes del genotipo
	 */
	public String[] darCaracteristicas() {
		String[] respuesta = new String[genes.length];
		for (int i = 0; i < genes.length; i++) respuesta[i] = genes[i].darCaracteristica();
		return respuesta;
	}
	/**
	 * Método que devuelve los genes que conforman este genotipo
	 */
	public Gen[] darGenes() {
		return genes;
	}
	/**
	 * Método que devuelve el Fenotipo que representa este Genotipo
	 */
	public String[] darFenotipo() {
		String[] respuesta = new String[genes.length];
		for (int i = 0; i < respuesta.length; i++) respuesta[i]=genes[i].darCaracteristicaDominante();
		return respuesta;
	}
	/**
	 * Método que devuelve true si el genotipo es Heterocigoto, de lo contrario devuelve false
	 */
	public boolean esHeterocigoto() {
		boolean respuesta = false;
		for (int i = 0; i < genes.length && !respuesta; i++)
			if(genes[i].esHeterocigoto()) respuesta = true;
		return respuesta;
	}
}
