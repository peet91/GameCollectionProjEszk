package Reversi;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Grafika extends JFrame {

    private JButton[][] buttons;// = new JButton[Logika.getROWS()][Logika.getCOLS()];
    private Logika logic;
    private String jatekos;
    private JPanel panel;

    public Grafika() {
        setSize(400, 400);
        //setSize(Logika.getROWS() * meret, Logika.getCOLS() * meret);
        setLocationRelativeTo(null);
        setResizable(false);
       // setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem tabla10 = new JMenuItem("10x10");
        JMenuItem tabla20 = new JMenuItem("20x20");
        JMenuItem tabla30 = new JMenuItem("30x30");
        JMenu newItem = new JMenu("New");
        newItem.add(tabla10);
        newItem.add(tabla20);
        newItem.add(tabla30);
        tabla10.addActionListener(new NewListener(10));
        tabla20.addActionListener(new NewListener(20));
        tabla30.addActionListener(new NewListener(30));
        menu.add(newItem);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new OpenListener());
        menu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(new SaveListener());
        menu.add(saveItem);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ExitListener());
        menu.add(exitItem);
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new HelpListener());
        helpMenu.add(help);
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new AboutListener());
        helpMenu.add(about);
        panel = new JPanel();
    }

    public void setGombSzin(int i, int j, Color color) {
        buttons[i][j].setBackground(color);
    }

    public void setGombState(int i, int j, boolean bool) {
        if (!(buttons[i][j].isEnabled() && bool)) {
            buttons[i][j].setEnabled(bool);
        }
    }

    public void setCim(int szin) {
        if (szin == 1) {
            this.jatekos = logic.getKor() + ". kör: Fekete jön";
            this.setTitle(jatekos);
        } else if (szin == 2) {
            this.jatekos = logic.getKor() + ". kör: Fehér jön";
            this.setTitle(jatekos);
        } else {
            this.jatekos = logic.getKor() + "Passz!";
            this.setTitle(jatekos);
        }

    }

    public void vege() {
        int tmp = logic.kiNyert();
        if (tmp == 1) {
            JOptionPane.showMessageDialog(null, "A Fekete nyert!", "Játék vége", JOptionPane.INFORMATION_MESSAGE);
        } else if (tmp == 2) {
            JOptionPane.showMessageDialog(null, "A Fehér nyert!", "Játék vége", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Döntetlen!", "Játék vége", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private static class HelpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, 
                    "A játékot két játékos játssza n×n-es négyzetrácsos táblán fekete és fehér korongokkal.\n "
                    + "A játék lényege, hogy a lépés befejezéseként az ellenfél ollóba fogott,\n "
                    + "azaz két oldalról közrezárt bábuit a saját színünkre cseréljük.","Játék leírás",1);
        }

    }

    private static class AboutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,
                    "A program készítője:Hechtl Olivér\nEHA:HEOSAAI.ELTE\nNeptun:TGBSJU","About",1);
        }
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            int rv = fc.showSaveDialog(null);
            if (rv == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                logic.elment(file);
            }
        }
    }

    private class OpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            int rv = fc.showOpenDialog(null);
            if (rv == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                if (!Logika.ellenoriz(file)) {
                    JOptionPane.showMessageDialog(null, "A megadott file nem tölthető be!", "Hiba!", 0);
                } else {
                    int size = Logika.getSizeFromFile(file);
                    int kor = Logika.getKorFromFile(file);
                    kirajzol(size, kor, file);

                }
            }
        }
    }

    private class NewListener implements ActionListener {

        private int size;

        public NewListener(int size) {
            this.size = size;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            kirajzol(size, 0, null);
        }
    }

    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private void kirajzol(int size, int type, File file) {
        this.panel.removeAll();
        logic = new Logika(this, size, 1);
        this.setSize(logic.getMeret() * 40, logic.getMeret() * 40);
        this.jatekos = "1. kör: Fekete jön";
        this.setTitle(this.jatekos);
        panel.setLayout(new GridLayout(size, size));
        buttons = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton();
                button.setName(i + "," + j);
                buttons[i][j] = button;
                panel.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = ((JButton) e.getSource()).getName();
                        String[] coords = name.split(",");
                        int i = Integer.parseInt(coords[0]);
                        int j = Integer.parseInt(coords[1]);
                        logic.gombNyomva(i, j, logic.getKor() % 2 == 0 ? 2 : 1);
                        logic.kovetkezoKor();
                    }
                });
            }
        }
        if (type == 0) {
            logic.kezdes();
        } else {
            logic.betolt(file);
        }
        add(panel);
        panel.repaint();
        setVisible(true);
    }
}
