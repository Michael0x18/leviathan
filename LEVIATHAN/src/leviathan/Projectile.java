package leviathan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;
import java.util.LinkedList;

//Generic Projectile (Timothy Liu)
//Used to handle shots of all types
//Fields are sprite, start position, damage, and lifetime in ticks

public class Projectile implements ActionListener {
	public static final boolean LEFT = false;
	public static final boolean RIGHT = true;
	private static LinkedList<Monster> monsters = Grid.g.getMonsters();
	private int damage = Grid.getCharge()/5;
	private boolean direction;
	private int fireTime = 10;
	private Timer t;
	private CoordinateSystem coords;
	private Image sprite = new ImageIcon("Build/fireball.png").getImage();
	private int finalX = 0;
	private int finalY = 0;
	private double slope;
	private double yDirection;
	private double xDirection;
	private int counter = 0;

	public Projectile(boolean direction) {
		// setup function
		this.setDirection(direction);
		t = new Timer(fireTime, this);
		coords = new CoordinateSystem(48.0, 32.0, this.sprite);
		this.damage = Grid.getCharge()/5;
		//System.err.println(this.damage);
		if(Grid.g.getMousePosition() != null) {
		this.finalX = Grid.g.getMousePosition().x+2;
		//System.out.println(finalX);
		this.finalY = Grid.g.getMousePosition().y+2;
		//System.out.println(finalY);
		//this.direction = finalX>Grid.g.getWidth()/2-100000;
//		this.xDirection = (finalX-400.0)/Math.abs(finalY-330.0);
//		this.yDirection = (finalY-330.0)/Math.abs((finalX-400.0));
		this.xDirection = (finalX-Grid.g.getWidth()/2.0+120.0)/500;
		this.yDirection = (finalY-Grid.g.getHeight()/2.0-30.0)/500;
		
		}
		this.slope = (this.finalY-32)/(this.finalX-(48+(direction? 4.0 : 32.0)));
		//System.out.println(this.slope);
		t.start();

	}

	public int getDamage() {
		return damage;             // Getter and setters for fields.
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public int getFireTime() {
		return fireTime;
	}

	public void setFireTime(int fireTime) {
		this.fireTime = fireTime;
	}

	public Timer getT() {
		return t;
	}

	public void setT(Timer t) {
		this.t = t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		counter++;
		Projectile.monsters = Grid.g.getMonsters();
		if (e.getSource().equals(t)) {
			this.coords.shift(xDirection, yDirection);
			
		}
		for (LinkedList<Tile> l : Grid.g.getTiles()) {
			for (Tile t : l) {
				if (counter > 10 && t.getX() / 8 == (this.coords.get()[0]+4) / 8 && t.getY() / 8 == (this.coords.get()[1]+4) / 8) {
					if (t.getType().equalsIgnoreCase("groundtile")) {
						this.t.stop();
						Grid.getShots().remove(this);
					}
				}
			}
		}
		for (Monster m : Projectile.monsters) {
			if (m.getX() / 8 == (this.coords.get()[0]+4) / 8 && m.getY() / 8 == (this.coords.get()[1]+4) / 8) {
				if (m.destroyed == false) {
					m.doDamage(this.damage);
					this.t.stop();
					Grid.getShots().remove(this);
				}
			}
		}

	}

	public CoordinateSystem getCoords() {
		return coords;
	}

	public void setCoords(CoordinateSystem coords) {
		this.coords = coords;
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		this.coords.drawImage(g, this.sprite);
	}

}
