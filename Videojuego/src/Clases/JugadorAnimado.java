package Clases;

import java.util.ArrayList;
import java.util.HashMap;

import com.sun.javafx.geom.AreaOp.XorOp;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;


public class JugadorAnimado extends ObjetoJuego {
	
	private int vidas;
	private HashMap<String,Animacion> animaciones;
	public  int xImagen;
	public int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion=1;
	public static Disparo disparoJugador;
	public static int disparado=0;
	public static int mana=100;
	public  ArrayList <Disparo> disparos = new ArrayList <Disparo>();
	
	private int monedas=0;
	private int puntos=0;
	


	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public JugadorAnimado( int x, int y, int velocidad, String nombreImagen, int vidas,String animacionActual) {
		super(x, y, velocidad, nombreImagen);
		this.vidas = vidas;
		this.animacionActual =  animacionActual;
		this.mana=mana;
		
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
		
	}
	
	public static int getMana() {
		return mana;
	}

	public static void setMana(int mana) {
		JugadorAnimado.mana = mana;
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
	//	graficos.strokeRect(x+10, y+50,(direccion)*anchoImagen-18-5,altoImagen-50);
//		graficos.strokeRect(x+(anchoImagen),y+4,1,altoImagen-7);//este
//		graficos.strokeRect(x,y,anchoImagen,1);//arriba
//		graficos.strokeRect(x,y+altoImagen,anchoImagen,1);//abajo
//		graficos.strokeRect(x,y+4,1,altoImagen-7);//oeste
		
}
	
@Override
	public void mover() {
	
	if(!Juego.limiteAbajo)
		if(Juego.abajo)
			y+= velocidad;
		
		if(!Juego.limiteArriba)
			if(Juego.arriba)
				y-= velocidad;
	
	if((Juego.tiles.get(Juego.tiles.size()-1).getX())<4253-35)
		Juego.nivel2=true;
	
	if((Juego.tiles.get(Juego.tiles.size()-1).getX())<2105)
		Juego.nivel3=true;
	
	//System.out.println((Juego.tiles.get(Juego.tiles.size()-1).getX()));
}

	

	


public Rectangle obtenerRectangulo() {
	
	return new Rectangle(x+10, y,(direccion)*anchoImagen-18,altoImagen);
} 

public Rectangle obtenerRectanguloPies() {
	
	return new Rectangle(x+10, y+50,(direccion)*anchoImagen-18-5,altoImagen-50);
} 

public Rectangle obtenerPuntosCardinales(String punto) {
	
	if(punto=="norte")
		return new Rectangle(x+5,y,anchoImagen-10,1);
	if(punto=="sur")
		return new Rectangle(x+5,y+altoImagen,anchoImagen-10,1);
	if(punto=="este")
		return new Rectangle(x+(anchoImagen),y+4,1,altoImagen-7);
	if(punto=="oeste")
		return new Rectangle(x,y+4,1,altoImagen-7);
	
	return null;
}


public void verificarColisionesItem(Item item) {
	
	if(!item.isCapturada() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
		if(item.getNombreImagen().equals("corazon")) {
		if(vidas<=3)
		vidas+=item.getCantidadVidas();
		}
		else
			if(item.getNombreImagen().equals("moneda")) {
			monedas=	monedas+1;
			
			puntos+=100;
	
	}
		if(item.getNombreImagen().equals("pocion mana")) 
			this.mana=100;
	
		
		item.setCapturada(true);
	}
		
	
}




public void disparar() {
	
	if(mana>=10) {
	disparos.add(new Disparo(this.x+60,this.y+10,7,"firepink","fuego rosa",-1));
	 
	 mana-=10;
	}
	 
	 
	
}





}
