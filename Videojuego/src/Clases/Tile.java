package Clases;



import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.shape.Rectangle;

public class Tile extends ObjetoJuego{

	private int xImagen;
	private int yImagen;

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
			
		case 17:
			this.xImagen=0;
			this.yImagen=0;
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
		
		if(!Juego.limiteDerecha)	
			if(Juego.derecha) 
				x-= velocidad;
		if(!Juego.limiteIzquerda)
				if(Juego.izquierda)
					x+= velocidad;
			
		
		
			
		
			
		}
	
public Rectangle obtenerRectangulo() {
		
		return new Rectangle(x, y,80-10,40);
	}

public Rectangle obtenerRectanguloLava() {
	
	return new Rectangle(x, y+5,70,70-5);
}

public void verificarColisionJugadorCosas(){
	
	if (this.obtenerRectangulo().intersects(Juego.jugadorAnimado.obtenerPuntosCardinales("norte").getBoundsInLocal()))
		
		Juego.limiteArriba=true;
	else
		Juego.paredArriba++;
	if (this.obtenerRectangulo().intersects(Juego.jugadorAnimado.obtenerPuntosCardinales("sur").getBoundsInLocal()))
		
		Juego.limiteAbajo=true;
	else
		Juego.paredAbajo++;
	
	if (this.obtenerRectangulo().intersects(Juego.jugadorAnimado.obtenerPuntosCardinales("este").getBoundsInLocal()))
		
		Juego.limiteDerecha=true;
	else
		Juego.paredDerecha++;
	if (this.obtenerRectangulo().intersects(Juego.jugadorAnimado.obtenerPuntosCardinales("oeste").getBoundsInLocal()))
		
		Juego.limiteIzquerda=true;
	else
		Juego.paredIzquierda++;
	
	
	
	
	
}


public void verificarJugadorLava() {
	
	
	if (this.obtenerRectanguloLava().intersects(Juego.jugadorAnimado.obtenerRectanguloPies().getBoundsInLocal()))
		Juego.jugadorAnimado.setVidas(Juego.jugadorAnimado.getVidas()-1);
}



public void verificarColisionFlechaPared(int i,Trampas flechas) {
	

		if (this.obtenerRectangulo().intersects(flechas.obtenerRectangulo().getBoundsInLocal())){
		
				flechas.setY(70*3/2);
				flechas.setColision(0);
		
		
	}
		
}


	public void verificarColisionParedDisparoEnemigo(Enemigo enemigo,int i) {
		try {
			if(this.obtenerRectangulo().getBoundsInLocal().intersects(enemigo.disparos.get(i).obtenerRectangulo().getBoundsInLocal()))  
				enemigo.disparos.remove(i);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
			

	}
	
public void verificarColisionParedDisparoJugador(int i) {
	try {
		if( this.obtenerRectangulo().getBoundsInLocal().intersects(Juego.jugadorAnimado.disparos.get(i).obtenerRectangulo().getBoundsInLocal())) 
			Juego.jugadorAnimado.disparos.remove(i);
		else
			if(Juego.jugadorAnimado.disparos.get(i).getX()>Juego.tiles.get(Juego.tiles.size()-1).getX())
				Juego.jugadorAnimado.disparos.remove(i);
	} catch (Exception e) {
	}
	
		
	}
	
		
	
}




