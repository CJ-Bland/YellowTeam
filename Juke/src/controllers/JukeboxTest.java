package controllers;

import static org.junit.Assert.assertEquals;

/*import project.Jukebox;
import project.Song;
import project.SongQueue;*/

/**
 * @author Simon
 * 
 * Class that tests the Jukebox class
 */
public class JukeboxTest {

	/**to count tests**/
	private static int test = 0;
	
	/**
	 * The jukebox object
	 */
	static Jukebox jukebox;

	//Set up things used for all tests.
	@org.junit.BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code
		System.out.println("@BeforeClass - oneTimeSetUp");
		jukebox = new Jukebox();
	}

	//Finish with things used for all tests
	@org.junit.AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
		System.out.println("@AfterClass - oneTimeTearDown");
		jukebox = null;
	}
	
	/**TRst before each test method**/
	@org.junit.Before
	public void setUp() throws Exception {
		test++;
	}
	
	/**Called after each test method**/
	@org.junit.After
	public void tearDown() throws Exception {
		jukebox = new Jukebox();
	}
	
	/**Testing inserting 1 dollar**/
	@org.junit.Test
	public void insertCoin() throws Exception {
		assertEquals("Test " + test + " insert coin", true, jukebox.insertCoin(1));
	}
	
	/**Testing inserting 3 dollars**/
	@org.junit.Test
	public void insertCoin2() throws Exception {
		assertEquals("Test " + test + " insert coin", true, jukebox.insertCoin(3));
	}
	
	/**Testing inserting an incorrect amount of money**/
	@org.junit.Test
	public void insertCoin3() throws Exception {
		assertEquals("Test " + test + " insert coin", false, jukebox.insertCoin(10));
	}
	
	/**Testing getting credits**/
	@org.junit.Test
	public void getCredits() throws Exception {
		jukebox.insertCoin(3);
		assertEquals("Test " + test + " getting credits", 5, jukebox.getCredits());
	}
	
	/**Testing getting total funds**/
	@org.junit.Test
	public void getTotalFunds() throws Exception {
		jukebox.insertCoin(3);
		jukebox.insertCoin(1);
		assertEquals("Test " + test + " getting total funds", 4, jukebox.getTotalFunds());
	}
	
	/**Testing getting one credit price**/
	@org.junit.Test
	public void getOneCreditPrice() throws Exception {
		assertEquals("Test " + test + " getting one credit price", 1, jukebox.getOneCreditPrice());
	}
	
	/**Testing getting five credits price**/
	@org.junit.Test
	public void getFiveCreditsPrice() throws Exception {
		assertEquals("Test " + test + " getting five credits price", 3, jukebox.getFiveCreditsPrice());
	}
	
	/**Testing setting one credit price**/
	@org.junit.Test
	public void setOneCreditPrice() throws Exception {
		jukebox.setOneCreditPrice(2);
		assertEquals("Test " + test + " setting one credit price", 2, jukebox.getOneCreditPrice());
	}
	
	/**Testing setting five credits price**/
	@org.junit.Test
	public void setFiveCreditsPrice() throws Exception {
		jukebox.setFiveCreditsPrice(4);
		assertEquals("Test " + test + " setting five credits price", 4, jukebox.getFiveCreditsPrice());
	}
	
	/**Testing getting venue name**/
	@org.junit.Test
	public void getVenueName() throws Exception {
		assertEquals("Test " + test + " getting venue name", "La belle avenue", jukebox.getVenueName());
	}
	
	/**Testing setting venue name**/
	@org.junit.Test
	public void setVenueName() throws Exception {
		jukebox.setVenueName("Casseurs Flowters");
		assertEquals("Test " + test + " setting venue name", "Casseurs Flowters", jukebox.getVenueName());
	}
	
	/**Testing displaying venue name**/
	/*@org.junit.Test
	public void displayVenueName() throws Exception {
		jukebox.displayVenueName(false);
		assertEquals("Test " + test + " displaying venue name", false, jukebox.getDisplayVenueName());
	}*/
	
	/**Testing getting display venue name value**/
	/*@org.junit.Test
	public void getDisplayVenueName() throws Exception {
		assertEquals("Test " + test + " getting display venue name value", true, jukebox.getDisplayVenueName());
	}*/
	
	/**Testing getting admin value**/
	@org.junit.Test
	public void isAdmin() throws Exception {
		assertEquals("Test " + test + " getting admin value", false, jukebox.isAdmin());
	}
	
	/**Testing setting admin**/
	@org.junit.Test
	public void setAdmin() throws Exception {
		jukebox.setAdmin(true);
		assertEquals("Test " + test + " setting admin", true, jukebox.isAdmin());
	}

}
