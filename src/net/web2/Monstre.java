package net.web2;

import android.graphics.Bitmap;

public class Monstre {
	
	Chemin chemin;
	Bitmap bitmap;
	float dDD; // distance Depuis Début
	float vx=1; // vitesse
	int position;
	TileView grille;
	float x;
	float y;
	
	private int getX(){
		x = grille.getX(position);
		return (int) x;
	}
	
	private int getY(){
		y = grille.getY(position);
		return (int) y;
	}
	
	private void move(){
		position = position + 1; // ou ++position
	}
}
