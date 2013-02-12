package net.web2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Monstre {
	
	Chemin chemin;
	Bitmap bitmap;
	float dDD; // distance Depuis Début
	float vx=1; // vitesse
	int position;
	TileView grille;
	Paint paint;
	Wave wave;
	float x;
	float y;
	
	void Monstre(Bitmap bitmap, float x, float y, Wave wave){
		bitmap = this.bitmap;
		x = this.x;
		y = this.y;
		wave = this.wave;
		paint = new Paint();
	}
	
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
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x, y, paint);
	}
}
