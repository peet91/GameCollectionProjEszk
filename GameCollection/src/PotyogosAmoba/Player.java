package PotyogosAmoba;

import javax.swing.ImageIcon;

/**
* A j�t�kos objektum
* @author Zolt�n
*/

public class Player {

    private int number;
    private Figura figura;

    public Player(String imageNev, int number) {
        this.number = number;
        figura = new Figura();
        figura.setImage(imageNev);
        figura.setImageNev(imageNev);
    }

    public Figura getFigura() {
        return figura;
    }

    public int getNumber() {
        return number;
    }
}