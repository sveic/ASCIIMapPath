package ascii;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import ascii.mapHandling.CharMapLoader;
import ascii.mapHandling.MapPath;

public class MapPathApp{
	
	
	public static void main(String[] args) {
		try {
			String errMsg = "OK"; 
			//Read name of the input file from the console
			Scanner readme = new Scanner(System.in);
			System.out.println("Enter the map file name:");
			String fileName = readme.nextLine();
			readme.close();
			  
			//List<String> allLines = Files.readAllLines(Paths.get("C:/Users/sveic/eclipse2019Demo/ASCIImapPath/map.txt"));
			List<String> allLines = Files.readAllLines(Paths.get(fileName));
			
			//TODO control of characters
			//transform to map
			 CharMapLoader charMapLoader = new CharMapLoader();
			 errMsg = charMapLoader.loadCharMap(allLines);
			 if(!errMsg.equals("OK"))
			 {
				System.out.print("Error "+ errMsg);
				return;
			 }
			 
			 //finding path
			 MapPath mapPath = new MapPath();
			 errMsg = mapPath.setMapPath(charMapLoader.getCharMap(), charMapLoader.getStartPoss(), charMapLoader.getEndPoss());
			 if(!errMsg.equals("OK"))
			 {
				System.out.print("Error "+ errMsg);
				return;
			 }
			 
			 //Printing letters
			 System.out.print("Letters ");
			 mapPath.printLetters();
	   		 
			
			//Printing path as characters
			System.out.println("");
			System.out.print("Path as characters ");
			mapPath.printPath();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
	}
		
		
	
	
}