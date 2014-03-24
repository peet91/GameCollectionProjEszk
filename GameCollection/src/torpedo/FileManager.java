/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package torpedo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author kacsa
 */
public class FileManager {

    private static String outputFile;
    private TorpeDoLogic theGame;
    private static int counter;

    public FileManager(TorpeDoLogic theGame) {
        this.theGame = theGame;
    }

    public static void save(String outputFile, int counter) throws FileNotFoundException {
        FileManager.outputFile = outputFile;

        FileOutputStream os = new FileOutputStream(outputFile);
        PrintStream ps = new PrintStream(os);
        ps.println(counter);
        for (int i = 0; i < TorpeDoLogic.COLS; ++i) {
            for (int j = 0; j < TorpeDoLogic.ROWS; ++j) {
                ps.print(TorpeDoLogic.statusME[i][j] + " ");
            }
            ps.print("\n");
        }
        ps.print("\n");
        for (int i = 0; i < TorpeDoLogic.COLS; ++i) {
            for (int j = 0; j < TorpeDoLogic.ROWS; ++j) {
                ps.print(TorpeDoLogic.statusYOU[i][j] + " ");
            }
            ps.print("\n");
        }
    }

    public static void load(String fajlnev) throws FileNotFoundException {
        File file = new File(fajlnev);
        Scanner sc = new Scanner(file);
        int c = sc.nextInt();
        counter = c;

        for (int i = 0; i < TorpeDoLogic.COLS; ++i) {
            for (int j = 0; j < TorpeDoLogic.ROWS; ++j) {
                TorpeDoLogic.statusME[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < TorpeDoLogic.COLS; ++i) {
            for (int j = 0; j < TorpeDoLogic.ROWS; ++j) {
                TorpeDoLogic.statusYOU[i][j] = sc.nextInt();
            }
        }


    }

    public static int getC() {
        return counter;
    }
}//end