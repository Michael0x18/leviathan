package leviathan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Restart extends JFrame implements ActionListener {
	private JButton restart = new JButton("restart");
	public Restart() {
		super();
		this.setBounds(10,10,100,20);
		this.setVisible(true);
		this.add(restart);
		this.setLocationRelativeTo(null);
		restart.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Grid.g.dispose();
		MainMenu m = new MainMenu();
		
	}
}
