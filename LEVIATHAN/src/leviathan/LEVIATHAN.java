package leviathan;
/*
 *     The main class.
 *     
 *     What better way for a man to die,
 *     than facing fearful odds,
 *     for the ashes of his fathers,
 *     and the temples of his gods?
 *     
 *     Run me.
 *     
 *     (____LEVIATHAN____)
 *     A game by:
 *     Michael Ferolito
 *     Timothy Liu
 *     Joshua Han
 */
public class LEVIATHAN {// this class written by Timothy Liu
	public static void main(String[] Args) {
		//leviathan.EasySound easy = new leviathan.EasySound("Build/BattleLoop.wav");//plays sound.
		//Call Grid.run(Args), which is basically a main method that is not run when the program is run.
		System.out.println(System.setProperty("apple.laf.useScreenMenuBar", "true"));
		MainMenu menu = new MainMenu();
		
	}
}

