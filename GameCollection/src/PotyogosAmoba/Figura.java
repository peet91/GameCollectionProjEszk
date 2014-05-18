
package PotyogosAmoba;

import javax.swing.ImageIcon;

/**
* A játékmezőben a játékost reprezentáló objektum
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
