package uniandes.cupi2.cupiFinca.mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

public class Cerca extends Construccion {
	
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	public final static String NOMBRE_CONSTRUCCION = "Cerca";
	public final static int COSTO_CONSTRUCCION = 50;
	public final static String RUTA_IMAGEN = "./data/imagenes/cerca.png";
	
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	
	public Cerca(int pX, int pY, int pAncho) {
		super(NOMBRE_CONSTRUCCION, pX, pY, pAncho, COSTO_CONSTRUCCION);
	}

	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	@Override
	public void dibujar(Graphics2D graphics) {
    	Graphics2D g = (Graphics2D) graphics;
    	
    	// Coordenadas de la esquina del cuadrado de la cerca
        int cotaX = x * ancho;
        int cotaY = y * ancho;
        
        /* -- DEBUG --
        g.setColor( Color.BLUE );
        g.fillRect( cotaX, cotaY, ancho, ancho );
        
        g.setColor(Color.RED);
        g.drawRect(cotaX, cotaY, 1, 1);
        */
        
        // Pintando las estacas
        // Calculando las coordenadas iniciales...
        double centroX = cotaX + 10; // Coordenadas para el avance sobre x...
        double centroY = cotaY + 10; // Coordenadas para el avance sobre y...
        
        double altoCerca = 62;
        double anchoCerca = 10;
        
        for (int i = 0; i < 3; i++) {
        	// Creando la estaca...
        	Shape estaca = new RoundRectangle2D.Double(centroX, centroY, anchoCerca, altoCerca, 4, 4);

            g.setColor(new Color(67,48,35));		// Color para el relleno
        	g.fill(estaca);							// Definiendo el relleno
        	g.setColor(Color.BLACK);				// Color para el borde
        	g.setStroke(new BasicStroke(2.0f));		// Definiendo el borde
        	g.draw(estaca);							// Dibujando la estaca
        	
        	// Avanzando para la siquiente estaca (avanza sobre las x's)
        	centroX += 20;
        }
        
        // Pintando las barras
        // Calculando las coordenadas iniciales...
        centroX = cotaX;
        centroY = cotaY + 20;
        
        altoCerca = 10;
        anchoCerca = ancho;
        
        for (int i = 0; i < 2; i++) {
        	// Creando la estaca...
        	Shape estaca = new RoundRectangle2D.Double(centroX, centroY, anchoCerca, altoCerca, 4, 4);
        	
        	g.setColor(new Color(67,48,35));		// Color para el relleno
        	g.fill(estaca);							// Definiendo el relleno
        	g.setColor(Color.BLACK);				// Color para el borde
        	g.setStroke(new BasicStroke(2.0f));		// Definiendo el borde
        	g.draw(estaca);							// Dibujando la estaca
        	
        	// Avanzando para la siquiente estaca (avanza sobre las y's)
        	centroY += 32;
        }
	}
	
	

}
