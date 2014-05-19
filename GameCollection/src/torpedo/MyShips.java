/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package torpedo;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * Ez az osztály szabályozza hogy a felhasználó hová helyezheti el saját hajóinak elemeit
 */
public class MyShips extends JFrame{
    private TorpeDoFrame gui;
    int COLS;
    int ROWS;
    int[][] statusME;

    public MyShips(int cols, int rows, int[][] statusME, TorpeDoFrame gui) {
        this.gui = gui;
        this.COLS = cols;
        this.ROWS = rows;
        this.statusME = statusME;
    }
    
    
    //KETTES HAJO   
    /**
     * Ez a függvény felelős az ellenőrzések elvégzéséért, hogy hova kerülhet a felhasználó 2 elemű hajója
     */
    public void placeNo2Ship(int i, int j) {
        statusME[i][j] = 1;
        //gui.setColorOfButtonME(i, j, Color.LIGHT_GRAY); //lereak egy részletet

        //tisztitas
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                gui.setButtonEnabledME(m, n, false);
            }
        }

        //ide rakhatsz hajot
        if (i + 1 < COLS) {
            gui.setButtonEnabledME(i + 1, j, true);
        }
        if (j + 1 < ROWS) {
            gui.setButtonEnabledME(i, j + 1, true);
        }
        if (i - 1 >= 0) {
            gui.setButtonEnabledME(i - 1, j, true);
        }
        if (j - 1 >= 0) {
            gui.setButtonEnabledME(i, j - 1, true);
        }

        //ahova mar raktal oda nem rakhatsz
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                if (statusME[m][n] == 1) {
                    gui.setButtonEnabledME(m, n, false);
                }
            }
        }
    }//kettes vege
    
    
    //HARMAS HAJO
    /**
     * 
     * Ez a függvény felelős az ellenőrzések elvégzéséért, hogy hova kerülhet a felhasználó 3 elemű hajója
     */
    public void placeNo3Ship(int i, int j, int c) {
        statusME[i][j] = 1;//lereak egy reszletet
        // gui.setColorOfButtonME(i, j, Color.GRAY); 

        //tisztitas
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                gui.setButtonEnabledME(m, n, false);
            }
        }

        //hajo elso darabja
        if (c == 2){
            if (i + 1 < COLS) {
                gui.setButtonEnabledME(i + 1, j, true);
            }
            if (j + 1 < ROWS) {
                gui.setButtonEnabledME(i, j + 1, true);
            }
            if (i - 1 >= 0) {
                gui.setButtonEnabledME(i - 1, j, true);
            }
            if (j - 1 >= 0) {
                gui.setButtonEnabledME(i, j - 1, true);
            }
        } else if (c > 2) {
            //hajó többi darabja     
            if (i - 1 >= 0 && statusME[i - 1][j] == 1 && i + 1 < ROWS) {//ha felette van az előző
                gui.setButtonEnabledME(i + 1, j, true);

            }
            if (i - 1 >= 0 && statusME[i - 1][j] == 1 && i - 2 >= 0) {
                gui.setButtonEnabledME(i - 2, j, true);
            }

            if (i + 1 < ROWS && statusME[i + 1][j] == 1 && i + 2 < ROWS) {//ha alatta van az előző
                gui.setButtonEnabledME(i + 2, j, true);
            }
            if (i + 1 < ROWS && statusME[i + 1][j] == 1 && i - 1 >= 0) {
                gui.setButtonEnabledME(i - 1, j, true);
            }

            if (j - 1 >= 0 && statusME[i][j - 1] == 1 && j + 1 < COLS) {//ha balra van az előző
                gui.setButtonEnabledME(i, j + 1, true);

            }
            if (j - 1 >= 0 && statusME[i][j - 1] == 1 && j - 2 >= 0) {
                gui.setButtonEnabledME(i, j - 2, true);
            }

            if (j + 1 < COLS && statusME[i][j + 1] == 1 && j + 2 < COLS) {//ha jobbra van az előző
                gui.setButtonEnabledME(i, j + 2, true);

            }
            if (j + 1 < COLS && statusME[i][j + 1] == 1 && j - 1 >= 0) {
                gui.setButtonEnabledME(i, j - 1, true);
            }



        }

        //ahova mar raktal oda nem rakhatsz
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                if (statusME[m][n] == 1) {
                    gui.setButtonEnabledME(m, n, false);
                }
            }
        }
    }//harmas vege
    
    //NEGYES HAJO
    /**
     * 
     * Ez a függvény felelős az ellenőrzések elvégzéséért, hogy hova kerülhet a felhasználó 4 elemű hajója
     * 
     */
    public void placeNo4Ship(int i, int j, int c) {
        statusME[i][j] = 1;
        //   gui.setColorOfButtonME(i, j, Color.DARK_GRAY); //lreak egy részletet

        //tisztitas
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                gui.setButtonEnabledME(m, n, false);
            }
        }

        //hajo elso darabja
        if (c == 5) {
            if (i + 1 < COLS) {
                gui.setButtonEnabledME(i + 1, j, true);
            }
            if (j + 1 < ROWS) {
                gui.setButtonEnabledME(i, j + 1, true);
            }
            if (i - 1 >= 0) {
                gui.setButtonEnabledME(i - 1, j, true);
            }
            if (j - 1 >= 0) {
                gui.setButtonEnabledME(i, j - 1, true);
            }
        } else if (c == 6) {
            //hajo tobbi darabja   

            if (i - 1 >= 0 && statusME[i - 1][j] == 1 && i + 1 < ROWS) {//ha felette van az előző
                gui.setButtonEnabledME(i + 1, j, true);

            }
            if (i - 1 >= 0 && statusME[i - 1][j] == 1 && i - 2 >= 0) {
                gui.setButtonEnabledME(i - 2, j, true);
            }

            if (i + 1 < ROWS && statusME[i + 1][j] == 1 && i + 2 < ROWS) {//ha alatta van az előző
                gui.setButtonEnabledME(i + 2, j, true);
            }
            if (i + 1 < ROWS && statusME[i + 1][j] == 1 && i - 1 >= 0) {
                gui.setButtonEnabledME(i - 1, j, true);
            }

            if (j - 1 >= 0 && statusME[i][j - 1] == 1 && j + 1 < COLS) {//ha balra van az előző
                gui.setButtonEnabledME(i, j + 1, true);

            }
            if (j - 1 >= 0 && statusME[i][j - 1] == 1 && j - 2 >= 0) {
                gui.setButtonEnabledME(i, j - 2, true);
            }

            if (j + 1 < COLS && statusME[i][j + 1] == 1 && j + 2 < COLS) {//ha jobbra van az előző
                gui.setButtonEnabledME(i, j + 2, true);

            }
            if (j + 1 < COLS && statusME[i][j + 1] == 1 && j - 1 >= 0) {
                gui.setButtonEnabledME(i, j - 1, true);
            }
        } else if (c > 6) {
            //hajo utolso darabja    

            if (i - 1 >= 0 && statusME[i - 1][j] == 1 && i + 1 < ROWS) {//ha felette van az előző
                gui.setButtonEnabledME(i + 1, j, true);

            }
            if (i - 1 >= 0 && statusME[i - 1][j] == 1 && i - 3 >= 0) {
                gui.setButtonEnabledME(i - 3, j, true);
            }

            if (i + 1 < ROWS && statusME[i + 1][j] == 1 && i + 3 < ROWS) {//ha alatta van az előző
                gui.setButtonEnabledME(i + 3, j, true);
            }
            if (i + 1 < ROWS && statusME[i + 1][j] == 1 && i - 1 >= 0) {
                gui.setButtonEnabledME(i - 1, j, true);
            }

            if (j - 1 >= 0 && statusME[i][j - 1] == 1 && j + 1 < COLS) {//ha balra van az előző
                gui.setButtonEnabledME(i, j + 1, true);

            }
            if (j - 1 >= 0 && statusME[i][j - 1] == 1 && j - 3 >= 0) {
                gui.setButtonEnabledME(i, j - 3, true);
            }

            if (j + 1 < COLS && statusME[i][j + 1] == 1 && j + 3 < COLS) {//ha jobbra van az előző
                gui.setButtonEnabledME(i, j + 3, true);

            }
            if (j + 1 < COLS && statusME[i][j + 1] == 1 && j - 1 >= 0) {
                gui.setButtonEnabledME(i, j - 1, true);

            }

        }

        //ahova mar raktal oda nem rakhatsz
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                if (statusME[m][n] == 1) {
                    gui.setButtonEnabledME(m, n, false);
                }
            }
        }
    }//negyes hajo vege
}
