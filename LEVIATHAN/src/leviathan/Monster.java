package leviathan;

import java.awt.Graphics;
import java.awt.Graphics.*;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

//Joshua Han                                  Last edited: Apr 10 7:27:12 PM


public class Monster implements ActionListener {
	private Image sprite;
	protected CoordinateSystem coords;

	private Timer t = new Timer(100, this);
	
	public static enum Type {VAMPIRE,GHOST,DARKKNIGHT};
	
	
	private String type = "";
	private int HP = 50;
	private int def = 0x0;
	private int spd = 0x1;
	private int att = 0x0;
	private int vit = 0x1;
	private Image deathImage = null;
	private int health = 0x1;
	private boolean s = false;
	private boolean s1 = false; //Stats: mostly hex
	private int displacement = 0;
	protected boolean destroyed = false;
	protected Image ghostLeft = new ImageIcon("Build/ghost1.png").getImage();
	protected Image ghostRight = new ImageIcon("Build/ghost2.png").getImage();

	public Monster(Type type,int x, int y,String spritePath) {
		switch(type) {
		case VAMPIRE:
			this.type = "vampire";
			break;
		case GHOST:
			this.type = "ghost";
			this.HP = 5;
			this.s1 = true;
			break;
		case DARKKNIGHT:
			this.type = "darkknight";
			break;
		default:
			//System.err.println("You are a retard.");
			System.exit(2);
		}
		this.sprite = new ImageIcon(spritePath).getImage();
		this.coords = new CoordinateSystem(x, y, this.sprite);
		t.start();
		
	}


	public boolean takeDamage(int amount) {
		this.HP -= amount;
		return this.HP < 0 ? this.die() : false;
	}

	public int[] getCoords() {
		return this.coords.get();
	}

	public int getX() {
		return this.coords.get()[0];
	}

	public int getY() {
		return this.coords.get()[1];
	}

	public void draw(Graphics g) {
		if (this.s1) {
			if (this.getX() > 52) {
				coords.drawImage(g, ghostLeft);
				// System.err.print("true1");
			} else if (this.getX() <= 52) {
				coords.drawImage(g, ghostRight);
				// System.err.print("true2");
			} else {
				coords.drawImage(g, ghostLeft);
				System.err.print("false");
			}
		} else {
			coords.drawImage(g, this.sprite);
			// System.out.println(this.sprite);
		}
	}

	public boolean makeRangedAttack() {
		return false;

	}

	public boolean makeMeleeAttack() {
		return false;

	}

	public boolean die() {
		this.t.stop();
		// Grid.monsters.remove(this);
		this.destroyed = true;
		Grid.addScore((int) (Math.random() * 40 + 80));
		return true;

	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public void set(int[] a) {
		this.HP = a[0];
		this.def = a[1];
		this.spd = a[2];
		this.setAtt(a[3]);
		this.setVit(a[4]);
	}

	public void get(int[] a) {
		int[] array = { 0, 0, 0, 0, 0 };
		a[0] = this.HP;
		a[1] = this.def;
		a[2] = this.spd;
		a[3] = this.att;
		a[4] = this.vit;

	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getVit() {
		return vit;
	}

	public void setVit(int vit) {
		this.vit = vit;
	}

	public Image getDeathImage() {
		return deathImage;
	}

	public void setDeathImage(Image deathImage) {
		this.deathImage = deathImage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void doDamage(int dmg) {
		this.HP -= dmg;
		if (this.HP < 0) {
			this.die();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.coords.get()[0] > 44 && this.coords.get()[0] < 60) {
			// System.out.println(true);
			if (this.coords.get()[1] >= 20 && this.coords.get()[1] <= 36) {
				Grid.player.doDamage(2);
			}

		}
		if (this.type.equals("darkknight")) {
			
			if (this.displacement < 24) {
				this.displacement++;
				this.coords.shift(1, 0);

			} else if (this.displacement < 48) {
				this.displacement++;
				this.coords.shift(-1, 0);
				if (this.displacement == 48) {
					this.displacement = 0;
				}
			}
		} else if (this.type.equals("ghost")) {
			if (this.getY() < 32) {
				// if (Math.random() > 0.0)
				this.coords.shift(0, 1);
			} else if (this.getY() > 32) {
				// if (Math.random() > 0.0)
				this.coords.shift(0, -1);
			}
			if (this.getX() < 48) {
				// if (Math.random() > 0.0)
				this.coords.shift(1, 0);
			} else if (this.getX() > 48) {
				// if (Math.random() > 0.0)
				this.coords.shift(-1, 0);
			}
		}
	}
}
