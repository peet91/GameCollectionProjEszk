/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package VakondReboot.src.Misc;

/**
 *
 * @author R
 */
public class Position2D {
    
    private int x, y;

    public Position2D() {
        x = 0;
        y = 0;
    }

    public Position2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj.getClass() == this.getClass())
            return this.x == ((Position2D)obj).getX()
                    && this.y == ((Position2D)obj).getY();
        else
            return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
