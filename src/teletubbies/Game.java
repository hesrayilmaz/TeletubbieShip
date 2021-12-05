package teletubbies;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener {

	Timer timer = new Timer(5, this);

	//counter will determine the frequency of the creation of meteors and fuels
	private int counter = 0;
	//initial score and heart
	private int score = 0;
	private int heart = 3;
	
	public Ship spaceShip = new Ship();
	
	//shot, meteor, and fuel (which is actually a meteor object) arraylists to add them to the screen or delete them from the screen
	private ArrayList<Shot> shots = new ArrayList<Shot>();
	private ArrayList<Meteor> meteors = new ArrayList<Meteor>();
	private ArrayList<Meteor> fuels = new ArrayList<Meteor>();

	
	public Game() {
		timer.start();
		setBackground(Color.black);
		spaceShip.picturizeShip(getGraphics());
	}

	
	//we checked if the shot and meteor is crushed by iterating each shot and meteor in the arraylists
	//we checked it by making them rectangle and controlling if they intersects
	//if there is a intersection it returns the meteor that is crushed
	//if not, it returns null
	public Meteor isCrushMShot() {
		for (Shot changedShot : shots) {
			for (Meteor changedMeteor : meteors) {
				if (new Rectangle(changedShot.getX(), changedShot.getY(), 10, 20)
						.intersects(new Rectangle(changedMeteor.getmeteorX(), changedMeteor.getmeteorY(), changedMeteor.getSizeX(), 
								changedMeteor.getSizeY()))) 
					return changedMeteor;	
				}
		}
		return null;
	}

	//we checked if the shot and fuel is crushed by iterating each shot and fuel in the arraylists
	//we checked it by making them rectangle and controlling if they intersects
	//if there is a intersection it returns the fuel that is crushed
	//if not, it returns null
	public Meteor isCrushFShot() {
		for (Shot changedShot : shots) {
			for (Meteor changedFuel : fuels) {
				if (new Rectangle(changedShot.getX(), changedShot.getY(), 10, 20)
						.intersects(new Rectangle(changedFuel.getmeteorX(), changedFuel.getmeteorY(), 40, 40))) 
					return changedFuel;			
				}
		}
		return null;
	}

	//we checked if the meteor and the ship is crushed by iterating each meteor in the arraylist
	//we checked it by making them rectangle and controlling if they intersects
	//if there is a intersection it returns the meteor that crushed
	//if not, it returns null
	
	public Meteor isCrushMShip() {
		for (Meteor changedMeteor : meteors) {
			if (new Rectangle(changedMeteor.getmeteorX(), changedMeteor.getmeteorY(), changedMeteor.getSizeX(), changedMeteor.getSizeY())
					.intersects(new Rectangle(spaceShip.getShipX(), 470, spaceShip.getShipImage().getWidth() / 12,
							spaceShip.getShipImage().getHeight() / 12))) 
				return changedMeteor;
		}
		return null;
	}
	
	
	//we checked if the fuel and the ship is crushed by iterating each fuel in the arraylist
	//we checked it by making them rectangle and controlling if they intersects
	//if there is a intersection it returns the fuel that crushed
	//if not, it returns null
	public Meteor isCrushFShip() {
		for (Meteor changedFuel : fuels) {
			if (new Rectangle(changedFuel.getmeteorX(), changedFuel.getmeteorY(), 40, 40)
					.intersects(new Rectangle(spaceShip.getShipX(), 470, spaceShip.getShipImage().getWidth() / 12,
							spaceShip.getShipImage().getHeight() / 12))) 
				return changedFuel;
		}
		return null;
	}

	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Font font = new Font("Times New Roman", Font.PLAIN, 20);
		g.setColor(Color.WHITE);
		g.setFont(font);
		
		//we put the current heart and score to the screen by setting their sizes
		g.drawString("Heart: " + heart, 20, 30);
		g.drawString("Score: " + score, 20, 50);

		//if shot leaves the top of the screen we delete it from the arraylist
		for (Shot changedShot : shots) {
			if (changedShot.getY() < 0)
				shots.remove(changedShot);
		}
		
		//if meteor leaves the bottom of the screen we delete it from the arraylist
		for (Meteor changedMeteor : meteors) {
			if (changedMeteor.getmeteorY() > 500)
				meteors.remove(changedMeteor);
		}
		
		//we set the directions and the size of the ship image
		g.drawImage(spaceShip.getShipImage(), spaceShip.getShipX(), 470, spaceShip.getShipImage().getWidth() / 12,
				spaceShip.getShipImage().getHeight() / 12, this);
		
		
		//we set the shot
		g.setColor(Color.ORANGE);
		for (Shot changedShot : shots)
			g.fillRect(changedShot.getX(), changedShot.getY(), 10, 20);

		//we set the meteor
		g.setColor(Color.GRAY);
		for (Meteor changedMeteor : meteors)
			g.fillOval(changedMeteor.getmeteorX(), changedMeteor.getmeteorY(), changedMeteor.getSizeX(), changedMeteor.getSizeY());
		
		//we set the fuel
		g.setColor(Color.BLUE);
		for (Meteor changedFuel : fuels) 
			g.fillRect(changedFuel.getmeteorX(), changedFuel.getmeteorY(), 40, 40);
		

		
		Meteor returnedMeteor = isCrushMShot();
		//if there is a crushed meteor by shot, score increases by 10, and this meteor is removed from the array list (from the screen)
		if (returnedMeteor != null) {
			score += 10;
			for (Meteor changedMeteor : meteors) {
				if (returnedMeteor == changedMeteor) 
					meteors.remove(changedMeteor);				
			}
		}

		
		Meteor returnedFuel = isCrushFShot();
		//if there is a crushed fuel by shot, if heart is less than 3, it increases by 1, and this fuel is removed from the array list (from the screen)
		if (returnedFuel != null) {
			if (heart == 3) 
				heart = 3;
		    else
				heart++;
			
			for (Meteor changedFuel : fuels) {
				if (returnedFuel == changedFuel)
					fuels.remove(changedFuel);
			}			
		}

		
		Meteor crushedMeteor = isCrushMShip();
		//if there is a meteor that crushed the ship, heart decreases by 1, and this meteor is removed from the array list (from the screen)
		//if heart becomes 0, timer stops and game over by printing the score to the screen
		if (crushedMeteor != null) {
			heart--;

			for (Meteor changedMeteor : meteors) {
				if (crushedMeteor == changedMeteor) {
					meteors.remove(changedMeteor);
					break;
				}
			}
			
			if (heart <= 0) {
				timer.stop();
				String message = "GAME OVER" + "\nScore:" + score;
				JOptionPane.showMessageDialog(this, message);
				System.exit(0);
			}
		}		
		
		
		Meteor crushedFuel = isCrushFShip();
		//if there is a fuel that crushed the ship, if heart is less than 3, it increases by 1, and this fuel is removed from the array list (from the screen)
		if(crushedFuel != null) {
			if (heart == 3) 
				heart = 3;
		    else
				heart++;
			
			for (Meteor changedFuel : fuels) {
				if (crushedFuel == changedFuel) 
					fuels.remove(changedFuel);
			}
		}
	}

	@Override
	public void repaint() {
		super.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		int c = e.getKeyCode();
		//if left key is pressed ship goes to left unless its x direction is 0 (the border of the screen)
		if (c == KeyEvent.VK_LEFT) {
			if (spaceShip.getShipX() <= 0) 
				spaceShip.setShipX(0);
			else 
				spaceShip.shiftLeft();
		} 
		
		//if right key is pressed ship goes to right unless its x direction is 650 (the border of the screen)
		else if (c == KeyEvent.VK_RIGHT) {
			if (spaceShip.getShipX() >= 650) 
				spaceShip.setShipX(650);
		    else 
				spaceShip.shiftRight();
		}
		
		//if space key is pressed a new shot is added to the shot arraylist
		else if (c == KeyEvent.VK_SPACE) {
			shots.add(new Shot(spaceShip.getShipX() + 63, 490));
		}

		//if p key is pressed while the game continues, game is paused by increasing the delay of the timer
		//if p key is pressed while the game is paused, game continues by setting the timer as its initial value 
		else if (c == KeyEvent.VK_P) {
			if (timer.getDelay() == 5) 
				timer.setDelay(999999999);
			 else {
				timer = new Timer(5, this);
				timer.start();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	//new meteors and fuels are generated according to the number of counter
	//the mode of the counters are different in order to generate meteors and fuels with different frequency. 
	public void meteorRandom() {
		if (counter % 80 == 0) 
			meteors.add(new Meteor());
	}

	public void fuelRandom() {
		if (counter % 500 == 0) 
			fuels.add(new Meteor());
	}

	
	//this method operates for every repeat of the timer
	public void actionPerformed(ActionEvent e) {
		
		//every time, shot, meteors and fuels is moved by changing their y direction
		for (Shot changedShot : shots) 
			changedShot.setY(changedShot.getY() - 5); 
		
		for (Meteor changedMeteor : meteors) 
			changedMeteor.setmeteorY(changedMeteor.getmeteorY() + 1); 
		
		for (Meteor changedFuel : fuels) 
			changedFuel.setmeteorY(changedFuel.getmeteorY() + 1); 
		
		//every time, a new meteor and fuel generating methods are called
		meteorRandom();
		fuelRandom();
		
		counter++;

		repaint();
	}

}