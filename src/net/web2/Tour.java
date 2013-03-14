package net.web2;

import android.graphics.Bitmap;

public class Tour {
	int i, j;
	int porteeMin, porteeMax;
	int puissance;
	Monstre cible;
	
	public Tour(int i, int j, int porteeMin, int porteeMax, int puissance) {
		this.i = i;
		this.j = j;
		this.porteeMin = porteeMin;
		this.porteeMax = porteeMax;
		this.puissance = puissance;
		this.cible = null;
	}
	
}
