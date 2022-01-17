package ascii.mapHandling;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharMapLoader {
	 private List<Point>  startPoss;
	 private List<Point> endPoss; 
	 private Map<Point, Character> charMap; 
	 
	 public CharMapLoader() {
		startPoss = new ArrayList<Point>();
		endPoss = new ArrayList<Point>();
		charMap =  new HashMap<Point, Character> ();
	}

	public List<Point> getStartPoss() {
		return startPoss;
	}
	
	public List<Point> getEndPoss() {
		return endPoss;
	}
	
	public Map<Point, Character> getCharMap() {
		return charMap;
	}


	public  String  loadCharMap(List<String> allLines) {
         
		  int row =0;
			for (String line : allLines) {
				row++;
			    // For each character in the String
		        // add it to the List
			    int col = 0;
			  
		        for (char ch : line.toCharArray()) {
		        	 col++;
		        	 if (!" @x+-|ABCDIEGHLNOS".contains(String.valueOf(ch)) ) 
		        	 //throw new RuntimeException("Invalid characters");
		        	 return "Invalid characters";
		             if (ch=='@')  	startPoss.add(new Point(row, col));
		             else if (ch=='x') endPoss.add(new Point(row, col));
		             
		             charMap.put(new Point(row, col), ch);
		       
			}
		       
		}
		    
		    return "OK";
	}

}
