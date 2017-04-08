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
    private ArrayList<Album> albums;
    
    public Artist(String name, ArrayList<Album> album){
    	this.name = name;
    	this.albums = album;
    }
    
    //Eclipse generated methods which get and set all fields
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}
	
	public String toString(){
		String a = "";
		for(int i=0; i<albums.size(); i++){
			a+= albums.get(i).getName();
		}
		return "Artist{name= " + name +
				", albums= " + a + "}";
						
	}
	
	
}
