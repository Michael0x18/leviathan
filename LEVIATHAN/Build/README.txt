Leviathan::Readme
Release 0.0


   ,--,                                                       ,----,                                        
,---.'|                                                     ,/   .`|       ,--,                        ,--. 
|   | :       ,---,.               ,---,   ,---,          ,`   .'  :     ,--.'|   ,---,              ,--.'| 
:   : |     ,'  .' |       ,---.,`--.' |  '  .' \       ;    ;     /  ,--,  | :  '  .' \         ,--,:  : | 
|   ' :   ,---.'   |      /__./||   :  : /  ;    '.   .'___,/    ,',---.'|  : ' /  ;    '.    ,`--.'`|  ' : 
;   ; '   |   |   .' ,---.;  ; |:   |  ':  :       \  |    :     | |   | : _' |:  :       \   |   :  :  | | 
'   | |__ :   :  |-,/___/ \  | ||   :  |:  |   /\   \ ;    |.';  ; :   : |.'  |:  |   /\   \  :   |   \ | : 
|   | :.'|:   |  ;/|\   ;  \ ' |'   '  ;|  :  ' ;.   :`----'  |  | |   ' '  ; :|  :  ' ;.   : |   : '  '; | 
'   :    ;|   :   .' \   \  \: ||   |  ||  |  ;/  \   \   '   :  ; '   |  .'. ||  |  ;/  \   \'   ' ;.    ; 
|   |  ./ |   |  |-,  ;   \  ' .'   :  ;'  :  | \  \ ,'   |   |  ' |   | :  | ''  :  | \  \ ,'|   | | \   | 
;   : ;   '   :  ;/|   \   \   '|   |  '|  |  '  '--'     '   :  | '   : |  : ;|  |  '  '--'  '   : |  ; .' 
|   ,/    |   |    \    \   `  ;'   :  ||  :  :           ;   |.'  |   | '  ,/ |  :  :        |   | '`--'   
'---'     |   :   .'     :   \ |;   |.' |  | ,'           '---'    ;   : ;--'  |  | ,'        '   : |       
          |   | ,'        '---" '---'   `--''                      |   ,/      `--''          ;   |.'       
          `----'                                                   '---'                      '---'         
                                                                                                            
A game by:

Michael Ferolito
Joshua Han
Timothy Liu


Leviathan: 
A sea monster, like a great serpent, that devours ships and their crews,
The master of the depths and the terror of the seas.

Prometheus:
The Maker of humankind,
who forged men from mud.
And was punished by the gods,
for giving them fire.

Colossus:
The Champion of the gods,
eternal in its glory,
but corrupted by a thirst for blood,
And seized by passion for death.


What better way for a man to die,
 than facing fearful odds,
For the ashes of his fathers,
and the temples of his gods?

Leviathan is a single-player platformer game. You play as a character in a cave, descending toward the center. The “w” key causes you to jump, the “a” key makes you go left, and the “d” key causes you to go right. Click on the screen to throw a fireball, or press space to launch a melee attack in the direction you are facing. Complete each level by reaching the gate at the end. Shooting fireballs consumes your mp, and getting hit by an enemy’s attacks consumes your hp. Defense reduces the damage dealt, and attack increases it. Dexterity is a measure of how fast you can shoot. Speed is a measure of how fast you can run. You will heal very slowly, and at a fixed rate. Healing items can be found in chests. Your inventory can hold up to six items of this sort. If you are killed, the game will end.


__Dat__:
__Packages__:

main:
>>> Package containing the main method.
>>> So that the protected keyword still functions as encapsulation, while making life easier.

__Classes__:

------main------
>>>contains main method.

methods:
main.

Leviathan:
>>> Package containing all classes. (except for main)

__Classes__:

------LeviathanMain:------
>>> Contains code for setting up a JFrame using Grid.
>>> Kind of an in-between between the program classes and the main.
>>> Contains one method: 

LeviathanMain::initLeviathan();
>>> static void, params void

------Tile------
>>> Abstract class reprsenting a simple tile, or block.
>>> Has a sprite, and some coordinates.
>>> Can translate left and right.
>>> Can translate up and down.
>>> Methods:

Tile::Tile(int x, int y);
>>> generic constructor.
>>> starts off sprite as "..\blanksprite.png"
>>> aforementioned sprite is a black 0xf by 0xf square.

Tile::Tile(x,y,Image pic);
>>> constructor with specified sprite.
>>> Image, not ImageIcon. There is a difference.
>>> Sprite must be 0xf * 0xf

Tile::shift(double dx, double dy);
>>> calls Affinetransform.shift(dx,dy)
>>> uses same parameters and passes onto coords field.

Tile::getX();
>>> getter for integer holding x coordinate, in decimal.

Tile::getY();
>>> getter for integer holding y coordinate, in decimal.

Tile::get();
>>> packer for coordinates.

Tile::inRange(int x,y,a);
>>> static boolean
>>> returns true if a is in between x and y
>>> returns false otherwise
>>> exception if any var is null.

TIle::isTouching(Monster m);
>>> Takes object of type monster as arg.
>>> Compound object is passed by REFERENCE, rather than by value.
>>> Java, so no need for the preceeding ampersand.

Tile::act();
>>> void
>>> abstract
>>> Tile performs action, tile.act may be overridden and called when player is touching tile.

Tile::toString();
>>> String abstract
>>> For printing and debugging subclasses of Tile.

Tile::getType();
>>> String abstract
>>> returns type of the Tile in question, in a string.
>>> hashcode?

Tile::draw(Graphics g)
>>> Makes the graphics object passed to it BY REFERENCE draw this Tile.
>>> calls coordinateSystem.draw.

>>>Fields:
private Image sprite
private CoordinateSystem coords


------<<Boss>>------
>>> interface
>>> contains template for use in creating a boss.
>>> must define methods:

Taunt
die
appear
trigger
nextPhase
gotoPhase
terminate

------BGTile.java------
>>> represents a tile that is in the background. eg: it cannot be interacted with by the user.
>>> non-abstract. 
>>> defines methods contained in Tile:

BGTile::toString();
>>> returns "BGTile"

BGTile::act();
>>> empty

BGTile::getType
>>> returns "BGTile"

------CoordinateSystem------
>>> Uses AffineTransform, BufferedImage.
>>> Methods:

CS::CoordinateSystem(int x, int y, Image pic);
>>> Creates CoordinateSystem with initial coords at x,y, and stored sprite as pic.

CS::shift(double dx, double dy);
>>> shifts by dx and dy using affineTransform

CS::get();
>>> packer for coords, int[]

CS::rotate();
@deprecated
>> > does not work

CS::drawImage(Graphics g, Image picture);
>>> draws image passed.
>>> uses a cast. WILL PASS AN ERROR TO RUNTIME IF YOU PASS AN ARGUMENT THAT IS NOT COMPATIBLE.

>>>fields:

private Image picture
private AffineTransform coordTransform
private int x,y //Both initialized to 0.

------




































