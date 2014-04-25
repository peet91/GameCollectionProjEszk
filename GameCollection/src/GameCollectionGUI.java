import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import torpedo.TorpeDoFrame;
import FourGame.GUI.FourGameGUI;
import PotyogosAmoba.BeadGameFrame;
import Reversi.Grafika;
import VakondReboot.src.Window.VakondGameFrame;
import dodgemgamee.gui.DodgemGameFrame;

//GUI
//Author: R.
public class GameCollectionGUI extends JFrame{
	
	private enum GameType{DODGEM, FOUR, AMOBA, REVERSI, TORPEDO, VAKOND};
	private JPanel gameSelectionPanel;
	private final int WIDTH = 410;
	private final int HEIGHT = 400;
	
	public GameCollectionGUI() { //R:
		//Inicializálás
		gameSelectionPanel = new JPanel();
		gameSelectionPanel.setLayout(new BoxLayout(gameSelectionPanel, BoxLayout.PAGE_AXIS));
		setSize(WIDTH, HEIGHT);
		buildGameSelectionPanel();
	}
	
	private void buildGameSelectionPanel(){
		//Gombok elhelyezése
		createButtonForGame("Images/Collection/b_dodgem.jpg", GameType.DODGEM);
		createButtonForGame("Images/Collection/b_fourgame.jpg", GameType.FOUR);
		createButtonForGame("Images/Collection/b_amoba.jpg", GameType.AMOBA);
		createButtonForGame("Images/Collection/b_reversi.jpg", GameType.REVERSI);
		createButtonForGame("Images/Collection/b_torpedo.jpg", GameType.TORPEDO);
		createButtonForGame("Images/Collection/b_vakond.jpg", GameType.VAKOND);
		getContentPane().add(gameSelectionPanel);
	}
	
	private void createButtonForGame(String imagePath, final GameType gameType){
		JButton button = new JButton(new ImageIcon(imagePath));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(gameType){
				case DODGEM:
					try {
						DodgemGameFrame gf = new DodgemGameFrame(10);
				        gf.setVisible(true);
					}
					catch(Exception ex){
						System.err.println("Ma nincs d�dzsem.");
					}
					break;
				case AMOBA:
					new BeadGameFrame().setVisible(true);
					break;
				case FOUR:
					new FourGameGUI().setVisible(true);
					break;
				case REVERSI:
					new Grafika().setVisible(true);
					break;
				case TORPEDO:
					new TorpeDoFrame().setVisible(true);
					break;
				case VAKOND:
					new VakondGameFrame();
					break;
				default:
					break;
				}
			}
		});
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setPreferredSize(new Dimension(10, 10));
		gameSelectionPanel.add(button);
		//gameSelectionPanel.add(Box.createRigidArea(new Dimension()));
	}
}
