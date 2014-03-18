
package PotyogosAmoba;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


class BeadGameFrame extends JFrame {

    private JButton[][] buttons;
    private BeadGameLogic logic;
    private JPanel panel;
    private JPanel topPanel;
    private JLabel statusBarLabel;
    private boolean fajlbolOlvas;

    public BeadGameFrame() {
        setTitle("Potyogós amőba");
        setSize(new Dimension(1000, 800));
        setMinimumSize(new Dimension(1000, 800));
        setLocation(new Point(150, 100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Fájl");
        menuBar.add(fileMenu);
        JMenu infoMenu = new JMenu("Súgó");
        menuBar.add(infoMenu);

        JMenuItem information = new JMenuItem("Játékszabály");
        infoMenu.add(information);
        information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(topPanel, "Játékszabály: \n \n Minden lépésben egy korongot teszünk az egyik oszlopba.\n"
                        + " A korong az oszlop legfelső szabad pozícióján áll meg:\n ha az oszlop üres, legalul,"
                        + " egyébként a legfelső korong fölött.\n Ha az oszlop betelt, oda már nem lehet tenni."
                        + " Ha minden oszlop betelt, döntetlen.\n \n Cél:\n A négy figuránk sorba állítása vízszintesen,"
                        + " függőlegesen vagy átlósan.");
            }
        });

        JMenuItem keszito = new JMenuItem("Készítő");
        infoMenu.add(keszito);
        keszito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(topPanel, "Készítő: \n \n Kis Zoltán \n KIZSAAI.ELTE \n Projekt eszközök");
            }
        });

        JMenu newItem = new JMenu("Új játék");
        fileMenu.add(newItem);

        for (int i = 6; i > 2; i--) {
            JMenuItem subItem = new JMenuItem(Integer.toString(40 / i) + " x " + Integer.toString(40 / i));
            newItem.add(subItem);
            subItem.setName(Integer.toString(40 / i) + " x " + Integer.toString(40 / i));
            subItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = ((JMenuItem) e.getSource()).getName();
                    String[] coords = name.split(" x ");
                    int i = Integer.parseInt(coords[0]);
                    int j = Integer.parseInt(coords[1]);
                    fajlbolOlvas = false;
                    tablaElkeszitese(i, j);
                }
            });
        }

        JMenuItem saveItem = new JMenuItem("Játék mentése");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fajlnev = JOptionPane.showInputDialog("Add meg milyen nevű fájlba mentsünk!");
                    logic.jatekMentese(fajlnev);
                } catch (FileNotFoundException ex) {
                    hibaUzen("A mentés sikertelen!");
                }
            }
        });
        fileMenu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Játék betöltése");
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fajlnev = JOptionPane.showInputDialog("Add meg milyen nevű fájlból töltsünk be!");
                betolt(fajlnev);

            }
        });
        fileMenu.add(loadItem);


        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ExitListener());
        fileMenu.add(exitItem);

        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());


        ImageIcon imageBack = new ImageIcon("hatter.png");
        JLabel background = new JLabel(imageBack);
        background.setBounds(0, 0, imageBack.getIconWidth(), imageBack.getIconHeight());
        getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        topPanel.setOpaque(false);
        setContentPane(topPanel);

        statusBarLabel = new JLabel(" Üdvözöllek a játékban!");

        statusBarLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        topPanel.add(statusBarLabel, BorderLayout.SOUTH);

    }

    public void tablaElkeszitese(int x, int y) {

        if (panel != null) {
            panel.removeAll();
            topPanel.remove(panel);
            if (!fajlbolOlvas) {
                logic = null;
            }
        }
        panel = new JPanel();
        panel.setMinimumSize(new Dimension(1000, 800));
        topPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(x, y));
        buttons = new JButton[x][y];
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                JButton button = new JButton();
                button.setBackground(Color.white);
                button.setName(i + ";" + j);
                button.setEnabled(false);
                button.setBorder(((BorderFactory.createEtchedBorder(Color.LIGHT_GRAY, Color.black))));
                buttons[i][j] = button;
                panel.add(button);
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
        pack();
        if (logic == null) {
            logic = new BeadGameLogic(this, x, y);
        }


    }

    public void setButton(int i, int j, boolean b) {
        buttons[i][j].setEnabled(b);
        if (b) {
            Color color = new Color(238, 224, 229);
            buttons[i][j].setBackground(color);
        } else {
            buttons[i][j].setBackground(Color.white);
        }
    }

    public void setButtonColor(int i, int j, ImageIcon image) {
        buttons[i][j].setIcon(image);
    }

    public void uzen(String uzenet) {
        int n = JOptionPane.showConfirmDialog(
                null,
                uzenet + "\n" + "Egy visszavágó?",
                "A játék véget ért!",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            logic.reset();
            logic = null;
            fajlbolOlvas = false;
            tablaElkeszitese(logic.ROWS, logic.COLS);
        } else {
            System.exit(0);
        }
    }

    public void showWin(int i, int j) {
        buttons[i][j].setBorder((BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.RED, Color.RED)));
    }

    public void setStatusBar(String uzenet) {
        statusBarLabel.setText(uzenet);
    }

    private void hibaUzen(String uzenet) {
        JOptionPane.showMessageDialog(rootPane, uzenet);


    }

    private static class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public void betolt(String fajlnev) {
        try {
            this.logic = new BeadGameLogic(fajlnev, this);
            fajlbolOlvas = true;
            tablaElkeszitese(logic.ROWS, logic.COLS);
            logic.szinezes();
            logic.setEnableButtons();
        } catch (FileNotFoundException ex) {
            hibaUzen("Nincs ilyen mentésed!");
        }
    }
}
