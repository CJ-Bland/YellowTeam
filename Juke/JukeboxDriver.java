import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Driver of the Jukebox
 * 
 * @author Simon and Prem 
 */
public class JukeboxDriver {

	static PlaySong playedSong;
	static Jukebox jukebox;

	/**
	 * Main method to select and play song.
	 * @param args
	 */
	public static void main(String[] args){

		jukebox = new Jukebox(0);

		String scan = "";
		Song song;

		boolean resultCoin;
		boolean input = false;
		boolean addMoreSong = true;

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Who are you ? (admin/user)");
		
		while (!input) {
			scan = scanner.nextLine();
			if(scan.equals("admin") || scan.equals("user")) {
				input = true;
				jukebox.setAdmin(true);
			} else
				System.out.println("Please say 'admin' or 'user'");
		}
		
//		while (!input) {
//			
//		}

		System.out.println("What would you like to do\n Press 1 for adding songs\n Press 2 for deleting songs");
		while(!input) {
			scan = scanner.nextLine();
			if(scan.equals("1") || scan.equals("2")) 
				input = true;
			else
				System.out.println("Please press 1 or 2");
		}

		if(scan.equals("1")) {
			while (addMoreSong) {
				System.out.println("Enter song name to add the song");

				String name = scanner.nextLine();

				System.out.println("Enter song location to add the song");

				String location = scanner.nextLine();

				System.out.println("Enter song fileName to add the song");

				String fileName = scanner.nextLine();

				song = new Song(name, "", "", "", location, fileName,"");

				jukebox.getSongList().addSong(song);

				System.out.println("The song" + name + 
						"has been added.\n Do you want to add another song ? (yes or no)");

				scan = scanner.nextLine();
				if (scan.equals("no"))
					addMoreSong = false;

			}

			System.out.println("How much money do you want to add ? ($1 : 1 song, $3 : 5 songs)");
			resultCoin = jukebox.insertCoin(Integer.parseInt(scanner.nextLine()));

			if (resultCoin) {

				System.out.println("Enter the name of a song :");
				song = jukebox.getSongList().getSongByName(scanner.nextLine());

				if (song != null) {

					System.out.println("Add the song to the queue ? (yes or no)");
					scanner = new Scanner(System.in);
					scan = scanner.nextLine();

					if (scan.equals("yes")) {

						jukebox.getSongQueue().add(song);

						System.out.println("====Now Playing Song====");

						//Set up the media playing object
						playedSong = new PlaySong();

						//Give it a tune to play
						playedSong.setMediaPlayer(song.getLocation(), song.getFileName());
					}

				} else {
					System.out.println("No song find with that name.");
				}

			} else {
				System.out.println("This amount is incorrect.");
			}

		} else if (scan.equals("2")) {
			System.out.println("Enter song name to delete the song");
			//jukebox.getSongList().deleteSong(song);				
		} else {
			System.out.println("This amount is incorrect.");
		}
	}

	/**
	 * This method read the song files to add them in the list
	 */
	public void addSongsInList() {
		// I create and insert the songs by reading each line of the files
		try {
			for (String line : Files.readAllLines(Paths.get("src/data/songs.txt"))) {			
				//matches sequence of one or more tabulations
				String data[] = line.split("\\t+");
				jukebox.getSongList().addSong(new Song(
						data[0], data[1], data[2], data[3], 
						data[4], data[5], data[6]));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
