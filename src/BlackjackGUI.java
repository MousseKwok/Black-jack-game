import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The user interface for a simple blackjack game.
 * 
 * @author Barbara Lerner, Xijie Guo
 * @version Mar 31, 2017
 *
 */
public class BlackjackGUI {
	// Strings shown in labels used to display information about the game's state.
	private static final String PLAYER_LOSSES_INITIAL_LABEL = "Player losses: ";
	private static final String PLAYER_WINS_INITIAL_LABEL = "Player wins: ";
	private static final String DEALER_POINTS_INITIAL_LABEL = "Dealer points: ";
	private static final String PLAYER_POINTS_INITIAL_LABEL = "Player points: ";
	private static final String DEALER_CARDS_INITIAL_LABEL = "Dealer cards: ";
	private static final String PLAYER_CARDS_INITIAL_LABEL = "Player cards: ";

	private BlackJackGame game;

	// List of cards in the player's hand
	private JLabel playerCards;

	// Total points the player's hand is worth
	private JLabel playerPoints;

	// Number of wins the player has
	private JLabel playerWins;

	// Number of losses the player has
	private JLabel playerLosses;

	// List of cards in the dealer's hand
	private JLabel dealerCards;

	// Total points of the dealer's hand
	private JLabel dealerPoints;

	// Button to conduct hitting
	private JButton hitButton;

	//Button to end the user's turn
	private JButton standButton;

	//Button to end the dealer's turn and start a new hand
	private JButton nextHandButton;

	//The frame to display game info
	private JFrame f;


	/**
	 * Creates and displays the user interface for the game.
	 */
	public BlackjackGUI() {
		createView();
		game = new BlackJackGame();
		//Set the initial display
		addPlayerCard(game.getPlayerHand().getCards());
		setPlayerPoints(game.getPlayerHand().getHandVal());
	}

	/**
	 * Create the display of the game
	 */
	private void createView() {
		f = new JFrame ("Blackjack");
		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		f.setSize(500, 500);

		// Add the panels to the window and display it.
		f.add(createButtonPanel());
		f.add(createInfoPanel());
		f.setSize(725, 180);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nextHandButton.setEnabled(false);
	}

	/**
	 * Create the hit button for adding new cards
	 * @return the hit button
	 */
	private JButton createHitButton() {
		JButton button = new JButton ("Hit");
		button.addActionListener(new ActionListener () {

			// Clears the card and point information on the display
			@Override
			public void actionPerformed(ActionEvent e) {
				//Check who's in the turn now
				if(game.isPlayerTurn()) {
					playerInTurn();
				}
				else {
					dealerInTurn();
				}
			}

		});
		return button;
	}

	/**
	 * If it's in player's turn, check rules and update player's info
	 */
	private void playerInTurn() {
		//First check if the player has black jack
		if(game.getPlayerHand().canHit()) {
			game.getPlayerHand().hitCard(game.getDeck());
			updatePlayerInfo();
		}
		else {
			if(!game.getPlayerHand().hasBlackJack()) {
				JOptionPane.showMessageDialog(f, "Can't hit anymore! "
						+ "The total hand value has exceeded 21");
			}
			
		}
	}

	/**
	 * If it's in dealer's turn, check rules and update dealer's info
	 */
	private void dealerInTurn() {
		if(game.getDealerHand().canHit()) {
			game.getDealerHand().hitCard(game.getDeck());
			updateDealerInfo();
		}
		else {
			if(!game.getDealerHand().hasBlackJack()) {
				JOptionPane.showMessageDialog(f, "Can't hit anymore! "
						+ "The total hand value has exceeded 21");
			}
			
		}
	}

	/**
	 * Update player's cards and points
	 */
	private void updatePlayerInfo() {
		addPlayerCard(game.getPlayerHand().getCards());
		setPlayerPoints(game.getPlayerHand().getHandVal());
	}

	/**
	 * Update number of player's wins and losses
	 */
	private void updateWinsAndLosses() {
		setWins(game.getPlayerNumWins());
		setLosses(game.getPlayerNumLosses());
	}

	/**
	 * Update dealer's cards and points
	 */
	private void updateDealerInfo() {
		addDealerCard(game.getDealerHand().getCards());
		setDealerPoints(game.getDealerHand().getHandVal());
	}

	/**
	 * The stand button is used to end the user's turn
	 * @return the stand button
	 */
	private JButton createStandButton() {
		JButton button = new JButton ("Don't hit");
		button.addActionListener(new ActionListener () {

			// Clears the card and point information on the display
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the next hand button to true here since it's already dealer's turn
				//now and if dealer's hand value exceeds 17, dealer can choose to end the round
				nextHandButton.setEnabled(true);

				game.isPlayerTurn = false;
				game.startDealerTurn(game.getDeck()); 

				//Update dealer's information
				updateDealerInfo();
				//After clicking on the stand button, disable it since the now it cannot
				//switch to user's turn 
				button.setEnabled(false);
			}

		});
		return button;
	}

	/**
	 * The next hand button is used to end the dealer's turn and start a new hand
	 * @return the next hand button
	 */
	private JButton createNextHandButton() {
		JButton button = new JButton ("Next hand");
		button.addActionListener(new ActionListener () {

			// Clears the card and point information on the display
			@Override
			public void actionPerformed(ActionEvent e) {

				//If dealer's hand value has not exceeded 17, dealer can't choose 
				//to end the round
				if(!game.getDealerHand().shouldHit()) {

					//Before starting a new round, update the info of wins and losses
					updateWinsAndLosses();

					//Print this to separate different decks of cards just for easy testing
					System.out.println("---------------------------------------");
					game.startNewRound();

					//It's now user's turn, user can't end the round since dealer hasn't played yet
					button.setEnabled(false);
					//User can end it's turn
					standButton.setEnabled(true);

					//Update information of the game
					updatePlayerInfo();
					addDealerCard(game.getDealerHand().getCards());
					setDealerPoints(0);
				}


			}

		});
		return button;
	}

	/**
	 * Create the button panel containing hit button, stand button, and 
	 * next hand button
	 * @return the button panel
	 */
	private JPanel createButtonPanel() {
		// Create and display the buttons
		JPanel buttonPanel = new JPanel(); 
		hitButton = createHitButton();
		standButton = createStandButton();
		nextHandButton = createNextHandButton();

		buttonPanel.add(hitButton);
		buttonPanel.add(standButton);
		buttonPanel.add(nextHandButton);
		return buttonPanel;
	}

	/**
	 * Create the information panel dipslaying progress of the game
	 * @return
	 */
	private JPanel createInfoPanel() {
		// Create the panel and labels that displays the game state.
		JPanel infoPanel = new JPanel ();
		infoPanel.setLayout(new GridLayout(0, 2, 20, 20));

		playerCards = new JLabel(PLAYER_CARDS_INITIAL_LABEL);
		playerPoints = new JLabel (PLAYER_POINTS_INITIAL_LABEL);
		infoPanel.add(playerCards);
		infoPanel.add(playerPoints);

		playerWins = new JLabel (PLAYER_WINS_INITIAL_LABEL);
		playerLosses = new JLabel (PLAYER_LOSSES_INITIAL_LABEL);
		infoPanel.add(playerWins);
		infoPanel.add(playerLosses);


		dealerCards = new JLabel(DEALER_CARDS_INITIAL_LABEL);
		dealerPoints = new JLabel(DEALER_POINTS_INITIAL_LABEL);
		infoPanel.add(dealerCards);
		infoPanel.add(dealerPoints);
		return infoPanel;
	}
	
	/** 
	 * Add a card to the list of player cards.
	 * @param cardName the text that you want to represent the card, like "King"
	 */
	public void addPlayerCard (String cardName) {
		playerCards.setText(PLAYER_CARDS_INITIAL_LABEL + cardName);
	}

	/** 
	 * Add a card to the list of dealer cards.
	 * @param cardName the text that you want to represent the card, like "King"
	 */
	public void addDealerCard (String cardName) {
		dealerCards.setText(DEALER_CARDS_INITIAL_LABEL + cardName);
	}

	/**
	 * Change the number of points to display for the player
	 * @param points number to display
	 */
	public void setPlayerPoints (int points) {
		playerPoints.setText(PLAYER_POINTS_INITIAL_LABEL + " " + points);
	}

	/**
	 * Change the number of points to display for the dealer
	 * @param points number to display
	 */
	public void setDealerPoints (int points) {
		dealerPoints.setText(DEALER_POINTS_INITIAL_LABEL + " " + points);
	}

	/**
	 * Change the number of wins to display for the player
	 * @param wins number to display
	 */
	public void setWins (int wins) {
		playerWins.setText(PLAYER_WINS_INITIAL_LABEL + " " + wins);
	}

	/**
	 * Change the number of losses to display for the player
	 * @param losses number to display
	 */
	public void setLosses (int losses) {
		playerLosses.setText(PLAYER_LOSSES_INITIAL_LABEL + " " + losses);
	}

	/**
	 * Main program to start the game
	 * @param args none expected
	 */
	public static void main (String[] args) {
		new BlackjackGUI();
	}
}
