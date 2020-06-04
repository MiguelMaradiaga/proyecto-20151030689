package Implementacion;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Clases.Enemigo;
import Clases.Fondo;
import Clases.Fondo2;
import Clases.Interfaz;
import Clases.Item;
import Clases.JugadorAnimado;
import Clases.Menu;
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

public class Juego extends Application implements Serializable{
	
	private static GraphicsContext graficos;
	private static Group root;
	private static Scene escena;
	private static Canvas lienzo;
	public static int  paredIzquierda; //se usara para verificar una colsion izquierda
	public static int paredDerecha;//verifica colision derecha
	public static int paredArriba;//verifica colision arriba
	public static int paredAbajo;//verifica colision abajo
	public static  int d=0;//variable que controla el numero de disparos del jugador
	public static int disparosEnemigo=0;
	public static  int repeticionsDeCiclo;//registra el numero de veces que se repite el ciclo de juego
	public static int numSlimes=2;//controla el apawm de slimes
	public static JugadorAnimado jugadorAnimado;
	private static Fondo fondo;
	private static Fondo2 fondo2;
	public static Menu menu;
	public static Enemigo enemigo1;
	public static Enemigo dragon;
	public static Enemigo slime;
	public static Trampas flechas;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean izquierda;
	public static boolean derecha;
	public static boolean disparar=false;
	public static boolean limiteArriba=false;
	public static boolean limiteAbajo=false;
	public static boolean limiteDerecha=false;
	public static boolean limiteIzquerda=false;
	public static boolean nivel2=false;
	public static boolean nivel3=false;
	public static boolean disparado=true;
	public static boolean gameOver;
	public static boolean ganaste;
	public static HashMap<String, Image> imagenes;
	public static String nombreJugador;
	AnimationTimer animationTimer;
	public static ArrayList<Tile> tiles= new ArrayList<Tile>();//piso
	public static ArrayList<Tile> tiles2= new ArrayList<Tile>();//paredes
	public static ArrayList<Tile> tiles3=new ArrayList<Tile>();//lava
	public static ArrayList<Trampas> trampas= new ArrayList<Trampas>();//trampas
	public static ArrayList<Enemigo> slimes= new ArrayList<Enemigo>();
	public static ArrayList<Item> vidas = new ArrayList<Item>();
	public static ArrayList<Item> monedas = new ArrayList<Item>();
	public static ArrayList<Item> pociones = new ArrayList<Item>();
	public static boolean juegoIniciado;
	private static int mapaSlimes[][]= {//se usara para gestionar aparicion de slimes
			{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
			{8,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,8,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,8},
			{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
			};
	
	private static Interfaz interfaz;
	
	
	public static void main(String args[]) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage ventana) throws Exception {
		String opcion = new String();
			
		nombreJugador= "Mangel";
		
		
				inicializarComponentes();
				gestionEventos();
				
				ventana.setScene(escena);
				ventana.setTitle("Fire Kingdom");
				ventana.show(); 
				
				cicloJuego();
				
				
		
			}
		
		
		
		
		
		
	
	
	public void cicloJuego() {
		long tiempoInicial= System.nanoTime();

		animationTimer= new AnimationTimer() {
			
			@Override
			public void handle(long tiempoActual) {
			
				double t= (tiempoActual-tiempoInicial)/1000000000.0;	
				if(juegoIniciado) {
				actualizarEstado(t);
				
				pintar();
				}
				else {
					
					menu.gestionEventos();
					menu.pintar(graficos);
				}
			}
			
			
		};
		
		animationTimer.start();
		
		
		return;
	}
	
	public void actualizarEstado(double t) {
	
		
		if(jugadorAnimado.getVidas()<=0) {
		
			juegoIniciado=false;
			menu.setNombreImagen("game over");
			menu.gestionEventos();
			fondo.pintar(graficos);
			menu.pintar(graficos);
			return;
		}
		
		if(ganaste) {
			juegoIniciado=false;
			menu.setNombreImagen("win");
			menu.gestionEventos();
			fondo.pintar(graficos);
			menu.gestionEventos();
			menu.pintar(graficos);
		return;	
		}
		
		
		
		jugadorAnimado.calcularFrame(t);
		jugadorAnimado.mover();
		
		
		
		
		for (int i = 0; i < jugadorAnimado.disparos.size(); i++) {
			jugadorAnimado.disparos.get(i).calcularFrame(t);
			jugadorAnimado.disparos.get(i).mover();
		}
		
		if(repeticionsDeCiclo==100)
			if(JugadorAnimado.mana<100)
				JugadorAnimado.mana=JugadorAnimado.mana+10;
		
		
		enemigo1.calcularFrame(t);
		enemigo1.mover();
		dragon.calcularFrame(t);
		dragon.mover();
		
		
		try {
			for (int i = 0; i < slimes.size(); i++) 
				 
					slimes.get(i).calcularFrame(t);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		try {
			for (int i = 0; i < slimes.size(); i++) 
			
					slimes.get(i).mover();
		} catch (Exception e) {
		}
	
		
		if(repeticionsDeCiclo%50==0&&enemigo1.getVidas()>0) {
		enemigo1.disparar();
		}
		if(nivel3)
		if(repeticionsDeCiclo%50==0&&dragon.getVidas()>0) {
			dragon.dispararDragon();
			}
		
		if(repeticionsDeCiclo==100)
			repeticionsDeCiclo=0;
		
		repeticionsDeCiclo++;
		
		
		
		
		for (int i = 0; i < enemigo1.disparos.size(); i++) {
			enemigo1.disparos.get(i).calcularFrame(t);
		}
		for (int i = 0; i < enemigo1.disparos.size(); i++) {
			enemigo1.disparos.get(i).mover();
		}
		
		for (int i = 0; i < dragon .disparos.size(); i++) {
			dragon.disparos.get(i).calcularFrame(t);
		}
		for (int i = 0; i < dragon.disparos.size(); i++) {
			dragon.disparos.get(i).mover();
		}
		
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).mover();
			
		
		}
		for (int i = 0; i < tiles2.size(); i++) {
			tiles2.get(i).mover();
			
		
		}
		
		for (int i = 0; i < tiles3.size(); i++) {
			tiles3.get(i).mover();
			
		
		}
		
		for (int i = 0; i < trampas.size(); i++) {
			trampas.get(i).calcularFrame(t);
			trampas.get(i).mover();
		}
		
		for (int i = 0; i < vidas.size(); i++) {
			vidas.get(i).mover();
		}
		
		for (int i = 0; i < monedas.size(); i++) {
			monedas.get(i).mover();
		}
		
		for (int i = 0; i < pociones.size(); i++) {
			pociones.get(i).mover();
		}
		coliciones();
		
		fondo2.cerrarHabitacion();
		
		
		
	}
	
	private void coliciones() {
	
		paredAbajo=0;
		paredArriba=0;
		paredDerecha=0;
		paredIzquierda=0;
		
		for (int i = 0; i < tiles2.size(); i++) {
			tiles2.get(i).verificarColisionJugadorCosas();
		}
		
		for (int i = 0; i < tiles3.size(); i++) {
			tiles3.get(i).verificarJugadorLava();
		}
		
		if(paredAbajo==tiles2.size())
			limiteAbajo=false;
		if(paredArriba==tiles2.size())
			limiteArriba=false;
		if(paredIzquierda==tiles2.size())
			limiteIzquerda=false;
		if(paredDerecha==tiles2.size())
			limiteDerecha=false;
		
		for (int i = 0; i < vidas.size(); i++) {
			jugadorAnimado.verificarColisionesItem(vidas.get(i));
		}
		
		for (int i = 0; i < monedas.size(); i++) {
			jugadorAnimado.verificarColisionesItem(monedas.get(i));
		}
		for (int i = 0; i < pociones.size(); i++) {
			jugadorAnimado.verificarColisionesItem(pociones.get(i));
		}
		
		if(enemigo1.getVidas()>0)
		for (int i = 0; i < jugadorAnimado.disparos.size(); i++) {
			jugadorAnimado.disparos.get(i).verificarColisionesEnemigo(enemigo1);
		}
		for (int i = 0; i < jugadorAnimado.disparos.size(); i++) {
			jugadorAnimado.disparos.get(i).verificarColisionesEnemigo(dragon);
		}
		
		for (int i = 0; i < enemigo1.disparos.size(); i++) {
			enemigo1.disparos.get(i).verificarColisionesJugador(jugadorAnimado);
		}
		
		for (int i = 0; i < dragon.disparos.size(); i++) {
			dragon.disparos.get(i).verificarColisionesJugador(jugadorAnimado);
		}
		
		for (int i = 0; i < jugadorAnimado.disparos.size(); i++) {
			enemigo1.sextoSentido(jugadorAnimado.disparos.get(i));
		}
		
		
			for (int i = 0; i < trampas.size(); i++) {
				trampas.get(i).colisionFlechaJugador();
			}
			
			for (int i = 0; i < trampas.size(); i++) {
				
				for (int j = 74; j < tiles2.size(); j++) {
					tiles2.get(j).verificarColisionFlechaPared(j, trampas.get(i));
				}
				
			}
			
			for (int j = 0; j < enemigo1.disparos.size(); j++) {
				for(int i=0;i<tiles2.size();i++) {
					
					tiles2.get(i).verificarColisionParedDisparoEnemigo(enemigo1,j);
			}}
			
			for (int j = 0; j < dragon.disparos.size(); j++) {
				for(int i=0;i<tiles2.size();i++) {
					
					tiles2.get(i).verificarColisionParedDisparoEnemigo(dragon,j);
			}}
		
			
		if(jugadorAnimado.disparos.size()>0)
		for (int j = 0; j < jugadorAnimado.disparos.size(); j++) {
			for(int i=0;i<tiles2.size();i++) {
				
				tiles2.get(i).verificarColisionParedDisparoJugador(j);;
		}}
		
			for (int i = 0; i < slimes.size(); i++) {
				slimes.get(i).colisionJugadorSlime(i);
			}
		
			
			for (int i = 0; i < slimes.size(); i++) {
				for (int j = 0; j < jugadorAnimado.disparos.size(); j++) {
					jugadorAnimado.disparos.get(j).colisionDisparoSlime(slimes.get(i), j,i);
				}
			}
				
	}

	public static void inicializarComponentes() {
		 imagenes= new HashMap<String, Image>();
		 
		 cargarImagenes();
		 
		 
		 jugadorAnimado = new JugadorAnimado(70,70*5,3,"soldado",3,"descanso derecha");
		
		 enemigo1 = new Enemigo("enemigo1",70*28,70*5,3,"soldado",10,"descanso izquierda enemigo");
		 
		dragon = new Enemigo("dragon", 70*87,70*5, 3, "dragon", 20, "descanso dragon");
		// dragon = new Enemigo("dragon", 200,400, 3, "dragon", 2, "descanso dragon");
		 nivel3=true;
		 for (int i = 0; i < mapaSlimes.length; i++) {
			for (int j = 0; j < mapaSlimes[i].length; j++) {
				if(mapaSlimes[i][j]==1)
					slimes.add( new Enemigo("slime", j*70,i*70,3, "slime", 2, "descanso slime"));
			}
		}
		 
		 
		 
		 fondo = new Fondo(0, 0, 3,"fondo negro","fondo2");
		 
		 for (int i = 1; i <tiles2.size(); i++) {
			trampas.add(new Trampas((70*i*2+(70/2)-8),70*3/2-8,3,"flechas","descanso flechas"));
		}
		 
		 
		 vidas.add(new Item(30*70+20,70*4+20,3,"corazon",1)) ;
		 vidas.add(new Item(30*70+20,70*6+20,3,"corazon",1)) ;
		 vidas.add(new Item(60*70+20,70*4+20,3,"corazon",1)) ;
		 vidas.add(new Item(60*70+20,70*6+20,3,"corazon",1)) ;
		 pociones.add(new Item(30*70+20,70*2+20,3,"pocion mana",1)) ;
		 pociones.add(new Item(30*70+20,70*8+20,3,"pocion mana",1)) ;
		 pociones.add(new Item(60*70+20,70*2+20,3,"pocion mana",1)) ;
		 pociones.add(new Item(60*70+20,70*8+20,3,"pocion mana",1)) ;
		 monedas.add( new Item(3*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(11*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(17*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(27*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(33*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(45*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(51*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(57*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(67*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(71*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(79*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(81*70+20,70*2+20, 3, "moneda", 0));
		fondo2=new Fondo2("piedra",3);
		 root = new Group();
		 escena= new Scene(root, 830,700); 
		 lienzo = new Canvas(850, 700);
		root.getChildren().add(lienzo);
		graficos = lienzo.getGraphicsContext2D();
		interfaz = new Interfaz(jugadorAnimado);
		menu = new Menu(75, 0, 3, "nuevo juego", escena);
		fondo = new Fondo(0, 0, 3, "fondo", "fondo");
		
		
	}
	
	
	public static void cargarImagenes() {
		
		imagenes.put("fondo",new Image("Fondo negro.png"));
		imagenes.put("fondo2",new Image("Fondo negro.png"));
		imagenes.put("tilemap",new Image("suelo y pared.png"));
		imagenes.put("soldado", new Image("Actor2_png.png"));
		imagenes.put("corazon", new Image("corazon.png"));
		imagenes.put("flechas", new Image("animacion flechas.png"));
		imagenes.put("firepink",new Image("firepink.png"));
		imagenes.put("firered",new Image("firered.png"));
		imagenes.put("lava",new Image("Lava #4.png") );
		imagenes.put("moneda", new Image("coin.png"));
		imagenes.put("slime", new Image("slime.png"));
		imagenes.put("dragon", new Image("dragon.png"));
		imagenes.put("nuevo juego",new Image("nuevoJuego.png"));
		imagenes.put("puntuaciones", new Image("puntuaciones.png"));
		imagenes.put("salir", new Image("Salir.png"));
		imagenes.put("game over", new Image("game over.png"));
		imagenes.put("mostrar puntos",new Image("mostrarPuntuaciones.png"));
		imagenes.put("espacio", new Image("space.png"));
		imagenes.put("enter", new Image("enter.jpg"));
		imagenes.put("teclas", new Image("teclas.jpg"));
		imagenes.put("pocion mana", new Image("pocionMana.png"));
		imagenes.put("win",new Image("win.png"));
	}
	
	public void pintar() {
		
		
		fondo.pintar(graficos);
		for (int i = 0; i < tiles.size(); i++) 
			tiles.get(i).pintar(graficos);
		
		
		for (int i = 0; i < tiles2.size(); i++) 
			tiles2.get(i).pintar(graficos);
		
		
		for (int i = 0; i < tiles3.size(); i++) 
			tiles3.get(i).pintar(graficos);
		

		
		jugadorAnimado.pintar(graficos);
		
		for (int i = 0; i < jugadorAnimado.disparos.size(); i++) 
			jugadorAnimado.disparos.get(i).pintar(graficos);
		
		
		
		
		
		enemigo1.pintar(graficos);
		dragon.pintar(graficos);
		try {
			for (int i = 0; i < slimes.size(); i++) 
				
					slimes.get(i).pintar(graficos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
		for (int i = 0; i < vidas.size(); i++) {
			vidas.get(i).pintar(graficos);
		}
		
		for (int i = 0; i < monedas.size(); i++) {
			monedas.get(i).pintar(graficos);
		}
		
		for (int i = 0; i < pociones.size(); i++) {
			pociones.get(i).pintar(graficos);
		}
		
		for (int j = 0; j < trampas.size(); j++) 
			trampas.get(j).pintar(graficos);
		
		
		
		
		for (int i = 0; i < enemigo1.disparos.size(); i++) 
			enemigo1.disparos.get(i).pintar(graficos);
		
		if(nivel3)
		for (int i = 0; i < dragon.disparos.size(); i++) 
			dragon.disparos.get(i).pintar(graficos);
		
		

		//graficos.fillText("vidas = "+jugadorAnimado.getVidas(), 900, 350);
		interfaz.pintar(graficos);
		
		
	}
	
	public static void gestionEventos() {
		
		if(juegoIniciado) {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			

			@Override
			public void handle(KeyEvent evento) {
			
				
				if(juegoIniciado)
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
					
				case "SPACE":
					if(disparado)
					if(JugadorAnimado.mana>9) {
					jugadorAnimado.disparar();
					disparado=false;
					
					}
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
					
					abajo = false;
					if(derecha==true)
						jugadorAnimado.setAnimacionActual("caminar derecha");
					if(izquierda==true)
						jugadorAnimado.setAnimacionActual("caminar izquierda");
					if(arriba==true)
						jugadorAnimado.setAnimacionActual("caminar arriba");
				
					break;
				case "SPACE":
					disparado=true;
					
					break;
				}
				
			}
				
			
				
				
				
				
		});
		
		}else {
			menu.gestionEventos();
		
		}
	}

	
	public static void reinicar() {

		 tiles= new ArrayList<Tile>();//piso
		 tiles2= new ArrayList<Tile>();//paredes
		 tiles3=new ArrayList<Tile>();//lava
		 trampas= new ArrayList<Trampas>();//trampas
		 slimes= new ArrayList<Enemigo>();
		 vidas = new ArrayList<Item>();
		 monedas = new ArrayList<Item>();
		 pociones = new ArrayList<Item>();
		
		 jugadorAnimado = new JugadorAnimado(70,70*5,3,"soldado",3,"descanso derecha");
			
		 enemigo1 = new Enemigo("enemigo1",70*28,70*5,3,"soldado",10,"descanso izquierda enemigo");
		 
		 dragon = new Enemigo("dragon", 70*87,70*5, 3, "dragon", 20, "descanso dragon");
		 
		 for (int i = 0; i < mapaSlimes.length; i++) {
			for (int j = 0; j < mapaSlimes[i].length; j++) {
				if(mapaSlimes[i][j]==1)
					slimes.add( new Enemigo("slime", j*70,i*70,3, "slime", 2, "descanso slime"));
			}
		}
		 
		 
		 
		 fondo = new Fondo(0, 0, 3,"fondo negro","fondo2");
		 
		 for (int i = 1; i < 45; i++) {
			 if(i!=14&&i!=15&&i!=29&&i!=30&&i!=44)
			trampas.add(new Trampas((70*i*2+(70/2)-8),70*3/2-8,3,"flechas","descanso flechas"));
		}
		 
		 vidas.add(new Item(30*70+20,70*4+20,3,"corazon",1)) ;
		 vidas.add(new Item(30*70+20,70*6+20,3,"corazon",1)) ;
		 vidas.add(new Item(60*70+20,70*4+20,3,"corazon",1)) ;
		 vidas.add(new Item(60*70+20,70*6+20,3,"corazon",1)) ;
		 pociones.add(new Item(30*70+20,70*2+20,3,"pocion mana",1)) ;
		 pociones.add(new Item(30*70+20,70*8+20,3,"pocion mana",1)) ;
		 pociones.add(new Item(60*70+20,70*2+20,3,"pocion mana",1)) ;
		 pociones.add(new Item(60*70+20,70*8+20,3,"pocion mana",1)) ;
		 monedas.add( new Item(3*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(11*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(17*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(27*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(33*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(45*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(51*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(57*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(67*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(71*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(79*70+20,70*2+20, 3, "moneda", 0));
		 monedas.add( new Item(81*70+20,70*2+20, 3, "moneda", 0));
		 
			fondo2=new Fondo2("piedra",3);
			
			interfaz = new Interfaz(jugadorAnimado);
			fondo = new Fondo(0, 0, 3, "fondo", "fondo");
			
			arriba=false;
			abajo=false;
			izquierda=false;
			derecha=false;
			disparar=false;
			limiteArriba=false;
			limiteAbajo=false;
			limiteDerecha=false;
			 limiteIzquerda=false;
			nivel2=false;
			nivel3=false;
			disparado=false;
			ganaste = false;
			
	}
	
}
