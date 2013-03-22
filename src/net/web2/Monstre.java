package net.web2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Monstre {
	
	Chemin chemin;
	Bitmap bitmap;
	float vx; // vitesse
	float position;
	TileView grille;
	Paint paint;
	Wave wave;

	public Monstre(Bitmap bitmap, float position, Wave wave, Chemin chemin){
		this.bitmap = bitmap;
		this.position = position;
		this.wave = wave;
		this.chemin = chemin;
		vx = (float) 0.1;
		paint = new Paint();
	}
	
	void move(){
		position += vx;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, getX(), getY(), paint);
	}

	public float getX() {
		return chemin.getXInterpolation(position);
	}
	
	public float getY() {
		return chemin.getYInterpolation(position);
	}
}
