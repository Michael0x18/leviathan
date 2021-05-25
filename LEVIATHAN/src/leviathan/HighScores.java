package leviathan;

import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.*;
import java.util.Scanner;

public class HighScores extends JFrame{

	public HighScores() {
		this.setBackground(Color.BLACK);
		this.setBounds(0,0,800,600);
		this.setLocationRelativeTo(null);
		JPanel p = new JPanel();
		p.setBackground(Color.BLACK);
		this.add(p);
		p.setLayout(null);
		
		
		
		JLabel score = new JLabel("HIGH SCORE: ");
		
		try {
			File f = new File("Info/HighScores.txt");
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				score.setText(score.getText()+s.nextLine()+ "     ");
				System.out.println(score.getText());
			}
		}catch(Exception e) {
			
		}
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		HighScores s = new HighScores();
	}

}
