package Clases;

import java.io.Serializable;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Interfaz implements Serializable{

	private JugadorAnimado jugador;
	
	public Interfaz(JugadorAnimado jugador) {
		super();
		this.jugador = jugador;
	}

	public void pintar(GraphicsContext graficos) {
		System.out.println("ejecutando");
		for (int i = 0; i <10; i++) {
			graficos.drawImage(Juego.imagenes.get("fondo"),700, i*70,150,70);
		}
		
		for (int i = 0; i < jugador.getVidas(); i++) {
			
			graficos.drawImage(Juego.imagenes.get("corazon"),710+i*40, 30,30,30);
		}
		
		
		for (int i = 0; i < jugador.getMonedas(); i++) {
			
			if(i<3)
				graficos.drawImage(Juego.imagenes.get("moneda"), 710+i*40, 80,30,30);
			else
				if(i<6)
					graficos.drawImage(Juego.imagenes.get("moneda"), 710+(i-3)*40, 120,30,30);
				else
					if(i<9)
						graficos.drawImage(Juego.imagenes.get("moneda"), 710+(i-6)*40, 160,30,30);
					else
						graficos.drawImage(Juego.imagenes.get("moneda"), 710+(i-9)*40, 200,30,30);
			
			
			
		
	
	}
		
		graficos.setFill(Color.WHITE);
		
		graficos.fillText("Puntos: "+jugador.getPuntos(), 710, 240, 120);
		graficos.fillText(Juego.nombreJugador, 740, 595);
		graficos.fillText("Mana:"+JugadorAnimado.getMana()+"/100", 710, 255);
		graficos.drawImage(Juego.imagenes.get("soldado"),48*4,298,48,60,740,600,48,60); //xima,yima,anchofrag,altofrag,xpintar,ypintar,anchopintar,ypintar
		
		graficos.fillText("mover", 730, 270);
		graficos.drawImage(Juego.imagenes.get("teclas"), 710, 280,100,100);
		graficos.fillText("disparar", 740, 395);
		
		graficos.drawImage(Juego.imagenes.get("espacio"), 700, 410,150,50);
		
	}
	
	
}
