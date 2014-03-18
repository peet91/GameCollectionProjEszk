/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PotyogosAmoba.bead;

import static PotyogosAmoba.bead.BeadGameLogic.COLS;
import javax.swing.ImageIcon;

/**
 *
 * @author Zoltán
 */
public class Szinezo {

    //A tábla egy adott elemének a hátterét állítja be
    public void szinez(int ROWS, ImageIcon[][] szinek, BeadGameFrame gui) {

        for (int j = 0; j < COLS; ++j) {
            for (int i = ROWS - 1; i >= 0 && szinek[i][j] != null; --i) {
                gui.setButtonColor(i, j, szinek[i][j]);
            }
        }
    }
}
