package stuff;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import controllers.Jukebox;
import controllers.PlaySong;
import entity.Song;

/**
 * Driver of the Jukebox
 * 
 * @author Simon, Prem
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
		int scanInt = 0;
		Song song;

		boolean resultCoin;
		boolean input = false;
		boolean addMoreSong = true;

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Who are you ? (admin/user)");
		
		while (!input) {
			scan = scanner.nextLine();
			if(scan.equals("admin")){
				input = true;
				jukebox.setAdmin(true);
			}else if(scan.equals("user")){
				input = true;
			}
			else
				System.out.println("Please say 'admin' or 'user'");
		}
		
		while (!scan.equals("Q")) {

			System.out.println("What do you want to do ?\n"
					+ "(insert credit : I)\n"
					+ "(change credit price : CP)\n"
					+ "(addSongsInList : A)\n"
					+ "(quit : Q)");

			scan = scanner.nextLine();

			switch(scan) {
			case "I":
				System.out.println("How much money do you want to put ?\n"
						+ jukebox.getOneCreditPrice() + "$ for 1 credit\n"
						+ jukebox.getFiveCreditsPrice() + "$ for 5 credits");
				scanInt = scanner.nextInt();
				if (jukebox.insertCoin(scanInt)) {
					jukebox.getSongList().viewLibrary();
					if (scanInt == jukebox.getOneCreditPrice()) {
						System.out.println("Choose the song you want to add to the queue:");
						jukebox.getSongQueue().add(getSong(scanner.nextLine()));
					} else {
						System.out.println("Choose the first song you want to add to the queue:");
						jukebox.getSongQueue().add(getSong(scanner.nextLine()));
						System.out.println("Choose a second song:");
						jukebox.getSongQueue().add(getSong(scanner.nextLine()));
						System.out.println("Choose a third song");
						jukebox.getSongQueue().add(getSong(scanner.nextLine()));
						System.out.println("Choose a forth song:");
						jukebox.getSongQueue().add(getSong(scanner.nextLine()));
						System.out.println("Choose a last song");
						jukebox.getSongQueue().add(getSong(scanner.nextLine()));
					}
				} else {
					System.out.println("You didn't insert a valid amount");
				}
			case "CP":
				if (jukebox.isAdmin()) {
					System.out.println("Price for 1 credit (current is " + jukebox.getOneCreditPrice() + "):");
					scan = scanner.nextLine();
					jukebox.setOneCreditPrice(Integer.parseInt(scan));
					System.out.println("Price for 5 credits (current is " + jukebox.getFiveCreditsPrice() + "):");
					scan = scanner.nextLine();
					jukebox.setFiveCreditsPrice(Integer.parseInt(scan));
				} else
					System.out.println("You are not an admin.");
			case "A":
				if (jukebox.isAdmin())
					//addSongsInList();
				//else
					System.out.println("You are not an admin");
			}
		}
	}

	
	/**
	 * This method read the song files to add them in the list
	 */
	/*
	public static void addSongsInList() {
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
	}*/

	public static Song getSong(String song) {
		return null;
	}

}
