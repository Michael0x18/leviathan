//Michael

//Michael

package leviathan;

import java.awt.Graphics;
import java.awt.Graphics.*;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public abstract class Tile {
	// The class from which all other tiles are derived
	
	private Image sprite;// = new ImageIcon("BlankSprite.png").getImage();
	protected CoordinateSystem coords; //CoordinateSystem
	private boolean destroyed = false;
	//public boolean hasPlayer = false;

	public Tile(int x, int y) {   //Basic constructor
		this.sprite = new ImageIcon("Build/BlankSprite.png").getImage();
		this.coords = new CoordinateSystem(x, y, this.sprite);
	}
	
	public boolean hasPlayer() {
		return false;
	}
	
	public abstract Tile c();

	public Tile(int x, int y, Image pic) {  //Constructor with image
		this.coords = new CoordinateSystem(x, y, pic);
		this.sprite = pic;
	}
	
	public Image getSprite() {
		return this.sprite;
	}

	public void shift(double dx, double dy) {  //Move the tile
		this.coords.shift(dx, dy);
		//System.out.println(this.getX()+ " " + this.getY());
	}

	public int getX() {       //Getter for x coordinate
		return this.coords.get()[0];
	}

	public int getY() {        //Getter for y coordinate
		return this.coords.get()[1];
	}
	
	public int[] get() {         //Getter for all coordinates
		return this.coords.get();
	}
	
	public static boolean inRange(int x, int y, int a) { //Check if value is in range x:y
		return a < y && a > x;
	}
	
	public boolean isTouching(Monster mon) { // check if monster is touching tile.
		boolean a = inRange(this.getX(),this.getX()+16,mon.getCoords()[0]);
		boolean b = inRange(this.getY(),this.getY()+16,mon.getCoords()[1]);
		return a&&b;
	}
	
	public boolean isTouching(Player p) {
		boolean a = inRange(this.getX(),this.getX()+16,p.getCoords()[0]);
		boolean b = inRange(this.getY(),this.getY()+16,p.getCoords()[1]);
		return a&&b;
	}
	
	
	
	public abstract void act();
	public abstract String toString();
	public abstract String getType();
	
	public void draw(Graphics g) {
		if (!this.destroyed)
			this.coords.drawImage(g, this.sprite);
		else {
			this.coords.shift(-2*this.getX(), -2*this.getY());
		}
		//System.out.println(this.getX()+" "+this.getY());
	}

	public void destroy() {
		// TODO Auto-generated method stub
		this.destroyed  = true;
		
	}
	
}
