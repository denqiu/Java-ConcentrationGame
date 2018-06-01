package concentrationGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

/**
 * A class that models a game board in a memory game
 * @author DennisQiu
 */
@SuppressWarnings("serial")
public class GameBoard extends JFrame {
	
	private static int x = 0;
	private static int y = 0;
	public static int totalPairs = 0;
	
	/**
	 * Calls the GameBoard constructor.
	 * @param args none
	 */
	public static void main(String [] args) {
		new GameBoard(8);
	}

	/**
	 * Creates a game board that can change size dimensions, depending on the number of 
	 * pairs used per game.
	 * @param nTotalPairs the total number of pairs used per game
	 */
	public GameBoard(int nTotalPairs) {
		totalPairs = nTotalPairs;
		
		setTitle("Concentration Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		setLocation(500, 30);

		Container c = getContentPane();
				
		Color[] color = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.ORANGE, Color.MAGENTA};
		int totalColors = color.length;
		int totalCards = totalPairs * 2;
		
		ArrayList<Integer> factors = new ArrayList<Integer>();
		if (totalPairs > 0) {
			int i = 0;

			for (i = 1; i <= Math.sqrt(totalCards); i++) {
				if (totalCards % i == 0) {
					factors.add(i);
					factors.add(totalCards / i);

					x = i;
					y = totalCards / i;
				}
			}
			c.setLayout(new GridLayout(x, y));
		}
		else {
			System.out.println("There is no such thing as " + totalPairs + " pairs."
					+ "\nENTER A REAL PAIR NUMBER");
			System.exit(0);
		}
		
		Card[] cards = new Card[totalCards];
		ArrayList<Integer>indexes = new ArrayList<Integer>(totalCards);
		
		int index = 0;
		int pairCount = 0;
		int colorIndex;
		Color thisColor;
		
		for (int i = 0; i < totalPairs; i++) {
			colorIndex = pairCount % totalColors;
			thisColor = color[colorIndex];
			
			for (int j = 0; j < 2; j++) {
				cards[index] = new Card(thisColor);
				indexes.add(index);
				index++;
			}	
			pairCount++;
		}
		Collections.shuffle(indexes);
		GameController gameController = new GameController();	
		
		for (int i: indexes) {
			cards[i].addActionListener(gameController);
			c.add(cards[i]);
		}
		pack();
		setVisible(true);
	}
}
