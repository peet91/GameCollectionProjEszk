package FourGame.Logic;

import java.io.Serializable;

/**
 * A játéklogikát megvalósító osztály
 * @author antalpeter
 *
 */

public class FourGameLogic implements Serializable {
    private int blocks[][];
    private boolean playerOneBlocks[][];
    private boolean playerTwoBlocks[][];
    private int size;
    private int p1score,p2score;
    public boolean p1active, p2active, success;
    
    public FourGameLogic(int _size) {
        this.size = _size;
        blocks = new int[_size][_size];
        playerOneBlocks = new boolean[_size][_size];
        playerTwoBlocks = new boolean[_size][_size];  
    }
    
    /**
     * Lekérdezi az adott blokkot
     * @param x
     * @param y
     * @return
     */
     public int getBlock(int x, int y) {
        return blocks[x][y];
    }
     
    /**
     * Az egyes blokkok "növeléséért" felelős  függvény
     * @param x
     * @param y
     */
    public void setBlock(int x, int y) {
        setSize(x, y);
        
        if (success) {
        setSize(x + 1, y);
        setSize(x - 1, y);
        setSize(x, y + 1);
        setSize(x, y - 1);
        
        setActivePlayer();
        }
    }
    
    /**
     * A pálya méretét beállító függvény
     * @param x
     * @param y
     */
    public void setSize(int x, int y) {
        if (x >= 0 && y >= 0 && x < this.size && y < this.size) {
            if (blocks[x][y] < 4) {
                blocks[x][y]++;
                success = true;
                if (blocks[x][y] == 4) {
                    if(p1active) {
                        playerOneBlocks[x][y] = true;
                    }
                    else if(p2active){
                        playerTwoBlocks[x][y] = true;
                    }
                }
            }
            else success = false;
        }
    }
    public int getSize() {
        return this.size;
    }
    /**
     * Az 1-es játékos pontszámát lekérdező függvény
     * @return
     */
    public int getPlayerOneScore() {
        this.p1score = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (playerOneBlocks[i][j]) {
                    this.p1score++;
                }
            }
        }
        return p1score;
    }
    /**
     * A 2-es játékos pontszámát lekérdező függvény
     * @return
     */
    public int getPlayerTwoScore() {
        this.p2score = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (playerTwoBlocks[i][j]) {
                    this.p2score++;
                }
            }
        }
        return p2score;
    }
    /**
     * Az 1-es játékos által megszerzett blokkokat lekérdező függvény
     * @param i
     * @param j
     * @return
     */
    public boolean getPlayerOneBlock(int i, int j) {
        return playerOneBlocks[i][j];
    }
    /**
     * A 2-es játékos által megszerzett blokkokat lekérdező függvény
     * @param i
     * @param j
     * @return
     */
    public boolean getPlayerTwoBlock(int i, int j) {
        return playerTwoBlocks[i][j];
    }
    /**
     * Vizsgálja hogy végetért-e a játék
     * @return
     */
    public boolean isGameEnded() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (blocks[i][j] != 4) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Lekérdezi az aktív játékos
     * @return
     */
    public String getActivePlayer() {
        if (p1active == true) {
            return "p1";
        }
        else{
            return "p2";
        }
    }
    /**
     * Váltogatja az aktív játékost
     */
    public void setActivePlayer() {
        if (p1active == true) {
            p1active = false;
            p2active = true;
        }
        else {
            p1active = true;
            p2active = false;
        }
    }
    
}
