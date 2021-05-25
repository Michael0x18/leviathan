package leviathan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.math.*;

public class LeviathanPanel extends JPanel {



	public LeviathanPanel() {
		super();
	}
	
	private Restart r;

	private Image gameover = new ImageIcon("Build/gameover.png").getImage();
	private LinkedList<LinkedList<Tile>> tiles = new LinkedList<LinkedList<Tile>>();
	
	public static boolean isUnderWater = false;
	
	protected boolean isDead = false;
	private Image Lava = new ImageIcon("Build/Lava.png").getImage();
	
	private Image LavaOverlay = new ImageIcon("Build/LavaOverlay.png").getImage();

	public void setTiles(LinkedList<LinkedList<Tile>> t) {
		this.setBackground(Color.DARK_GRAY);
		this.tiles = t;
	}
	
	private Image overlay = new ImageIcon("Build/WaterOverlay.png").getImage();
	public static boolean isUnderLava = false;

	private static final long serialVersionUID = -4593569340321448146L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.isUnderWater = false;
		this.isUnderLava = false;
		
		int width = this.getWidth();
		int height = this.getHeight();
		double ratioX = (double) width / (250.0);
		double ratioY = (double) height / (150.0);

		Graphics2D g2 = (Graphics2D) g;
		g2.scale(ratioX, ratioY);                                                          //scale
		this.setBounds(0, 0, Grid.g.getWidth(), Grid.g.getHeight());
		
		for(LinkedList<Tile> t : this.tiles) {
			for(Tile s : t) {
				if(s.getType().equalsIgnoreCase("watertile")) {
					if(s.hasPlayer()) {
						//System.out.println("yes");
						this.isUnderWater = true;
					}
				}
			}
		}
		for(LinkedList<Tile> t : this.tiles) {
			for(Tile s : t) {
				if(s.getType().equalsIgnoreCase("lavatile")) {
					if(s.hasPlayer()) {
						//System.out.println("yes");
						this.isUnderLava  = true;
					}
				}
			}
		}
		
		
		g2.drawImage(Lava, 0, 0, null);
		
		for (LinkedList<Tile> a : this.tiles) {
			for (Tile t : a) {
				if (Math.abs(t.getX() - 48) < 56 && Math.abs(t.getY() - 32) < 45)          //render
					t.draw(g);
			
			}
		}

		// g.drawImage(Grid.stone1, 0, 0, null);

		for (Monster m : Grid.g.getMonsters()) {
			if (Math.abs(m.coords.get()[0] - 48) < 56 && Math.abs(m.coords.get()[1] - 32) < 45)
				if (m.destroyed == false)
					m.draw(g2);
		}

		// updates tables.

		for (Projectile p : Grid.getShots()) {
			p.draw(g);
		}
		Grid.player.set((int) ratioX / 4, (int) ratioY / 4);
		Grid.player.draw(g);

		if (isDead) {
			((Graphics2D) g).drawImage(gameover, 0, 0, this);
			//if(r == null)
				//r = new Restart();
				
			
		}
		g.setColor(Color.GRAY);
		g.fill3DRect(208, 0, 42, 150, true);
		g.setColor(Color.GREEN);
		g.drawString("Level:    " + Grid.side, 212, 6);
		g.drawString("Score:    " + Grid.getScore(), 212, 10);
		g.drawString("Charge:  " + Grid.getCharge() / 5, 212, 14);
		g.drawString("Time:     " + Grid.g.endTimer, 212, 18);
		// g.draw3DRect(10, 10, 10, 10, false);
		// g.setColor(Color.MAGENTA);
		// g.fillRect(11, 11, 9, 9);

		if (Grid.side == 1) {
			g.setColor(Color.WHITE);
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {    // Try/catch for IterruptedException
//				e.printStackTrace();
//			}
			//g.drawString("Use the arrow keys to move!", 90, 50);
		}
		if (Grid.side == 2) {
			g.setColor(Color.WHITE);
			//g.drawString("Hold space to fly.", 60, 50);
			//g.drawString("Be warned: It will drain your mana!", 70, 60); //Tutorial code.
		}
		if (Grid.side == 3) {
			g.setColor(Color.WHITE);
			//g.drawString("You are now on your own. Press v to charge. Release to shoot.", 100, 50);
		}
		
		if(isUnderWater) {
			g.drawImage(overlay,0,0,null);
		}
		if(isUnderLava) {
			g.drawImage(LavaOverlay,0,0,null);
		}

	}

}
