/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package torpedo;

import javax.swing.JFrame;

/**
 *
 * a TorpeDo osztály tartalmazza a futtatható állományt
 */
public class TorpeDo {

    /**
     * Ez az osztály példányosítja valamint indítja el a programot.
     */
	
    public static void main(String[] args) {
        JFrame table = new TorpeDoFrame();
        table.setVisible(true);
    }
}
