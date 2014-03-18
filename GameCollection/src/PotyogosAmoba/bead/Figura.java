/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PotyogosAmoba.bead;

import javax.swing.ImageIcon;

/**
 *
 * @author Zoltán
 */
public class Figura {

    private ImageIcon image;
    private String imageNeve;

    void setImageNev(String imageNev) {
        this.imageNeve = imageNev;
    }

    void setImage(String imageNev) {
        this.image = new ImageIcon(imageNev);
    }

    ImageIcon getImage() {
        return image;
    }

    String getImageNev() {
        return imageNeve;
    }
}
