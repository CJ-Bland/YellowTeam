package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import entity.Song;
//import project.SongQueue;

/**
 * @author Simon
 * 
 * Class test for the SongQueue class
 */
public class SongQueueTest {

	/**to count tests**/
	private static int test = 0;
	
	/**
	 * The SongQueue object
	 */
	static SongQueue songQueue;
	
	/**
	 * The Song object
	 */
	static Song song;

	//Set up things used for all tests.
	@org.junit.BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code
		System.out.println("@BeforeClass - oneTimeSetUp");
		songQueue = new SongQueue();
		song = new Song("name", "artist", "album", 
			"location", "filename", null);
	}

	//Finish with things used for all tests
	@org.junit.AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
		System.out.println("@AfterClass - oneTimeTearDown");
		songQueue = null;
	}
	
	/**TRst before each test method**/
	@org.junit.Before
	public void setUp() throws Exception {
		test++;
	}
	
	/**Called after each test method**/
	@org.junit.After
	public void tearDown() throws Exception {
		songQueue = new SongQueue();
	}
	
	/**Testing adding a song**/
	@org.junit.Test
	public void add() throws Exception {
		songQueue.add(song);
		assertEquals("Test " + test + " adding song", 1, songQueue.size());
	}
	
	/**Testing polling a song**/
	@org.junit.Test
	public void poll() throws Exception {
		songQueue.add(song);
		Song result = songQueue.peek();
		assertEquals("Test " + test + " polling song", result, songQueue.poll());
	}
	
	/**Testing peeking a song**/
	@org.junit.Test
	public void peek() throws Exception {
		songQueue.add(song);
		Song result = songQueue.peek();
		assertEquals("Test " + test + " peeking song", result, songQueue.peek());
	}
	
	/**Testing removing a song**/
	@org.junit.Test
	public void remove() throws Exception {
		songQueue.add(song);
		songQueue.remove();
		assertEquals("Test " + test + " removing song", 0, songQueue.size());
	}
	
	/**Testing getting the size of the queue**/
	@org.junit.Test
	public void size() throws Exception {
		songQueue.add(song);
		songQueue.add(song);
		songQueue.add(song);
		assertEquals("Test " + test + " getting size of the queue", 3, songQueue.size());
	}

}
