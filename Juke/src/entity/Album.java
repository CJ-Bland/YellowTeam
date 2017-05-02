package entity;

import java.util.ArrayList;

/**
 * A class which models an album
 * 
 * might be removed later, tbd
 * @author CJ
 * @version Sprint 3
 */
public class Album {
	/**
     * The name of the album
     */
    private String name;
    /**
     * The artist of the album
     */
    private String artist;
    /**
     * The year of the album
     */
    private String year;
    /**
	 * The artwork of the song
	 */
	private String artwork;
	/**
	 * List of songs on the album
	 */
	private ArrayList<Song> songs;
	
	public Album(String name, String artist, String year, String artwork, ArrayList<Song> songs){
		this.name = name;
		this.artist = artist;
		this.year = year;
		this.artwork = artwork;
		this.songs = songs;
	}
	
	//Eclipse generated methods which get and set all fields
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getArtwork() {
		return artwork;
	}
	public void setArtwork(String artwork) {
		this.artwork = artwork;
	}
}
