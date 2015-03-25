package pruebas;

import estructurasDatos.arboles.IIndiceLexicograficoUnico;
import estructurasDatos.arboles.IndiceLexicograficoUnico;
import excepciones.CriterioOrdenamientoInvalidoException;
import excepciones.ElementoExisteException;

import junit.framework.TestCase;

public class TestIndiceLexicografoUnico extends TestCase {
	// Contenedora para realizar las pruebas
	private IIndiceLexicograficoUnico<Identificable> contenedora;
	// Algunos elementos para realizar las pruebas
	private String[] palabrasColombianas = {
			"chévere", "que bien", "divertido",
			"embarrarla", "meter", "la", "pata",
			"chichón", "de suelo", "bajito",
			"vacano", "buen", "tipo", "que bueno",
			"quiubo", "que tal", "que hubo",
			"sardino", "jovencito",
			"dorar", "la píldora", "engañar", "sutilmente",
			"mata", "planta",
			"saco", "chaqueta",  "jersey",
			"parqueadero","parking",
			"plata","dinero",
			"carro","coche",
			"aburrido","triste",
			"guayabo","morriña",
			"piso","suelo",
			"esfero","bolígrafo",
			"bomba","gasolinera",
			"gomelo"," pijo",
			"chupamedias","lameculos",
			"cola","culo", "trasero",
			"oso","vergüenza",
			"media","calcetín",
			"machuca","follar",
			"rico","bueno","bien"
	};
	// Constructor predeterminado de la clase
	public TestIndiceLexicografoUnico() {
		
	}
	// Llena la contenedora con algunos datos iniciales
	public void setupEscenario1() {
		contenedora = new IndiceLexicograficoUnico<Identificable>();
		for(String s : palabrasColombianas) {
			try {
				contenedora.agregarObjeto(new Identificable(s));
			} catch (ElementoExisteException e) {
				System.err.println("TestIndiceLexicografico.setupEscenario1().ElementoExisteException: "+e.getMessage());
				fail("No hay dos elementos el mismo id en los casos de prueba");
			} catch (CriterioOrdenamientoInvalidoException e) {
				System.err.println("TestIndiceLexicografico.setupEscenario1().CriterioOrdenamientoInvalidoException: "+e.getMessage());
				fail("Todos los criterios de ordenamiento son válidos");
			}
		}
	}
	
	public void testInicializarContenedora() {
		setupEscenario1();
		assertEquals(palabrasColombianas.length, contenedora.darNumeroElementos());
	}
	
	public void testAgregarElementosRepetidos() {
		setupEscenario1();
		try {
			for(String s : palabrasColombianas)
				contenedora.agregarObjeto(new Identificable(s));
			fail("El elemento recién agregado ya se encontraba en la contenendora");
		} catch (ElementoExisteException e) {
			
		} catch (CriterioOrdenamientoInvalidoException e) {
			System.err.println("TestIndiceLexicografico.testAgregarElementosRepetidos().CriterioOrdenamientoInvalidoException: "+e.getMessage());
			fail("Todos los criterios de ordenamiento son válidos");
		}
	}
	
	public void testBuscarElementos() {
		setupEscenario1();
		try {
			for(String s : palabrasColombianas) {
				Identificable i = contenedora.recuperarObjeto(s);
				assertEquals(i.getId(), s);
			}
		} catch (CriterioOrdenamientoInvalidoException e) {
			System.err.println("TestIndiceLexicografico.testBuscarElementos().CriterioOrdenamientoInvalidoException: "+e.getMessage());
			fail("Todos los criterios de ordenamiento son válidos");
		}
	}
}