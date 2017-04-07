package controllers;
import java.util.ArrayList;

import entity.Song;

/**
 * The controller of the song list
 * @author Prem Bengali
 */
public class SongList {
	
	/**
	 * The list of song
	 */
	private ArrayList<Song> songList;
	
	/**
	 * The constructor of the song list class
	 */
	public SongList() {
		songList = new ArrayList<>();
	}
	
	/**
	 * Method used to add a song
	 * @param song
	 */
	public void addSong(Song song) {
		songList.add(song);
	}
	
	/**
	 * Method used to see all the song of the song list
	 */
	public void viewLibrary(){
        for(Song s: songList){
            System.out.print(s.getName());
        }
    }

    /**
     * Method used to search a song by its name
     * @param name
     * @return the song if it is find
     */
	//CJ: Unlikely, but what if two songs have the same name, but are by different artist?
		//Maybe this should be a list too
    public Song getSongByName(String name){
    	Song song = null;
    	for(Song s: songList){
            if(s.getName().equals(name)){
                song = s;
            }
        }
        return song;
    }
    
    /**
     * Method used to search a song by its artist
     * @param artist
     * @return the song if it is find
     */
    //CJ: Artists usually have multiple songs, shouldn't this return a list of songs? 
    public Song getSongByArtist(String artist){
        Song song = null;
    	for(Song s: songList){
            if(s.getArtist().contains(artist)){
                song = s;
            }
        }
    	return song;
    }

    /**
     * Method used to search a song by its year
     * @param year
     * @return the song if it is find
     */
  //CJ: Multiple songs might have been released in the same year, 
    //shouldn't this return a list of songs? 
    public Song getSongByYear(String year){
    	Song song = null;
        for(Song s: songList){
            if(s.getYear().contains(year)){
                song= s;
            }
        }
        return song;
    }
    
    /**
     * Delete the song from the list
     * @param song to delete
     */
    public void deleteSong(Song song) {
    	
    	if(songList.contains(song)) {
    		songList.remove(song);
    	}
    	else {
    		System.out.println("Error! Song not found");
    	}
    	
    }

}
