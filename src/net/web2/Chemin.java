package net.web2;

public class Chemin {
	int[] debut;
	int[] fin;
	private int[][] run;

	public Chemin(){
		run = new int[][]{
				{0,0},{0,1},{0,2},{0,3},{1,3},{2,3},{3,3},{3,2},{3,1},
				{4,1},{5,1},{6,1},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},
				{6,6},{5,6},{4,6},{4,7},{4,8},{4,9},{5,9},{6,9},{6,10},
				{6,11},{6,12},{7,12},{8,12},{9,12},{9,13},{9,14}};
	}
	

	private int[] getDebut(){
		return debut = run[0];
	}
	private int[] getFinal(){
		return fin = run[run.length-1];
	}
}
