package leviathan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Timer;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.ImageIcon;

public class Portal extends GroundTile implements ActionListener{
	private static Image sprite = new ImageIcon("Build/Portal.gif").getImage();// Gif created by making one sprite then rotating it around. A lot.
	
	private Timer t = new Timer(100,this);

	public Portal(int i, int j) {
		super(i, j,Portal.sprite);
	//System.out.println("<Gate of Ascention> Come to me..."); //is Tile
	//System.out.println("<Gate of Ascention> I shall bide my time...");
	t.start();
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (Math.abs(this.coords.get()[0] - 48) < 16 && Math.abs(this.coords.get()[1] - 32) < 16) {
			 t.stop();
			 //System.out.println("<Gate of Ascention> AAH... YOU HAVE ARRIVED...");
			 //System.out.println("<Ethereal voice> Continue, for a new challence...>"); // random text.
			 //System.out.println("<Gate of Satan> Passage has gone...");
			 //System.out.println("<Null> The goose has cried, the gods have died,\n the Juice is loose in the halls of doom...");
			 Grid.g.reset();
		 }
		
	}
	
	
}
