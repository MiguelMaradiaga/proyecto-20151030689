package Clases;

import java.util.ArrayList;

import Implementacion.Juego;


public class Fondo2 {
	private String materialPiso;
	private String cosas;
	private int x;
	private int y;
	private int velocidad;
	private  int puerta1;
	private  int puerta2;
	private Tile ultimoTile;
	private boolean puerta1Cargada=false; 
	private boolean puerta2Cargada=false;
	private int pared1;
	private int  pared2;

	private ArrayList<Tile> copia;
	

	private int paredes[][]= {
			{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
			{8,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,8,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,8,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
			{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
			
			};
	
	private int lava[][]= {
			{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
			{8,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,8,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,8,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,8},
			{8,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,8,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,8,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,8,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,8,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,8},
			{8,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,16,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,16,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,8,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,8,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,8},
			{8,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,8,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,8,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,8},
			{8,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,8,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,8,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,8},
			{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
			
			};
	
	
	
	
	private int tilemap[][]= new int[paredes.length][paredes[0].length];
	

	public Fondo2(String materialPiso,int velocidad) {
		
		this.materialPiso = materialPiso;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.tilemap = tilemap;
		this.paredes = paredes;
		copia= new ArrayList<Tile>();
		inicializarSuelo(materialPiso);
		agregarSuelo(tilemap);
		agregarCosas();
		agregarLava();
		cargarLava();
	}
	
	
	public void agregarCosas() {
		
		
	
		for (int i = 0; i < paredes.length; i++) {
			for (int j = 0; j < paredes[i].length; j++) {
				
				if (paredes[i][j]!=0) 
					Juego.tiles2.add(new Tile(paredes[i][j],j*70,i*70,velocidad, "tilemap",95,95));
				
				if(i==4&&j==29) {
					pared1=Juego.tiles2.size()-1;
					pared2=Juego.tiles2.size();
	}
				if(i==5&&j==29) {
					puerta1=Juego.tiles2.size()-1;
					puerta2= Juego.tiles2.size();
					}
				
			}
		}
		
		
	}
				
		
public void agregarLava() {
		
		for (int i = 0; i < lava.length; i++) {
			for (int j = 0; j < lava[i].length; j++) {
				
				if (lava[i][j]==0&&j%2==0) 
					lava[i][j]=17;
			}
		}
		
	}
	
	public void cargarLava() {
		
		
	for (int i = 0; i < lava.length; i++) {
		for (int j = 0; j < lava[i].length; j++) {
			
			if (lava[i][j]==17) 
				Juego.tiles3.add(new Tile(lava[i][j],j*70,i*70,3, "lava",70,70));
		}
	}
		 
	}	
	
	
	public void inicializarSuelo(String materialPiso) {
		switch (materialPiso) {
		case "piedra":
				for (int i = 0; i < tilemap.length; i++) {
					for (int j = 0; j < tilemap[i].length; j++) {
						tilemap[i][j]=1;
						
				
					}
				}
			
				agregarSuelo(tilemap);
			break;
			
		case "otro":
				
			for (int i = 0; i < tilemap.length; i++) {
				for (int j = 0; j < tilemap[i].length; j++) {
					tilemap[i][j]=2;//piedra
					
				}
			}
			agregarSuelo(tilemap);
			
			
			break;
			
		
		
		}
			
		
	}
	
	
	public void cerrarHabitacion() {
		if(Juego.nivel2)
		if(!puerta1Cargada) {
			ultimoTile = Juego.tiles2.get(puerta1);
		Juego.tiles2.set(puerta1, (new Tile(8, Juego.tiles2.get(29).getX(),5*70,3, "tilemap",95,95)));
		Juego.tiles2.add(ultimoTile);
		Juego.tiles2.set(pared1, new Tile(8, Juego.tiles2.get(29).getX(),Juego.tiles2.get(pared1).getY(), velocidad, "tilemap", 95, 95));
		
		puerta1Cargada=true;
		}
				
		if(Juego.nivel3)
			if(!puerta2Cargada){
				ultimoTile = Juego.tiles2.get(puerta2);
				Juego.tiles2.set(puerta2, (new Tile(8, Juego.tiles2.get(59).getX(),5*70,3, "tilemap",95,95)));
				Juego.tiles2.add(ultimoTile);
				Juego.tiles2.set(pared2, new Tile(8, Juego.tiles2.get(59).getX(),Juego.tiles2.get(pared2).getY(), velocidad, "tilemap", 95, 95));
				
				puerta2Cargada=true;
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

	



	


	
}







	
	
	
	
	
	
