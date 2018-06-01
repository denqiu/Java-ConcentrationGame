package concentrationGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * A class that models a card in a memory game
 * @author DennisQiu
 */
@SuppressWarnings("serial")
public class Card extends JButton { 
	
	private static int width = 800;
	private static int height = 600;	
	
	private Color faceColor;
	private static Color backgroundColor = Color.LIGHT_GRAY;
	private boolean isFaceDown = true;
	
	/**
	 * Creates a card with width and height dimensions, and sets border. 
	 * The card automatically faces downward, with it's background color facing upwards.
	 * @param fColor the face color of each created card
	 */
	public Card(Color fColor) {
		faceColor = fColor;
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		faceDown();
	}
	
	/**
	 * Changes color to face color if the card does not face downward.
	 */
	public void faceUp() {
		setFaceDown(false);
		changeColor(faceColor); 
	}

	/**
	 * Changes color to background color if the card does face downward.
	 */
	public void faceDown() {
		setFaceDown(true);
		changeColor(backgroundColor);
	}
	
	/**
	 * Immediately paints each card with its own (0,0), width, and height.
	 * @param color the color on each card
	 */
	private void changeColor(Color color) {
		setBackground(color);
		setOpaque(true);
		paintImmediately(0,0, width, height); 
	}
	
	/**
	 * Sets the background color of the component object, shaped like a rectangle (a card), 
	 * with its own (0,0), width, and height.
	 */
	public void paintComponent(Graphics graphics) {
		graphics.setColor(getBackground());
		graphics.fillRect(0,0, getWidth(), getHeight());
	}
	
	/**
	 * Sets the face of the card down.
	 * @param isFaceDown the card automatically faces downward 
	 */
	public void setFaceDown(boolean isFaceDown) {
		this.isFaceDown = isFaceDown;
	}
	
	/**
	 * Finds whether the face of each card automatically faces downward or upward.
	 * @return Whether the face of each card is automatically facing downward or upward.
	 */
	public boolean isFaceDown() {
		return isFaceDown;
	}

	/**
	 * Finds whether or not the first card and the second card matches their face colors.
	 * @param card the card being compared to the first card
	 * @return True if both cards match colors, otherwise false if their colors don't match.
	 */
	public boolean isFaceColorMatch(Card card) {
		return (faceColor.equals(card.faceColor));
	}
	
	/**
	 * Finds whether Card objects match their face colors or not.
	 */
	public boolean equals(Object other) {
		Card otherCard = (Card) other;
		return (faceColor.equals(otherCard.faceColor));
	}
}
