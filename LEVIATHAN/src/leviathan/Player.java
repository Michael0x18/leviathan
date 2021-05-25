// Michael Ferolito
// Player class
// Apr 27 2019 Version Beta

package leviathan;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	
	/**********FIELDS**********/
	private String name = "null";
	private int level = 0x0;
	private long XP = 0x0;
	
	public int xV = 0;
	public int yV = 0;
	
	protected Image sprite = this.spriteRight;//new ImageIcon("BlankSprite.png").getImage();
	protected Image spriteLeft = new ImageIcon("Build/wiz.png").getImage();
	protected Image spriteRight = new ImageIcon("Build/wiz2.png").getImage();
	private CoordinateSystem coords; //CoordinateSystem
	
	private int attack = 0x0;
	private int defense = 0x0;       //player stats are hex values, NOT DECIMAL!!!!!!!!!!!!
	private int speed = 0x0;
	private int dexterity = 0x0;
	private int mana = 100;
	private int mp = 100;
	private int health  = 200;
	private int hp = 200;
	
	private StatusBar hpBar = new StatusBar("HP",100);
	private ManaBar manaBar = new ManaBar("MP",100);
	
	private boolean facing = true; //true is left, false is right
	
	private int fallCount = 0;
	
	/**************************/
	
	public Player(String playerName, String spritePath,int x, int y) {
		this.sprite = new ImageIcon(spritePath).getImage();
		this.spriteLeft = new ImageIcon("Build/wiz.png").getImage();
		this.spriteRight = new ImageIcon("Build/wiz2.png").getImage();
		this.coords = new CoordinateSystem(x, y, this.sprite);
	}
	
	/********MOVEMENT**********/
	public void moveLeft() {
		//Grid.rightShift(this.speed);
	}
	
	public void moveRight() {                            //     Movement methods
		//Grid.leftShift(this.speed);
	}
	
	public void jump(){
		//Grid.jump();
	}
	
	public void set(int x, int y) {
		//this.coords.shift(x-this.getX(),y-this.getY());
	}
	
//	public void fall() {
//		Grid.fall();
//	}
	
	public void faceLeft() {
		this.sprite = this.spriteLeft;//this.spriteLeft;
		this.facing = true;
		
	}
	
	public void faceRight() {
		this.sprite = this.spriteRight;//System.out.println("boi");
		//System.out.println("called right");
		this.facing = false;
		
	}
	
	public void shift(double dx, double dy) {
		this.coords.shift(dx,dy);
	}
	/**************************/
	
	
	
	
	
	
	/**********************************************LONG*LIST*OF*GETTER*AND*SETTER*METHODS*************************************************/
	
	public int getX() {                                        //                   X Coordinate getter
		return 0;
	}
	public int getY() {                                        //                   Y Coordinate getter
		return 0;
	}
	
	public int[] getCoords() {                                 //                   Both Coordinates getter
		int[] a = {0,0};
		return a;
	}
	
	public int getLevel() {
		return this.level;
	}
	public void setLevel(int param) {
		this.level+= param;
	}
	
	public long getXP() {
		return this.XP;
	}
	
	public void addXP(int amount) {
		this.XP += (long)amount;
	}
	
	public void setXP(long amount) {
		this.XP = amount;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {                                  //                   Name getter
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {					       //                   Name setter
		this.name = name;
	}
	/**
	 * @return the attack
	 */
	public int getAttack() {								   //                   Attack getter
		return attack;
	}
	/**
	 * @param attack the attack to set
	 */
	public void setAttack(int attack) {                        //                   Attack setter
		this.attack = attack;
	}
	/**
	 * @return the defense
	 */
	public int getDefense() {                                  //                   Defense getter
		return defense;
	}
	/**
	 * @param defense the defense to set
	 */
	public void setDefense(int defense) {                      //                   Defense setter
		this.defense = defense;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {                                    //                   Speed getter
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {                          //                   Speed setter
		this.speed = speed;
	}
	/**
	 * @return the dexterity
	 */
	public int getDexterity() {                                //                   Dexterity getter
		return dexterity;
	}
	/**
	 * @param dexterity the dexterity to set
	 */
	public void setDexteriy(int dexterity) {                    //                  Dexterity setter
		this.dexterity = dexterity;
	}
	/**
	 * @return the mana
	 */
	public int getMana() { 										//                  Mana setter
		return mana;
	}
	/**
	 * @param mana the mana to set
	 */
	public void setMana(int mana) {								//                  Mana getter
		this.mana = mana;
	}
	/**
	 * @return the mp
	 */
	public int getMp() {										//                  Mp getter
		return mp;
	}
	/**
	 * @param mp the mp to set
	 */
	public void setMp(int mp) {    								//                  Mp setter
		this.mp = mp;
	}
	/**
	 * @return the health
	 */
	public int getHealth() {									//					Health getter
		return health;
	}
	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {							//                  Healsh setter
		this.health = health;
	}
	/**
	 * @return the hp
	 */
	public int getHp() {                                        //                  Hp getter
		return hp;
	}
	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {                                 //                  Hp setter
		this.hp = hp;
	}
	
	/**
	 * @return the sprite
	 */
	public Image getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	/**
	 * @return the coords
	 */
	public CoordinateSystem getCoordsystem() {
		return coords;
	}

	/**
	 * @param coords the coords to set
	 */
	public void setCoordsystem(CoordinateSystem coords) {
		this.coords = coords;
	}
	/************************************************************************************************************************************/
	
	public String toString() {
		return ""+this.name+" "+this.getLevel();
	}

	public void draw(Graphics g) {
		this.hpBar.draw(2, 2, g);
		this.manaBar.draw(2,6,g);
		this.coords.drawImage(g, this.sprite);
		
		
		
	}
	
	public void doDamage(int d) {
		this.hp -= d;
	}

	public boolean isFacing() {
		return facing;
	}

	public void setFacing(boolean facing) {
		this.facing = facing;
	}
	
	

	

	
	
}
