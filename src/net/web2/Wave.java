package net.web2;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Wave {
	Bitmap bitmap;
	ArrayList<Monstre> liste_monstres;
	float position;
	
	public Wave(Bitmap bitmap) {
		this.bitmap = bitmap;
		liste_monstres = new ArrayList<Monstre>();
		
		liste_monstres.add(new Monstre(bitmap, position, this));
		liste_monstres.add(new Monstre(bitmap, position, this));
		liste_monstres.add(new Monstre(bitmap, position, this));

	}
	
	public void draw(Canvas canvas) {
		for(Monstre monstre: liste_monstres){
			monstre.draw(canvas);
			canvas.drawBitmap(monstre.bitmap, monstre.x, monstre.y, monstre.paint);
		}
	}

}
