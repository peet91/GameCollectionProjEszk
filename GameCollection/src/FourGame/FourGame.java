package FourGame;
/**
 * @author peet91
 */
import FourGame.GUI.FourGameGUI;
/**
 * Ez az osztály tartalmazza a futtatható állományt
 * 
 */

public class FourGame {
    /**
     * A játékot indító függvény
     * 
     * @param args
     */
    public static void main(String[] args) {
    
        new FourGameGUI().setVisible(true);
    }
}
