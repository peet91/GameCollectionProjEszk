/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vakond.Window;

import Vakond.Logic.Game;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author R
 */
public class GameFrame extends JFrame{

	public final int WINDOW_WIDTH = 800;
	public final int WINDOW_HEIGHT = 600;
	private JLabel mouseCoordsL;
	private GrassPanel panel;

	public GameFrame() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		setLayout(new BorderLayout());
		panel = new GrassPanel();
		panel.setX_BOUNDARY(WINDOW_WIDTH);
		panel.setY_BOUNDARY(WINDOW_HEIGHT);
		getContentPane().add(panel, BorderLayout.CENTER);
		mouseCoordsL = new JLabel();
		setVisible(true);
		
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		getContentPane().setCursor(blankCursor);
	}
}
