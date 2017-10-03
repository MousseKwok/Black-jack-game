/**
 * Construct a card
 * @author Xijie Guo
 *
 */
public class Card {

	//points of the card
	private int point;
	//rank of the card
	private String rank; 
	
	/**
	 * Constructor of Card
	 * @param rank rank of the card
	 * @param point point of the card
	 */
	public Card(String rank, int point) {
		this.rank = rank;
		this.point = point;
	}
	
	/**
	 * Get the point of the card
	 * @return the point of the card
	 */
	public int getPoint() {
		return point;
	}
	
	/**
	 * Get the rank of the card
	 * @return the rank of the card
	 */
	public String getRank() {
		return rank;
	}

}
