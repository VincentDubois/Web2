package net.web2;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Wave {
	Bitmap bitmap;
	ArrayList<Monstre> liste_monstres;
	
	public Wave(Bitmap bitmap, Chemin chemin) {
		this.bitmap = bitmap;
		liste_monstres = new ArrayList<Monstre>();
		liste_monstres.add(new Monstre(bitmap, 0, this,chemin));
		liste_monstres.add(new Monstre(bitmap, 1, this,chemin));
		liste_monstres.add(new Monstre(bitmap, 2, this,chemin));
	}
	
	public void draw(Canvas canvas) {
		for(Monstre monstre: liste_monstres){
			monstre.draw(canvas);
			canvas.drawBitmap(monstre.bitmap, monstre.getX(), monstre.getY(), monstre.paint);
		}
	}
	
	public void move(){
		for(Monstre monstre: liste_monstres){
			monstre.move();
		}
	}

}
