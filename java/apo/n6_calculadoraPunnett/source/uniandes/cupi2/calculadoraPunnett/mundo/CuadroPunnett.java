package uniandes.cupi2.calculadoraPunnett.mundo;
/*
 * Recibe un arreglo de dos genotipos que representan los genotipos 
 * de los padres y calcula a partir
 * 
 */
public class CuadroPunnett {
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	private Genotipo[] genotiposPadres;
	private Genotipo[][] genotiposPosibles;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	public CuadroPunnett(Genotipo[] genotiposPadresP) throws Exception {
		if (genotiposPadresP.length != 2) throw new Exception("La calculadora requiere exactamente dos genotipos padre");
		genotiposPadres = genotiposPadresP;
		int combinacionesPosiblesPadre = genotiposPadres[0].darNumeroCombinacionesPosibles();
		int combinacionesPosiblesMadre = genotiposPadres[1].darNumeroCombinacionesPosibles();
		genotiposPosibles = new Genotipo[combinacionesPosiblesPadre][combinacionesPosiblesMadre];
		// Calculando genotipos posibles
		calcularGenotipos();
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	private void calcularGenotipos() throws Exception {
		Alelo[][] combinacionesPadre = genotiposPadres[0].darCombinacionesPosiblesAlelo();
		Alelo[][] combinacionesMadre = genotiposPadres[1].darCombinacionesPosiblesAlelo();
		// Extrayendo características de cada gen
		String[] caracteristicasGenes = genotiposPadres[0].darCaracteristicas();
		
		genotiposPosibles = new Genotipo[combinacionesPadre.length][combinacionesMadre.length];
		// Calculando el número de alelos a recorrer en cada iteración
		int numeroAlelos = combinacionesPadre[0].length;
		// Recorriendo combinaciones del padre y de la madre
		for (int i = 0; i < combinacionesPadre.length; i++)
			for (int j = 0; j < combinacionesMadre.length; j++) {
				// Recorriendo cada uno de los alelos y generando genes
				Gen[] tempGenes = new Gen[numeroAlelos];
				for (int k = 0; k < numeroAlelos; k++) {
					Alelo[] tempAlelos = {combinacionesPadre[i][k], combinacionesMadre[j][k]};
					tempGenes[k] = new Gen(tempAlelos, caracteristicasGenes[k]);
				}
				genotiposPosibles[i][j] = new Genotipo(tempGenes);
			}
	}
	/**
	 * Método que devuelve una matriz de Strings con la representación de cada uno de los genotipos posibles para los hijos.
	 */
	public String[][] darGenotiposHijoString() {
		String[][] respuesta = new String[genotiposPosibles.length][genotiposPosibles[0].length];
		for (int i = 0; i < respuesta.length; i++)
			for (int j = 0; j < respuesta[i].length; j++)
				respuesta[i][j] = genotiposPosibles[i][j].darStringGenotipo();
		return respuesta;
	}
	/**
	 * Método que calcula la probabilidad de un genotipo específico
	 */
	public float calcularProbabilidadGenotipo(Genotipo genotipoP) {
		int contador = 0;
		for (int i = 0; i < genotiposPosibles.length; i++)
			for (int j = 0; j < genotiposPosibles[i].length; j++) {
				Genotipo tempGenotipo = genotiposPosibles[i][j];
				if(tempGenotipo.darStringGenotipo().equals(genotipoP.darStringGenotipo())) contador++;
			}
		int numeroGenotiposPosibles = genotiposPosibles.length*genotiposPosibles[0].length;
		return (float)contador/numeroGenotiposPosibles;
	}
	/** 
	 * Método de devuelve los genotipos que generan este cuadro
	 */
	public Genotipo[] darGenotiposPadres() {
		return genotiposPadres;
	}
	/**
	 * Método que calcula la frecuencia de aparición de un gen en la diagonal
	 */
	public float calcularFrecuenciaGen(Gen genP) {
		float contador = 0;
		System.out.println("Calculando la frecuencia para el gen "+genP.darStringGen());
		for (int i = 0; i < genotiposPosibles.length; i++) {
			Gen[] tempGenes = genotiposPosibles[i][i].darGenes();
			for (int j = 0; j < tempGenes.length; j++)
				if (tempGenes[j].darStringGen().equals(genP.darStringGen())) contador++;
		}
		contador = 100*(contador/(genotiposPosibles.length));
		return contador;
	}
	/**
	 * Método que busca genotipos homocigotos entre los genotipos posibles
	 */
	public boolean hayHomocigotos() {
		boolean respuesta = false;
		for (int i = 0; i < genotiposPosibles.length && !respuesta; i++)
			for (int j = 0; j < genotiposPosibles[i].length && !respuesta; j++) {
				Genotipo tempGenotipo = genotiposPosibles[i][j];
				if (!tempGenotipo.esHeterocigoto()) respuesta = true;
			}
		return respuesta;
	}
}
