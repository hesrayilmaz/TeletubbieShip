package teletubbies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ship {

	//the initial x direction of the ship
	private int shipX=300;
	private BufferedImage shipImage;
	
	//gets ship picture that is inside the project 
	public void picturizeShip(Graphics g) {
		try {
			shipImage=ImageIO.read(new File("ship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	
	//ship goes to left by 20
	public void shiftLeft() {
		this.shipX-=20;
	}
	//ship goes to right by 20
	public void shiftRight() {
		this.shipX+=20;
	}
	
	public int getShipX(){
		return shipX;
	}
	
	public void setShipX(int x) {
		shipX=x;
	}
	
	public BufferedImage getShipImage() {   
		return shipImage;
	}

}