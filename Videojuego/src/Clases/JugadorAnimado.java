package Clases;

import java.util.HashMap;

import Implementacion.Juego;

import javafx.scene.canvas.GraphicsContext;

import java.awt.Color;
import java.util.ArrayList;

import javafx.scene.shape.Rectangle;


public class JugadorAnimado extends ObjetoJuego {
	
	private int vidas;
	private HashMap<String,Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion=1;
	
	


	public JugadorAnimado( int x, int y, int velocidad, String nombreImagen, int vidas,String animacionActual) {
		super(x, y, velocidad, nombreImagen);
		this.vidas = vidas;
		this.animacionActual =  animacionActual;
		
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
		
	}
	
	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public String getAnimacionActual() {
		return animacionActual;
	}






	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}


	


	public int getVidas() {
		return vidas;
	}

 	public void setVidas(int vidas) {
		this.vidas = vidas;
	}


	public void inicializarAnimaciones() {
		
		
			Rectangle coordenadasCorrerDe[] = {
					new Rectangle(48*3,440,60,48),
					new Rectangle(48*4,440,60,48),
					new Rectangle(48*5,440,60,48),
					
					};
			
			
			Animacion animacionCorrerDe = new Animacion(0.2,coordenadasCorrerDe );
			animaciones.put("caminar derecha", animacionCorrerDe);
			
			Rectangle coordenadasCorrerIz[] = {
					new Rectangle(48*3,370,60,48),
					new Rectangle(48*4,370,60,48),
					new Rectangle(48*5,370,60,48),
					};
			
			Animacion animacionCorrerIz = new Animacion(0.2,coordenadasCorrerIz);
			animaciones.put("caminar izquierda", animacionCorrerIz);
			
			Rectangle coordenadasCorrerUp[] = {
					new Rectangle(48*3,516,60,48),
					new Rectangle(48*4,516,60,48),
					new Rectangle(48*5,516,60,48)
					
					};
			
			Animacion animacionCorrerUp = new Animacion(0.2,coordenadasCorrerUp);
			animaciones.put("caminar arriba", animacionCorrerUp);
			
			Rectangle coordenadasCorrerDown[] = {
					new Rectangle(48*3,298,60,48),
					new Rectangle(48*4,298,60,48),
					new Rectangle(48*5,298,60,48)
					
					};
			
			Animacion animacionCorrerDown = new Animacion(0.2,coordenadasCorrerDown);
			animaciones.put("caminar abajo", animacionCorrerDown);
			
			
			//animaciones de descanso
			Rectangle coordenadasDescansoDe[] = {new Rectangle(48*4,440,60,48)};
			
			Animacion 	animacionDescansoDe = new Animacion(0.2, coordenadasDescansoDe);
			animaciones.put("descanso derecha",animacionDescansoDe);
			
			Rectangle coordenadasDescansoUp[] = {new Rectangle(48*4,516,60,48)};
			
			Animacion 	animacionDescansoUp = new Animacion(0.2, coordenadasDescansoUp);
			animaciones.put("descanso arriba",animacionDescansoUp);
			
			Rectangle coordenadasDescansoDown[] = {new Rectangle(48*4,298,60,48)};
			
			Animacion 	animacionDescansoDown = new Animacion(0.2, coordenadasDescansoDown);
			animaciones.put("descanso abajo",animacionDescansoDown);
			
			Rectangle coordenadasDescansoIz[] = {new Rectangle(48*4,370,60,48)};
			
			Animacion 	animacionDescansoIz = new Animacion(0.2, coordenadasDescansoIz);
			animaciones.put("descanso izquierda",animacionDescansoIz);
			
			
		
			
			
			
			
		
	
		
		
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
	
@Override
	public void mover() {
	
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

public void verificarColisionesItem(Item item) {
	
	if(!item.isCapturada() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
		vidas+=item.getCantidadVidas();
	item.setCapturada(true);
	}
	
}




}
