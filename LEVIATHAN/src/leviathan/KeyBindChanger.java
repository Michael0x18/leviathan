package leviathan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KeyBindChanger extends JFrame implements KeyListener, ActionListener {
	private JButton buttonUp;
	private JButton buttonSpace;
	private JButton buttonLeft;
	private JButton buttonRight;
	private JButton buttonV;

	private JButton focused;

	public KeyBindChanger() {
		super();
		this.setFocusable(true);
		this.setBounds(0, 0, 400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Keybinds");
		// this.setLayout(null);
		JPanel p = new JPanel();
		this.add(p);
		p.setLayout(null);
		p.setBackground(Color.BLACK);
		this.setIconImage(new ImageIcon("Build/gear.png").getImage());
		buttonUp = new JButton("Jump: " + Grid.g.UpArrow);
		buttonUp.setBounds(10, 10, 120, 20);

		buttonSpace = new JButton("Fly: " + Grid.g.Fly);
		buttonSpace.setBounds(10, 40, 120, 20);

		buttonLeft = new JButton("Left: " + Grid.g.LeftArrow);
		buttonLeft.setBounds(10, 70, 120, 20);

		buttonRight = new JButton("Right: " + Grid.g.RightArrow);
		buttonRight.setBounds(10, 100, 120, 20);

		buttonV = new JButton("Shoot: " + Grid.g.Shoot);
		buttonV.setBounds(10, 130, 120, 20);

		p.add(buttonUp);
		p.add(buttonSpace);
		p.add(buttonLeft);
		p.add(buttonRight);
		p.add(buttonV);

		buttonUp.setFocusable(false);
		buttonSpace.setFocusable(false);
		buttonLeft.setFocusable(false);
		buttonRight.setFocusable(false);
		buttonV.setFocusable(false);

		buttonUp.addKeyListener(this);
		buttonSpace.addKeyListener(this);
		buttonLeft.addKeyListener(this);
		buttonRight.addKeyListener(this);
		buttonV.addKeyListener(this);

		p.addKeyListener(this);
		this.addKeyListener(this);

		buttonUp.addActionListener(this);
		buttonSpace.addActionListener(this);
		buttonLeft.addActionListener(this);
		buttonRight.addActionListener(this);
		buttonV.addActionListener(this);

		focused = null;

		this.revalidate();
		this.setVisible(true);

	}

	public void keyPressed(KeyEvent e) {
		// System.out.println(this.focused);
		if (this.focused != null) {
			if (this.focused.equals(buttonUp)) {
				this.focused = null;
				Grid.g.UpArrow = e.getKeyCode();
				this.buttonUp.setText("Jump: " + Grid.g.UpArrow);
				return;
				// System.out.println("boi");
			}
			if (this.focused.equals(buttonSpace)) {
				this.focused = null;
				Grid.g.Fly = e.getKeyCode();
				this.buttonSpace.setText("Fly: " + Grid.g.Fly);
				return;
				// System.out.println("boi");
			}
			if (this.focused.equals(buttonLeft)) {
				this.focused = null;
				Grid.g.LeftArrow = e.getKeyCode();
				this.buttonLeft.setText("Left: " + Grid.g.LeftArrow);
				return;
				// System.out.println("boi");
			}
			if (this.focused.equals(buttonRight)) {
				this.focused = null;
				Grid.g.RightArrow = e.getKeyCode();
				this.buttonRight.setText("Right: " + Grid.g.RightArrow);
				return;
				// System.out.println("boi");
			}
			if (this.focused.equals(buttonV)) {
				this.focused = null;
				Grid.g.Shoot = e.getKeyCode();
				this.buttonV.setText("Shoot: " + Grid.g.Shoot);
				return;
				// System.out.println("boi");
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] Args) {
		KeyBindChanger k = new KeyBindChanger();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonUp)) {
			focused = buttonUp;
			buttonUp.setText("-Hit Key-");
			Grid.g.UpArrow = -1;

		}
		if (e.getSource().equals(buttonSpace)) {
			focused = buttonSpace;
			buttonSpace.setText("-Hit Key");
			Grid.g.Fly = -1;
		}
		if (e.getSource().equals(buttonLeft)) {
			focused = buttonLeft;
			buttonLeft.setText("-Hit Key");
			Grid.g.LeftArrow = -1;
		}
		if (e.getSource().equals(buttonRight)) {
			focused = buttonRight;
			buttonRight.setText("-Hit Key");
			Grid.g.RightArrow = -1;
		}
		if (e.getSource().equals(buttonV)) {
			focused = buttonV;
			buttonV.setText("-Hit Key");
			Grid.g.Shoot = -1;
		}

	}

}
