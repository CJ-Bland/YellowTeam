package controllers;
import java.util.ArrayList;

/**
 * Created by CJ on 3/17/2017.
 * Modified by Simon
 */
public class Jukebox {

	/**
	 * The list of song
	 */
	private SongList songList;
	/**
	 * The queue of songs
	 */
	private SongQueue songQueue;
	/**
	 * The number of credits
	 */
	private int credits;
	/**
	 * The total number of credits
	 */
	private int totalFunds;
	/**
	 * The price of one credit
	 */
	private int oneCreditPrice;
	/**
	 * The price of five credits
	 */
	private int fiveCreditsPrice;
	/**
	 * The name of the venue
	 */
	private String venueName;
	/**
	 * Boolean used to display the name of the venue or not
	 */
	private boolean displayVName;
	/**
	 * True if the user is an admin, otherwise false
	 */
	private boolean isAdmin;
	/**
	 * The password to access admin functionalities
	 */
	private String password;
	/**
	 * A message the admin can display or not
	 */
	private String message;
	/**
	 * Boolean to siplay the message or not
	 */
	private boolean displayMessage;

	/**
	 * Constructor of the Jukebox class
	 * 
	 * @param credits
	 */
	public Jukebox(int credits) {
		this.songList = new SongList();
		this.songQueue = new SongQueue();
		this.credits = 0;
		this.totalFunds = 0;
		this.displayVName = true;
		this.displayMessage = false;
		this.isAdmin = false;
		this.oneCreditPrice = 1;
		this.fiveCreditsPrice = 3;
		this.venueName = "La belle avenue";
		this.password = "1234";
		this.message = "";
	}
	
	/**
	 * Constructor of the Jukebox class without parameters
	 */
	public Jukebox(){
		this.songList = new SongList();
		this.songQueue = new SongQueue();
		this.credits = 0;
		this.totalFunds = 0;
		this.displayVName = true;
		this.displayMessage = false;
		this.isAdmin = false;
		this.oneCreditPrice = 1;
		this.fiveCreditsPrice = 3;
		this.venueName = "La belle avenue";
		this.password = "1234";
		this.message = "Enjoy the tunes";
	}

	/**
	 * Method called when inserting coins
	 * 
	 * @param money	the amount of money the user put
	 * @return	true if correct amount, otherwise false
	 */
	public boolean insertCoin(int money){
		boolean result = false;
		if (money == oneCreditPrice) {
			credits += 1;
			totalFunds += money;
			result = true;
		} else if (money == fiveCreditsPrice) {
			credits += 5;
			totalFunds += money;
			result = true;
		}
		return result;
	}

	/**
	 * @return	the list of song
	 */
	public SongList getSongList() {
		return songList;
	}

	/**
	 * @return	the number of credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * @return	the queue of song
	 */
	public SongQueue getSongQueue() {
		return songQueue;
	}

	/**
	 * @param totalFunds
	 */
	public void setTotalFunds(int totalFunds) {
		this.totalFunds = totalFunds;
	}

	/**
	 * @return	the total number of funds
	 */
	public int getTotalFunds() {
		return this.totalFunds;
	}

	/**
	 * @return	the price for one credit
	 */
	public int getOneCreditPrice() {
		return oneCreditPrice;
	}

	/**
	 * @param oneCreditPrice
	 */
	public void setOneCreditPrice(int oneCreditPrice) {
		this.oneCreditPrice = oneCreditPrice;
	}

	/**
	 * @return	the price for 5 credits
	 */
	public int getFiveCreditsPrice() {
		return fiveCreditsPrice;
	}

	/**
	 * @param fiveCreditsPrice
	 */
	public void setFiveCreditsPrice(int fiveCreditsPrice) {
		this.fiveCreditsPrice = fiveCreditsPrice;
	}

	/**
	 * @return	the name of the venue
	 */
	public String getVenueName() {
		return venueName;
	}

	/**
	 * @param venueName
	 */
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	/**
	 * @return	true if the user is an admin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	/**
	 * @return	the admin password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return	the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return	displayVName
	 */
	public boolean isDisplayVName() {
		return displayVName;
	}

	/**
	 * @param displayVName
	 */
	public void setDisplayVName(boolean displayVName) {
		this.displayVName = displayVName;
	}

	/**
	 * @return	displayMessage
	 */
	public boolean isDisplayMessage() {
		return displayMessage;
	}

	/**
	 * @param displayMessage
	 */
	public void setDisplayMessage(boolean displayMessage) {
		this.displayMessage = displayMessage;
	}
	
	/**
	 * Method used to decrement the credits when a song is added
	 */
	public void decrementCredits() {
		credits--;
	}

}
