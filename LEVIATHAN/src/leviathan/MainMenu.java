package leviathan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainMenu implements ActionListener {
	private JMenuBar jmb = new JMenuBar();
	private JFrame window = new JFrame("LEVIATHAN");
	private JButton go;
	private JMenu file = new JMenu("File");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenuItem credits = new JMenuItem("Credits");
	private JMenuItem KeyBinds = new JMenuItem("Keybinds");
	private JMenu help = new JMenu("About");
	Timer t = new Timer(10, this);
	private int counter;
	private JLabel label = new JLabel("LEVIATHAN");

	public MainMenu() {
		BufferedImage buttonIcon;
		try {
			buttonIcon = ImageIO.read(new File("Build/button.png"));
			go = new JButton(new ImageIcon(buttonIcon));
			go.setBorder(BorderFactory.createEmptyBorder());
			go.setContentAreaFilled(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			go = new JButton("Launch");
		}

		counter = 0;
		//System.out.println(System.setProperty("apple.laf.useScreenMenuBar", "true"));
		//System.out.println(System.setProperty("apple.laf.useScreenMenuBar", "true"));

		label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
		label.setForeground(Color.BLACK);
		JPanel panel = new JPanel();
		window.setBounds(100, 100, 800, 600);
		window.setLocationRelativeTo(null);
		window.setUndecorated(true);
		panel.setLayout(null);
		window.setBackground(Color.BLACK);

		go.setBounds(-100, 400, 1000, 50);
		go.setBackground(Color.WHITE);
		go.addActionListener(this);
		go.setFocusable(false);
		jmb.add(file);
		file.add(exit);
		exit.addActionListener(this);
		jmb.add(help);
		help.add(credits);
		help.add(KeyBinds);
		credits.addActionListener(this);
		KeyBinds.addActionListener(this);
		window.setIconImage(new ImageIcon("Build/dragon_fireball.png").getImage());

		window.add(panel);
		panel.setBackground(Color.BLACK);
		label.setBounds(120, 100, 600, 100);
		panel.add(label);
		panel.add(go);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setJMenuBar(jmb);
		t.start();

		window.setVisible(true);

		BGMusic m = new BGMusic();
		m.run();
	}

	public void launch() {
		leviathan.Grid.run(); // The program must execute this stuff first.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != null && e.getActionCommand().equals("Exit")) {
			System.exit(0);
		} else if (e.getActionCommand() != null && e.getActionCommand().equals("Credits")) {
			Credits.init();
		} else if (e.getSource().equals(this.go)) {
			Grid.run();
			this.window.dispose();

		} else if (e.getSource().equals(this.KeyBinds)) {
			KeyBindChanger k = new KeyBindChanger();
		}
		if (counter != 256) {
			label.setForeground(new Color(counter, 0, 0));
			counter++;
		}
	}

}
