package net.web2;

import android.graphics.Bitmap;

public class WaveManager {
	Wave vague;

	public WaveManager() {
	}
	
	public void setWave(Bitmap bitmap, Chemin chemin, int nombre_monstres){
		vague = new Wave(bitmap, chemin, nombre_monstres);
	}
	
	public Wave getWave(){
		return vague;
	}
	
	public void addMonstre(Wave vague){
		vague.addMonstre();
		vague.nombre_monstres--;
	}
	
	public boolean complete(){
		if(vague.nombre_monstres == 0)	return true;
		else return false;
	}
}
