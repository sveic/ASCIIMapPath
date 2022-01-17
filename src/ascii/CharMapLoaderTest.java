package ascii;
import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ascii.mapHandling.CharMapLoader;
import ascii.mapHandling.CharPosition;

public class CharMapLoaderTest {
	@Test	
	public void	loadCharMapTest() {
		List<String> allLines = new ArrayList<String>();  
		allLines.add("@");
		allLines.add("| +-C--+");
		allLines.add("A |    |");
		allLines.add("+---B--+");
		allLines.add("  |      x");
		allLines.add("  |      |");
		allLines.add("  +---D--+");
		
		CharMapLoader charMapLoader = new CharMapLoader();
		charMapLoader.loadCharMap(allLines);
		Map<Point, Character> charMap = charMapLoader.getCharMap();
		CharPosition charP = new CharPosition(new Point(2,5),charMap.get(new Point(2,5)));
		CharPosition charPE = new CharPosition( new Point(2,5),'C');
		assertEquals(charPE.getPosition(), charP.getPosition());
		assertEquals(String.valueOf(charPE.getC()), String.valueOf(charP.getC()));
	}
	

}
