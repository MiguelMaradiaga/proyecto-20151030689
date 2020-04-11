package Clases;

import Implementacion.Juego;

import javafx.scene.canvas.GraphicsContext;


public class Jugador extends ObjetoJuego {
	
	private int vidas;
	
	
	
	
	
	public Jugador(int x, int y, int velocidad, String nombreImagen, int vidas) {
		super(x, y, velocidad, nombreImagen);
		this.vidas = vidas;
	}

	

	


	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}


@Override
	public void pintar(GraphicsContext graficos) {
		
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen),x,y);
		
	}
	
@Override
	public void mover() {
		
		if(Juego.derecha) 
			x+= velocidad;
		
		if(Juego.izquierda)
			x-= velocidad;

		if(Juego.abajo)
			y+= velocidad;
		if(Juego.arriba)
			y-= velocidad;
	}
}
