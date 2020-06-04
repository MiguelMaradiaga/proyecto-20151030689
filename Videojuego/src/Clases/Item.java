package Clases;



import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item extends ObjetoJuego{
	private int cantidadVidas;
	private boolean capturada=false;

	public boolean isCapturada() {
		return capturada;
	}

	public void setCapturada(boolean capturada) {
		this.capturada = capturada;
	}

	public Item(int x, int y, int velocidad, String nombreImagen, int cantidadPuntos) {
		super(x, y, velocidad, nombreImagen);
		this.cantidadVidas = cantidadPuntos;
		this.ancho = (int)Juego.imagenes.get("corazon").getWidth();
		this.alto = (int)Juego.imagenes.get("corazon").getHeight();
		}

	public int getCantidadVidas() {
		return cantidadVidas;
	}

	public void setCantidadVidas(int cantidadVidas) {
		this.cantidadVidas = cantidadVidas;
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		if(!capturada) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen),this.x,this.y,30,30);
	
		}
	}

	@Override
	public void mover() {
		
		
		if(!Juego.limiteDerecha)	
			if(Juego.derecha) 
				x-= velocidad;
		if(!Juego.limiteIzquerda)
				if(Juego.izquierda)
					x+= velocidad;
			
		
		
			
		}
			
	
	public Rectangle obtenerRectangulo() {
		
		return new Rectangle(x, y,27,27);
	}
	
}
