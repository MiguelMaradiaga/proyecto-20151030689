package Clases;

import java.util.HashMap;
import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Disparo extends ObjetoJuego{
	private HashMap<String,Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private boolean impactado=false;



	public boolean isImpactado() {
		return impactado;
	}

	public void setImpactado(boolean impactado) {
		this.impactado = impactado;
	}

	private String animacionActual;
	private int direccion=-1;
	
	
	public Disparo(int x, int y, int velocidad, String nombreImagen, String animacionActual,int direccion) {
		super(x, y, velocidad, nombreImagen);
this.animacionActual =  animacionActual;
this.direccion = direccion;

		
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
		
	}
	
	public void inicializarAnimaciones() {
		
		Rectangle coordenadasFuegoRosa[]= { new Rectangle(0,0,197,512),
								new Rectangle(512*1,0,197,512),
								new Rectangle(512*2,0,197,512),
								new Rectangle(0,197,197,512),
								new Rectangle(512,197,197,512),
								new Rectangle(512*2,197,197,512),};
		Animacion animacionFuegoRosa= new Animacion(0.2, coordenadasFuegoRosa);
		animaciones.put("fuego rosa", animacionFuegoRosa);
		
		
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
		if(!impactado)
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,x,y,(direccion)*80,50); //xima,yima,anchofrag,altofrag,xpintar,ypintar,anchopintar,ypintar
		//graficos.setStroke(javafx.scene.paint.Color.BLUE);
//		int valor;
//		if(direccion==1)
//			valor=0;
//		else 
//			valor=1;
//		graficos.strokeRect(x-(valor*80)+(valor)*30, y-40+15,150,110);
		
	}
	public Rectangle obtenerTrayectoria() {
		int valor;
		if(direccion==1)
			valor=0;
		else 
			valor=1;
		return new Rectangle(x-(valor*80)+(valor)*30, y-40+15,150,110);
		}
	
	public Rectangle obtenerRectangulo() {
		int valor;
		if(direccion==1)
			valor=0;
		else 
			valor=1;
		return new Rectangle(x-(valor*80)+(valor)*30, y+15,80-30,50-15);
	}
	
	public void verificarColisionesEnemigo(Enemigo enemigo) {
		if(!impactado)
		if( this.obtenerRectangulo().getBoundsInLocal().intersects(enemigo.obtenerRectangulo().getBoundsInLocal())) {
			
				enemigo.setVidas(enemigo.getVidas()-1);
				if(enemigo.getVidas()==0)
					Juego.jugadorAnimado.setPuntos(Juego.jugadorAnimado.getPuntos()+1000);
				impactado=true;
		}
		if(enemigo.getVidas()==0&&enemigo.nombreImagen.contentEquals("dragon")) {
			Juego.ganaste=true;
			
			
			
		
		}
		
		
		}
	
	
	
public void verificarColisionesJugador(JugadorAnimado jugadorAnimado) {
		if(!impactado)
		if( this.obtenerRectangulo().getBoundsInLocal().intersects(jugadorAnimado.obtenerRectangulo().getBoundsInLocal())) {
			
			Juego.jugadorAnimado.setVidas(Juego.jugadorAnimado.getVidas()-1);
			impactado=true;
		}
		
		}

public void colisionDisparoSlime(Enemigo slime, int j, int i) {
	
	if( this.obtenerRectangulo().getBoundsInLocal().intersects(slime.obtenerRectangulo().getBoundsInLocal())) {
		System.out.println("choca");
		slime.setVidas(slime.getVidas()-1);
		Juego.jugadorAnimado.disparos.remove(j);
		if(slime.getVidas()==0)
			Juego.slimes.remove(Juego.slimes.get(i));
		Juego.jugadorAnimado.setPuntos(Juego.jugadorAnimado.getPuntos()+100);
	}
}
	


	@Override
	public void mover() {
		if(!Juego.derecha&&!Juego.izquierda||Juego.limiteIzquerda||Juego.limiteDerecha||(Juego.izquierda&&Juego.derecha))
		x+=(-1)*direccion*velocidad;
		
		if((Juego.izquierda&&!Juego.derecha&&direccion==1&&!Juego.limiteIzquerda)||(Juego.izquierda&&!Juego.derecha&&direccion==-1&&!Juego.limiteIzquerda))
			x+=(-1)*direccion*velocidad+Juego.jugadorAnimado.velocidad;
		
		if((Juego.derecha&&!Juego.izquierda&&direccion==1&&!Juego.limiteDerecha)||(Juego.derecha&&!Juego.izquierda&&direccion==-1&&!Juego.limiteDerecha))
			x+=(-1)*direccion*velocidad-Juego.jugadorAnimado.velocidad;
		
	}
	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	
	
	
}



