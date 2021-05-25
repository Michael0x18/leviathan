package leviathan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Credits extends JPanel implements MouseListener,KeyListener{
	
	
	public static void init() {
		Credits c = new Credits();
		JFrame window = new JFrame("Credits");
		window.setUndecorated(true);
		
		//window.setLayout(null);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setBackground(Color.BLACK);
		window.setVisible(true);
		c.setBackground(Color.BLACK);
		window.add(c);
		window.setFocusable(true);
		window.addMouseListener(c);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.requestFocus();
		window.revalidate();
		
	}

	private float counter1 = 0;
	private float counter2 = -20;
	private float counter3 = -40;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		counter1 += 0.2;
		counter2 += 0.2;
		counter3 += 0.2;
		g.setColor(Color.GREEN);
		g.drawString("LEVIATHAN", this.getWidth()/2-40, (int) (this.getHeight()-counter1) );
		g.drawString("Executive producer: ", this.getWidth()/2-45, (int) (this.getHeight()-counter2));
		g.drawString("Michael Ferolito", this.getWidth()/2-40, (int) (this.getHeight()-counter3));
		if (counter1 > this.getHeight()) {
			counter1 = 0;
		}
		if (counter2 > this.getHeight()) {
			counter2 = 0;
		}
		if (counter3 > this.getHeight()) {
			counter3 = 0;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.exit(0);
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) {
		System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}