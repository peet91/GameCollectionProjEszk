/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package torpedo;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * A játék menetének szabályait állítja be
 * 
 */
public class TorpeDoLogic extends JFrame {

    public static int ROWS = 8;
    public static int COLS = 8;
    private TorpeDoFrame gui;
    private MyShips ms;
    private EnemysShips es;
    private Coloring coloring;
    // -1 = tamadott de nem talalt
    // 0 = viz;
    // 1 = hajo;
    // 2 = felrobbant hajo;
    public static int[][] statusME;
    public static int[][] statusYOU;
    public static int c; // counter 
    private Random generator = new Random();
    private int round = 0;
    private int lx = 0;
    private int ly = 0;
    private int rx = 0;
    private int ry = 0;
    private boolean b;

    public TorpeDoLogic(TorpeDoFrame gui) {

        this.c = 0;
        this.gui = gui;
        statusME = new int[ROWS][COLS];
        statusYOU = new int[ROWS][COLS];
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                statusME[m][n] = 0;
                statusYOU[m][n] = 0;
            }
        }
        ms = new MyShips(COLS, ROWS, statusME, gui);
        es = new EnemysShips(COLS, ROWS, statusYOU, gui);
        coloring = new Coloring(COLS, ROWS, statusME, statusYOU, c, gui);
    }//konstruktor vege

    /**
     * 
     * Ez a függvény határozza meg a játék milyen fázisában a kattintásokank milyen szerepe legyen
     * 
     */
    public void buttonPressed(int i, int j) {
//HAJOIM LEPAKOLASA

        if (c < 2) {
            ms.placeNo2Ship(i, j);
        }
        if (c == 1) {
            JOptionPane.showMessageDialog(gui, "You placed the first ship!");
            enableME();
        }


        if ((c > 1) && (c < 5)) {
            ms.placeNo3Ship(i, j, c);
        }
        if (c == 4) {
            JOptionPane.showMessageDialog(gui, "You placed the second ship!");
            enableME();
        }

        if ((c > 4) && (c < 9)) {
            ms.placeNo4Ship(i, j, c);
        }
        if (c == 8) {
            JOptionPane.showMessageDialog(gui, "You placed your last ship!");
            disableME();
            enableYOU();
        }

//ELLENFEL HAJOINAK LEPAKOLASA
        if (c == 0) {
            es.placeShips();
        }
//CSATAAA
        if (c > 8) {
            //te losz
            if (statusYOU[i][j] == 0) {
                statusYOU[i][j] = -1;
                // gui.setColorOfButtonYOU(i, j, Color.WHITE);
                System.out.println("Mellé!");
            } else if (statusYOU[i][j] == 1) {
                statusYOU[i][j] = 2;
                //  gui.setColorOfButtonYOU(i, j, Color.RED);
                System.out.println("Találat!");
            }
            gui.setButtonEnabledYOU(i, j, false);
            //a gep lo
            attack(c);
        }

        youWin();
        youLost();
        coloring.coloringME();
        coloring.coloringYOU();
        System.out.print(c);
        ++c;
    }

    /**
     * 
     * Engedélyezi a kattintásokat a felhasználó térfelén	
     * 
     */
    public void enableME() {//barhova rakhatsz 
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                if (statusME[m][n] != 1) {
                    gui.setButtonEnabledME(m, n, true);
                }
            }
        }
    }

    /**
     * 
     * Engedélyezi az ellenfél térfelére való kattintást
     * 
     */
    public void enableYOU() {//barhova rakhatsz 
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                gui.setButtonEnabledYOU(m, n, true);
            }
        }
    }

    /**
     * 
     * Letiltja a felhasználó térfelére való kattintást
     * 
     */
    public void disableME() {//sehova nem rakhatsz 
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                if (statusME[m][n] != 1) {
                    gui.setButtonEnabledME(m, n, false);
                }
            }
        }
    }

    /**
     * 
     * Letiltja az ellenfél térfelére való kattintást
     * 
     */
    public void disableYOU() {//sehova nem rakhatsz 
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                gui.setButtonEnabledYOU(m, n, false);
            }
        }
    }

    /**
     * 
     * Random koordinátákat generáló függvény
     * 
     */
    public void rand(int c) {
        rx = generator.nextInt(2) + 1;
        ry = generator.nextInt(2) + 1;

    }

//ELLENFEL TAMAD
    
    /**
     * 
     * Ellenfél támadásának szimulációja
     * 
     */
    public void attack(int c) {
        int x = generator.nextInt(ROWS);
        int y = generator.nextInt(COLS);
        if (statusME[x][y] == 2 || statusME[x][y] == -1 && c != 1000) {
            attack(c);
        } else if (round > 0 && lx + rx < ROWS && ly + ry < COLS) {
            if ((statusME[lx - 1 + rx][ly - 1 + ry] == 2 || statusME[lx - 1 + rx][ly - 1 + ry] == -1) && c != 1000) {
                rand(c);
            } else if (statusME[lx - 1 + rx][ly - 1 + ry] == 1) {
                statusME[lx - 1 + rx][ly - 1 + ry] = 2;
                // gui.setColorOfButtonME(lx - 1 + rx, ly - 1 + ry, Color.RED);
            } else if (statusME[lx - 1 + rx][ly - 1 + ry] == 0) {
                statusME[lx - 1 + rx][ly - 1 + ry] = -1;
                // gui.setColorOfButtonME(lx - 1 + rx, ly - 1 + ry, Color.WHITE);
            }
            round -= 1;
        } else if (statusME[x][y] == 1) {
            statusME[x][y] = 2;
            gui.setColorOfButtonME(x, y, Color.RED);
            round = 3;
            lx = x;
            ly = y;

        } else if (statusME[x][y] == 0) {
            statusME[x][y] = -1;
            gui.setColorOfButtonME(x, y, Color.WHITE);
        }

    }

    /**
     * 
     * Nyerés ellenőrzés a felhasználó oldaláról
     * 
     */
    public void youWin() {// gyozelem ellenorzes
        boolean win = true;
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                if (statusYOU[m][n] == 1) {
                    win = false;
                }
            }
        }
        if (win) {
            JOptionPane.showMessageDialog(gui, "You win! You destroyed your enemy in " + (c - 8) + " steps!");
        }


        // System.out.println(win);
    }

    /**
     * 
     * Nyerés ellenőrzés az ellenfél oldaláról
     * 
     */
    public void youLost() {// vesztes ellenorzes
        boolean lost = true;
        for (int m = 0; m < ROWS; ++m) {
            for (int n = 0; n < COLS; ++n) {
                if (statusME[m][n] == 1) {
                    lost = false;
                }
            }
        }
        if (lost) {
            JOptionPane.showMessageDialog(gui, "You lost!");
            disableYOU();
        }
        // System.out.println(lost);
    }
}//program vege