/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dodgemgamee.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dodgemgamee.logic.GameLogic;
import dodgemgamee.logic.Way;

/**
 *
 * @author Ă�kos
 */
public final class GameFrame extends JFrame {

    GameLogic logic;
    private int size;
    private ButtonNumber[][] btns;
    private JPanel panel = new JPanel();
    private Way way;
    private ButtonNumber tmp = new ButtonNumber(0, 0);
    private JTextField txt = new JTextField();
    private Image blue;
    private Image red;
    
    private final ActionListener ButtonActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tmp = (ButtonNumber) e.getSource();
        }
    };
    private final KeyListener KeyActionListener = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                logic.move(tmp.getPntX(), tmp.getPntY(), Way.FRONT);
            }
            if (e.getKeyChar() == KeyEvent.VK_1) {
                logic.move(tmp.getPntX(), tmp.getPntY(), Way.LEFT);
            }
            if (e.getKeyChar() == KeyEvent.VK_2) {
                logic.move(tmp.getPntX(), tmp.getPntY(), Way.RIGHT);
            }
            updatePanel();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    private Action newGameAction10 = new AbstractAction("10X10") {
        @Override
        public void actionPerformed(ActionEvent e) {
            setSize(750, 900);
            newGame(10);
        }
    };
    private Action newGameAction20 = new AbstractAction("20X20") {
        @Override
        public void actionPerformed(ActionEvent e) {
            setSize(1000, 1200);
            newGame(20);
        }
    };
    private Action newGameAction30 = new AbstractAction("30X30") {
        @Override
        public void actionPerformed(ActionEvent e) {
            setSize(1500, 1800);
            newGame(30);
        }
    };
    private Action saveGameAction = new AbstractAction("MentĂ©s") {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();

            int button = chooser.showSaveDialog(GameFrame.this);

            if (button == JFileChooser.APPROVE_OPTION) {
                File saveFile = chooser.getSelectedFile();

                try {
                    FileOutputStream fo = new FileOutputStream(saveFile);
                    ObjectOutputStream os = new ObjectOutputStream(fo);
                    os.writeObject(logic);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(GameFrame.this, "Nem sikerĂĽlt");
                }
            }

        }
    };
    private Action loadGameAction = new AbstractAction("BetĂ¶ltĂ©s") {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int button = chooser.showOpenDialog(GameFrame.this);
            if (button == JFileChooser.APPROVE_OPTION) {
                File saveFile = chooser.getSelectedFile();

                try {
                    
                    FileInputStream fi = new FileInputStream(saveFile);
                    ObjectInputStream is = new ObjectInputStream(fi);
                    logic = (GameLogic) is.readObject();
                    size = logic.getSize();

                    btns = new ButtonNumber[size][size];
                    panel.removeAll();
                    panel.setLayout(new GridLayout(size, size));

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            ButtonNumber btn = new ButtonNumber(i, j);
                            if (logic.getMap(i, j) == 1) {
                                btn.setBackground(Color.gray);
                                btn.setIcon(new ImageIcon(red));
                            }
                            if (logic.getMap(i, j) == 2) {
                                btn.setBackground(Color.gray);
                                btn.setIcon(new ImageIcon(blue));
                            }
                            if (logic.getMap(i, j) == 0) {
                                btn.setIcon(null);
                                btn.setBackground(Color.gray);
                            }
                            btn.addActionListener(ButtonActionListener);
                            btn.addKeyListener(KeyActionListener);
                            btns[i][j] = btn;
                            panel.add(btn);
                        }
                    }
                    getContentPane().add(panel, BorderLayout.CENTER);
                    updatePanel();
                    
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(GameFrame.this, "Nem sikerĂĽlt");
                }
            }

        }
    };
    private Action descriptionAction = new AbstractAction("LeĂ­rĂˇs") {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(rootPane, "A Dodgem jĂˇtĂ©kban egy nĂ—n-es jĂˇtĂ©ktĂˇblĂˇn a jĂˇtĂ©kosok n-1 dodgemmel rendelkeznek," + "\n" + "melyek kezdetben az ĂˇbrĂˇn lĂˇthatĂł mĂłdon helyezkednek el. A bal oldali jĂˇtĂ©kos fel, le, illetve jobbra," + "\n" + "mĂ­g az alsĂł jĂˇtĂ©kos fel, balra Ă©s jobbra mozgathatja a bĂˇbuit "
                    + "ĂĽres mezĹ‘re," + " \n" + "valamint mindketten lelĂ©phetnek a tĂˇblĂˇrĂłl, ha elĂ©rtĂ©k a tĂˇbla mĂˇsik vĂ©gĂ©t."
                    + "\n" + "\n" + "A jĂˇtĂ©kosok felvĂˇltva lĂ©pnek, Ă©s az veszĂ­t, aki nem tud lĂ©pni egyetlen bĂˇbujĂˇval sem,"
                    + "\n" + "mert az ellenfĂ©l blokkolja minden, a jĂˇtĂ©ktĂˇblĂˇn maradt bĂˇbujĂˇt"
                    + "\n" + "Az Enterrel elĹ‘re, mĂ­g az 1-essel balra a 2-essel jobbra lĂ©phetĂĽnk" + "\n\n" + "Made by Akos Komuves", "LeĂ­rĂˇs", JOptionPane.INFORMATION_MESSAGE);

        }
    };

    public GameFrame(int size) throws IOException {
        super("Dodgem Game");
        this.size = size;
        red = ImageIO.read(new File("red.jpg"));
        blue = ImageIO.read(new File("blue.jpg"));
        setLocation(0, 0);
        setSize(750, 900);
        setLayout(new BorderLayout());
        setJMenuBar(createMenu());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        newGame(10);
        JPanel SouthPanel = new JPanel();
        txt.setEditable(false);
        txt.setHorizontalAlignment(JTextField.CENTER);
        SouthPanel.add(txt);
        getContentPane().add(SouthPanel, BorderLayout.SOUTH);
    }

    public final void newGame(int size) {

        this.size = size;
        logic = new GameLogic(size);
        btns = new ButtonNumber[size][size];
        panel.removeAll();
        panel.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ButtonNumber btn = new ButtonNumber(i, j);
                if (logic.getMap(i, j) == 1) {
                    btn.setBackground(Color.gray);
                    btn.setIcon(new ImageIcon(red));
                }
                if (logic.getMap(i, j) == 2) {
                    btn.setBackground(Color.gray);
                    btn.setIcon(new ImageIcon(blue));
                }
                if (logic.getMap(i, j) == 0) {
                    btn.setIcon(null);
                    btn.setBackground(Color.gray);
                }
                btn.addActionListener(ButtonActionListener);
                btn.addKeyListener(KeyActionListener);
                btns[i][j] = btn;
                panel.add(btn);
            }
        }
        getContentPane().add(panel, BorderLayout.CENTER);
        updatePanel();
    }

    private void updatePanel() {

        if (logic.endGame() == 1) {
            JOptionPane.showMessageDialog(rootPane, "The Blue won the game!", "Nice!", JOptionPane.INFORMATION_MESSAGE);
            logic = new GameLogic(size);
        }
        if (logic.endGame() == 2) {
            JOptionPane.showMessageDialog(rootPane, "The Red won the game!", "Nice!", JOptionPane.INFORMATION_MESSAGE);
            logic = new GameLogic(size);

        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (logic.getMap(i, j) == 1) {
                    btns[i][j].setBackground(Color.gray);
                    btns[i][j].setIcon(new ImageIcon(red));
                }
                if (logic.getMap(i, j) == 2) {
                    btns[i][j].setBackground(Color.gray);
                    btns[i][j].setIcon(new ImageIcon(blue));
                }
                if (logic.getMap(i, j) == 0) {
                    btns[i][j].setIcon(null);
                    btns[i][j].setBackground(Color.gray);
                }
            }
        }
        if (logic.getRedTheNext() == true) {
            txt.setText("Piros kĂ¶vetkezik");
        } else {
            txt.setText("KĂ©k kĂ¶vetkezik");
        }
        panel.revalidate();
        panel.repaint();
    }

    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu mainMenu = new JMenu("LehetĹ‘sĂ©gek");

        JMenu newGameMenu = new JMenu("Ăšj JĂˇtĂ©k");
        JMenuItem newGame5 = new JMenuItem(newGameAction10);
        JMenuItem newGame10 = new JMenuItem(newGameAction20);
        JMenuItem newGame15 = new JMenuItem(newGameAction30);

        JMenuItem saveGameItem = new JMenuItem(saveGameAction);

        JMenuItem loadGameItem = new JMenuItem(loadGameAction);

        JMenuItem descriptionItem = new JMenuItem(descriptionAction);

        newGameMenu.add(newGame5);
        newGameMenu.add(newGame10);
        newGameMenu.add(newGame15);

        mainMenu.add(newGameMenu);
        mainMenu.add(saveGameItem);
        mainMenu.add(loadGameItem);
        mainMenu.add(descriptionItem);

        menuBar.add(mainMenu);
        return menuBar;
    }
}
