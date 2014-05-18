
package PotyogosAmoba;

import static PotyogosAmoba.BeadGameLogic.COLS;

import javax.swing.ImageIcon;

/**
* Egy adott mező színét meghatározó objektum
* @author Zoltán
*/
public class Szinezo {

    public void szinez(int ROWS, ImageIcon[][] szinek, BeadGameFrame gui) {

        for (int j = 0; j < COLS; ++j) {
            for (int i = ROWS - 1; i >= 0 && szinek[i][j] != null; --i) {
                gui.setButtonColor(i, j, szinek[i][j]);
            }
        }
    }
}
