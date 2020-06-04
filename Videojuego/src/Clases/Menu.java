package Clases;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Implementacion.Juego;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Menu extends ObjetoJuego {

	private Scene escena;
	private ArrayList<Puntuacion> puntuaciones = new ArrayList<Puntuacion>();
	private final String nombreArchivo = "puntuacion.data";
	private Puntuacion puntuacion;


	public Menu(int x, int y, int velocidad, String nombreImagen, Scene escena) {
		super(x, y, velocidad, nombreImagen);
		this.escena = escena;
		cargarPuntuaciones();
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		Fondo fondo = new Fondo(0, 0, 3, "fondo", "fondo");
		fondo.pintar(graficos);
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen), x, y);
		
		if(nombreImagen.equals("mostrar puntos")) {
			for (int i = 0; i < puntuaciones.size(); i++) {
				graficos.setFill(Color.WHITE);
				graficos.fillText(puntuaciones.get(i).toString(), 320, 380+i*20);
			}
			
		}
		
		
		if(nombreImagen.equals("nuevo juego")||nombreImagen.equals("puntuaciones")||nombreImagen.equals("salir")) {
			
			
		}
			
		
	}
	
	public void cargarPuntuaciones() {
		
		
		try {
			ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream(nombreArchivo));
			while(true) {
				puntuaciones.add((Puntuacion)flujoEntrada.readObject());
			}
		}catch(EOFException e) {
			System.out.println("fin de archivo");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mover() {
		
		gestionEventos();


		
	}
	
	
public void gestionEventos() {
		
		
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
			
				
				
				switch(evento.getCode().toString()){
			
				
				
				case "UP":
					if(nombreImagen=="nuevo juego")
						nombreImagen="salir";
					else
						if(nombreImagen=="puntuaciones")
							nombreImagen="nuevo juego";
						else
							if(nombreImagen=="salir")
							nombreImagen="puntuaciones";
					
					break;
					
				case "DOWN":
					if(nombreImagen=="nuevo juego")
						nombreImagen="puntuaciones";
					else
						if(nombreImagen=="puntuaciones")
							nombreImagen="salir";
						else
							if(nombreImagen=="salir")
							nombreImagen="nuevo juego";
					break;
					
				case "ENTER":
					System.out.println(nombreImagen);
					
					
					if(nombreImagen=="nuevo juego") {
						Juego.juegoIniciado=true;
						Juego.nombreJugador= JOptionPane.showInputDialog("ingrese el nombre de su personaje");
						Juego.reinicar();
					Juego.gestionEventos();}
					else
						if(nombreImagen== "game over") {
							Juego.juegoIniciado=false;
						nombreImagen="nuevo juego";
						puntuacion=new Puntuacion(Juego.nombreJugador, Juego.jugadorAnimado.getPuntos());
						System.out.println(puntuacion);
					
						if(verificarPuntuaciones(puntuacion)) {
						for (int i = 10; i < puntuaciones.size(); i++) {
							puntuaciones.remove(i);						}
						guardarEnArchivo();
						Juego.reinicar();
						}
						}else
							if(nombreImagen.equals("puntuaciones")) 
								nombreImagen = "mostrar puntos";
								else
									if(nombreImagen.equals("mostrar puntos"))
										nombreImagen = "nuevo juego";
									else
										if(nombreImagen.equals("salir"))
											System.exit(0); 
					
					if(nombreImagen== "win") {
						Juego.juegoIniciado=false;
					
					puntuacion=new Puntuacion(Juego.nombreJugador, Juego.jugadorAnimado.getPuntos());
					System.out.println(puntuacion);
				
					if(verificarPuntuaciones(puntuacion)) {
					for (int i = 10; i < puntuaciones.size(); i++) {
						puntuaciones.remove(i);						}
					guardarEnArchivo();
					Juego.reinicar();
					nombreImagen="nuevo juego";
					}
					}
					break;
					}
				
				}
			}
		);
		
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				
				
				switch(evento.getCode().toString()){
				
				
				case "UP":
				
					break;
					
				case "DOWN":
					
					break;
				case "SPACE":
					
					
					break;
				}
			}	
		});
	}
	


public void guardarEnArchivo() {
System.out.println(puntuaciones.size());
	try {
			
			ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
			for(int i=0;i<puntuaciones.size();i++)
				flujoSalida.writeObject(puntuaciones.get(i));
		flujoSalida.close();
		
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public boolean verificarPuntuaciones(Puntuacion puntuacion) {
		boolean entra=false;
		for (int i = 0; i < puntuaciones.size(); i++) {
			if(puntuacion.getPuntuacion()>=puntuaciones.get(i).getPuntuacion()) {
				
				for (int j = i+1; j <puntuaciones.size()-i; j++) {
					puntuaciones.set(j, puntuaciones.get(j-1));
					
				}
				puntuaciones.set(i, puntuacion);
				
				entra=true;
				
			}
			if(entra==true)
				break;
			System.out.println(i);
		}
		return entra;}
	
	

}
