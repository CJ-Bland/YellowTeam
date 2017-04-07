package entity;
/**
 * A class which models a song, with all getters and setters
 * 
 * Created by CJ on 3/17/2017.
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
     * The year of the song
     */
    private String year;
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
	//CJ: May change, can just use the artwork for the album
	// if it exists
	private String artwork;

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
	public Song(String name, String artist, String album, String year, 
			String location, String fileName, String artwork) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.plays = 0;
		this.location = location;
		this.fileName = fileName;
		this.artwork = artwork;
	}

	/**
	 * @return	the year of the song
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return	the artwork of the song
	 */
	public String getArtwork() {
		return artwork;
	}

	/**
	 * @param artwork
	 */
	public void setArtwork(String artwork) {
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
	
}
