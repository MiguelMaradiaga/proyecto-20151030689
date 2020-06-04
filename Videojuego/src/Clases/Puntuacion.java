package Clases;

import java.io.Serializable;

public class Puntuacion implements Serializable{

	private String nombre;
	private int puntuacion;
	public Puntuacion(String nombre, int puntuacion) {
		super();
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	@Override
	public String toString() {
		return "nombre:" + nombre + "\t\t\t puntuacion:" + puntuacion;
	}
	
}
