package concentrationGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that models a game controller in a memory game
 * @author DennisQiu
 */
public class GameController implements ActionListener {
	
	private static int countClicks = 0;
	private static int matchedPairs = 0;
	
	private Card card1 = null;
	private Card card2 = null;
	
	/**
	 * Performs the various actions whenever a card is clicked and finds 
	 * the total number of clicks by user per game.
	 */
	public void actionPerformed(ActionEvent arg0) {
		countClicks++;

		Object o = arg0.getSource();
		Card clickedCard = (Card) o;
		
		if (clickedCard.isFaceDown() == false) {
			countClicks--;
			
			if (clickedCard == card1) {
				System.out.println("You clicked the same card, CHOOSE A DIFFERENT CARD. -- Click " 
			+ countClicks);
			} 
			else {
				System.out.println("You already matched this card, CHOOSE A DIFFERENT CARD. -- Click "
			+ countClicks);
			}
			return;
		}
		clickedCard.faceUp();
		
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (card1 == null) {
			card1 = clickedCard;
			System.out.println("You clicked a card. -- Click " + countClicks);
		}
		else {
			card2 = clickedCard;
			System.out.println("You clicked another card. -- Click " + countClicks);

			if (card1.isFaceColorMatch(card2)) {
				matchedPairs++;
				if (matchedPairs == GameBoard.totalPairs) {
					System.out.println("\nYou matched all card pairs! -- " + matchedPairs 
							+ "\nYou won the game!!!\nTotal cards clicked: " + countClicks);
				} 
				else {
					System.out.println("\nCARDS MATCH\nYou matched card pair #" 
				+ matchedPairs + "!" + "\nCards clicked: " + countClicks);
				}
			} 
			else {
				card1.faceDown();
				card2.faceDown();
				System.out.println("NO MATCH");
			}
			card1 = null;
			card2 = null;
		}
	}
}
