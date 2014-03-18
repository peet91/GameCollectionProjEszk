/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PotyogosAmoba.bead;

import javax.swing.ImageIcon;

/**
 *
 * @author Buci
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