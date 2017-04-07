package controllers;
import java.util.LinkedList;
import java.util.Queue;

import entity.Song;

/**
 * The queue of song that will be played
 * @author Simon Bessenay
 */
public class SongQueue {

	/**
	 * The queue of songs
	 */
	private Queue<Song> queue;
	
	/**
	 * The constructor of the class creates a new queue
	 */
	public SongQueue() {
		queue = new LinkedList();
	}
	
	/**
	 * This method is used to add a song to the queue
	 * @param song	the song to add
	 */
	public void add(Song song) {
		queue.add(song);
	}
	
	/**
	 * This method remove and return the song at the head of the queue
	 * @return	the song at the head
	 */
	public Song poll() {
		return queue.poll();
	}
	
	/**
	 * This method return the song at the head of the queue
	 * @return	the song at the head
	 */
	public Song peek() {
		return queue.peek();
	}
	
	/**
	 * This method remove the song at the head of the queue
	 */
	public void remove() {
		queue.remove();
	}
	
}
