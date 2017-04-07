import java.io.File;
import java.net.URI;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * A class that creates a media player to play a song
 * @author Simon Bessenay
 *
 */
public class PlaySong {

	/**The media files location***/
	String mediaLocation;

	/**The file name to play**/
	String fileName;

	/**The song that's being plays**/
	Media song;

	/**The media player object for the music**/
	MediaPlayer mediaPlayer;

	/**Will create a My_Media_Player object avoiding the need for Java FX as this has not been 
	 * introduced yet.
	 */
	public PlaySong() {
		JFXPanel fxPanel = new JFXPanel();//A Little trick to get the JavaFX toolkit to load.
	}

	/**
	 * Set up the media player to play some music.
	 * @param mediaLocation The location of the media file on the local computer.
	 * @param fileName The file name of the file to play on the local computer.
	 */
	public MediaPlayer setMediaPlayer(String mediaLocation, String fileName){

		this.mediaLocation = mediaLocation;
		this.fileName = fileName;

		File  file = new File( mediaLocation + fileName);
		
		if(file.exists()) {
		URI uri = file.toURI();

		//Output URI String representation
		System.out.println("Attempting to play: " + uri);

		song = new Media(uri.toString());
		mediaPlayer = new MediaPlayer(song);

		mediaPlayer.setOnError(new Runnable() {
			@Override public void run() {
				songError(fileName);
			}
		});
		mediaPlayer.setOnPlaying(new Runnable() {
			@Override public void run() {
				songPlaying(fileName);
			}
		});
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override public void run() {
				songEnded(fileName);
			}
		});

		mediaPlayer.setOnStopped(new Runnable() {
			@Override public void run() {
				songStopped(fileName);
			}
		});
		mediaPlayer.play();
		}
		else {
			System.out.println("Error! Song not found");
		}

		return mediaPlayer;
	}

	/**
	 * When the music has started playing
	 * @param fileName The name of the file playing
	 */
	private static void songPlaying(String fileName){
		System.out.println("Playing:" + fileName);
	}
	
	/**
	 * When the music has stopped playing
	 * @param fileName The name of the file that was playing.
	 */
	public static void songStopped(String fileName){
		System.out.println("Stopped playing: " + fileName);
	}
	
	/**
	 * When the music has ended
	 * @param fileName The name of the file that was playing.
	 */
	public static void songEnded(String fileName){
		System.out.println( fileName + " has ended");
	}
	
	/**
	 * When the music can't play because of an error.
	 * @param fileName The name of the file that the media player attempted to play
	 */
	public static void songError(String fileName){
		System.out.println("There was an error playing: " + fileName);
	}
	
	/**
	 * Stop the music playing
	 */
	public void stop_music(){
		mediaPlayer.stop();
	}

	public void pause_music(){
		if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED)
			mediaPlayer.play();
		else
			mediaPlayer.pause();
	}
}