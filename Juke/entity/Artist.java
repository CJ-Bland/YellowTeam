package entity;

import java.util.ArrayList;

/**
 * A class which models an artist
 * 
 * might be removed later, tbd
 * @author CJ
 * @version Sprint 3
 */
public class Artist {
	/**
     * The name of the artist
     */
    private String name;
    /**
     * The albums by the artist
     */
    private ArrayList<Song> albums;
    
    //Eclipse generated methods which get and set all fields
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Song> getAlbums() {
		return albums;
	}
	public void setAlbums(ArrayList<Song> albums) {
		this.albums = albums;
	}
}
