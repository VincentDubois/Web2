package net.web2;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Wave {
	Bitmap bitmap;
	Chemin chemin;
	ArrayList<Monstre> liste_monstres;
	int nombre_monstres;

	public Wave(Bitmap bitmap, Chemin chemin, int nombre_monstres) {
		this.bitmap = bitmap;
		this.chemin = chemin;
		this.nombre_monstres = nombre_monstres;
		liste_monstres = new ArrayList<Monstre>();
	}

	public void draw(Canvas canvas) {
		for(Monstre monstre: liste_monstres){
			monstre.draw(canvas);
		}
	}

	public void move(){
		for(Monstre monstre: liste_monstres){
			monstre.move();
		}
	}
	
	public boolean arrived(Chemin chemin){
		Iterator<Monstre> it = liste_monstres.iterator();
		while(it.hasNext()){
			Monstre monstre = it.next();
			if(monstre.position >= (float) chemin.getPositionFinale()){
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public void addMonstre(){
		liste_monstres.add(new Monstre(bitmap, 0, this, chemin));
	}
	
	public boolean destroyed(){
		if(liste_monstres.isEmpty())	return true;
		else	return false;
	}
}
