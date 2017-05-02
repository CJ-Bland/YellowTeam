package entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Unit tests for Song
 * @author CJ
 * @version Sprint 3
 */
public class SongTest {
	/**to count tests**/
	private static int test = 0;

	/**Products created before each test**/
	Song song1, song2;


	//Set up things used for all tests.
	@org.junit.BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code
		System.out.println("@BeforeClass - oneTimeSetUp");
	}

	//Finish with things used for all tests
	@org.junit.AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
		System.out.println("@AfterClass - oneTimeTearDown");
	}

	@Before
	public void setUp() throws Exception {
		song1 = new Song("Snakes", "Voltaire", "The Devils Bris", "file:/C:/", "snakes.mp3", new ImageView(new Image("/img1.png")));
		song2 = new Song("Yakaty Sax", "Boots Randolph", "Yakaty Sax", "file:/C:/", "Benny-hill-theme.mp3", new ImageView(new Image("/img2.png")));
		test++;
	}

	@After
	public void tearDown() throws Exception {
		song1 = null;
		song2 = null;
	}

	@Test
	public void testGetName() {
		assertEquals("Test " + test + ": getName", "Snakes", song1.getName());
	}

	@Test
	public void testSetName() {
		song2.setName("Run");
		assertEquals("Test  " + test + " setName", "Run",song2.getName());
	}

	@Test
	public void testGetArtist() {
		//fail("Not yet implemented");
		assertEquals("Test " + test + ": getArtist", "Voltaire", song1.getArtist());
	}

	@Test
	public void testSetArtist() {
		song2.setArtist("Benny Hill");
		assertEquals("Test  " + test + " setArtist", "Benny Hill",song2.getArtist());
	}

	@Test
	public void testGetAlbum() {
		//fail("Not yet implemented");
		assertEquals("Test " + test + ": getAlbum", "The Devils Bris", song1.getAlbum());
	}

	@Test
	public void testSetAlbum() {
		song2.setAlbum("Run Around");
		assertEquals("Test  " + test + " set", "Run Around",song2.getAlbum());
	}

	@Test
	public void testGetPlays() {
		assertEquals("Test " + test + ": getPlays", 0, song1.getPlays());

	}

	@Test
	public void testSetPlays() {
		song2.setPlays(1000000);
		assertEquals("Test  " + test + " set", 1000000,song2.getPlays());

	}

	@Test
	public void testGetLocation() {
		assertEquals("Test " + test + ": getLocation", "file:/C:/", song1.getLocation());

	}

	@Test
	public void testSetLocation() {
		song2.setLocation("file:/C:/Music/");
		assertEquals("Test  " + test + " set", "file:/C:/Music/",song2.getLocation());

	}

	@Test
	public void testGetFileName() {
		assertEquals("Test " + test + ": getFileName", "snakes.mp3", song1.getFileName());

	}

	@Test
	public void testSetFileName() {
		song2.setFileName("WackySax");
		assertEquals("Test  " + test + " set", "WackySax", song2.getFileName());

	}

	@Test
	public void testGetArtwork() {
		assertEquals("Test " + test + "getArtwork", "img1.png", song1.getArtwork());
	}

	@Test
	public void testSetArtwork() {
		song2.setArtwork(new ImageView(new Image("/sax.png")));
		assertEquals("Test  " + test + " set", "sax.png", song2.getArtwork());
	}

	@Test
	public void testToString(){
		assertEquals("Song{name=Snakes, artist=Voltaire, album=The Devils Bris"
				+ ", year=1990, plays=0, location=file:/C:/, file name=snakes.mp3"
				+ ", artwork=img1.png}", song1.toString());
	}

	/**Test two products are considered equals when their states are the same*/
	@Test
	public void equals(){
		Song songA = new Song("Snakes", "Voltaire", "The Devils Bris",  "file:/C:/", "snakes.mp3", new ImageView(new Image("/img1.png")));

		assertTrue("Test " + test + "a equals", song1.equals(songA));
		assertTrue("Test " + test + "b equals", songA.equals(song1));
	}
	//Different names
	@Test
	public void not_equals(){
		Song songA = new Song("Snek", "Voltaire", "The Devils Bris", "file:/C:/", "snakes.mp3", new ImageView(new Image("/img1.png")));

		assertFalse("Test  " + test + "a not equals on name",song1.equals(songA));
		assertFalse("Test  " + test + "b not equals on name", songA.equals(song1));
	}

	//Different artist
	@Test
	public void not_equals2(){
		Song songA = new Song("Snakes", "Boltaire", "The Devils Bris",  "file:/C:/", "snakes.mp3", new ImageView(new Image("/img1.png")));
		assertFalse("Test  " + test + "a not equals on artist",song1.equals(songA));
		assertFalse("Test  " + test + "b not equals on artist", songA.equals(song1));
	}

	//Different album
	@Test
	public void not_equals3(){
		Song songA = new Song("Snakes", "Voltaire", "The Dis Bris",  "file:/C:/", "snakes.mp3", new ImageView(new Image("/img1.png")));
		assertFalse("Test  " + test + "a not equals on album",song1.equals(songA));
		assertFalse("Test  " + test + "b not equals on album", songA.equals(song1));
	}

	//Different plays
	@Test
	public void not_equals5(){
		Song songA = new Song("Snakes", "Voltaire", "The Devils Bris",  "file:/C:/", "snakes.mp3", new ImageView(new Image("/img1.png")));
		songA.setPlays(10);
		assertFalse("Test  " + test + "a not equals on plays",song1.equals(songA));
		assertFalse("Test  " + test + "b not equals on plays", songA.equals(song1));
	}

	//Different location
	@Test
	public void not_equals6(){
		Song songA = new Song("Snakes", "Voltaire", "The Devils Bris", "file:/C:/Music/", "snakes.mp3", new ImageView(new Image("/img1.png")));
		assertFalse("Test  " + test + "a not equals on location",song1.equals(songA));
		assertFalse("Test  " + test + "b not equals on location", songA.equals(song1));
	}

	//Different fileName
	@Test
	public void not_equals7(){
		Song songA = new Song("Snakes", "Voltaire", "The Devils Bris",  "file:/C:/", "snakes.wav", new ImageView(new Image("img1.png")));
		assertFalse("Test  " + test + "a not equals on filename",song1.equals(songA));
		assertFalse("Test  " + test + "b not equals on filename", songA.equals(song1));
	}

	//Different artwork
	@Test
	public void not_equals8(){
		Song songA = new Song("Snakes", "Voltaire", "The Devils Bris",  "file:/C:/", "snakes.mp3", new ImageView(new Image("img10.png")));
		assertFalse("Test  " + test + "a not equals on artwork",song1.equals(songA));
		assertFalse("Test  " + test + "b not equals on artwork", songA.equals(song1));
	}

	//Same object
	@Test
	public void equals_same(){
		Song songB = new Song("Snakes", "Voltaire", "The Devils Bris", "file:/C:/", "snakes.mp3", new ImageView(new Image("img10.png")));
		Song songC = songB;
		assertTrue("Test  " + test + "equals on same product",songB.equals(songC));		

	}

	//Null object
	@Test
	public void equals_null(){
		Song songA = new Song("Snakes", "Voltaire", "The Devils Bris", "file:/C:/", "snakes.mp3", new ImageView(new Image("/img10.png")));
		Song songB = null;
		assertFalse("Test  " + test + "a not equals on same product",songA.equals(songB));;
	}

	//Different classes
	@Test
	public void not_equal_different_class(){
		Song songA = new Song("Snakes", "Voltaire", "The Devils Bris", "file:/C:/", "snakes.mp3", new ImageView(new Image("/img10.png")));
		String some_object  = new String("WCU");
		assertFalse("Test  " + test + "a not equals on same product",songA.equals(some_object));;
	}
}
