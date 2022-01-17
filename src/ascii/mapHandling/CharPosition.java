package ascii.mapHandling;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CharPosition {
	protected Point position;
	protected Character c;
	
	public CharPosition(Point position, Character c) {
		this.position=position;
		this.c=c;
	}
	
	public Point getPosition() {
		return position;
	}

	public Character getC() {
		return c;
	}


	//because of distinct on stream
	 @Override
	  public boolean equals(Object obj) {
		 CharPosition cp = (CharPosition) obj;
	     return cp.position.equals(this.position);
	    }
	 @Override
	    public int hashCode() {
	        return this.position.hashCode();
	    }
	    
	
	public List<CharPosition> getNext(Map<Point, Character> charMap, List<Integer> changeX, List<Integer> changeY) throws RuntimeException {
		List<CharPosition> nextChPositions = new ArrayList<CharPosition>() ; 
		List<CharPosition> nextChPositionsF = new ArrayList<CharPosition>() ; 
		List<Point> nextPoints = new ArrayList<Point>() ; 
		changeX.stream().forEach(chgx -> nextPoints.add(new Point(position.x+chgx, position.y)));
		changeY.stream().forEach(chgy -> nextPoints.add(new Point(position.x, position.y+chgy)));
	
		
		nextPoints.stream().forEach(cp ->nextChPositions.add(new CharPosition(cp, charMap.get(cp)) ));
		nextChPositionsF = nextChPositions.stream()
		.filter(cp -> cp.c!=null && cp.c!=' ')
	    .collect(Collectors.toList());
		
		return nextChPositionsF;
			
		
	}

}
