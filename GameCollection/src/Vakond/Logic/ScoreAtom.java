/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vakond.Logic;

/**
 *
 * @author R
 */
public class ScoreAtom implements Comparable<ScoreAtom> {

    private String name;
    private int score;

    public ScoreAtom() {
    }

    public ScoreAtom(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(ScoreAtom o) {
        if (o != null) {
            if (o.getScore() > this.score) {
                return -1;
            } else if (o.getScore() < this.score) {
                return 1;
            } else if (o.getScore() == this.score)
                return 0;
        }
        return 0;
    }
}
