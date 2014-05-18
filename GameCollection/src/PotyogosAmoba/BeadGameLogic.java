
package PotyogosAmoba;

import java.io.FileNotFoundException;
import javax.swing.ImageIcon;

/**
* A játék logikai működéséért felelős osztály
*/

final class BeadGameLogic {

    public static int ROWS;
    public static int COLS;
    BeadGameFrame gui;
    private ImageIcon[][] szinek;
    Player player1;
    Player player2;
    Player aktPlayer;
    Fajlkezelo kezelo;
    Szinezo szinezo;
    
    /**
    * Új játék indításánál lefutó konstruktor
    */

    public BeadGameLogic(BeadGameFrame gui, int x, int y) {
        this.gui = gui;
        setMeret(x, y);
        player1 = new Player("x.gif", 1);
        player2 = new Player("o.png", 2);
        szinezo = new Szinezo();
        aktPlayer = player2;
        setEnableButtons();
        gui.setStatusBar("A(z) " + aktPlayer.getNumber() + ". játékos kezd!");
    }
    /**
    * Játék betöltésénél lefutó konstruktor
    */

    public BeadGameLogic(String fajlnev, BeadGameFrame gui) throws FileNotFoundException {
        this.gui = gui;
        player1 = new Player("x.gif", 1);
        player2 = new Player("o.png", 2);
        jatekBetolt(fajlnev);

    }

    /**
    * A gomb megnyomás hatásait létrehozó függvény
    */

    public void buttonPressed(int i, int j) {
        insertButton(i, j);
        szinezo.szinez(ROWS, szinek, gui);
        Biro biro = new Biro();
        biro.win(i, j, szinek, gui, ROWS, aktPlayer);
        setAllButtonDisable();
        setEnableButtons();
        nextPlayer();
    }

    /**
    * Aktív gombok beállítása
    */

    public void setEnableButtons() {
        boolean b = true;
        for (int j = 0; j < COLS; ++j) {
            b = true;
            for (int i = ROWS - 1; i >= 0 && b; --i) {
                b = !(szinek[i][j] == null);
                gui.setButton(i, j, !b);
            }
        }

    }

    /**
    * Tábla összes gombjának inaktívvá állítása
    */

    private void setAllButtonDisable() {
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                gui.setButton(i, j, false);
            }
        }
    }

    private void insertButton(int x, int y) {
        szinek[x][y] = aktPlayer.getFigura().getImage();
    }


    /**
    * Következő játékos meghatározása
    */
    
    private void nextPlayer() {
        if (aktPlayer == player1) {
            aktPlayer = player2;
        } else {
            aktPlayer = player1;
        }
        gui.setStatusBar("A(z) " + aktPlayer.getNumber() + ". játékos következik!");
    }

    /**
    * Tábla méretének beállítása
    */
    
    public void setMeret(int i, int j) {
        ROWS = i;
        COLS = j;
        szinek = new ImageIcon[ROWS][COLS];
    }

    /**
    * Kezdőállípot visszaállítása
    */

    public void reset() {
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                szinek[i][j] = null;
            }
        }
        szinezo.szinez(ROWS, szinek, gui);
    }

    /**
    * Fájlkezelőnek adatok átadása
    */

    void jatekMentese(String fajlnev) throws FileNotFoundException {
        if (kezelo == null) {
            kezelo = new Fajlkezelo(this);
        }
        kezelo.Mentes(fajlnev, player1.getFigura().getImageNev(), player2.getFigura().getImageNev(), aktPlayer.getNumber(), ROWS, COLS, szinek);
    }

    /**
    * Betöltési beállítások
    */
    
    void jatekBetolt(String fajlnev) throws FileNotFoundException {
        if (kezelo == null) {
            kezelo = new Fajlkezelo(this);
        }
        kezelo.Betoltes(fajlnev);
    }

    public void setPlayer1Image(String imageNev) {
        player1.getFigura().setImage(imageNev);
    }

    public void setPlayer2Image(String imageNev) {
        player2.getFigura().setImage(imageNev);
    }

    public void setAktPlayer(int szam) {
        if (player1.getNumber() == szam) {
            aktPlayer = player1;
        } else {
            aktPlayer = player2;
        }
    }

    public void setSzinek(int i, int j, ImageIcon image) {
        szinek[i][j] = image;
    }

    void szinezes() {
        szinezo.szinez(ROWS, szinek, gui);
    }
}
