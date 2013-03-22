package net.web2;


public class Tour {
	int i, j;
	int porteeMin = 0, porteeMax = 2;
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
	
	public void cible(double d, Monstre monstre) {
		if (porteeMin<d && d<porteeMax){
			cible = monstre;
		}
	}
}
