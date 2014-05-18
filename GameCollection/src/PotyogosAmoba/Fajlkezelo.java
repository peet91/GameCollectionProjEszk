
package PotyogosAmoba;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import javax.swing.ImageIcon;

/**
* A játék mentéséért és betöltéséért felelős osztály
* @author Zoltán
*/
public class Fajlkezelo {

    private String beFajlnev;
    private String kiFajlnev;
    private BeadGameLogic kezeltJatek;

    public Fajlkezelo(BeadGameLogic kezeltJatek) {
        this.kezeltJatek = kezeltJatek;
    }

    /**
    * Aktuális állapot mentése
    */
    public void Mentes(String kiFajlnev, String elsojatekosKep, String masodikjatekosKep, int aktJatekos, int sorok, int oszlopok, ImageIcon[][] elemek) throws FileNotFoundException {
        this.kiFajlnev = kiFajlnev;
        FileOutputStream os = new FileOutputStream(kiFajlnev);
        PrintStream ps = new PrintStream(os);

        ps.println(elsojatekosKep + " " + masodikjatekosKep + " " + aktJatekos + " " + " " + sorok + " " + oszlopok);
        int k = 0;
        int c = 0;
        while (k < sorok) {
            while (c < oszlopok) {
                ps.print(elemek[k][c] + " ");
                c++;
            }
            ps.print("\n");
            c = 0;
            k++;
        }



    }
    
    /**
    * Aktuális állapot betöltése
    */

    public void Betoltes(String fajlnev) throws FileNotFoundException {
        File file = new File(fajlnev);
        Scanner sc = new Scanner(file);
        kezeltJatek.setPlayer1Image(sc.next());
        kezeltJatek.setPlayer2Image(sc.next());
        kezeltJatek.setAktPlayer(sc.nextInt());
        int sorok = sc.nextInt();
        int oszlopok = sc.nextInt();

        kezeltJatek.setMeret(sorok, oszlopok);

        ImageIcon[][] matrix = new ImageIcon[sorok][oszlopok];
        int k = 0;
        int c = 0;
        String image = null;
        while (sc.hasNext()) {
            image = sc.next();
            if (!image.equals("null")) {
                kezeltJatek.setSzinek(k, c, new ImageIcon(image));
                image = null;
            }
            c++;
            if (c == oszlopok) {
                c = 0;
                k++;
            }
        }
    }
}
