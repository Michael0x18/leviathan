package leviathan;

// Michael Ferolito.
// Controls all main functions of program and contains LinkedLists that hold all information.

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.applet.*;
//import processing.core.PApplet;

public class Grid extends JFrame implements ActionListener, KeyListener, MouseListener {

	public static int UpArrow = KeyEvent.VK_W;
	public static int DownArrow = KeyEvent.VK_S;
	public static int LeftArrow = KeyEvent.VK_A;
	public static int RightArrow = KeyEvent.VK_D;
	public static int Shoot = KeyEvent.VK_V;
	public static int Fly = KeyEvent.VK_SPACE;

	private SoundEffect effect = new SoundEffect("Build/laser.wav");
	private boolean falling = false;
	public static Grid g; // The current Grid.
	private Timer fallDelay = new Timer(5, this);
	private Timer move = new Timer(20, this);
	private LinkedList<Monster> monsters = new LinkedList<Monster>();
	private LinkedList<Projectile> shots = new LinkedList<Projectile>(); // A static list that contains projectiles.
	private LinkedList<LinkedList<Tile>> tiles = new LinkedList<LinkedList<Tile>>(); // LinkedList for tiles.
	private int jumpVar = 0;
	private int fallCount = 0; // The counter for falling, maximum of six.
	private boolean canFall = true; // A condition to allow falling.

	private static boolean allowJump = true; // A boolean that tells if the player is allowed to jump.
	private boolean canJump = true; // Tells if Player can jump.

	private boolean leftPressed = false; // A boolean that is true whenever the left arrow is held.
	private boolean rightPressed = false; // A boolean that is true whenever the right arrow is held.
	private boolean spacePressed = false; // A boolean that is true whenever the space key is held.

	// private int bX = 0, bY = 0; // Representing shift for compensation by Tiles
	// to find absolute coordinates.
	private static final long serialVersionUID = 1L; // Class is serialized.
	private Timer ghostSpawn = new Timer(10000, this); // Spawns a ghost every 10 seconds.
	private Timer t = new Timer(60, this); // Timer for repaint.
	private Timer m = new Timer(20, this); // Timer for monsters.
	private Timer l = new Timer(1000, this); // Timer for shot movement.
	private Timer end = new Timer(1000, this); // Timer until end of level (Grid dissolves).
	protected int endCounter = 0; // Counter for grid dissolution.
	protected int endTimer = 60; // Countdown until dissolution.
	private Timer checkHP = new Timer(10, this); // Timer for checking Player HP.
	Timer s = new Timer(150, this); // Timer for monster actions.
	private LeviathanPanel panel = new LeviathanPanel(); // JPanel extension for drawing stuff.
	private Tile t1; // Generic Tile1.
	private Tile t2; // Generic Tile2.

	public final Image stone = new ImageIcon("Build/Stone.png").getImage(); // Stone sprite.
	public final Image Vampire = new ImageIcon("Build/Vampire.png").getImage();

	private JMenuBar jmb; // Menu bar on window.
	private JMenuItem KeyBinds = new JMenuItem("Keybinds");
	private boolean f = true;
	private boolean canFly = true;
	protected final static Player player = new Player("Player 1", "Build/wiz.png", 48, 32); // Player.
	public static int side = 1; // Length of side of Grid.
	private static boolean shootPressed = false; // Tells if the shoot button is pressed.

	private static int charge = 0; // The amount of charge the player has stored.
	private static long score = 0; // The player's score.

	public void reset() { // Resets the Grid to prepare for a new level. Increments side by 1;
		ghostSpawn.start(); // Makes Ghosts start to appear at level 2.
		end.restart(); // Restart the end timer.
		endCounter = 0; // Restart the end counter.
		endTimer = 60 * (Grid.side + 1); // Set the timer.
		this.setTiles(new LinkedList<LinkedList<Tile>>());
		this.panel.setTiles(this.getTiles());
		repaint();
		// System.err.println("Call to reset"); //Debug: Tell if method is called
		for (Monster m : Grid.g.getMonsters()) {
			m = null;
			Grid.g.getMonsters().remove(m);
		}

		Grid.addScore(Grid.side * Grid.side * 100);
		Grid.side++;

		System.err.println(Grid.side);
		for (int i = 0; i < side * 16; i++) {
			LinkedList<Tile> a = new LinkedList<Tile>();
			for (int j = 0; j < 16 * side; j++) {
				Tile t = new GroundTile(0, 0, stone);
				a.add(t);
			}
			this.getTiles().add(a);
		}
		Grid.g.init(); // Call init

	}

	public Grid() {
		super("Leviathan");
		jmb = new JMenuBar();
		ImageIcon icon = new ImageIcon("Build/dragon_fireball.png");
		this.setIconImage(icon.getImage());
//		try {
//			this.setIconImage(ImageIO.read(new File("Build/HeadOfPrometheus.png")));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		fallDelay.start();
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiExit = new JMenuItem("Exit");
		jmFile.add(jmiExit);
		jmb.add(jmFile);
		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiCredits = new JMenuItem("Credits");
		jmHelp.add(jmiCredits);
		jmb.add(jmHelp);
		jmHelp.add(KeyBinds);
		KeyBinds.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiCredits.addActionListener(this);
		// Constructor
		this.setJMenuBar(jmb);
		this.setVisible(true);
		this.move.start();
		// this.add(menu);
		// this.setCursor(CROSSHAIR_CURSOR);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("Build/Cursor.png");
		Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
		this.setCursor(c);
		// this.end.setInitialDelay(600);
		for (int i = 0; i < side * 16; i++) {
			LinkedList<Tile> a1 = new LinkedList<Tile>();
			for (int j = 0; j < 16 * side; j++) {
				Tile t = new GroundTile(0, 0, stone);
				a1.add(t);
			}
			this.getTiles().add(a1);
		}

		this.setVisible(true);
		this.setBounds(0, 0, 1000, 620);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.BLUE);
		panel.addKeyListener(this);
		this.init();
		panel.setBounds(0, 0, 800, 600);
		this.setFocusable(true);
		this.add(panel);
		this.addKeyListener(this);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		t.start();
		m.start();
		checkHP.start();
		panel.addMouseListener(this);
		this.addMouseListener(this);
		l.start();
		end.start();
		repaint();
		// fallDelay.start();

	}

	public void setUp() {// Generates Grid.
		String a1 = HyperRoom.generate();
		int a2 = 0;
		for (String c : a1.split("")) {

			if (c.equals("0") || c.equals("V") || c.equals("K") || c.equals("G") || c.equals("E")) {
				// this.tiles.get(a2 / 16).set(a2 % 16, new BGTile(this.tiles.get(a2 /
				// 16).get(a2 % 16).getX(),
				// this.tiles.get(a2 / 16).get(a2 % 16).getY()));
				int yindex = (a2 / (16 * side));
				int xindex = a2 % (16 * side);
				this.getTiles().get(xindex).set(yindex, new BGTile(8 * xindex, 8 * yindex));
				if (c.equals("V")) {
					Monster m = new Monster(Monster.Type.VAMPIRE, 8 * xindex, 8 * yindex, "Build/Vampire.png");
					Grid.g.getMonsters().add(m);
				}
				if (c.equals("K")) {
					Monster m = new Monster(Monster.Type.DARKKNIGHT, 8 * xindex, 8 * yindex, "Build/DarkKnight.gif");
					// System.out.println("boi");
					Grid.g.getMonsters().add(m);
				}
				if (c.equals("G")) {
					Monster m = new Monster(Monster.Type.GHOST, 8 * xindex, 8 * yindex, "Build/ghost1.png");
					Grid.g.getMonsters().add(m);
				}
				if (c.equals("E")) {

				}

			} else if (c.equals("P")) {
				int yindex = (a2 / (16 * side));
				int xindex = a2 % (16 * side);
				this.getTiles().get(xindex).set(yindex, new Portal(8 * xindex, 8 * yindex));
			}

			else if (c.equals("T")) {
				int yindex = (a2 / (16 * side));
				int xindex = a2 % (16 * side);
				this.getTiles().get(xindex).set(yindex, new TrapTile(8 * xindex, 8 * yindex));
			} else if (c.equals("W")) {
				int yindex = (a2 / (16 * side));
				int xindex = a2 % (16 * side);
				this.getTiles().get(xindex).set(yindex, new WaterTile(8 * xindex, 8 * yindex));
			} else if (c.equals("L")) {
				int yindex = (a2 / (16 * side));
				int xindex = a2 % (16 * side);
				this.getTiles().get(xindex).set(yindex, new LavaTile(8 * xindex, 8 * yindex));
			}
			a2++;
		}

		String[] str = Interpreter.getMaze();

		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				int currentCellX = 2 * i + 1;
				int currentCellY = 2 * j + 1;
				int currentCellSouthX = currentCellX;
				int currentCellSouthY = currentCellY + 1;
				int currentCellWestX = currentCellX + 1;
				int currentCellWestY = currentCellY;
				// System.out.println(currentCellX+" "+ currentCellY);
				if (str[currentCellSouthY].charAt(currentCellSouthX) == 'X') {
					this.popDown(i, j);
					if (Math.random() > 0.8) {
						this.popDown(i, j);
					}
				}
				if (str[currentCellWestY].charAt(currentCellWestX) == 'X') {
					// System.out.println(true);
					this.popRight(i, j);
					if (Math.random() > 0.8) {
						this.popRight(i, j);
					}
				}
			}
		}
	}

	private void popRight(int i, int j) {// destroys a random sequence of 4 tiles to right of room.
		int row = (int) (Math.random() * 14 + 1);
		int roomX = 16 * i;
		int roomY = 16 * j;
		this.getTiles().get(roomX + 14).set(roomY + row, new BGTile((roomX + 14) * 8, (roomY + row) * 8));
		this.getTiles().get(roomX + 15).set(roomY + row, new BGTile((roomX + 15) * 8, (roomY + row) * 8));
		this.getTiles().get(roomX + 16).set(roomY + row, new BGTile((roomX + 16) * 8, (roomY + row) * 8));
		this.getTiles().get(roomX + 17).set(roomY + row, new BGTile((roomX + 17) * 8, (roomY + row) * 8));
	}

	private void popDown(int i, int j) { // popRight for down
		int column = (int) (Math.random() * 14 + 1);
		int roomX = 16 * i;
		int roomY = 16 * j;
		this.getTiles().get(roomX + column).set(roomY + 14, new BGTile((roomX + column) * 8, (roomY + 14) * 8));
		this.getTiles().get(roomX + column).set(roomY + 15, new BGTile((roomX + column) * 8, (roomY + 15) * 8));
		this.getTiles().get(roomX + column).set(roomY + 16, new BGTile((roomX + column) * 8, (roomY + 16) * 8));
		this.getTiles().get(roomX + column).set(roomY + 17, new BGTile((roomX + column) * 8, (roomY + 17) * 8));
	}

	@Override
	public void actionPerformed(ActionEvent e) {// for actions
		String str = e.getActionCommand();

		if (e.getSource().equals(KeyBinds)) {
			KeyBindChanger k = new KeyBindChanger();
		}

		if (e.getSource().equals(fallDelay)) {
			if (jumpVar != 0) {
				// System.out.println("called");
				// fallDelay.stop();
				boolean nope = false;
				for (LinkedList<Tile> l : this.getTiles()) {
					for (Tile t : l) {
						if (t.getX() > 41 && t.getX() < 55) {
							if (t.getY() == 25) {
								if (t.getType().equalsIgnoreCase("groundtile")) {
									nope = true; // Shifts Grid down, then up.
									this.shiftUp(1);
									repaint();
									this.shiftDown(1);
									// this.t.stop();
									// s.start();
								}
							}

						}
					}
				}
				if (!nope) {
					// t.stop();
					// s.start();
					this.shiftUp(1);

					// this.f = false;
					// fallDelay.start();
				} else {
					// i--;
				}

				repaint();
				this.jumpVar--;
				if (this.jumpVar <= 0) {
					fallDelay.stop();
					this.canFall = true;
					this.canFly = true;
				}
				// System.out.println(this.jumpVar);

				// this.jumpVar = true;
				// fallDelay.stop();
			}
		}

		if (e.getSource().equals(move)) {

			if (!leftPressed && !rightPressed) {
				player.xV /= 2;
			}

			if (player.xV < 0) {

				player.faceLeft();
				player.sprite = player.spriteLeft;
				for (int i = 0; i < -player.xV; i++) {
					for (LinkedList<Tile> l : this.getTiles()) {
						for (Tile t : l) {
							if (t.getX() >= 41 && t.getX() < 48) {
								if (t.getY() >= 32 && t.getY() < 40) {
									if (t.getType().equalsIgnoreCase("groundtile")) {
										return;
									}
								}

							}
						}
					}
				}

				this.shiftLeft(1);
				repaint();
			} else if (player.xV > 0) {
				player.faceRight();
				player.sprite = player.spriteRight;
				for (int i = 0; i < player.xV; i++) {
					for (LinkedList<Tile> l : this.getTiles()) {
						for (Tile t : l) {
							if (t.getX() >= 48 && t.getX() < 56) {
								if (t.getY() >= 32 && t.getY() < 40) {

									if (t.getType().equalsIgnoreCase("groundtile")) {
										return;
									}
								}

							}
						}
					}
				}

				this.shiftRight(1);
				repaint();
			}
		}

		if (str != null && str.equals("Exit")) {
			System.exit(0);
		}
		if (str != null && str.equals("Credits")) {
			Credits credit = new Credits();
			this.dispose();
			credit.init();
		}
		if (e.getSource().equals(ghostSpawn)) {
			int gx = this.getTiles().get(0).get(0).getX();
			int gy = this.getTiles().get(0).get(0).getY();
			Monster m = new Monster(Monster.Type.GHOST, gx, gy, "ghost1.png");
			Grid.g.getMonsters().add(m);
		}
		if (e.getSource().equals(end)) {
			if (endTimer <= 0 && endCounter < this.getTiles().size()) {
				for (LinkedList<Tile> l : this.getTiles()) {
					if (l.size() != 0) {
						l.get(endCounter).destroy();
						player.doDamage(20);
					}

				}
				endCounter++;
			} else {

				endTimer--;
			}
		}
		if (e.getSource().equals(l)) {
			if (player.getHp() < player.getHealth())
				if (Math.random() > 0.1)
					player.doDamage(-1);
			if (player.getMp() < player.getMana()) {
				if (Math.random() > 0.0) {
					player.setMp(player.getMp() + 1);
				}
			}
			if (player.getMp() < player.getMana()) {
				if (Math.random() > 0.2) {
					player.setMp(player.getMp() + 1);
				}
			}
			if (player.getMp() < player.getMana()) {
				if (Math.random() > 0.2) {
					player.setMp(player.getMp() + 1);
				}
			}
			if (player.getMp() < player.getMana()) {
				if (Math.random() > 0.6) {
					player.setMp(player.getMp() + 1);
				}
			}
		}

		if (e.getSource().equals(t)) {
			this.fall();

		}

		if (e.getSource().equals(checkHP)) {
			if (player.getHp() <= 0) {
				this.setFocusable(false);
				this.panel.isDead = true;
				repaint();
			}
		}

		if (e.getSource().equals(s)) {
			t.start();
			s.stop();
		}

		if (e.getSource().equals(m)) {
			if (this.leftPressed) {
				player.xV -= 1;
				if (player.xV < -1) {
					player.xV = -1;
				}

//				player.faceLeft();
//				player.sprite = player.spriteLeft;
//
//				for (LinkedList<Tile> l : this.getTiles()) {
//					for (Tile t : l) {
//						if (t.getX() >= 41 && t.getX() < 48) {
//							if (t.getY() >= 32 && t.getY() < 40) {
//								if (t.getType().equalsIgnoreCase("groundtile")) {
//									return;
//								}
//							}
//
//						}
//					}
//				}
//
//				this.shiftLeft(1);
//				repaint();

			}
			if (this.rightPressed) {
				player.xV += 1;
				if (player.xV > 1) {
					player.xV = 1;
				}
//				player.faceRight();
//				player.sprite = player.spriteRight;
//				for (LinkedList<Tile> l : this.getTiles()) {
//					for (Tile t : l) {
//						if (t.getX() >= 48 && t.getX() < 56) {
//							if (t.getY() >= 32 && t.getY() < 40) {
//
//								if (t.getType().equalsIgnoreCase("groundtile")) {
//									return;
//								}
//							}
//
//						}
//					}
//				}
//
//				this.shiftRight(1);
//				repaint();

			}

			if (Grid.shootPressed) {
				Grid.setCharge(Grid.getCharge() + (int) (3 * Math.random() + 1));
			}

			if (this.spacePressed) {
				boolean stopped = false;
				if (Grid.g.canFly && Grid.player.getMp() > 0) {
					this.canFall = false;
					for (LinkedList<Tile> l : this.getTiles()) {
						for (Tile t : l) {
							if (t.getX() > 41 && t.getX() < 55) {
								if (t.getY() == 24 || t.getY() == 25 || t.getY() == 26) {
									if (t.getType().equalsIgnoreCase("groundtile")) {
										stopped = true;
									} else {
										stopped = false;
									}
								}

							}
						}
					}
					if (!stopped) {
						this.shiftUp(1);
					}
					player.setMp(player.getMp() - 1);
				} else {
					this.canFall = true;
				}

			}

		}
		if (this.jumpVar == 0) {
			fallDelay.stop();
		}

		repaint();

	}

	public static void shoot() {// makes a projectile fire
		Projectile p = new Projectile((player.isFacing()));
		System.out.println(player.isFacing());
		Grid.getShots().add(p);
		Grid.shootPressed = false;
		Grid.setCharge(5);
	}

	public void init() {
		Room.setUp();
		// part 2 of generation
		int a = -1;

		for (LinkedList<Tile> xindex : this.getTiles()) {
			a++;
			int b = -1;
			for (Tile t : xindex) {
				// System.out.println(" "+a+" "+(b+1));
				b++;
				Tile l = new GroundTile(a * 8, b * 8, this.stone);
				t = l.c();
				// System.out.println(""+t.getX()+" "+t.getY());
				t.shift(8 * a, 8 * b);
				// System.out.println(""+t.getX()+" "+t.getY());
				this.getTiles().get(a).set(b, t);
			}

		}
		// System.out.println(this.tiles.length);
		setUp();

		this.getTiles().get(0).remove();
		this.getTiles().get(0).add(0, new GroundTile(0, 0, stone));

		panel.setTiles(this.getTiles());
		//
		// for (LinkedList<Tile> f : this.tiles) {
		// for (Tile t : f) {
		// if (t.getType().equalsIgnoreCase("BGTile")) {
		// if (Math.random() > 0.95) {
		// Monster m = new Monster(t.getX(), t.getY(), "Vampire.png");
		// this.getMonsters.add(m);
		// }
		// }
		// }
		// }
		// System.out.println(this.tiles.get(1).size());
		// System.out.println(this.tiles[1][1]);
	}

	@Override
	public void keyPressed(KeyEvent e) {// handles when a key is pressed down.
		if (e.getKeyCode() == Shoot) {
			Grid.shootPressed = true;
		}
		if (e.getKeyCode() == LeftArrow) {
			this.leftPressed = true;
			repaint();
		}

		if (e.getKeyCode() == RightArrow) {
			this.rightPressed = true;
			repaint();
		}
		if (e.getKeyCode() == UpArrow) {

			if (this.canJump && Grid.allowJump && this.canFall)
				this.jump();
			Grid.allowJump = false;
		}

		if (e.getKeyCode() == Fly) {
			this.spacePressed = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) { // fires when key is released.
		if (e.getKeyCode() == Grid.Shoot) {
			Grid.shootPressed = false;
			Projectile p = new Projectile(!player.isFacing());
			Grid.getShots().add(p);
			Grid.setCharge(5);
			// effect.run();

		}
		if (e.getKeyCode() == Grid.LeftArrow) {
			this.leftPressed = false;
			repaint();
		}
		if (e.getKeyCode() == Grid.RightArrow) {
			this.rightPressed = false;
			repaint();
		}
		if (e.getKeyCode() == Grid.UpArrow) {

			Grid.allowJump = true;
			this.fallDelay.stop();
			this.canFall = true;
			this.canFly = true;

		}

		if (e.getKeyCode() == Grid.Fly) {
			this.spacePressed = false;
			this.canJump = false;
			Grid.allowJump = false;
			this.canFall = true;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {// Nothing here.
	}

	private void shiftLeft(int h) {// shifts all left
		for (LinkedList<Tile> i : this.getTiles()) {

			for (Tile j : i) {
				j.shift(h, 0);
			}

		}
		for (Monster m : Grid.g.getMonsters()) {
			m.coords.shift(h, 0);
			repaint();
		}
		for (Projectile p : Grid.getShots()) {
			p.getCoords().shift(h, 0);
			repaint();
		}
	}

	private void shiftRight(int h) {// shifts all right.

		t1 = new BGTile(1, 2);
		t2 = new BGTile(1, 2);
		for (LinkedList<Tile> listTile : this.getTiles()) {
			for (Tile t : listTile) {
				if (t.getX() == 48) {

				}
			}
		}

		if (t1.getType().equalsIgnoreCase("groundtile") || t2.getType().equalsIgnoreCase("groundtile")) {
			fallCount = 0;
			// System.out.println("reset");//Debug: check call to reset from shift.
			while (t1.getY() > 48) {
				if (this.canFall)
					this.shiftDown(1);
			}
			this.canJump = true;
			return;
		}
		for (LinkedList<Tile> i : this.getTiles()) {
			for (Tile j : i) {
				j.shift(-h, 0);
				repaint();
			}

		}
		for (Monster m : Grid.g.getMonsters()) {
			m.coords.shift(-h, 0);
			repaint();
		}
		for (Projectile p : Grid.getShots()) {
			p.getCoords().shift(-h, 0);
			repaint();
		}
	}

	public void paintComponent(Graphics g) {// Depricated paint method.
		super.paintComponents(g);
		g.setColor(Color.BLUE);
		g.drawString("Leviathan", 100, 100);
		// System.out.println("PAINT");

	}

	public static void run() {// Grid.run
		Grid grid = new Grid();
		Grid.g = grid;
		// grid.repaint();
	}

	public void fall() {//////////////////////////////////////////////////////////////////////////// FALL////////////////////////////////////////////
		// System.out.println(fallCount);
		// canJump = false;

		player.yV++;
		if (player.yV > 6) {
			player.yV = 6;
		}
		t1 = new BGTile(1, 2);
		t2 = new BGTile(1, 2);
		// player coords at 48,24; bottom coords at 48,32, and 56,32;\
		// System.out.println(this.canFall);
		this.shiftUp(1);
		this.shiftDown(1);
		for (LinkedList<Tile> listTile : this.getTiles()) {
			for (Tile t : listTile) {

				if (t.getX() <= 48 && t.getX() >= 42) {
					if (40 + player.yV > t.getY() && 32 + player.yV < t.getY()) {
						t1 = t;
					} // makes t1 and t2 the two (or 1 ) tiles under the player.
				}
				if (t.getX() >= 48 && t.getX() <= 54) {
					if (40 + player.yV > t.getY() && 32 + player.yV < t.getY()) {
						t2 = t;
					}

				}
			}
		}

		if (t1.getType().equalsIgnoreCase("watertile")
				|| t1.getType().equalsIgnoreCase("lavatile") && t2.getType().equalsIgnoreCase("watertile")
				|| t2.getType().equalsIgnoreCase("lavatile")) {
			t.setDelay(120);
		}
		else {
			t.setDelay(60);
		}

			if (t1.getType().equalsIgnoreCase("groundtile") || t2.getType().equalsIgnoreCase("groundtile")) {
				player.yV = 0;
//			while (t1.getY() > 48) {
//				if (this.canFall) {
//					this.shiftDown(player.yV);
//					//this.falling = true;
//					this.falling =false;
//				}
//			}
				// this.falling = true;
				this.canJump = true;
				if (this.falling) {
					this.falling = false;
					// Sound.play("Build/land.wav");
					// System.out.println("fell");
				}
				return;
			}

			else {
				if (this.canFall) {
					this.shiftDown(player.yV);
					repaint();
					this.falling = true;
				}
			}

	}

	public void shiftUp(int h) {

		this.shiftDown(-h);// shifts up

	}

	public void shiftDown(int h) {
		// shifts down
		for (LinkedList<Tile> i : this.getTiles()) {
			for (Tile j : i) {
				j.shift(0, -h);
				repaint();
			}

		}
		for (Monster m : this.getMonsters()) {
			m.coords.shift(0, -h);
		}
		for (Projectile p : Grid.getShots()) {
			p.getCoords().shift(0, -h);
			repaint();
		}

	}

	private void jump() {// causes jump.
		this.canFly = false;
		this.canFall = false;
		this.canJump = false;
		boolean nope = false;
		// for (int i = 0; i < 30; i++) {
		// int a = 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1
		// + 1 + 1 + 1 + 1 + 1 + 1 + 1// uncomment to take up clock cycles.
		// + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1;
		jumpVar = 30;
		fallDelay.start();

		// }
		// this.canFall = true;
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) { //Check for InterruptedException
		// e.printStackTrace();
		// }
		// this.canJump = true;

	}

	public static int getCharge() {
		return charge; // getter for charge.
	}

	public static void setCharge(int charge) { // setter for charge.
		Grid.charge = charge;
	}

	public static long getScore() {
		// setter for score
		return Grid.score;
	}

	public static void addScore(int amount) {// adds to score.
		Grid.score += amount;
	}

	public LinkedList<Monster> getMonsters() {
		return Grid.g.monsters;
	}

	public void setMonsters(LinkedList<Monster> monsters) {
		Grid.g.setMonsters(monsters);
	}

	public static LinkedList<Projectile> getShots() {
		return Grid.g.shots;
	}

	public static void setShots(LinkedList<Projectile> shots) {
		Grid.g.shots = shots;
	}

	public LinkedList<LinkedList<Tile>> getTiles() {
		return tiles;
	}

	public void setTiles(LinkedList<LinkedList<Tile>> tiles) {
		this.tiles = tiles;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Grid.shootPressed = true;
		// Grid.setCharge(20);
		// Projectile p = new Projectile(!player.isFacing());
		// Grid.getShots().add(p);
		// Grid.setCharge(5);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Grid.shootPressed = false;
		Projectile p = new Projectile(!player.isFacing());
		Grid.getShots().add(p);
		Grid.setCharge(5);

	}
}