package ascii.mapHandling;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapPath {
	private List<CharPosition> charPositions;
	private String errMsg;
	public MapPath() {
		charPositions = new ArrayList<CharPosition>();
		errMsg = "OK";
	}


	public List<CharPosition> getCharPositions() {
		return charPositions;
	}


	public void printLetters()  {
   			List<Character> letters = charPositions.stream()
   					//only these letters
   					.filter(cp -> "ABCDIEGHLNOS".contains(String.valueOf(cp.c)))
   					//remove letters on the same positions
   					.distinct()
   					//get only letters
   					.map(cp ->cp.c)
   					.collect(Collectors.toList());
   			letters.stream().forEach(System.out::print);
   				
	  }
	
   		public void printPath()  {
   			List<Character>  characters = charPositions.stream()
   					.map(cp -> cp.c).collect(Collectors.toList());
   			characters.stream().forEach(System.out::print);
				
  	  }


		public String  setMapPath(Map<Point, Character> charMap, List<Point> startPoss, List<Point> endPoss){
			
			//if (startPoss.size( )==0) throw new RuntimeException("No start");
			if (startPoss.size( )==0) return("No start");
			if (startPoss.size()>1 ) return("Multiple starts");	
			if (endPoss.size()==0) return("No end");
			
					  	
			 CharPosition charPos = new CharPosition(startPoss.get(0),'@');
		 	 charPositions.add(charPos);
			
			 for (int i=1; i< charMap.size(); i++)
			 {
				CharPosition charP = null;
				
				if (i==1 ) {
					charP = addMapPathChar(charMap, charPositions.get(i-1), null);
				}
				else charP = addMapPathChar(charMap, charPositions.get(i-1), charPositions.get(i-2));
			   
			   if (!errMsg.equals("OK")) return errMsg;
			  
			   charPositions.add(charP);
			   
			   if (charP.c !=null && charP.c=='x' ) break;
			   //System.out.print(charPositionArr[i].c);
			   
			 }
			 if (endPoss.size()>1 ) return("Multiple ends");	
			 
			 return("OK");
		 }
		
	
	public CharPosition  addMapPathChar(Map<Point, Character> charMap, CharPosition charP_, CharPosition charP__ ){
			
			 List<CharPosition> charPositionsP =new ArrayList<CharPosition>();
		  	 
			  if (charP__==null ) {
					charPositionsP=charP_.getNext(charMap, Arrays.asList(-1,1), Arrays.asList(-1,1));
					if (charPositionsP.size()==0) {errMsg = "Broken path"; return null; }	
					if (charPositionsP.size()>1)  {errMsg = "Multiple starting paths";  return null; } 
				    return charPositionsP.get(0);
			  }
			 
			   //turns
			   if (charP_.c=='+')  {
					if (charP_.position.x==charP__.position.x)
					charPositionsP = charP_.getNext(charMap,Arrays.asList(-1,1), new ArrayList<Integer>());
					else charPositionsP = charP_.getNext(charMap, new ArrayList<Integer>(), Arrays.asList(-1,1));
					if (charPositionsP.size()==0) {errMsg = "Fake turn"; return null; }	
					if (charPositionsP.size()>1) {errMsg = "T forks"; return null; }
				}	
		   	     //Keep on current direction (for -|ABCDIEGHLNOS)
				else if (charP_.position.x==charP__.position.x) 
				 {
				    //if same x with bigger y 
					if (charP_.position.y>charP__.position.y) 
					charPositionsP = charP_.getNext(charMap, new ArrayList<Integer>(), Arrays.asList(1));
				  	else 
					//if same x with smaller y 
				  	charPositionsP = charP_.getNext(charMap, new ArrayList<Integer>(), Arrays.asList(-1));	
		   		  }	
				 else if (charP_.position.y==charP__.position.y) 
				 {
					if (charP_.position.x>charP__.position.x) 
				     //if same y with bigger x
					charPositionsP = charP_.getNext(charMap, Arrays.asList(1), new ArrayList<Integer>());
				   else 
		   			//if same y with smaller x 
					charPositionsP = charP_.getNext(charMap, Arrays.asList(-1), new ArrayList<Integer>());
			   	 }
			     if (charPositionsP.size()==0 && "ABCDIEGHLNOS".contains(String.valueOf(charP_.c))) {
			    	 //if letter is on turn
			       	   if (charP_.position.x==charP__.position.x) 
			       		charPositionsP = charP_.getNext(charMap,Arrays.asList(-1,1), new ArrayList<Integer>());
		   				else charPositionsP = charP_.getNext(charMap,new ArrayList<Integer>(), Arrays.asList(-1,1));
			       	    if (charPositionsP.size()==0)  {errMsg = "Fake turn"; return null; }
					    if (charPositionsP.size()>1)  {errMsg = "T forks"; return null; }
			      }
			     
			   //No turn, no keeping direction - break
			   if (charPositionsP.size()==0) {errMsg = "Broken path";  return null; }	 
			   
			 
			 return(charPositionsP.get(0));
		 }

}
