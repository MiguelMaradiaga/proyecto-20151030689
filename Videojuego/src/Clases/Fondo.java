package Clases;

import Implementacion.Juego;

import javafx.scene.canvas.GraphicsContext;


public class Fondo extends ObjetoJuego{

	private String nombreImagen2;
	private int x2;
	
	public Fondo(int x, int y, int velocidad, String nombreImagen, String nombreImagen2) {
		super(x, y, velocidad, nombreImagen);
		this.nombreImagen2 = nombreImagen2;
		this.ancho = (int)Juego.imagenes.get("fondo").getWidth();
		this.alto = (int)Juego.imagenes.get("fondo2").getHeight();
		this.x2 = this.ancho + this.x;
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen), x, y);
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen2), x2, y);
		
	}

	@Override
public void mover() {
		if(x<=-1*ancho)
			x=ancho;
		if(x2<=-1*ancho)
			x2=ancho;
		
		if(Juego.derecha) {
			x-= velocidad;
			x2-= velocidad;
		}
		
		if(Juego.izquierda) {
			x+= velocidad;
			x2+= velocidad;
		}
		
		if(Juego.abajo) {
			y-= velocidad;
		}
		
		if(Juego.arriba) {
			y+= velocidad;
		}
	}

}
