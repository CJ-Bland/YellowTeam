package entity;

import javafx.scene.image.ImageView;

/**
 * A class which models a song, with all getters and setters
 * 
 * Created by CJ on 3/17/2017.
 * @author CJ
 * @version Edited on Sprint 3, added to toString and equals
 */
public class Song {

	/**
	 * The name of the song
	 */
	private String name;
	/**
	 * The artist of the song
	 */
	private String artist;
	/**
	 * The album of the song
	 */
	private String album;
	
	/**
	 * The number of times the song is played
	 */
	private int plays;
	/**
	 * The location of the song
	 */
	private String location;
	/**
	 * The file name
	 */
	private String fileName;
	/**
	 * The artwork of the song
	 */
	
	private ImageView artwork;

	/**
	 * The constructor of the song class
	 * 
	 * @param name
	 * @param artist
	 * @param album
	 * @param year
	 * @param location
	 * @param fileName
	 * @param artwork
	 */
	public Song(String name, String artist, String album,
			String location, String fileName, ImageView artwork) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.plays = 0;
		this.location = location;
		this.fileName = fileName;
		this.artwork = artwork;
	}

	
	/**
	 * @return	the artwork of the song
	 */
	public ImageView getArtwork() {
		return artwork;
	}

	/**
	 * @param artwork
	 */
	public void setArtwork(ImageView artwork) {
		this.artwork = artwork;
	}

	/**
	 * @return	the name of the song
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the artist of the song
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return	the album of the song
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return
	 */
	public int getPlays() {
		return plays;
	}

	/**
	 * @param plays
	 */
	public void setPlays(int plays) {
		this.plays = plays;
	}

	/**
	 * Method to increment the plays by one when played by the media player
	 */
	public void incPlays(){
		plays++;
	}
	/**
	 * @return	the location of the file
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return	the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String toString(){
		return "Song{" + "name=" + name + 
				", artist=" + artist + 
				", album=" + album + 
				", plays=" + plays + 
				", location=" + location + 
				", file name=" + fileName + 
				", artwork=" + artwork + "}";
	} 

	@Override
	public boolean equals(Object o){
		if (this == o) 
			return true;

		if (o == null) 
			return false;

		if (getClass() != o.getClass()) 
			return false;

		Song song = (Song) o;

		if(!name.equals(song.name)) return false;

		if(!artist.equals(song.artist)) return false;

		if(!album.equals(song.album)) return false;

		if(plays != song.plays) return false;

		if(!location.equals(song.location)) return false;

		if(!fileName.equals(song.fileName)) return false;

		if(!artwork.equals(song.artwork)) return false;

		return true;
	}

}
