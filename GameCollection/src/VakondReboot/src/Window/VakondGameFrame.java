/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VakondReboot.src.Window;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author R
 */
public class VakondGameFrame extends JFrame{

	public final int WINDOW_WIDTH = 800;
	public final int WINDOW_HEIGHT = 600;
	private GrassPanel panel;
	private final String TITLE = "Vakond Killer 2000 Ultimate";

	public VakondGameFrame() {
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE);
		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		setLayout(new BorderLayout());
		panel = new GrassPanel();
		panel.setX_BOUNDARY(WINDOW_WIDTH);
		panel.setY_BOUNDARY(WINDOW_HEIGHT);
		getContentPane().add(panel, BorderLayout.CENTER);
		new JLabel();
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
