package ascii;

//import static org.junit.Assert.assertNotNull;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ascii.mapHandling.CharPosition;
import ascii.mapHandling.MapPath;

public class MapPathTest {

@Test	
public void	addMapPathCharKeepDirectTest() {
	//keep direction test
	CharPosition charP__ = new CharPosition(new Point(2,2), '-'); 
	CharPosition charP_ = new CharPosition(new Point(2,3), '-'); 
	Map<Point, Character>	charMap =  new HashMap<Point, Character> ();
	charMap.put(new Point(2,4), '|');
	MapPath mapPath = new MapPath();
	CharPosition charP = mapPath.addMapPathChar(charMap, charP_, charP__);
	assertEquals(new Point(2,4), charP.getPosition() );
	
}

@Test	
public void	addMapPathCharTurnTest() {
	//turn test
	CharPosition charP__ = new CharPosition(new Point(2,2), '-'); 
	CharPosition charP_ = new CharPosition(new Point(2,3), '+'); 
	Map<Point, Character>	charMap =  new HashMap<Point, Character> ();
	charMap.put(new Point(3,3), '|');
	MapPath mapPath = new MapPath();
	CharPosition charP = mapPath.addMapPathChar(charMap, charP_, charP__);
	assertEquals(new Point(3,3), charP.getPosition() );
	
}
@Test	
public void	addMapPathCharLettTurnTest() {
	//letter on turn
	CharPosition charP__ = new CharPosition(new Point(2,2), '-'); 
	CharPosition charP_ = new CharPosition(new Point(2,3), 'A'); 
	Map<Point, Character>	charMap =  new HashMap<Point, Character> ();
	charMap.put(new Point(3,3), '|');
	MapPath mapPath = new MapPath();
	CharPosition charP = mapPath.addMapPathChar(charMap, charP_, charP__);
	assertEquals(new Point(3,3), charP.getPosition() );
}

@Test	
public void	addMapPathCharIntersTest() {
	//straight through intersection
	CharPosition charP__ = new CharPosition(new Point(3,2), '-'); 
	CharPosition charP_ = new CharPosition(new Point(3,3), '-'); 
	Map<Point, Character>	charMap =  new HashMap<Point, Character> ();
	charMap.put(new Point(3,4), '-');
	charMap.put(new Point(2,3), '|');
	MapPath mapPath = new MapPath();
	CharPosition charP = mapPath.addMapPathChar(charMap, charP_, charP__);
	assertEquals(new Point(3,4), charP.getPosition() );
}
}
