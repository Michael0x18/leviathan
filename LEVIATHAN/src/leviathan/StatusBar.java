package leviathan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// Joshua Han
// Health bar for the player.

public class StatusBar {
	private String name;
	private int max;
	private int current;
	
	public StatusBar(String name, int max) {
		this.name= name;
		this.max = max;
		this.current = max;
		
	}
	public void setValue(int value) {
		this.current = value;
	}
	
	public void draw(int x, int y,Graphics g) {
		g.setColor(Color.RED);
		g.drawRoundRect(x, y, 100, 2, 5, 5);
		g.fillRoundRect(x, y, Grid.player.getHp()/2, 2, 5, 5);
		g.setColor(Color.WHITE);
		Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(3F); 
        g.setFont(newFont);
		g.drawString(Grid.player.getHp() + "/" + (max+100), 42, 4);
	}
}
