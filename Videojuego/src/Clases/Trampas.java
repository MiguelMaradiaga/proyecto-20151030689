package Clases;

import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Trampas extends ObjetoJuego{
	
	private HashMap<String,Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion=1;

	
	
	public Trampas(int x, int y, int velocidad, String nombreImagen) {
		super(x, y, velocidad, nombreImagen);
		this.animaciones = animaciones;
		this.xImagen = xImagen;
		this.yImagen = yImagen;
		this.anchoImagen = anchoImagen;
		this.altoImagen = altoImagen;
		this.animacionActual = animacionActual;
		this.direccion = direccion;
	}

	public void inicializarAnimaciones() {
		Rectangle coordenadasFlechas[] = {
				new Rectangle(0,0,7,6),
				new Rectangle(145,0,60,145),
				new Rectangle(145*2,0,60,145),
				new Rectangle(145*3,0,250,145),
				};
		
		
		Animacion animacionFlechas = new Animacion(0.2,coordenadasFlechas );
		
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get("animacion flechas"),this.x,this.y,30,30);
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}

}
