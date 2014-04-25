package FourGame.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import FourGame.Logic.FourGameLogic;

public class FourGameGUI extends JFrame {
    private JPanel form;
    private JPanel scoreBoard;
    private JButton[][] buttons;
    private JLabel[] scoreBoardArray;
    private FourGameLogic fourGameLogic;
    
    public FourGameGUI() {
        setTitle("FourGame");
        setLocation(40, 30);
        setLayout(new BorderLayout());
        form = new JPanel();
        scoreBoard = new JPanel();
        scoreBoardArray = new JLabel[3];
        newGame(10);
        JLabel p1score= new JLabel("p1 score: " + fourGameLogic.getPlayerOneScore());
        JLabel p2score=new JLabel("p2 score: " + fourGameLogic.getPlayerTwoScore());
        JLabel ap=new JLabel("active_player: " + fourGameLogic.getActivePlayer());
        scoreBoard.add(p1score,BorderLayout.EAST);
        scoreBoard.add(p2score,BorderLayout.WEST);
        scoreBoard.add(ap,BorderLayout.CENTER);
        scoreBoardArray[0]=p1score;
        scoreBoardArray[1]=p2score;
        scoreBoardArray[2]=ap;
        add(form,BorderLayout.CENTER);
        add(scoreBoard,BorderLayout.NORTH);
        add(new JButton(newGameAction), BorderLayout.SOUTH);
        add(new JButton(saveGameAction), BorderLayout.WEST);
        add(new JButton(loadGameAction), BorderLayout.EAST);
        
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addButtons();
        paintButtons();
        
        pack();
    }
    private Action newGameAction = new AbstractAction("New game") {

        @Override
        public void actionPerformed(ActionEvent e) {

            Object[] options = {"10x10",
                "20x20",
                "30x30"};
            int n = JOptionPane.showOptionDialog(null, "MEZO MERET?", "NEW GAME",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[2]);
            switch (n) {
                case 0:
                    newGame(10);
                    break;
                case 1:
                    newGame(20);
                    break;
                case 2:
                    newGame(30);
                    break;
            }
        }
    };
    private Action saveGameAction = new AbstractAction("Save game") 
     {

	@Override
	public void actionPerformed(ActionEvent e)
	{
	    JFileChooser fileChooser = new JFileChooser();
	    int button = fileChooser.showSaveDialog(FourGameGUI.this);
	    
	    if(button == JFileChooser.APPROVE_OPTION) 
	    {
                ObjectOutputStream outputStream = null;
		File saveFile = fileChooser.getSelectedFile();
		
		try
		{
		    outputStream = new ObjectOutputStream(new FileOutputStream(saveFile));
		    outputStream.writeObject(fourGameLogic);
		}
		catch(Exception exception)
		{
		    JOptionPane.showMessageDialog(FourGameGUI.this,
                            "error while saving: "+exception.getMessage());
		}
                finally
                {   try
                    {
                    outputStream.close();
                    }
                    catch(Exception exception)
                    {
                            JOptionPane.showMessageDialog(FourGameGUI.this,
                                    "error while saving: "+exception.getMessage());
                    }
                }
	    }
	}
    };
    private Action loadGameAction = new AbstractAction("Load game") 
     {

	@Override
	public void actionPerformed(ActionEvent e)
	{
	    JFileChooser fileChooser = new JFileChooser(); 
	    int button = fileChooser.showOpenDialog(FourGameGUI.this);
	    
            if(button == JFileChooser.APPROVE_OPTION)
	    {
		File saveFile = fileChooser.getSelectedFile();
		
		try
		{
		    ObjectInputStream inputStream;
                    inputStream = new ObjectInputStream(new FileInputStream(saveFile));
		    fourGameLogic = (FourGameLogic)inputStream.readObject();
		    addButtons();
		    paintButtons();
		}
		catch(Exception exception)
		{
		    JOptionPane.showMessageDialog(FourGameGUI.this,
                            "error while loading: "+exception.getMessage());
		}
	    }
	}
    };
    
    private Action gameButtonAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            fourGameLogic.setBlock(Integer.parseInt(button.getName().split(":")[0]),
                    Integer.parseInt(button.getName().split(":")[1]));      
            if (fourGameLogic.isGameEnded()) {
                if (fourGameLogic.getPlayerOneScore() > fourGameLogic.getPlayerTwoScore()) {
                    JOptionPane.showMessageDialog(
                            FourGameGUI.this, "p1 won");
                    newGame(fourGameLogic.getSize());
                    addButtons();
                } else {
                    JOptionPane.showMessageDialog(
                            FourGameGUI.this, "p2 won");
                    newGame(fourGameLogic.getSize());
                    addButtons();
                }
            }
        paintButtons();    
        }
    };
    
    private void newGame(int _size) {
        buttons = new JButton[_size][_size];
	fourGameLogic = new FourGameLogic(_size);
        addButtons();
    }
    
    private void updateScoreBoard(){
        scoreBoardArray[0].setText("p1 score: " + fourGameLogic.getPlayerOneScore());
        scoreBoardArray[1].setText("p2 score: " + fourGameLogic.getPlayerTwoScore());
        scoreBoardArray[2].setText("active_player: " + fourGameLogic.getActivePlayer());     
    }
    
    private void addButtons() {
        int size = fourGameLogic.getSize();
        form.removeAll();
        form.setLayout(new GridLayout(size, size));
        buttons = new JButton[size][size];
        
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                int height = fourGameLogic.getBlock(i, j);
                JButton button = new JButton(gameButtonAction);
                button.setName(i+":"+j);
                button.setText(Integer.toString(height));
		if(fourGameLogic.getPlayerOneBlock(i, j))
		{
		    button.setBackground(Color.RED);
		}
		else if(fourGameLogic.getPlayerTwoBlock(i, j))
		{
		    button.setBackground(Color.BLUE);
		}
		else
		{
		    button.setBackground(Color.LIGHT_GRAY);
		}
                buttons[i][j]= button;
		form.add(button);
            }
        }
        pack();
    }
    
    private void paintButtons() {
    int size = fourGameLogic.getSize();
	
	for (int i = 0; i <size; i++)
	{
	    for (int j = 0; j <size; j++)
	    {
		int height = fourGameLogic.getBlock(i,j);
		JButton button = buttons[i][j];
		if(fourGameLogic.getPlayerOneBlock(i, j))
		{
		    button.setBackground(Color.RED);
		}
		else if(fourGameLogic.getPlayerTwoBlock(i, j))
		{
		    button.setBackground(Color.BLUE);
		}
		else
		{
		    button.setBackground(Color.LIGHT_GRAY);
		}
		button.setText(Integer.toString(height));
		buttons[i][j]= button;
		form.add(button);
	    }
	}
	form.revalidate();
	form.repaint();
        updateScoreBoard();
        scoreBoard.revalidate();
        scoreBoard.repaint();
        
    }
    
}
