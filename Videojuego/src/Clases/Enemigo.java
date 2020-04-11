package Clases;

import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Enemigo extends ObjetoJuego{
	private int vidas;
	private HashMap<String,Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion=1;
	private String tipoPersonaje;
	


	public Enemigo(String tipoPersonaje, int x, int y, int velocidad, String nombreImagen, int vidas,String animacionActual) {
		super(x, y, velocidad, nombreImagen);
		this.vidas = vidas;
		this.animacionActual =  animacionActual;
		this.tipoPersonaje = tipoPersonaje;
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
		
	}


	public void inicializarAnimaciones() {
		
		Rectangle coordenadasCorrerDe[] = {
				new Rectangle(0,440,60,48),
				new Rectangle(48,440,60,48),
				new Rectangle(48*2,440,60,48),
				
				};
		
		
		Animacion animacionCorrerDe = new Animacion(0.2,coordenadasCorrerDe );
		animaciones.put("caminar derecha enemigo", animacionCorrerDe);
		
		Rectangle coordenadasCorrerIz[] = {
				new Rectangle(0,370,60,48),
				new Rectangle(48,370,60,48),
				new Rectangle(48*2,370,60,48),
				};
		
		Animacion animacionCorrerIz = new Animacion(0.2,coordenadasCorrerIz);
		animaciones.put("caminar izquierda enemigo", animacionCorrerIz);
		
		Rectangle coordenadasCorrerUp[] = {
				new Rectangle(0,513,60,48),
				new Rectangle(48,513,60,48),
				new Rectangle(48*2,513,60,48)
				
				};
		
		Animacion animacionCorrerUp = new Animacion(0.2,coordenadasCorrerUp);
		animaciones.put("caminar arriba enemigo", animacionCorrerUp);
		
		Rectangle coordenadasCorrerDown[] = {
				new Rectangle(0,298,60,48),
				new Rectangle(48,298,60,48),
				new Rectangle(48*2,298,60,48)
				
				};
		
		Animacion animacionCorrerDown = new Animacion(0.2,coordenadasCorrerDown);
		animaciones.put("caminar abajo enemigo", animacionCorrerDown);
		
		
		//animaciones de descanso
		Rectangle coordenadasDescansoDeEnemigo[] = {new Rectangle(0,440,60,48)};
		
		Animacion 	animacionDescansoDeEnemigo = new Animacion(0.2, coordenadasDescansoDeEnemigo);
		animaciones.put("descanso derecha enemigo",animacionDescansoDeEnemigo);
		
		Rectangle coordenadasDescansoUpEnemigo[] = {new Rectangle(0,295,60,48)};
		
		Animacion 	animacionDescansoUpEnemigo = new Animacion(0.2, coordenadasDescansoUpEnemigo);
		animaciones.put("descanso arriba enemigo",animacionDescansoUpEnemigo);
		
		Rectangle coordenadasDescansoDownEnemigo[] = {new Rectangle(0,298,60,48)};
		
		Animacion 	animacionDescansoDownEnemigo = new Animacion(0.2, coordenadasDescansoDownEnemigo);
		animaciones.put("descanso abajo enemigo",animacionDescansoDownEnemigo);
		
		Rectangle coordenadasDescansoIzEnemigo[] = {new Rectangle(0,370,60,48)};
		
		Animacion 	animacionDescansoIzEnemigo = new Animacion(0.2, coordenadasDescansoIzEnemigo);
		animaciones.put("descanso izquierda enemigo",animacionDescansoIzEnemigo);
		
	}
	
	@Override
	public void mover() {
	
	
	
		if(Juego.derecha) 
		x-= velocidad;
		
		if(Juego.izquierda)
			x+= velocidad;
	

		
	

		if(Juego.jugadorAnimado.y<90)
			if(Juego.arriba)
				y+= velocidad;
			
		
		if(!Juego.limiteAbajo)
				if(Juego.abajo)
					y+= velocidad;
				
				
				if(!Juego.limiteArriba)
					if(Juego.arriba)
						y-= velocidad;
			
	
	
		
	}

	


public Rectangle obtenerRectangulo() {
	
	return new Rectangle(x+10, y,(direccion)*anchoImagen-18,altoImagen);
}
public void calcularFrame(double t) {
	
	Rectangle coordenadas=	animaciones.get(animacionActual).calcularFrameActual(t);
	this.xImagen = (int)coordenadas.getX();
	this.yImagen = (int)coordenadas.getY();
	this.altoImagen = (int)coordenadas.getWidth();
	this.anchoImagen = (int)coordenadas.getHeight();
	}
@Override
public void pintar(GraphicsContext graficos) {
	
	graficos.drawImage(Juego.imagenes.get(this.nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,x,y,(direccion)*anchoImagen,altoImagen); //xima,yima,anchofrag,altofrag,xpintar,ypintar,anchopintar,ypintar
	
}

public void disparo() {
	
	
}


public int getVidas() {
	return vidas;
}


public void setVidas(int vidas) {
	this.vidas = vidas;
}


public HashMap<String, Animacion> getAnimaciones() {
	return animaciones;
}


public void setAnimaciones(HashMap<String, Animacion> animaciones) {
	this.animaciones = animaciones;
}


public int getxImagen() {
	return xImagen;
}


public void setxImagen(int xImagen) {
	this.xImagen = xImagen;
}


public int getyImagen() {
	return yImagen;
}


public void setyImagen(int yImagen) {
	this.yImagen = yImagen;
}


public int getAnchoImagen() {
	return anchoImagen;
}


public void setAnchoImagen(int anchoImagen) {
	this.anchoImagen = anchoImagen;
}


public int getAltoImagen() {
	return altoImagen;
}


public void setAltoImagen(int altoImagen) {
	this.altoImagen = altoImagen;
}


public String getAnimacionActual() {
	return animacionActual;
}


public void setAnimacionActual(String animacionActual) {
	this.animacionActual = animacionActual;
}


public int getDireccion() {
	return direccion;
}


public void setDireccion(int direccion) {
	this.direccion = direccion;
}


public String getTipoPersonaje() {
	return tipoPersonaje;
}


public void setTipoPersonaje(String tipoPersonaje) {
	this.tipoPersonaje = tipoPersonaje;
}




}
