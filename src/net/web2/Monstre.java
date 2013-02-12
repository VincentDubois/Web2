package net.web2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Monstre {
	
	Chemin chemin;
	Bitmap bitmap;
	float dDD; // distance Depuis Début
	float vx=1; // vitesse
	float position;
	TileView grille;
	Paint paint;
	Wave wave;
	float x;
	float y;
	
	public Monstre(Bitmap bitmap, float position, Wave wave, Chemin chemin){
		this.bitmap = bitmap;
		this.position = position;
		this.wave = wave;
		paint = new Paint();
		this.chemin = chemin;
	}
	
	private void move(){
		position = position + 1; // ou ++position
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x, y, paint);
	}
}
