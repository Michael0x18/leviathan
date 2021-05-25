package leviathan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// Joshua Han
// Mana bar for the player.

public class ManaBar {
	private String name;
	private int max;
	private int current;
	
	public ManaBar(String name, int max) {
		this.name= name;
		this.max = max;
		this.current = max;
		
	}
	public void setValue(int value) {
		this.current = value;
	}
	
	public void draw(int x, int y,Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRoundRect(x, y, 100, 2, 5, 5);
		g.fillRoundRect(x, y, Grid.player.getMp(), 2, 5, 5);
		g.setColor(Color.WHITE);
		Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(3F); 
        g.setFont(newFont);
		g.drawString(Grid.player.getMp() + "/" + max, 42, 8);
	}
}
