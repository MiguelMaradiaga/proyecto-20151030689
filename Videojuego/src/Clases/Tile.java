package Clases;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Tile extends ObjetoJuego{

	private int xImagen;
	private int yImagen;
	private int tipoTile;
	public Tile(int tipoTile,int x, int y, int velocidad, String nombreImagen,int ancho,int alto) {
		
		super(x, y, velocidad, nombreImagen);
		
		this.alto = alto;
		this.ancho = ancho;
		
		switch (tipoTile) {
		case 1:
			this.xImagen=0;
			this.yImagen=50;
			break;
		
		case 2:
			this.xImagen=95;
			this.yImagen=50;
			break;
		
		case 3:
			this.xImagen=96*2;
			this.yImagen=50;
			break;
			
			
		case 4:
			this.xImagen=96*3;
			this.yImagen=50;
			break;
			
		case 5:
			this.xImagen=96*4;
			this.yImagen=50;
			break;
			
		case 6:
			this.xImagen=96*5;
			this.yImagen=50;
			break;
		
		case 7:
			this.xImagen=96*6;
			this.yImagen=50;
			break;
		
		case 8:
			this.xImagen=96*7;
			this.yImagen=50;
			break;
			
			
		case 9:
			this.xImagen=0;
			this.yImagen=95+50;
			break;
	
			
		case 10:
			this.xImagen=96*1;
			this.yImagen=95+50;
			break;
		
		case 11:
			this.xImagen=96*2;
			this.yImagen=95+50;
			break;
		
		case 12:
			this.xImagen=96*3;
			this.yImagen=95+50;
			break;
			
			
		case 13:
			this.xImagen=96*4;
			this.yImagen=95+50;
			break;
			
		case 14:
			this.xImagen=96*5;
			this.yImagen=95+50;
					;
			break;
			
		case 15:
			this.xImagen=96*6;
			this.yImagen=95+50;
					;
			break;
			
		case 16:
			this.xImagen=96*7;
			this.yImagen=95+50;
					;
			break;
			
		}
	}
	public int getxImagen() {
		return xImagen;
	}
	public void setxImagen(int xImagen) {
		this.xImagen = xImagen;
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, yImagen,ancho,alto,x,y,70,70);
		
	}
	
	@Override
	
		public void mover() {
		
		
			if(Juego.derecha) 
				x-= velocidad;
			
				if(Juego.izquierda)
					x+= velocidad;
			
		
		
			if(Juego.jugadorAnimado.y>70*9)
				if(Juego.abajo)
				y-= velocidad;
			
		
		if(Juego.jugadorAnimado.y<90)
			if(Juego.arriba)
				y+= velocidad;
		
			
		}
	
public Rectangle obtenerRectangulo() {
		
		return new Rectangle(x, y,80,80);
	}

public void verificarColisionCosas(int i) {
	
	if(i<20) {
		if (this.obtenerRectangulo().intersects(Juego.jugadorAnimado.obtenerRectangulo().getBoundsInLocal())){
		
				Juego.limiteArriba=true;
			System.out.println("choca");
		
		}else {
			
			Juego.h++;
		}
	}
	
	if(i>33&&i<43) {
		if (this.obtenerRectangulo().intersects(Juego.jugadorAnimado.obtenerRectangulo().getBoundsInLocal())){
		
				Juego.limiteAbajo=true;
			System.out.println("choca");
		
		}else {
			
			Juego.h++;
		}
	}
		
	
	


	
	}
		
}
	




