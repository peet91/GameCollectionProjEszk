/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package torpedo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author kacsa
 */
public class TorpeDoFrame extends JFrame {

    private JButton[][] buttonsME = new JButton[TorpeDoLogic.ROWS][TorpeDoLogic.COLS];
    private JButton[][] buttonsYOU = new JButton[TorpeDoLogic.ROWS][TorpeDoLogic.COLS];
    private TorpeDoLogic logic;
    private Coloring coloring;
    private JLabel statusBarLabel;
    private JLabel you;
    private JLabel enemy;

    public TorpeDoFrame() {
        setTitle("TorpeDo");
        setLocationRelativeTo(null);
        setSize(new Dimension(610, 350)); //width, hight
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        //Menü
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);


        JMenu fileMenu = new JMenu("Menü");
        menuBar.add(fileMenu);

        JMenuItem newItem = new JMenuItem("Új Játék");
        newItem.addActionListener(new NewListener());
        fileMenu.add(newItem);

        JMenuItem saveItem = new JMenuItem("Mentés");
        saveItem.addActionListener(new saveListener());
        fileMenu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Betöltés");
        loadItem.addActionListener(new loadListener());
        fileMenu.add(loadItem);


        JMenuItem exitItem = new JMenuItem("Kilépés");
        exitItem.addActionListener(new ExitListener());
        fileMenu.add(exitItem);

        JMenu infoMenu = new JMenu("Súgó");
        menuBar.add(infoMenu);

        JMenuItem rulesItem = new JMenuItem("Szabályok");
        rulesItem.addActionListener(new rules());
        infoMenu.add(rulesItem);

        JMenuItem legendItem = new JMenuItem("Jelmagyarázat");
        legendItem.addActionListener(new legend());
        infoMenu.add(legendItem);

        JMenuItem infoItem = new JMenuItem("Info");
        infoItem.addActionListener(new info());
        infoMenu.add(infoItem);




        //Ablak + háttér
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.CYAN);
        topPanel.setLayout(new BorderLayout(2, 2));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 2, 2, 2));
        add(topPanel);


// status bar
        statusBarLabel = new JLabel(" TorpeDo - Created by KaCsa©");
        enemy = new JLabel("                                ellenfél térfele");
        you = new JLabel("te térfeled                                   ");
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        panel.add(you);
        panel.add(statusBarLabel);
        panel.add(enemy);
        panel.setBackground(Color.BLACK);
        topPanel.add(panel, BorderLayout.SOUTH);
        statusBarLabel.setForeground(Color.CYAN);


//gombok
        //játékos térfele
        //JPanel panel = new JPanel();
        JPanel buttonPanelMe = new JPanel();

        buttonPanelMe.setLayout(new GridLayout(8, 8, 2, 2));
        buttonPanelMe.setBackground(Color.CYAN);
//        buttonPanelMe.setLayout(new GridLayout(TorpeDoLogic.ROWS, TorpeDoLogic.COLS, 3, 3));
//        buttonPanelMe.setBackground(Color.ORANGE);

        for (int i = 0; i < TorpeDoLogic.ROWS; ++i) {
            for (int j = 0; j < TorpeDoLogic.COLS; ++j) {
                JButton button = new JButton();
                button.setName(i + ";" + j);
                buttonsME[i][j] = button;
                buttonPanelMe.add(button);
                button.setBackground(Color.BLUE);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = ((JButton) e.getSource()).getName();
                        String[] coords = name.split(";");
                        int i = Integer.parseInt(coords[0]);
                        int j = Integer.parseInt(coords[1]);
                        logic.buttonPressed(i, j);
                    }
                });
            }
        }
        logic = new TorpeDoLogic(this);
        topPanel.add(buttonPanelMe, BorderLayout.WEST);

        //ellenfér térfele
        JPanel buttonPanelEnemy = new JPanel();

        buttonPanelEnemy.setLayout(new GridLayout(8, 8, 2, 2));
        buttonPanelEnemy.setBackground(Color.CYAN);

        for (int x = 0; x < TorpeDoLogic.ROWS; ++x) {
            for (int y = 0; y < TorpeDoLogic.COLS; ++y) {
                JButton button = new JButton();
                button.setName(x + ";" + y);
                buttonsYOU[x][y] = button;
                buttonPanelEnemy.add(button);
                button.setBackground(Color.BLUE);
                buttonsYOU[x][y].setEnabled(false);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = ((JButton) e.getSource()).getName();
                        String[] coords = name.split(";");
                        int x = Integer.parseInt(coords[0]);
                        int y = Integer.parseInt(coords[1]);
                        logic.buttonPressed(x, y);
                    }
                });
            }
        }
        logic = new TorpeDoLogic(this);
        coloring = new Coloring(logic.COLS, logic.ROWS, logic.statusME, logic.statusYOU, logic.c, this);
        topPanel.add(buttonPanelEnemy, BorderLayout.EAST);

    }//konstruktor vége

// Actions
    //exit
    private static class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    //new game
    private class NewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int m = 0; m < TorpeDoLogic.ROWS; ++m) {
                for (int n = 0; n < TorpeDoLogic.COLS; ++n) {
                    TorpeDoLogic.statusME[m][n] = 0;
                    TorpeDoLogic.statusYOU[m][n] = 0;
                    buttonsME[m][n].setEnabled(true);
                    buttonsME[m][n].setBackground(Color.BLUE);
                    buttonsYOU[m][n].setBackground(Color.BLUE);
                }
            }
            logic.enableME();
            logic.disableYOU();
            TorpeDoLogic.c = 0;

        }
    }

    //save
    private class saveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String fajlnev = null;
            try {
                fajlnev = JOptionPane.showInputDialog("Add a file name to save!");
                FileManager.save(fajlnev, TorpeDoLogic.c);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error!");
            }
        }
    }

    //load
    private class loadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String fajlnev = null;
            try {
                fajlnev = JOptionPane.showInputDialog("Add a file name to load!");
                FileManager.load(fajlnev);
                coloring.coloringME();
                coloring.coloringYOU();
                logic.disableME();
                logic.enableYOU();
                TorpeDoLogic.c = FileManager.getC();
                System.out.println(TorpeDoLogic.c);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error!");
            }
        }
    }

    //rules
    private class rules implements ActionListener {

        private Component Message;

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(Message, "Torpedó játék! \n"
                    + "Eslő feladatod, hogy lerakd a hajóidat:\n"
                    + "Három hajód van. Rendre: 2, 3, 4 hosszúak.\n"
                    + "Ezután már csak meg kell találnod az ellenfél hajóit! ;) \n"
                    + "Jó szórakozást!");
        }
    }

    //legend
    private class legend implements ActionListener {

        private Component Message;

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(Message, "Jelmagyarázat: \n"
                    + "kék - víz \n"
                    + "szürke - hajó\n"
                    + "fahér - lőtt de nem talált\n"
                    + "piros - találat");
        }
    }

    //nfo
    private class info implements ActionListener {

        private Component Message;

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(Message, "Készítette: \n"
                    + "Katona Csaba\n"
                    + "Dátum:\n"
                    + "2013-2014-as tanév \n"
                    + "tárgy:\n"
                    + "Project Eszközök");
        }
    }

//függvények
    public void setColorOfButtonME(int i, int j, Color color) {
        buttonsME[i][j].setBackground(color);
    }

    public void setColorOfButtonYOU(int i, int j, Color color) {
        buttonsYOU[i][j].setBackground(color);
    }

    public void setButtonEnabledME(int i, int j, boolean b) {
        buttonsME[i][j].setEnabled(b);
    }

    public void setButtonEnabledYOU(int i, int j, boolean b) {
        buttonsYOU[i][j].setEnabled(b);
    }
}//program vége
