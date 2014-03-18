/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vakond.Logic;

import Vakond.Misc.Position2D;

/**
 *
 * @author R
 */
public class GameObject {
    private Position2D pos;

    public GameObject() {
        pos = new Position2D(0, 0);
    }

    public GameObject(Position2D pos) {
        this.pos = pos;
    }

    public Position2D getPos() {
        return pos;
    }

    public void setPos(Position2D pos) {
        this.pos = pos;
    }
}
