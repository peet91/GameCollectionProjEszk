/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dodgemgamee.gui;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JButton;

/**
 * Ez valositja meg a kepen lathato teruleteket, amikre lehet kattintani
 * @author Akos
 */
public class ButtonNumber extends JButton{
    private Point pnt;
    /**
     * A konstruktora
     * @param x az x koordinata
     * @param y az y koordinata
     */
    public ButtonNumber(int x, int y)
    {
        super();
        pnt = new Point(x, y);
        setBackground(Color.red);
    }
    
  
    public int getPntX()
    {
        return pnt.x;
    }
    
 
    public int getPntY()
    {
        return pnt.y;
    }
        
}