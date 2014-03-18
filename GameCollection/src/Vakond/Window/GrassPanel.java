package Vakond.Window;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Vakond.Logic.Game;

@SuppressWarnings("serial")
public class GrassPanel extends JPanel implements MouseListener {

	private TreeMap<String, ImageIcon> images;
	private Timer drawTimer;
	private JLabel scoreLabel;
	private JLabel secondsLabel;
	private JPanel northPanel;

	private Game game;
	private Timer moleTimer;
	private Timer gameTimer;

	private int X_BOUNDARY, Y_BOUNDARY;

	public GrassPanel() {
		// TODO Auto-generated constructor stub
		game = new Game();
		loadImages();
		initTimers();

		scoreLabel = new JLabel();
		secondsLabel = new JLabel();

		setLayout(new BorderLayout());
		northPanel = new JPanel(new GridLayout(1, 2));
		northPanel.add(scoreLabel);
		northPanel.add(secondsLabel);
		add(northPanel, BorderLayout.NORTH);
		addMouseListener(this);
	}

	private void loadImages() {
		ImageIcon ic = new ImageIcon("mole.png");
		images = new TreeMap<>();
		images.put("MOLE", ic);
		ic = new ImageIcon("hof.png");
		images.put("HOF", ic);
		ic = new ImageIcon("bloody_mole.png");
		images.put("MOLE_BLOOD", ic);
		ic = new ImageIcon("hatter.jpg");
		images.put("BCKGRND", ic);
		ic = new ImageIcon("crosshair_small.png");
		images.put("CHR", ic);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g); // To change body of generated methods, choose Tools |
						// Templates.
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(images.get("BCKGRND").getImage(), 0, 50, null);
		switch (game.getState()) {
		case RUN:
			g2d.drawImage(images.get("MOLE").getImage(), game.getMole()
					.getPos().getX(), game.getMole().getPos().getY(), null);
			scoreLabel.setText("Score: " + game.getPlayerScore() + " pts");
			break;
		case HIT:
			g2d.drawImage(images.get("MOLE_BLOOD").getImage(), game.getMole()
					.getPos().getX(), game.getMole().getPos().getY(), null);
			scoreLabel.setText("Score: " + game.getPlayerScore() + " pts");
			break;
		case END:
			break;
		}
		drawCrossHair(g2d);
	}

	private void drawCrossHair(Graphics2D g2d) {
		if (getMousePosition() != null) {
			double mx, my;
			mx = getMousePosition().getX();
			my = getMousePosition().getY();
			g2d.drawImage(images.get("CHR").getImage(), (int) mx, (int) my,
					null);
		}
	}

	private ActionListener drawActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	};

	private ActionListener secondsActionL = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (game.getState() == Game.GameState.RUN
					|| game.getState() == Game.GameState.HIT) {
				game.setSeconds(game.getSeconds() - 1);
				secondsLabel.setText("Time: " + game.getSeconds() + " s");
			}
		}
	};

	private ActionListener gameActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			game.update();
		}
	};

	private void initTimers() {
		moleTimer = new Timer(500, gameActionListener);
		gameTimer = new Timer(1000, secondsActionL);
		drawTimer = new Timer(10, drawActionListener);

		drawTimer.start();
		moleTimer.start();
		gameTimer.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		double mx, my;
		mx = getMousePosition().getX();
		my = getMousePosition().getY();

		if (game.hitMole(mx, my, images.get("MOLE").getImage().getHeight(null),
				images.get("MOLE").getImage().getWidth(null))) {
			gameActionListener.actionPerformed(null);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	// setter getter
	public void setX_BOUNDARY(int x_BOUNDARY) {
		X_BOUNDARY = x_BOUNDARY;
	}

	public void setY_BOUNDARY(int y_BOUNDARY) {
		Y_BOUNDARY = y_BOUNDARY;
	}
}
