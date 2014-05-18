/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package torpedo;

import java.awt.Color;

/**
 * Ez az osztály végzi a hajók lepakolásának vizuális megjelenítését. 
 */
public class Coloring {

    private TorpeDoFrame gui;
    int COLS;
    int ROWS;
    int[][] statusME;
    int[][] statusYOU;
    int c;

    public Coloring(int cols, int rows, int[][] statusME, int[][] statusYOU, int c, TorpeDoFrame gui) {
        this.gui = gui;
        this.COLS = cols;
        this.ROWS = rows;
        this.statusME = statusME;
        this.statusYOU = statusYOU;
        this.c = c;
    }

    /**
     * Ez a függvény felelős azért hogy amikor a felhasználó lepakolja a saját hajóit annak színei elkülönüljenek a pálya más elemeitől
     */
    public void coloringME() {
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                if (statusME[m][n] == 0) {
                    gui.setColorOfButtonME(m, n, Color.BLUE);
                } else if (statusME[m][n] == 1) {
                    if (c < 2) {
                        gui.setColorOfButtonME(m, n, Color.LIGHT_GRAY);
                    } else if ((c > 1) && (c < 5)) {
                        gui.setColorOfButtonME(m, n, Color.GRAY);
                    } else if ((c > 4) && (c < 9)) {
                        gui.setColorOfButtonME(m, n, Color.DARK_GRAY);
                    }
                } else if (statusME[m][n] == 2) {
                    gui.setColorOfButtonME(m, n, Color.RED);
                } else if (statusME[m][n] == -1) {
                    gui.setColorOfButtonME(m, n, Color.WHITE);
                }
            }
        }
    }

    /**
     * Ez a föggvény felelős az ellenfél mezőjén való interakciók megjelenítéséért
     */
    public void coloringYOU() {
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                if (statusYOU[m][n] == -1) {
                    gui.setColorOfButtonYOU(m, n, Color.WHITE);
                } else if (statusYOU[m][n] == 2) {
                    gui.setColorOfButtonYOU(m, n, Color.RED);
                }
            }
        }
    }
}
