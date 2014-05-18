
package PotyogosAmoba;

import java.io.FileNotFoundException;
import javax.swing.ImageIcon;

/**
* A játék logikai működéséért felelős osztály
* @author Zoltán
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
    * @param x a tábla szélessége
    * @param y a tábla magassága
    * @param a logikai részt kezelő gui
    */
    public BeadGameLogic(BeadGameFrame gui, int x, int y) {
        this.gui = gui;
        setMeret(x, y);
        player1 = new Player("images/amoba/x.gif", 1);
        player2 = new Player("images/amoba/o.png", 2);
        szinezo = new Szinezo();
        aktPlayer = player2;
        setEnableButtons();
        gui.setStatusBar("A(z) " + aktPlayer.getNumber() + ". játékos kezd!");
    }
    /**
    * Játék betöltésénél lefutó konstruktor
    * @param fajlnev a betoltesi fájl neve
    * @param gui a logikai részt kezelő grafikus felület
    */
    public BeadGameLogic(String fajlnev, BeadGameFrame gui) throws FileNotFoundException {
        this.gui = gui;
        player1 = new Player("x.gif", 1);
        player2 = new Player("o.png", 2);
        jatekBetolt(fajlnev);

    }

    /**
    * A gomb megnyomás hatásait létrehozó függvény
    * @param i a megnyomott gomb x koordinátája
    * @param j a megnyomott gomb y koordinátája
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
    * @param i a tábla mérete széltében
    * @param j a tábla mérete hosszában
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
