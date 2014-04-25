/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package VakondReboot.src.Logic;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author R
 */
public class ScoreHandler {
    private ArrayList<ScoreAtom> scores;

    public ScoreHandler() {
        scores = new ArrayList<>();
    }

    public ArrayList<ScoreAtom> getScores() {
        return scores;
    }
    
    public void sort(){
        Collections.sort(scores);
    }
}
