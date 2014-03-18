import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		System.out.println("Starting GUI");		
		GameCollectionGUI gameCollectionGUI = new GameCollectionGUI();
		
		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader("petofi.vers")));
			while(sc.hasNext())
				System.out.println(sc.nextLine());
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
