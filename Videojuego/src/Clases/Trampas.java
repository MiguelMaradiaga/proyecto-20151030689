package Clases;

import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Trampas extends ObjetoJuego{
	
	private HashMap<String,Animacion> animaciones = new HashMap<String, Animacion>();
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion=1;
	private int colision = 0;
	private int  t = 0;//variable que cuenta los ciclos y genera lava
	private int mapa[][]= {
			{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
			{8,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
			
			};
	

	
	
	public Trampas(int x, int y, int velocidad, String nombreImagen,String animacionActual) {
		super(x, y, velocidad, nombreImagen);
		this.animaciones = animaciones;
		this.xImagen = xImagen;
		this.yImagen = yImagen;
		this.anchoImagen = anchoImagen;
		this.altoImagen = altoImagen;
		this.animacionActual = animacionActual;
		this.direccion = direccion;
		
		inicializarAnimaciones();
	}

	public void inicializarAnimaciones() {
		Rectangle coordenadasFlechas[] = {
				new Rectangle(7*3,23,11,7),
				};
		
		
		Animacion animacionFlechas = new Animacion(0.2,coordenadasFlechas );
		animaciones.put("flechas", animacionFlechas);
		
		Rectangle descansoFlechas[] = {
				new Rectangle(7*3,23,11,7)};
		Animacion animacionDescansoFlechas = new Animacion(0.2,descansoFlechas );
		animaciones.put("descanso flechas", animacionDescansoFlechas);
		
		Rectangle coordenadasLava[]= {new Rectangle(0,0,70,70),new Rectangle(70,0,70,70)};
		Animacion Lava = new Animacion(0.2,coordenadasLava);
		animaciones.put("lava",Lava);		
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		if(nombreImagen=="flechas") {
		if(colision==0)
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,x,y,anchoImagen*2,altoImagen*2); //xima,yima,anchofrag,altofrag,xpintar,ypintar,anchopintar,ypintar
		graficos.drawImage(Juego.imagenes.get("flechas"),0,0,anchoImagen,altoImagen,x,70*3/2,anchoImagen*2,altoImagen*2);
		/*graficos.setStroke(javafx.scene.paint.Color.BLUE);
		graficos.strokeRect(x,y,anchoImagen*2,altoImagen*2);*/
		}
	}
	
public int getColision() {
		return colision;
	}

	public void setColision(int colision) {
		this.colision = colision;
	}

public void calcularFrame(double t) {
		
		Rectangle coordenadas=	animaciones.get(animacionActual).calcularFrameActual(t);
		this.xImagen = (int)coordenadas.getX();
		this.yImagen = (int)coordenadas.getY();
		this.altoImagen = (int)coordenadas.getWidth();
		this.anchoImagen = (int)coordenadas.getHeight();
		}

	@Override
	public void mover() {
		if(!Juego.limiteDerecha)	
			if(Juego.derecha) 
				x-= Juego.jugadorAnimado.velocidad;
		if(!Juego.limiteIzquerda)
				if(Juego.izquierda)
					x+= Juego.jugadorAnimado.velocidad;
		
	
	
		if(Juego.jugadorAnimado.y>70*9)
			if(Juego.abajo)
			y-= velocidad;
		
	
	if(Juego.jugadorAnimado.y<90)
		if(Juego.arriba)
			y+= velocidad;
	
	
		y+= velocidad+6;
		
	}
	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}

public void colisionFlechaJugador() {
	
	if (this.obtenerRectangulo().intersects(Juego.jugadorAnimado.obtenerRectangulo().getBoundsInLocal())&&colision==0) {
	
	Juego.jugadorAnimado.setVidas((Juego.jugadorAnimado.getVidas())-1);
	colision=1;
	}}



public  Rectangle obtenerRectangulo() {
	
	return new Rectangle(x,y,anchoImagen*2,altoImagen*2);
}


		
		
		
		
		
		
		

}
