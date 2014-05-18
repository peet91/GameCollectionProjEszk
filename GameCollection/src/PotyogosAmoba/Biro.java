
package PotyogosAmoba;

import static PotyogosAmoba.BeadGameLogic.COLS;
import javax.swing.ImageIcon;

/**
* Ez az osztály felelős a játék állapotának kiértékeléséért
*/

public class Biro {

    void win(int x, int y, ImageIcon[][] szinek, BeadGameFrame gui, int ROWS, Player aktPlayer) {
        boolean vege = false;
        //VÃ­zszintes gyÅzelem vizsgÃ¡lat
        int k = 1;
        ImageIcon szin = null;
        for (int z = y - 3; z < y + 4 && !vege; ++z) {
            if (z >= 0 && z < COLS) {
                if (szin != null && szinek[x][z] != null && szin.toString().equals(szinek[x][z].toString())) {
                    k++;
                } else {
                    k = 1;
                    szin = szinek[x][z];
                }
                if (k >= 4) {
                    vege = true;
                    for (int w = z - 3; w <= z; w++) {
                        gui.showWin(x, w);
                    }
                    gui.uzen("VÃ­zszintes gyÅzelemmel nyert a " + aktPlayer.getNumber() + ". jÃ¡tÃ©kos! ");
                }
            }
        }
        
        /**
        * Függőleges győzelem vizsgálat
        */
        
        szin = null;
        k = 1;
        for (int z = x - 3; z < x + 4 && !vege; ++z) {
            if (z >= 0 && z < ROWS) {
                if (szin != null && szinek[z][y] != null && szin.toString().equals(szinek[z][y].toString())) {
                    k++;
                } else {
                    k = 1;
                    szin = szinek[z][y];
                }
                if (k >= 4) {
                    vege = true;
                    for (int w = z - 3; w <= z; w++) {
                        gui.showWin(w, y);
                    }
                    gui.uzen("FÃ¼ggÅleges gyÅzelemel nyert a " + aktPlayer.getNumber() + ". jÃ¡tÃ©kos!");
                }
            }
        }
        /**
        * Átlós győzelem vizsgálat
        */
        szin = null;
        k = 1;
        for (int z = -3; z < 4 && !vege; ++z) {
            if (x + z >= 0 && y + z >= 0 && x + z < ROWS && y + z < COLS) {
                if (szin != null && szinek[x + z][y + z] != null && szin.toString().equals(szinek[x + z][y + z].toString())) {
                    k++;
                } else {
                    k = 1;
                    szin = szinek[x + z][y + z];
                }
                if (k >= 4) {
                    vege = true;
                    for (int w = z - 3; w <= z; w++) {
                        gui.showWin(w + x, w + y);
                    }
                    gui.uzen("CsÃ¶kkenÅ Ã¡tlÃ³s gyÅzelemmel nyert a " + aktPlayer.getNumber() + ". jÃ¡tlÃ©kos!");
                }
            }
        }
        /**
        * Átlós győzelem vizsgálat
        */
        szin = null;
        k = 1;
        for (int z = -3; z < 4 && !vege; ++z) {
            if (x + z >= 0 && y - z >= 0 && x + z < ROWS && y - z < COLS) {
                if (szin != null && szinek[x + z][y - z] != null && szin.toString().equals(szinek[x + z][y - z].toString())) {
                    k++;
                } else {
                    k = 1;
                    szin = szinek[x + z][y - z];
                }
                if (k >= 4) {
                    vege = true;
                    for (int w = z - 3; w <= z; w++) {
                        gui.showWin(w + x, y - w);
                    }
                    gui.uzen("NÃ¶vekvÅ Ã¡tlÃ³s gyÅzelemmel nyert a " + aktPlayer.getNumber() + ". jÃ¡tÃ©kos!");
                }
            }
        }
        /**
        * Döntetlen játék vizsgálata
        */
        
        boolean b = true;
        for (int d = 0; d < COLS && b; d++) {
            if (szinek[0][d] != null) {
                b = true;
            } else {
                b = false;
            }
        }
        if (b) {
            gui.uzen("Nem bÃ­rtatok egymÃ¡ssal!");
        }
    }
}
