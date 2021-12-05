package teletubbies;

import java.util.Random;

public class Meteor{
    //x direction of the meteor
	private int meteorX;
	//x direction of the meteor
	private int meteorY;
	//meteor's width and length
	private int sizeX,sizeY;
	
	
	public Meteor() {
		//creates random object to make the x direction random
		Random meteorDirec=new Random();
		//creates random object to make the width and length random
		Random size=new Random();
		//it is up to 700 because of our screen size
		meteorX= meteorDirec.nextInt(700);
		//y direction is 0 at first
		meteorY=0;
		sizeX=size.nextInt(31)+40;
		sizeY=size.nextInt(31)+40;
	}
	

	public int getSizeX() {
		return sizeX;
	}


	public int getSizeY() {
		return sizeY;
	}


	public int getmeteorX() {
		return meteorX;
	}


	public void setmeteorX(int xMeteor) {
		this.meteorX = xMeteor;
	}


	public int getmeteorY() {
		return meteorY;
	}


	public void setmeteorY(int yMeteor) {
		this.meteorY = yMeteor;
	}
	
	
}