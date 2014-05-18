package VakondReboot.src.Logic;

import VakondReboot.src.Misc.Position2D;
import java.util.Random;

/**
 * 
 * @author R
 * A játék osztálya
 */

public class Game {

	/*
	 * public enum GameState:
	 * A játék állapotait reprezentáló felsoroló típus.
	 */
	
	/**
	 * @author R
	 *
	 */
	public enum GameState {

		RUN, END, HIT
	}

	private final int HITBORDER = 100; //a találati mező mérete
	private final int MAX_SECONDS = 90; //a maximális idő
	private final int GENERATION_SPEED = 5; //a vakondok generálásnak 
	private final int X_BORDER = 700; // a játékmező szélessége
	private final int Y_BORDER = 500; // -II- magassága
	private final int MAX_BLOODY_MOLE_DOSPLAY_SECONDS = 1; //max meddig mutathatja a lelőtt vakondot

	private int playerScore;
	private int seconds;
	private int hitSeconds;
	private int generationDelayCounter;
	private GameObject mole; // a vakond
	private GameState state; // 

	/**
	 * 
	 */
	public Game() {
		state = GameState.RUN;
		playerScore = 0;
		hitSeconds = 0;
		seconds = MAX_SECONDS;
		generationDelayCounter = 0;
		Random r = new Random();
		Position2D p = new Position2D(r.nextInt(X_BORDER), r.nextInt(Y_BORDER));
		mole = new GameObject(p);
	}

	/**
	 * 
	 */
	public void update() {
		switch (state) {
		case RUN:
			if (seconds > 0) {
				++generationDelayCounter;
				if (generationDelayCounter >= GENERATION_SPEED) {
					generationDelayCounter = 0;
					generateMole();
				}
			} else
				state = GameState.END;
			break;
		case HIT:
			if (hitSeconds - seconds >= MAX_BLOODY_MOLE_DOSPLAY_SECONDS) {
				hitSeconds = 0;
				state = GameState.RUN;
				generateMole();
			}
			break;
		}
	}

	// Vakond generálása random helyre
	/**
	 * 
	 */
	public void generateMole() {
		Random r = new Random();
		mole.getPos().setX(r.nextInt(X_BORDER));
		mole.getPos().setY(r.nextInt(Y_BORDER));
	}

	/**
	 * @return
	 */
	public int getPlayerScore() {
		return playerScore;
	}

	/**
	 * @param playerScore
	 */
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	/**
	 * @return
	 */
	public GameObject getMole() {
		return mole;
	}

	/**
	 * @param mx
	 * @param my
	 * @param iconHeight
	 * @param iconWidth
	 * @return
	 */
	public boolean hitMole(double mx, double my, int iconHeight, int iconWidth) {
		if (state == GameState.RUN) {
			if (mx >= mole.getPos().getX()
					&& mx <= mole.getPos().getX() + iconWidth
					&& my >= mole.getPos().getY()
					&& my <= mole.getPos().getY() + iconHeight) {
				++playerScore;
				state = GameState.HIT;
				hitSeconds = seconds;
				return true;
			}
		}
		return false;
	}

	/**
	 * @return
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * @param seconds
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	/**
	 * @return
	 */
	public int getGenerationDelayCounter() {
		return generationDelayCounter;
	}

	/**
	 * @return
	 */
	public GameState getState() {
		return state;
	}

}