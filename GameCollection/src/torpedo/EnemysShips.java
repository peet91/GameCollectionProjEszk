/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package torpedo;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author kacsa
 */
public class EnemysShips extends JFrame{
    private TorpeDoFrame gui;
    int COLS;
    int ROWS;
    int[][] statusYOU;
    private Random generator = new Random();
    private boolean b;
    
    public EnemysShips(int cols, int rows,int[][] statusYOU, TorpeDoFrame gui) {
        this.gui=gui;
        this.COLS=cols;
        this.ROWS=rows;
        this.statusYOU=statusYOU;
    }
    
    public void placeShips(){
        eNo2();
        eNo3();
        eNo4();
    }
    
    
//ELLENFÉL HAJÓI
//KETTES
    public void eNo2() {
        int x = generator.nextInt(ROWS);
        int y = generator.nextInt(COLS);
        b = generator.nextBoolean();

        if (x + 1 < COLS && y + 1 < ROWS) {
            statusYOU[x][y] = 1;

            if (b) {
                statusYOU[x + 1][y] = 1;

            } else {
                statusYOU[x][y + 1] = 1;

            }
        } else {
            eNo2();
        }
    }

    public void cheat(int x, int y) {
        gui.setColorOfButtonYOU(x, y, Color.MAGENTA);
        gui.setColorOfButtonYOU(x + 1, y, Color.GREEN);
        gui.setColorOfButtonYOU(x, y + 1, Color.YELLOW);

    }
    
    //HÁRMAS

    public void eNo3() {
        int x = generator.nextInt(ROWS);
        int y = generator.nextInt(COLS);
        b = generator.nextBoolean();

        if (x + 2 < COLS && y + 2 < ROWS && statusYOU[x][y] != 1
                && statusYOU[x + 1][y] != 1 && statusYOU[x][y + 1] != 1
                && statusYOU[x + 2][y] != 1 && statusYOU[x][y + 2] != 1) {
            statusYOU[x][y] = 1;
//            gui.setColorOfButtonYOU(x, y, Color.MAGENTA);
            if (b) {
                statusYOU[x + 1][y] = 1;
                statusYOU[x + 2][y] = 1;
//                gui.setColorOfButtonYOU(x + 1, y, Color.GREEN);
//               gui.setColorOfButtonYOU(x + 2, y, Color.GREEN);
            } else {
                statusYOU[x][y + 1] = 1;
                statusYOU[x][y + 2] = 1;
//                gui.setColorOfButtonYOU(x, y + 1, Color.YELLOW);
//                gui.setColorOfButtonYOU(x, y + 2, Color.YELLOW);
            }
        } else {
            eNo3();
        }
    }
    
    //NÉGYES

    public void eNo4() {
        int x = generator.nextInt(ROWS);
        int y = generator.nextInt(COLS);
        b = generator.nextBoolean();

        if (x + 3 < COLS && y + 3 < ROWS && statusYOU[x][y] != 1
                && statusYOU[x + 1][y] != 1 && statusYOU[x][y + 1] != 1
                && statusYOU[x + 2][y] != 1 && statusYOU[x][y + 2] != 1
                && statusYOU[x + 3][y] != 1 && statusYOU[x][y + 3] != 1) {
            statusYOU[x][y] = 1;
//            gui.setColorOfButtonYOU(x, y, Color.MAGENTA);
            if (b) {
                statusYOU[x + 1][y] = 1;
                statusYOU[x + 2][y] = 1;
                statusYOU[x + 3][y] = 1;
//                gui.setColorOfButtonYOU(x + 1, y, Color.GREEN);
//                gui.setColorOfButtonYOU(x + 2, y, Color.GREEN);
//                gui.setColorOfButtonYOU(x + 3, y, Color.GREEN);
            } else {
                statusYOU[x][y + 1] = 1;
                statusYOU[x][y + 2] = 1;
                statusYOU[x][y + 3] = 1;
//                gui.setColorOfButtonYOU(x, y + 1, Color.YELLOW);
//                gui.setColorOfButtonYOU(x, y + 2, Color.YELLOW);
//                gui.setColorOfButtonYOU(x, y + 3, Color.YELLOW);
            }
        } else {
            eNo4();
        }
    }
    
}
