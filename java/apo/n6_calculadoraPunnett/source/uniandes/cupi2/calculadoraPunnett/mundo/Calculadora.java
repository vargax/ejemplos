package uniandes.cupi2.calculadoraPunnett.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Calculadora {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * Arreglo para las combinaciones homocigotas y heterocigotas generadas a partir del archivo de propiedades
	 * genes[i][0] : Gen homocigoto dominante generado a partir del gen heterocigoto i.
	 * genes[i][1] : Gen heterocigoto i extraido del archivo de propiedades.
	 * genes[i][2] : Gen homocigoto recesivo generado a partir del gen heterocigoto i.
	 */
	private Gen[][] genes;
	/** Arreglo para los genotipos posibles a partir de los genes **/
	private Genotipo[] genotipos;
	/** Contiene el Cuadro de Punnett generado a partir de los genotipos de los padres seleccionados por el usuario **/
	private CuadroPunnett punnett;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	/**
	 * Crea el mundo del problema a partir del archivo de propiedades.
	 * 1. Recibe el archivo y lo convierte a un objeto de tipo properties.
	 * 2. Procesa las propiedades extrayendo cada uno de los genes, con sus alelos y fenotipos.
	 * 3. Genera las combinaciones homocigotas dominantes y homocigotas recesivas a partir de los genes heterocigotos extraidos de las propiedades
	 * 4. Genera todos los genotipos posibles a partir de las combinaciones de los genes homocigotos y heterocigotos. 
	 */
	public Calculadora(File archivo) throws Exception {
		//-----------------------
		// Recibiendo el archivo
		//-----------------------
		FileInputStream in = new FileInputStream(archivo);
		Properties propiedades = new Properties();
		try {
			// Generando propiedades
			propiedades.load(in);
			// Cerrando el archivo
			in.close();
		} catch (Exception e){
			e.printStackTrace();
			throw new Exception("Formato Inválido");
		}
		//-----------------------
		// Procesando propiedades
		//-----------------------
		// Recuperando el número de genes a procesar
		String strNumeroGenes = propiedades.getProperty("punnett.genes");
		int numeroGenes = Integer.parseInt(strNumeroGenes);
		System.out.println("Se han encontrado "+numeroGenes+" genes");
		// Inicializando el arreglo para almacenar los genes
		Gen[] genesHeterocigotos = new Gen[numeroGenes];
		// Recuperando cada uno de los genes
		for (int i = 1; i <= numeroGenes; i++) {
			// Recuperando la caracteristica del gen
			String strCaracteristicaGen = propiedades.getProperty("punnett.gen"+i);
			// Recuperando los dos alelos del gen
			Alelo[] tempAlelos = new Alelo[2];
			System.out.println("Extrayendo alelos...");
			for (int j = 1; j <= 2; j++) {
				String strAlelo = propiedades.getProperty("punnett.gen"+i+".alelo"+j);
				System.out.println("Alelo "+strAlelo);
				String strFenotipo = propiedades.getProperty("punnett.gen"+i+".fenotipo"+j);
				System.out.println("Fenotipo "+strFenotipo);
				tempAlelos[j-1] = new Alelo(strAlelo.charAt(0), strFenotipo);
			}
			// Creando el gen a partir de los alelos recuperados
			genesHeterocigotos[i-1] = new Gen(tempAlelos, strCaracteristicaGen);
			// Verificando que sea un gen heterocigoto
			if (!genesHeterocigotos[i-1].esHeterocigoto()) 
				throw new Exception ("El gen " + i + " definido en el archivo de propiedades no es heterocigoto.");
		}
		//------------------------------
		// Calculando genes posibles
		//------------------------------
		// Generando genes homocigotos a partir de los heterocigotos
		Gen[][] genesHomocigotos = new Gen[genesHeterocigotos.length][2];
		for (int i = 0; i < genesHomocigotos.length; i++) {
			Alelo[] tempAlelos = genesHeterocigotos[i].darAlelos();
			String tempCaracteristica = genesHeterocigotos[i].darCaracteristica();
			// Homocigoto dominante
			Alelo[] tempDominante = {tempAlelos[0], tempAlelos[0]};
			Gen homocigotoDominante = new Gen(tempDominante, tempCaracteristica);
			// Homocigoto recesivo
			Alelo[] tempRecesivo = {tempAlelos[1], tempAlelos[1]};
			Gen homocigotoRecesivo = new Gen(tempRecesivo, tempCaracteristica);
			// Asignando al arreglo
			genesHomocigotos[i][0] = homocigotoDominante;
			genesHomocigotos[i][1] = homocigotoRecesivo;
		}
		// Almacenado en el arreglo de genes
		genes = new Gen[genesHeterocigotos.length][3];
		for (int i = 0; i < genes.length; i++) {
			genes[i][0] = genesHomocigotos[i][0];
			genes[i][1] = genesHeterocigotos[i];
			genes[i][2] = genesHomocigotos[i][1];
		}
		//------------------------------
		// Calculando genotipos posibles
		//------------------------------
		// Calculando los genotipos posibles a partir de las combinaciones homocigotas y heterocigotas
		int combinacionesPosibles = (int) Math.pow(3,genes.length);
		System.out.println("Calculando los "+combinacionesPosibles+" genotipos posibles");
		genotipos = new Genotipo[combinacionesPosibles];
		// Calculando cada una de las combinaciones
		for (int i = 0; i < combinacionesPosibles; i++) {
			// Convirtiendo a base 3 la combinación posible actual
			String combinacionActual = Integer.toString(i,3);
			// Completando con ceros a la izquierda de ser necesario
			for (int j = 1; j < genes.length; j++)
				if (combinacionActual.length() < genes.length) combinacionActual = "0"+combinacionActual;
				else break;
			// Inicializando arreglo para los genes
			Gen[] tempGenes = new Gen[genes.length];
			for (int j = 0; j < genes.length; j++) {
				int a = Integer.parseInt(""+combinacionActual.charAt(j));
				tempGenes[j] = genes[j][a];
			}
			// Creando genotipo a partir de los genes y anexándolo al arreglo de genotipos
			genotipos[i] = new Genotipo(tempGenes);
		}
	}
	/**
	 * Método que devuleve las combinaciones posibles para los genotipos generados
	 */
	public int darCombinacionesPosibles(){
		return genotipos[0].darNumeroCombinacionesPosibles();
	}
	/**
	 * Método que devuelve un arreglo de Strings con los genotipos posibles para los padres.
	 */
	public String[] darGenotiposPosibles() {
		String[] respuesta = new String[genotipos.length];
		for (int i = 0; i < genotipos.length; i++)
			respuesta[i] = genotipos[i].darStringGenotipo();
		return respuesta;
	}
	/**
	 * Método que genera el Cuadro de Punnett dados los genotipos de los padres
	 * @return Arreglo de Strings de 2xcombinacionesPosibles que contiene en la
	 * primera dimensión las combinaciones posibles para el padre y en la segunda
	 * dimensión las combinaciones posibles para la madre. 
	 */
	public void generarPunnett(String[] genotiposPadres) throws Exception {
		// Buscando los genotipos de los padres
		Genotipo[] tempGenotipos = new Genotipo[2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < genotipos.length; j++) {
				Genotipo tempGenotipo = genotipos[j];
				if (tempGenotipo.darStringGenotipo().equals(genotiposPadres[i]))
					tempGenotipos[i] = tempGenotipo;
			}
		// Generando el Cuadro de Punnett a partir de los genotipos encontrados
		punnett = new CuadroPunnett(tempGenotipos);
	}
	/** 
	 * Método que devuelve las combinaciones posibles de los genotipos de los padres 
	 */
	public String[][] darCombinacionesPosiblesPadres() {
		Genotipo[] tempGenotipos = punnett.darGenotiposPadres();
		String[] combPadre = tempGenotipos[0].darCombinacionesPosiblesString();
		String[] combMadre = tempGenotipos[1].darCombinacionesPosiblesString();
		String[][] respuesta = new String[2][combPadre.length];
		respuesta[0] = combPadre;
		respuesta[1] = combMadre;
		return respuesta;		
	}
	/**
	 * Método que devuelve los genotipos posibles para el hijo 
	 */
	public String[][] darGenotiposPosiblesHijo() {
		return punnett.darGenotiposHijoString();
	}
	/**
	 * Método que devuelve los fenotipos posibles
	 * @return Devuelve una matriz de ix3 Strings que contiene las características y los fenotipos de cada uno de los genes
	 * heterocigotos
	 * respuesta[i][0] : Característica Gen i
	 * respuesta[i][1] : Fenotipo Alelo 1 Gen i (Dominante)
	 * respuesta[i][2] : Fenotipo Alelo 2 Gen i (Recesivo)
	 */
	public String[][] darFenotipos() {
		String[][] respuesta = new String[genes.length][3];
		for (int i = 0; i < respuesta.length; i++) {
			respuesta[i][0] = genes[i][1].darCaracteristica();
			respuesta[i][1] = genes[i][1].darCaracteristicaDominante();
			respuesta[i][2] = genes[i][1].darCaracteristicaRecesiva();
		}
		return respuesta;
	}
	/**
	 * Método que calcula la probabilidad de ocurrencia de un genotipo específico
	 */
	public float calcularProbabilidad(String[] fenotipos) {
		// Generando string que representa todos los fenotipos
		String fenotiposP = "";
		for (int i = 0; i < fenotipos.length; i++) fenotiposP += fenotipos[i];
		float respuesta = 0;
		// Buscando el genotipo correspondiente
		for (int i = 0; i < genotipos.length; i++) {
			Genotipo tempGenotipo = genotipos[i];
			String[] fenotiposComp = tempGenotipo.darFenotipo();
			String fenotiposString = "";
			for (int j = 0; j < fenotiposComp.length; j++) {
				fenotiposString += fenotiposComp[j]; 
			}
			// Comparando fenotipos
			if(fenotiposString.equals(fenotiposP)) {
				respuesta = punnett.calcularProbabilidadGenotipo(genotipos[i]);
				break;
			}
		}
		return respuesta;
	}
	/**
	 * Método 1
	 */
	public float metodo1(String genP) throws Exception{
		Gen tempGen = null;
		boolean continuar = true;
		int i = 0; int j = 0;
		for (i = 0; i < genes.length && continuar; i++)
			for(j = 0; j < genes[i].length; j++)
				if (genes[i][j].darStringGen().equals(genP)){
					continuar = false;
					tempGen = genes[i][j];
				}
					
		if (continuar) throw new Exception("El gen "+genP+" no se encuentra listado en este cuadro.");
		return punnett.calcularFrecuenciaGen(tempGen);
	}
	/**
	 * Método 2
	 */
	public String metodo2() {
		boolean homocigotos = punnett.hayHomocigotos();
		if (homocigotos) return "Existen genes homocigotos en el cruce";
		else return "El resultado del cruce es enteramente heterocigoto";
	}
}