import java.util.Optional;

import entity.Song;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * A testing class which has a rudimentary UI and includes sorting and searching functionality
 * @author CJ
 *
 */
public class Testing extends Application {

	private TableView<TestSong> table = new TableView<TestSong>();
	private ObservableList<TestSong> data =
			FXCollections.observableArrayList();

	private final ObservableList<TestSong> fullData = FXCollections.observableArrayList(
			new TestSong("ABC", "Michael Jackson", "abc", "C://Music/abc.mp3"),
			new TestSong("Yakaty Sax", "Boots Randolph", "Yakaty Sax", "C://Music/sax.mp3")
			);

	final HBox hb = new HBox();

	final HBox searchHB = new HBox();

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage stage) {
		//Sets up the scene
		Scene scene = new Scene(new Group());
		stage.setTitle("JukeMeister");
		stage.setWidth(550);
		stage.setHeight(550);

		final Label label = new Label("Songs");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		//Creates all the columns of the table
		TableColumn nameCol = new TableColumn("Song Name");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Song, String>("name"));

		TableColumn artistCol = new TableColumn("Artist");
		artistCol.setMinWidth(100);
		artistCol.setCellValueFactory(
				new PropertyValueFactory<Song, String>("artist"));

		TableColumn albumCol = new TableColumn("Album");
		albumCol.setMinWidth(100);
		albumCol.setCellValueFactory(
				new PropertyValueFactory<Song, String>("album"));

		TableColumn fileCol = new TableColumn("File Name");
		fileCol.setMinWidth(200);
		fileCol.setCellValueFactory(
				new PropertyValueFactory<Song, String>("file"));

		//Pop
		data=fullData;
		table.setItems(data);
		table.getColumns().addAll(nameCol, artistCol, albumCol, fileCol);

		//Makes the file names column blank, unnecessary for users to see
		//fileCol.setVisible(false);

		final TextField addName = new TextField();
		addName.setPromptText("Name");
		addName.setMaxWidth(nameCol.getPrefWidth());

		final TextField addArtist = new TextField();
		addArtist.setMaxWidth(artistCol.getPrefWidth());
		addArtist.setPromptText("Artist");

		final TextField addAlbum = new TextField();
		addAlbum.setPromptText("Album");
		addAlbum.setMaxWidth(nameCol.getPrefWidth());

		final TextField addFilePath = new TextField();
		addFilePath.setMaxWidth(fileCol.getPrefWidth());
		addFilePath.setPromptText("File Path");

		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data.add(new TestSong(
						addName.getText(),
						addArtist.getText(),
						addAlbum.getText(),
						addFilePath.getText()));
				addName.clear();
				addArtist.clear();
				addAlbum.clear();
				addFilePath.clear();
			}
		});


		hb.getChildren().addAll(addName, addArtist, addAlbum, addFilePath, addButton);
		hb.setSpacing(5);

		final TextField searchTerm = new TextField();
		searchTerm.setMaxWidth(fileCol.getPrefWidth());
		searchTerm.setPromptText("Search Term");

		
		/**
		 * Creates a search button and implements a case insensitive search of the songs, the column to be searched
		 * through is chosen by the user through a dialog prompt
		 */
		final Button search = new Button("Search");
		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//a sub list which holds all songs that meet search criteria
				final ObservableList<TestSong> searchData =
						FXCollections.observableArrayList();
				//creates the dialog box
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setTitle("Select Search Focus");
				alert.setContentText("Choose which field to search through");
				
				ButtonType bName = new ButtonType("Name");
				ButtonType bArtist = new ButtonType("Artist");
				ButtonType bAlbum = new ButtonType("Album");
				ButtonType bCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

				alert.getButtonTypes().setAll(bName, bArtist, bAlbum, bCancel);

				Optional<ButtonType> result = alert.showAndWait();
				
				//Handles all the possible button presses
				//Each button has a similar function (which I dont think can be put into helper functions)
					//It searches through the chosen column, adds it to the new list, and if some song is found
					//Adds the list to the table. If the user does not opt to view the full data, any further searches
					//Will be from the sublist, not the full list of songs
				if (result.get() == bName){
					System.out.println("Searching name for " + searchTerm.getText());
					int i=0;
					boolean found = false;
					do{
						
						if(data.get(i).getName().toLowerCase().equals(searchTerm.getText().toLowerCase()) )
						{
							//System.out.println(searchTerm.getText());
							found = true;
							searchData.add(data.get(i));				
						}
						i++;
					}while(data.size()>i);

					if(!found){
						//System.out.println("No Matches Found");
						infoBox("No Matches Found", "Error");
					}
					else if(found){
						data=searchData;
						table.setItems(data);
					}
				} else if (result.get() == bArtist) {
					System.out.println("Searching artist for " + searchTerm.getText());
					int i=0;
					boolean found = false;
					do{
						if(data.get(i).getArtist().toLowerCase().equals(searchTerm.getText().toLowerCase()) )
						{
							//System.out.println(searchTerm.getText());
							found = true;
							searchData.add(data.get(i));
						}
						i++;
					}while(data.size()>i);
					if(!found){
						//System.out.println("No Matches Found");
						infoBox("No Matches Found", "Error");
					}
					else if(found){
						data=searchData;
						table.setItems(data);
					}
				} else if (result.get() == bAlbum) {
					System.out.println("Searching album for " + searchTerm.getText());
					int i=0;
					boolean found = false;
					do{
						if(data.get(i).getAlbum().toLowerCase().equals(searchTerm.getText().toLowerCase()) )
						{
							//System.out.println(searchTerm.getText());
							found = true;
							searchData.add(data.get(i));
						}
						i++;
					}while(data.size()>i);
					if(!found){
						//System.out.println("No Matches Found");
						infoBox("No Matches Found", "Error");
					}
					else if(found){
						data=searchData;
						table.setItems(data);
					}
				} else {
					//user chose CANCEL or closed the dialog
				}
			}
		});

		//Fills the table with the full list of songs
		final Button viewAll = new Button("See Full List");
		viewAll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data = fullData;
				table.setItems(data);
			}
		});

		searchHB.getChildren().addAll(searchTerm, search, viewAll);
		searchHB.setSpacing(5);

		final VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb, searchHB);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	//Dialog methods which create the not found box
	public static void infoBox(String infoMessage, String titleBar)
	{
		/* By specifying a null headerMessage String, we cause the dialog to
           not have a header */
		infoBox(infoMessage, titleBar, null);
	}
	
	//Dialog methods which create the not found box
	public static void infoBox(String infoMessage, String titleBar, String headerMessage)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

	/**
	 * An inner class to represent song with only string parameters, no other classes
	 * @author CJ
	 */
	public static class TestSong {
		private final SimpleStringProperty name;
		private final SimpleStringProperty artist;
		private final SimpleStringProperty album;
		private final SimpleStringProperty file;

		private TestSong(String name, String artist, String album, String file) {
			this.name = new SimpleStringProperty(name);
			this.artist = new SimpleStringProperty(artist);
			this.album = new SimpleStringProperty(album);
			this.file = new SimpleStringProperty(file);
		}

		public String getName() {
			return name.get();
		}
		public void setName(String name) {
			this.name.set(name);
		}

		public String getArtist() {
			return artist.get();
		}
		public void setArtist(String artist) {
			this.artist.set(artist);
		}

		public String getAlbum() {
			return album.get();
		}
		public void setAlbum(String album) {
			this.album.set(album);
		}

		public String getFile() {
			return file.get();
		}
		public void setFile(String file) {
			this.file.set(file);
		}

	}

} 