package entity;
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
