package Clases;

import java.util.ArrayList;

import Implementacion.Juego;


public class Fondo2 {
	private String materialPiso;
	private String cosas;
	private int x;
	private int y;
	private int velocidad;

	
	private int tilemap[][]= new int[10][10];
	private int paredes[][]= {
			{8,8,8,8,8,8,8,8,8,8},
			{8,16,16,16,16,16,16,16,16,8},
			{8,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,16},
			{8,0,0,0,0,0,0,0,0,0},
			{8,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,8},
			{8,8,8,8,8,8,8,8,8,8},
			
			};
	

	public Fondo2(String materialPiso,int velocidad) {
		
		this.materialPiso = materialPiso;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.tilemap = tilemap;
		this.paredes = paredes;
		inicializarSuelo(materialPiso);
		agregarSuelo(tilemap);
		agregarCosas();
	}
	
	
	public void agregarCosas() {
		
		
	
		for (int i = 0; i < paredes.length; i++) {
			for (int j = 0; j < paredes[i].length; j++) {
				
				if (paredes[i][j]!=0) 
					Juego.tiles2.add(new Tile(paredes[i][j],j*70,i*70,velocidad, "tilemap",95,95));
			}
		}
		
		
	}
				
		
		
		 
		
	
	
	public void inicializarSuelo(String materialPiso) {
		switch (materialPiso) {
		case "madera":
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						tilemap[i][j]=1;//madera
					}
				}
			
				agregarSuelo(tilemap);
			break;
			
		case "piedra":
				
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					tilemap[i][j]=2;//piedra
				}
			}
			agregarSuelo(tilemap);
			
			break;
			
		
		
		}
			
		
	}
	
	public void agregarSuelo( int tilemap[][]) {
		
			
		
		for (int i = 0; i < tilemap.length; i++) {
			for (int j = 0; j < tilemap[i].length; j++) {
				
				if (tilemap[i][j]!=0) 
					Juego.tiles.add(new Tile(tilemap[i][j],x+j*70,y+i*70,velocidad, "tilemap",95,95));
			}
		}
	}
	
	public String getMaterialPiso() {
		return materialPiso;
	}

	public void setMaterialPiso(String materialPiso) {
		this.materialPiso = materialPiso;
	}

	


	public int[][] getTilemap() {
		return tilemap;
	}

	public void setTilemap(int[][] tilemap) {
		this.tilemap = tilemap;
	}


	
}







	
	
	
	
	
	
