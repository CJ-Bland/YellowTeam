package entity;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Unit tests for Artist
 * @author CJ
 * @version Sprint 3
 */
public class ArtistTest {
	
	/**to count tests**/
	private static int test = 0;

	/**Products created before each test**/
	Artist a1, a2;
	
	ArrayList<Album> albums;
	
	ArrayList<Song> songs;

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
		songs = new ArrayList<Song>();
		songs.add(new Song("Snakes", "Voltaire", "The Devils Bris",  "file:/C:/", "snakes.mp3", new ImageView(new Image("/img1.jpg"))));
		albums = new ArrayList<Album>();
		albums.add(new Album("Top Hits", "Journey", "1980", "img2.jpg", songs));
		a1 = new Artist("ABBA", albums);
	}

	@After
	public void tearDown() throws Exception {
		songs = null;
		albums = null;
		a1 = null;
	}

	@Test
	public void testGetName() {
		assertEquals("Test " + test + ": getName", "ABBA", a1.getName());
	}

	@Test
	public void testSetName() {
		a1.setName("The BeeGees");
        assertEquals("Test  " + test + " setName", "The BeeGees",a1.getName());
	}

	@Test
	public void testGetAlbums() {
		songs.add(new Song("Snakes", "Voltaire", "The Devils Bris", "file:/C:/", "snakes.mp3", new ImageView(new Image("/img1.png"))));		
		albums.add(new Album("Top Hits", "Journey", "1980", "img2.jpg", songs));
		assertEquals("Test " + test + ": getAlbums", albums, a1.getAlbums());
	}

	@Test
	public void testSetAlbums() {
		//ArrayList<Song> songs1 = new ArrayList<Song>();
		//ArrayList<Album> album1 = new ArrayList<Album>();
		
		songs.add(new Song("Snekes", "Volebear", "The Angels Bris", "file:/C:/Music/", "snekes.mp3", new ImageView(new Image("/img1.png"))));		
		albums.add(new Album("Flop Hits", "Tourney", "1280", "img21.jpg", songs));
		a1.setAlbums(albums);
		assertEquals("Test  " + test + " setAlbums", albums, a1.getAlbums());
	}
	
	@Test
	public void testToString(){
		assertEquals("Artist{name= ABBA, albums= Top Hits}", a1.toString());
	}

}
