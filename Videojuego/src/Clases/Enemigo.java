package Clases;


import java.util.ArrayList;
import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Enemigo extends ObjetoJuego{
	private int vidas=10;
	private HashMap<String,Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion=1;
	private String tipoPersonaje;
	public static Disparo disparo;
	protected boolean disparado=false;
	public ArrayList<Disparo> disparos= new ArrayList<Disparo>();
	private String movimiento= new String();
	private Boolean esquivando=false;
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
		
		
		
			
			Rectangle coordenadasArriba[] = {
					new Rectangle(0,0,24,24),
					new Rectangle(24,0,24,24),
					new Rectangle(48,0,24,24)
					
					};
			Animacion animacionSlimeArriba = new Animacion(0.2, coordenadasArriba);
			animaciones.put("slime Arriba",animacionSlimeArriba);
			
			Rectangle coordenadasAbajo[] = {
					new Rectangle(0,48,24,24),
					new Rectangle(24,48,24,24),
					new Rectangle(48,48,24,24)
					
					};
			Animacion animacionSlimeAbajo = new Animacion(0.2, coordenadasAbajo);
			animaciones.put("slime Abajo",animacionSlimeAbajo);
			
			
			
			Rectangle coordenadasLateral[] = {
					new Rectangle(0,24,24,24),
					new Rectangle(24,24,24,24),
					new Rectangle(48,24,24,24)
					
					};
			Animacion animacionSlimeLateral = new Animacion(0.2, coordenadasLateral);
			animaciones.put("slime derecha",animacionSlimeLateral);
			
			
			Rectangle coordenadasIzquierda[] = {
					new Rectangle(0,72,24,24),
					new Rectangle(24,72,24,24),
					new Rectangle(48,72,24,24)
					
					};
			Animacion animacionSlimeIzquierda = new Animacion(0.2, coordenadasIzquierda);
			animaciones.put("slime izquierda",animacionSlimeIzquierda);
			
			Rectangle coordenadasDescanso[] = {new Rectangle(0,24,24,24)};
			
			Animacion 	animacionDescanso = new Animacion(0.2, coordenadasDescanso);
			animaciones.put("descanso slime",animacionDescanso);
			
			Rectangle coordenadasAbajoDragon[] ={
				new Rectangle(0,0,120,120),
				new Rectangle(120,0,120,120),
				new Rectangle(240,0,120,120)
			};
			Animacion animacionAbajoDragon = new Animacion(0.2, coordenadasAbajoDragon);
			animaciones.put("dragon abajo",animacionAbajoDragon);
			
			Rectangle coordenadasIzDragon[] ={
					new Rectangle(0,120,120,120),
					new Rectangle(120,120,120,120),
					new Rectangle(240,120,120,120)
				};
			Animacion animacionIzDragon = new Animacion(0.2, coordenadasIzDragon);
			animaciones.put("dragon izquierda",animacionIzDragon);
			
			Rectangle coordenadasDeDragon[] ={
					new Rectangle(0,240,120,120),
					new Rectangle(120,240,120,120),
					new Rectangle(240,240,120,120)
				};
			Animacion animacionDeDragon = new Animacion(0.2, coordenadasDeDragon);
			animaciones.put("dragon derecha",animacionDeDragon);
			
			Rectangle coordenadasUpDragon[] ={
					new Rectangle(0,360,120,120),
					new Rectangle(120,360,120,120),
					new Rectangle(240,360,120,120)
				};
			Animacion animacionUpDragon = new Animacion(0.2, coordenadasUpDragon);
			animaciones.put("dragon arriba",animacionUpDragon);
			
			Rectangle coordenadasDescansoDragon[]= {new Rectangle(120,120,120,120)};
			Animacion animacionDescansoDragon = new Animacion(0.2, coordenadasDescansoDragon);
			
			animaciones.put("descanso dragon", animacionDescansoDragon);
			
		}
		
		
		
	
	
public Rectangle premonicion(String sentido) {
		
		switch (sentido) {
		case "arriba":
			
			return new Rectangle(x+10, y-altoImagen/2,(direccion)*anchoImagen-18,altoImagen/2);
			
		case "abajo":
			
			return new Rectangle(x+10, y+altoImagen,(direccion)*anchoImagen-18,altoImagen/2);
		case"enfrente":
			
		return new Rectangle(x+10-anchoImagen, y,(direccion)*anchoImagen-18,altoImagen);
		
		}
		
		return null;
	}
	
	public void sextoSentido(Disparo disparo) {
		
		if(this.premonicion("enfrente").intersects(disparo.obtenerTrayectoria().getBoundsInLocal())) { 
			if(!esquivando) {
		 if(this.premonicion("enfrente").getY()>disparo.obtenerTrayectoria().getY()) {
			 movimiento="abajo";
			 esquivando=true;
		 }
			
			if(this.premonicion("enfrente").getY()<disparo.obtenerTrayectoria().getY()) {
			 movimiento="arriba";
			 esquivando=true;
		}}
		}else {
				
			esquivando=false;
			
		}
	}
	
	@Override
	public void mover() {
		if(this.tipoPersonaje.equals("enemigo1")) {
		if(esquivando)
		switch (movimiento) {
		case "arriba":
			y-=velocidad-1/2;
			animacionActual="caminar arriba enemigo";
			break;
			
		case "abajo":
			y+=velocidad-1/2;
			animacionActual="caminar abajo enemigo";
			
			default:
			break;
		}
		
		if(!esquivando) {
			if(Juego.jugadorAnimado.getY()>y) {
				y+=velocidad-1;
				animacionActual="caminar abajo enemigo"; }
			if(Juego.jugadorAnimado.getY()<y) {
				y-=velocidad-1;

				animacionActual="caminar arriba enemigo";
			
			
				}
			
			
			if(Juego.jugadorAnimado.getY()==y+1) {
				animacionActual="descanso izquierda enemigo";
				}
				if(Juego.jugadorAnimado.getY()==y-1) {
					animacionActual="descanso izquierda enemigo";
					}
				if(Juego.jugadorAnimado.getY()==y) {
					animacionActual="descanso izquierda enemigo";
				}
				
				
				
		
		}
		
		if(!Juego.limiteDerecha)	
			if(Juego.derecha) 
				x-= velocidad;
		if(!Juego.limiteIzquerda)
				if(Juego.izquierda)
					x+= velocidad;
	
		}
		
		if(tipoPersonaje.equals("slime")&&this.vidas>0) {
			
			boolean moviendoHorizontal;
			if(Juego.nivel2) {
			if(Juego.jugadorAnimado.getY()>y-30&&Juego.jugadorAnimado.getY()<y+30) {
					
					if(Juego.jugadorAnimado.getX()>x) {
						x+=velocidad-1;
						animacionActual="slime derecha"; }
					if(Juego.jugadorAnimado.getX()<x) {
						x-=velocidad-1;

						animacionActual="slime izquierda";}
					moviendoHorizontal=true;
				}else
					moviendoHorizontal=false;
			
				
				
				if(Juego.jugadorAnimado.getY()>y-20) {
					y+=velocidad-1;
					if(!moviendoHorizontal)
					animacionActual="slime Abajo"; }
				
				if(Juego.jugadorAnimado.getY()<y-20) {
					y-=velocidad-1;
					if(!moviendoHorizontal)
					animacionActual="slime Arriba";
				
				
		}
			}
			
			if(!Juego.limiteDerecha)	
				if(Juego.derecha) 
					x-= velocidad;
			if(!Juego.limiteIzquerda)
					if(Juego.izquierda)
						x+= velocidad;
		}
			
		if(this.tipoPersonaje.equals("dragon")) {
			
			
		
				
				if(Juego.jugadorAnimado.getY()>y+30+30) {
					y+=velocidad-1;
					animacionActual="dragon abajo"; }
				else
				if(Juego.jugadorAnimado.getY()<y+30+11) {
					y-=velocidad-1;

					animacionActual="dragon arriba";
				}
				
				System.out.println("dragon: "+y);
				System.out.println("juagdor: "+Juego.jugadorAnimado.getY());
				
				
					if(Juego.jugadorAnimado.getY()>=y+30+11&&Juego.jugadorAnimado.getY()<=y+30+30) {
						animacionActual="descanso dragon";
						}
					
					
			
			
			if(!Juego.limiteDerecha)	
				if(Juego.derecha) 
					x-= velocidad;
			if(!Juego.limiteIzquerda)
					if(Juego.izquierda)
						x+= velocidad;
		}
			
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
	
	if(vidas>0&&tipoPersonaje.equals("enemigo1")||vidas>0&&tipoPersonaje.equals("dragon"))
	graficos.drawImage(Juego.imagenes.get(this.nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,x,y,(direccion)*anchoImagen,altoImagen); //xima,yima,anchofrag,altofrag,xpintar,ypintar,anchopintar,ypintar
	
	if(vidas>0&&tipoPersonaje.equals("slime"))
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,x,y,(direccion)*50,50);
	
	
	//	graficos.strokeRect(x+10, y-altoImagen/2,(direccion)*anchoImagen-18,altoImagen/2);//ARRIBA
//	graficos.strokeRect(x+10-anchoImagen, y+5,(direccion)*anchoImagen-18,altoImagen-4);//delante
}

public Disparo getDisparo() {
	return disparo;
}



public void disparar() {
	
	 
	 disparos.add(new Disparo(this.x,this.y+10,7,"firered","fuego rosa",1));
	
	
	
	 
}

public void dispararDragon() {
	
	 
	 disparos.add(new Disparo(this.x,this.y+30-30,7,"firered","fuego rosa",1));
	 disparos.add(new Disparo(this.x,this.y+30,7,"firered","fuego rosa",1));
	 disparos.add(new Disparo(this.x,this.y+30+30,7,"firered","fuego rosa",1));
	
	 
}

public void colisionJugadorSlime(int i) {
	
	if ( this.obtenerRectangulo().getBoundsInLocal().intersects(Juego.jugadorAnimado.obtenerRectangulo().getBoundsInLocal())) {
		Juego.jugadorAnimado.setVidas(Juego.jugadorAnimado.getVidas()-1);
		Juego.slimes.remove(i);
	}
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
