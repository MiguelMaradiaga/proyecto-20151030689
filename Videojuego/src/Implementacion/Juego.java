package Implementacion;


import java.util.ArrayList;
import java.util.HashMap;

import Clases.Enemigo;
import Clases.Fondo;
import Clases.Fondo2;
import Clases.Item;
import Clases.JugadorAnimado;
import Clases.Tile;
import Clases.Trampas;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Juego extends Application{
	
	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	public static int  h; //se usara para verificar una colsion 
	//private Jugador jugador;
	public static JugadorAnimado jugadorAnimado;
	private Fondo fondo;
	private Fondo2 fondo2;
	private Enemigo enemigo1;
	private Trampas flechas;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean izquierda;
	public static boolean derecha;
	public static boolean limiteArriba=false;
	public static boolean limiteAbajo=false;
	public static boolean limiteDerecha=false;
	public static boolean limiteIzquerda=false;
	public static HashMap<String, Image> imagenes;
	
	private Item item;
	public static ArrayList<Tile> tiles= new ArrayList<Tile>();//piso
	public static ArrayList<Tile> tiles2= new ArrayList<Tile>();//paredes
	public static ArrayList<Tile> tiles3= new ArrayList<Tile>();//trampas
	private int tilemap[][]= {
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			
	};
	
	private int cosas[][]= {
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			
	};
	
	
	
	public static void main(String args[]) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage ventana) throws Exception {
		
		inicializarComponentes();
		gestionEventos();
		
		ventana.setScene(escena);
		ventana.setTitle("El Fuego Que Arde En Ti");
		ventana.show(); 
		cicloJuego();
		
		
		
	}
	
	public void cicloJuego() {
		long tiempoInicial= System.nanoTime();
		
		AnimationTimer animationTimer= new AnimationTimer() {

			@Override
			public void handle(long tiempoActual) {
			
				double t= (tiempoActual-tiempoInicial)/1000000000.0;	
				
				actualizarEstado(t);
				
				pintar();
				 
				 
			}
			
			
		};
		
		animationTimer.start();
	}
	
	public void actualizarEstado(double t) {
		jugadorAnimado.calcularFrame(t);
		jugadorAnimado.mover();
		enemigo1.calcularFrame(t);
		enemigo1.mover();
		
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).mover();
			
		
		}
		for (int i = 0; i < tiles2.size(); i++) {
			tiles2.get(i).mover();
			
		
		}
		
		flechas.pintar(graficos);
		item.mover();
		coliciones();
		
	}
	
	private void coliciones() {
		h=0;
		//verificar colision arriba
		for (int i = 0; i < 20; i++) {
			tiles2.get(i).verificarColisionCosas(i);
		}
		
		
		if(h==20)
			limiteArriba=false;
		
		h=0;
		
		for (int i = 33; i < 43; i++) {
			tiles2.get(i).verificarColisionCosas(i);
		}
		System.out.println(h);
		
		if(h==9)
			limiteAbajo=false;
		
		
		
		jugadorAnimado.verificarColisionesItem(item);
		
	}

	public void inicializarComponentes() {
		 imagenes= new HashMap<String, Image>();
		 cargarImagenes();
		 jugadorAnimado = new JugadorAnimado(80,70*5,3,"soldado",3,"descanso derecha");
		 enemigo1 = new Enemigo("enemigo1",80*7,70*5,3,"soldado",3,"descanso izquierda enemigo");
		 fondo = new Fondo(0, 0, 3,"fondo","fondo2");
		 flechas = new Trampas(100,200,3,"animacion flechas");
		 
		 item = new Item(300,500,3,"item",1);
		fondo2=new Fondo2("madera",3);
		
		 //tile = new Tile(0,0,0,"tilemap",0,0,130,1);
		 root = new Group();
		 escena= new Scene(root, 700,700); 
		 lienzo = new Canvas(700, 700);
		root.getChildren().add(lienzo);
		graficos = lienzo.getGraphicsContext2D();
	}
	
	
	public void cargarImagenes() {
		imagenes.put("dragon-derecha", new Image("dragon-derecha.png"));
		imagenes.put("dragon-izquierda", new Image("dragon-izquierda.png"));
		imagenes.put("dragon-arriba", new Image("dragon-arriba2.png"));
		imagenes.put("dragon-abajo", new Image("dragon-abajo.png"));
		imagenes.put("fondo",new Image("Fondo negro.png"));
		imagenes.put("fondo2",new Image("Fondo negro.png"));
		imagenes.put("tilemap",new Image("suelo y pared.png"));
		imagenes.put("soldado", new Image("Actor2_png.png"));
		imagenes.put("item", new Image("corazon.png"));
		imagenes.put("flecha", new Image("animacion flechas.png"));
	}
	
	public void pintar() {
		
		
		fondo.pintar(graficos);
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).pintar(graficos);
		}
		
		for (int i = 0; i < tiles2.size(); i++) {
			tiles2.get(i).pintar(graficos);
		}
		
		jugadorAnimado.pintar(graficos);
		enemigo1.pintar(graficos);
		item.pintar(graficos);
		graficos.fillText("vidas = "+jugadorAnimado.getVidas(), 20, 20);
		flechas.pintar(graficos);
		
	}
	
	public void gestionEventos() {
		
		//escena.setOnKeyPressed(new Evento());
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
			
				
				
				switch(evento.getCode().toString()){
			
				case "RIGHT":
				
					jugadorAnimado.setAnimacionActual("caminar derecha");
					jugadorAnimado.setDireccion(1);

					derecha = true;
				
					break;
					
				case "LEFT":
					/*jugadorAnimado.setAnimacionActual("caminar derecha");
					jugadorAnimado.setDireccion(-1);
					*/
					jugadorAnimado.setAnimacionActual("caminar izquierda");
					izquierda = true;
					break;
				
				case "UP":
					jugadorAnimado.setAnimacionActual("caminar arriba");
					enemigo1.setAnimacionActual("caminar arriba enemigo");
					arriba = true;
					
					if(derecha==true)
						jugadorAnimado.setAnimacionActual("caminar derecha");
					if(izquierda==true)
						jugadorAnimado.setAnimacionActual("caminar izquierda");
					break;
					
				case "DOWN":
					jugadorAnimado.setAnimacionActual("caminar abajo");
					enemigo1.setAnimacionActual("caminar abajo enemigo");
					abajo = true;
					if(derecha==true)
						jugadorAnimado.setAnimacionActual("caminar derecha");
					if(izquierda==true)
						jugadorAnimado.setAnimacionActual("caminar izquierda");
					break;
				}
				
				
				
			}
			
			
		});
		
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				
				
				switch(evento.getCode().toString()){
				
				case "RIGHT":
					
					derecha = false;
					jugadorAnimado.setAnimacionActual("descanso derecha");
					
					if(arriba==true)
						jugadorAnimado.setAnimacionActual("caminar arriba");
					if(abajo==true)
						jugadorAnimado.setAnimacionActual("caminar abajo");
					if(izquierda==true)
						jugadorAnimado.setAnimacionActual("caminar izquierda");
					break;
					
				case "LEFT":
					
					
					izquierda = false;
					jugadorAnimado.setAnimacionActual("descanso izquierda");
					if(arriba==true)
						jugadorAnimado.setAnimacionActual("caminar arriba");
					if(abajo==true)
						jugadorAnimado.setAnimacionActual("caminar abajo");
					if(derecha==true)
						jugadorAnimado.setAnimacionActual("caminar derecha");
					
					break;
				
				case "UP":
					jugadorAnimado.setAnimacionActual("descanso arriba");
					enemigo1.setAnimacionActual("descanso izquierda enemigo");
					arriba = false;
					if(derecha==true)
						jugadorAnimado.setAnimacionActual("caminar derecha");
					if(izquierda==true)
						jugadorAnimado.setAnimacionActual("caminar izquierda");
					if(abajo==true)
						jugadorAnimado.setAnimacionActual("caminar abajo");
					
					break;
					
				case "DOWN":
					jugadorAnimado.setAnimacionActual("descanso abajo");
					enemigo1.setAnimacionActual("descanso izquierda enemigo");
					abajo = false;
					if(derecha==true)
						jugadorAnimado.setAnimacionActual("caminar derecha");
					if(izquierda==true)
						jugadorAnimado.setAnimacionActual("caminar izquierda");
					if(arriba==true)
						jugadorAnimado.setAnimacionActual("caminar arriba");
				
					break;
				}
				
			}
				
			
				
				
				
				
		});
	}

}
