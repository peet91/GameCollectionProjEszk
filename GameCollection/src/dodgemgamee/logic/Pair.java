/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dodgemgamee.logic;

/**
 * egy koordinatat megvalosito par osztaly
 * @author Akos
 *
 */
class Pair {
    private int x;
    private int y;
    
    public Pair(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }

    void setX(int i) {
        this.x=i;
    }

    void setY(int i) {
        this.y=i;
    }
            
            
}
