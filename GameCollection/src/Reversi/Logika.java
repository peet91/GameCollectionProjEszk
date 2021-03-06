package Reversi;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public final class Logika {

    private int meret;
    private Grafika gui;
    private int[][] colors;
    private int kor;
    private boolean feherPasszol;
    private boolean feketePasszol;
    /**
    * Getter for kor attribute
    * @return the kor attribute
    */
    public int getKor() {
        return kor;
    }
    /**
    * Getter for meret attribute
    * @return the meret attribute
    */
    public int getMeret() {
        return meret;
    }
    /**
    * Initializes the Logika class
    * sets the size of the play field
    * sets the fields to the default color
    * @param gui the Grafika class
    * @param meret the size of the play area
    * @param kor the actual round number
    */
    public Logika(Grafika gui, int meret, int kor) {
        this.meret = meret;
        this.gui = gui;
        this.colors = new int[meret][meret];
        for (int i = 0; i < meret; ++i) {
            for (int j = 0; j < meret; ++j) {
                colors[i][j] = 0;
            }
        }

        feherPasszol = false;
        feketePasszol = false;
        this.kor = kor;
    }
    //Reversi logika: egyik player jön,aztán a másik player jön,eventmanager,
    //+gomb hogy ki jön,kezdőhelyzet,hova rakhat a következő játékos
    //hely ellenőrzése,
    /**
    * Sets the colors array to the
    * starting configuration 
    * sets the center fields according to
    * the rules of Reversi
    */
    public void kezdes() {
        //kezdőhelyzet:
        colors[meret / 2 - 1][meret / 2 - 1] = 2;
        colors[meret / 2][meret / 2 - 1] = 1;
        colors[meret / 2 - 1][meret / 2] = 1;
        colors[meret / 2][meret / 2] = 2;
        gombKirajzol();
        showLegalMoves(kor % 2 == 0 ? 2 : 1);
    }
    /**
    * reads the colors array, and sets the buttons 
    * in the gui to the colors of the array.
    */
    public void gombKirajzol() {
        for (int i = 0; i < meret; ++i) {
            for (int j = 0; j < meret; ++j) {
                if (colors[i][j] == 1) {
                    gui.setGombSzin(i, j, Color.black);
                } else if (colors[i][j] == 2) {
                    gui.setGombSzin(i, j, Color.white);
                }
                gui.setGombState(i, j, false);
            }
        }
    }
    /**
    * Shows the legal moves on the playing field
    * @param szin the color of the actual player
    * @return a boolean value containig whether the player has legal moves
    */
    public boolean showLegalMoves(int szin) {
        boolean has = false;
        int aszin;
        if (szin == 2) {
            aszin = 1;
        } else {
            aszin = 2;
        }
        for (int i = 0; i < meret; ++i) {
            for (int j = 0; j < meret; ++j) {
                boolean h = colors[i][j] == aszin;
                if (h) {
                    h = colors[i][j] == aszin;
                    int k = j;
                    //jobbra
                    while (h && k < meret - 1) {
                        h = h && colors[i][++k] == aszin;
                    }
                    if (j != 0) {
                        if (colors[i][k] == szin && colors[i][j - 1] == 0) {
                            gui.setGombState(i, j - 1, true);
                            has = true;
                        }
                    }
                    h = colors[i][j] == aszin;
                    k = j;
                    //balra
                    while (h && k > 0) {
                        h = h && colors[i][--k] == aszin;
                    }
                    if (j != meret - 1) {
                        if (colors[i][k] == szin && colors[i][j + 1] == 0) {
                            gui.setGombState(i, j + 1, true);
                            has = true;
                        }
                    }
                    h = colors[i][j] == aszin;
                    k = i;
                    //le
                    while (h && k < meret - 1) {
                        h = h && colors[++k][j] == aszin;
                    }
                    if (i != 0) {
                        if (colors[k][j] == szin && colors[i - 1][j] == 0) {
                            gui.setGombState(i - 1, j, true);
                            has = true;
                        }
                    }
                    h = colors[i][j] == aszin;
                    k = i;
                    //fel
                    while (h && k > 0) {
                        h = h && colors[--k][j] == aszin;
                    }
                    if (i != meret - 1) {
                        if (colors[k][j] == szin && colors[i + 1][j] == 0) {
                            gui.setGombState(i + 1, j, true);
                            has = true;
                        }
                    }
                    h = colors[i][j] == aszin;
                    k = i;
                    int l = j;
                    //jobbfel
                    while (h && k > 0 && l < meret - 1) {
                        h = h && colors[--k][++l] == aszin;
                    }
                    if (i != meret - 1 && j != 0) {
                        if (colors[k][l] == szin && colors[i + 1][j - 1] == 0) {
                            gui.setGombState(i + 1, j - 1, true);
                            has = true;
                        }
                    }
                    h = colors[i][j] == aszin;
                    k = i;
                    l = j;
                    //jobble
                    while (h && k < meret - 1 && l < meret - 1) {
                        h = h && colors[++k][++l] == aszin;
                    }
                    if (i != 0 && j != 0) {
                        if (colors[k][l] == szin && colors[i - 1][j - 1] == 0) {
                            gui.setGombState(i - 1, j - 1, true);
                            has = true;
                        }
                    }
                    h = colors[i][j] == aszin;
                    k = i;
                    l = j;
                    //balfel
                    while (h && k > 0 && l > 0) {
                        h = h && colors[--k][--l] == aszin;
                    }
                    if (i != meret - 1 && j != meret - 1) {
                        if (colors[k][l] == szin && colors[i + 1][j + 1] == 0) {
                            gui.setGombState(i + 1, j + 1, true);
                            has = true;
                        }
                    }
                    h = colors[i][j] == aszin;
                    k = i;
                    l = j;
                    //balle
                    while (h && k < meret - 1 && l > 0) {
                        h = h && colors[++k][--l] == aszin;
                    }
                    if (i != 0 && j != meret - 1) {
                        if (colors[k][l] == szin && colors[i - 1][j + 1] == 0) {
                            gui.setGombState(i - 1, j + 1, true);
                            has = true;
                        }
                    }
                }
            }
        }
        return has;
    }
    /**
    * Pushes the button on the gui
    * @param i the number of the row on the playing field
    * @param j the number of the column on the playing field
    * @param szin the color of the actual player
    */
    public void gombNyomva(int i, int j, int szin) {
        colors[i][j] = szin;
        if (szin == 2) {
            gui.setGombSzin(i, j, Color.white);
        } else {
            gui.setGombSzin(i, j, Color.black);
        }
        fordit(i, j, szin);
        for (int k = 0; k < meret; ++k) {
            for (int l = 0; l < meret; ++l) {
                gui.setGombState(k, l, false);
            }
        }
    }
    /**
    * Turns the buttons on the playing field according
    * to the turned button by the actual player
    * @param i the number of the row of the turned button by the actual player
    * @param j the number of the column of the turned button by the actual player
    * @param szin the color of the actual player
    */
    private void fordit(int i, int j, int szin) {
        int aszin;
        Color mire;
        if (szin == 2) {
            aszin = 1;
            mire = Color.white;
        } else {
            aszin = 2;
            mire = Color.black;
        }
        boolean h = colors[i][j] == szin;
        if (h) {
            int k = j;
            //jobbra
            while (h && k < meret - 1) {
                h = h && colors[i][++k] == aszin;
            }
            if (colors[i][k] == szin) {
                for (int tmp = j; tmp < k; tmp++) {
                    gui.setGombSzin(i, tmp, mire);
                    colors[i][tmp] = szin;
                }
            }
            h = colors[i][j] == szin;
            k = j;
            //balra
            while (h && k > 0) {
                h = h && colors[i][--k] == aszin;
            }
            if (colors[i][k] == szin) {
                for (int tmp = j; tmp > k; tmp--) {
                    gui.setGombSzin(i, tmp, mire);
                    colors[i][tmp] = szin;
                }
            }
            h = colors[i][j] == szin;
            k = i;
            //fel
            while (h && k > 0) {
                h = h && colors[--k][j] == aszin;
            }
            if (colors[k][j] == szin) {
                for (int tmp = i; tmp > k; tmp--) {
                    gui.setGombSzin(tmp, j, mire);
                    colors[tmp][j] = szin;
                }
            }
            h = colors[i][j] == szin;
            k = i;
            //le
            while (h && k < meret - 1) {
                h = h && colors[++k][j] == aszin;
            }
            if (colors[k][j] == szin) {
                for (int tmp = i; tmp < k; tmp++) {
                    gui.setGombSzin(tmp, j, mire);
                    colors[tmp][j] = szin;
                }
            }
            h = colors[i][j] == szin;
            k = i;
            int l = j;
            //jobbfel
            while (h && k > 0 && l < meret - 1) {
                h = h && colors[--k][++l] == aszin;
            }
            if (colors[k][l] == szin) {
                for (int tmp = 1; tmp < l - j; tmp++) {
                    gui.setGombSzin(i - tmp, j + tmp, mire);
                    colors[i - tmp][j + tmp] = szin;
                }
            }
            h = colors[i][j] == szin;
            k = i;
            l = j;
            //jobble
            while (h && k < meret - 1 && l < meret - 1) {
                h = h && colors[++k][++l] == aszin;
            }
            if (colors[k][l] == szin) {
                for (int tmp = 1; tmp < l - j; tmp++) {
                    gui.setGombSzin(i + tmp, j + tmp, mire);
                    colors[i + tmp][j + tmp] = szin;
                }
            }
            h = colors[i][j] == szin;
            k = i;
            l = j;
            //balfel
            while (h && k > 0 && l > 0) {
                h = h && colors[--k][--l] == aszin;
            }
            if (colors[k][l] == szin) {
                for (int tmp = 1; tmp < j - l; tmp++) {
                    gui.setGombSzin(i - tmp, j - tmp, mire);
                    colors[i - tmp][j - tmp] = szin;
                }
            }
            h = colors[i][j] == szin;
            k = i;
            l = j;
            //balle
            while (h && k < meret - 1 && l > 0) {
                h = h && colors[++k][--l] == aszin;
            }
            if (colors[k][l] == szin) {
                for (int tmp = 1; tmp < j - l; tmp++) {
                    gui.setGombSzin(i + tmp, j - tmp, mire);
                    colors[i + tmp][j - tmp] = szin;
                }
            }
        }
    }
    /**
    * Sets the next round according to the played field
    */
    public void kovetkezoKor() {
        kor++;
        if (feherPasszol && feketePasszol) {
            gui.vege();
            return;
        }
        boolean tmp = showLegalMoves(kor % 2 == 0 ? 2 : 1);
        if (!tmp) {
            if (kor % 2 == 0) {
                feherPasszol = true;
            } else {
                feketePasszol = true;
            }
            gui.setCim(0);
            kovetkezoKor();
        } else {
            if (kor % 2 == 0) {
                feherPasszol = false;
                gui.setCim(2);
            } else {
                feketePasszol = false;
                gui.setCim(1);
            }
        }
    }
    /**
    * Computes the winner player
    * @return the number of the winner player
    */
    public int kiNyert() {
        int fekete = 0;
        int feher = 0;
        for (int i = 0; i < meret; ++i) {
            for (int j = 0; j < meret; ++j) {
                if (colors[i][j] == 1) {
                    fekete++;
                } else if (colors[i][j] == 2) {
                    feher++;
                }
            }
        }
        if (fekete > feher) {
            return 1;
        } else if (fekete < feher) {
            return 2;
        } else {
            return 0;
        }
    }
    /**
    * Loads a saved game state from a file
    * @param file the name of the file relative of the main class
    */
    public void betolt(File file) {
        try {
            Scanner in = new Scanner(file);
            this.meret = in.nextInt();
            this.kor = in.nextInt();
            for (int i = 0; i < meret; ++i) {
                for (int j = 0; j < meret; ++j) {
                    this.colors[i][j] = in.nextInt();
                }
            }
            gombKirajzol();
            showLegalMoves(kor % 2 == 0 ? 2 : 1);
            gui.setCim(kor%2==0?2:1);
        } catch (FileNotFoundException ex) {
            System.out.println("Nem találom a fájlt.");
        }

    }
    /**
    * Checks the syntax of a saved file.
    * @param file the name of the file relative of the main class
    * @return boolean value containing whether the syntax being correct in the file
    */
    public static boolean ellenoriz(File file) {
        try {
            Scanner in = new Scanner(file);
            if(!in.hasNextInt()){
                return false;
            }
            int meretTest = in.nextInt();
            if (!(in.hasNextInt() && (meretTest == 10 || meretTest == 20 || meretTest == 30))) {
                return false;
            }
            int korTest = in.nextInt();
            if (!(in.hasNextInt() && korTest > 0)) {
                return false;
            }
            int elem;
            int szamlalo = 0;
            while (in.hasNextInt()) {
                elem = in.nextInt();
                if (!(elem == 0 || elem == 1 || elem == 2)) {
                    return false;
                }
                szamlalo++;
            }
            if (szamlalo != (meretTest * meretTest)) {
                return false;
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Nem találom a fájlt.");
            return false;
        }
    }
    /**
    * Saves the current state of the game
    * @param file the name of the file relative of the main class
    */
    public void elment(File file) {
        try {
            PrintWriter out = new PrintWriter(file);
            out.print(this.meret + " ");
            out.print(this.kor + " \n");
            for (int i = 0; i < this.meret; ++i) {
                for (int j = 0; j < this.meret; ++j) {
                    out.print(colors[i][j] + " ");
                }
                out.print("\n");
            }
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Nem találom a fájlt.");
        }
    }
    /**
    * Gets the size of the playing field from a saved state file
    * @param file the name of the file relative of the main class
    * @return the size of the field
    */
    public static int getSizeFromFile(File file) {
        try {
            Scanner in = new Scanner(file);
            return in.nextInt();
        } catch (FileNotFoundException ex) {
            System.out.println("Nem találom a fájlt.");
            return 0;
        }
    }
    /**
    * Gets the number of the actual round from the file
    * @param file the name of the file relative of the main class
    * @return the number of the actual round
    */
    public static int getKorFromFile(File file) {
        try {
            Scanner in = new Scanner(file);
            int kor = in.nextInt();
            return in.nextInt();
        } catch (FileNotFoundException ex) {
            System.out.println("Nem találom a fájlt.");
            return 0;
        }
    }
}
