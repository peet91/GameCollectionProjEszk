package VakondReboot.src.Logic;

import VakondReboot.src.Misc.Position2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author R
 */
public class Game {

	public enum GameState {

		RUN, END, HIT
	}

	private final int HITBORDER = 100;
	private final int MAX_SECONDS = 90;
	private final int GENERATION_SPEED = 5;
	private final int X_BORDER = 700;
	private final int Y_BORDER = 500;
	private final int MAX_BLOODY_MOLE_DOSPLAY_SECONDS = 1;

	private ArrayList<GameObject> moles;
	private int playerScore;
	private int seconds;
	private int hitSeconds;
	private int generationDelayCounter;
	private GameObject mole;
	private GameState state;

	public Game() {
		state = GameState.RUN;
		playerScore = 0;
		hitSeconds = 0;
		seconds = MAX_SECONDS;
		generationDelayCounter = 0;
		moles = new ArrayList<>();
		Random r = new Random();
		Position2D p = new Position2D(r.nextInt(X_BORDER), r.nextInt(Y_BORDER));
		mole = new GameObject(p);
	}

	public ArrayList<GameObject> getMoles() {
		return moles;
	}

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

	public void setMoles(ArrayList<GameObject> moles) {
		this.moles = moles;
	}

	// Vakond generálása random helyre
	public void generateMole() {
		Random r = new Random();
		mole.getPos().setX(r.nextInt(X_BORDER));
		mole.getPos().setY(r.nextInt(Y_BORDER));
	}

	// Vakondok törlése
	public void destroyMoles() {

	}

	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public GameObject getMole() {
		return mole;
	}

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

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getGenerationDelayCounter() {
		return generationDelayCounter;
	}

	public GameState getState() {
		return state;
	}

}