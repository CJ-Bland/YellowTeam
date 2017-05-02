package stuff;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import controllers.PlaySong;
import entity.Song;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.embed.swing.JFXPanel;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * A testing class which has a rudimentary UI and includes sorting and searching functionality
 * @author CJ
 * @version Sprint 3
 */
public class Testing extends Application {

	static MediaPlayer mediaPlayer = null;

	private TableView<Song> table = new TableView<Song>();
	
	private ObservableList<Song> data =
			FXCollections.observableArrayList();

	private final ObservableList<Song> fullData = FXCollections.observableArrayList(
			new Song("Daybreak", "Unknown", "Unknown", "","file:/C:/Daybreak.mp3", new ImageView(new Image("/img.jpg"))),
			new Song("Yakaty Sax", "Boots Randolph", "Yakaty Sax", "","file:/C:/Benny-hill-theme.mp3", null),
			new Song("Jam Funk Rhythm", "Unknown", "Unknown","","file:/C:/JamFunkRhythm-DRAFT.wav", new ImageView(new Image("/img1.jpg"))),
			new Song("Something Funky", "Unknown", "Unknown", "","file:/C:/SomthingFunkyV3b.mp3", new ImageView(new Image("/img.jpg"))),
			new Song("C90", "Unknown", "Unknown", "","file:/C:/C90.mp3", null),
			new Song("Engine", "Unknown", "Unknown", "", "file:/C:/engine.wav", new ImageView(new Image("/img1.jpg"))),
			new Song("Slow Blues", "Unknown", "Unknown", "", "file:/C:/SlowBluesyV3.0.mp3", null)
			);

	final HBox hb = new HBox();

	final HBox searchHB = new HBox();

	private Queue<Media> queue = new LinkedList<Media>();
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) {
		//Sets up the scene
				
		Scene scene = new Scene(new Group());
		stage.setTitle("JukeMeister");
		//stage.setWidth(550);
		stage.setHeight(550);

		final Label label = new Label("Songs");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);		
		
		//Creates all the columns of the table
		
		TableColumn<Song, ImageView> imgCol = new TableColumn<Song, ImageView>("Artwork");
		imgCol.setMinWidth(50);
		imgCol.setPrefWidth(100);
		imgCol.setMaxWidth(200);
		imgCol.setCellValueFactory(
				new PropertyValueFactory<Song, ImageView>("artwork"));		          
		
		TableColumn<Song, String> nameCol = new TableColumn<Song, String>("Song Name");
		nameCol.setMinWidth(50);
		nameCol.setPrefWidth(100);
		nameCol.setMaxWidth(200);
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Song, String>("name"));

		TableColumn<Song, String> artistCol = new TableColumn<Song, String>("Artist");
		artistCol.setMinWidth(50);
		artistCol.setPrefWidth(100);
		artistCol.setMaxWidth(200);
		artistCol.setCellValueFactory(
				new PropertyValueFactory<Song, String>("artist"));

		TableColumn<Song, String> albumCol = new TableColumn<Song, String>("Album");
		albumCol.setMinWidth(50);
		albumCol.setPrefWidth(100);
		albumCol.setMaxWidth(200);
		albumCol.setCellValueFactory(
				new PropertyValueFactory<Song, String>("album"));

		TableColumn<Song, String> fileCol = new TableColumn<Song, String>("File Name");
		fileCol.setMinWidth(100);
		fileCol.setPrefWidth(200);
		fileCol.setMaxWidth(500);
		fileCol.setCellValueFactory(
				new PropertyValueFactory<Song, String>("fileName"));
				
		//Populates the table
		data=fullData;
		for(int i =0; i < data.size(); i++){
			//If no artwork, set to default pic
			if(data.get(i).getArtwork() == null){
				data.get(i).setArtwork(new ImageView(new Image("/img2.jpg")));
			}
			//Sets restrictions on img dimensions
			data.get(i).getArtwork().setFitWidth(95);
			data.get(i).getArtwork().setFitHeight(90);
			data.get(i).getArtwork().setPreserveRatio(true);			
		}
		table.setItems(data);
		table.getColumns().addAll(imgCol, nameCol, artistCol, albumCol, fileCol);
		//table.setMaxSize(500, 500);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				
		//A handler to register which row is clicked
		table.setRowFactory( tv -> {
			TableRow<Song> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					Song rowData = row.getItem();
					System.out.println(rowData.getName().toString());

					//WILL IMPLEMENT WITH PLAYSONG AND SONGQUEUE WHEN INTERFACE IS MADE
					//Right now just plays when the song is clicked, not added to a queue
					//JFXPanel fxPanel = new JFXPanel();
					
					//bip gets file name from file column			
					
					String bip = rowData.getFileName().toString();
					Media hit = new Media(bip);
					queue.add(hit);
					
					
					
					if(mediaPlayer == null){						
						//mediaPlayer = new MediaPlayer(hit);
						mediaPlayer = new MediaPlayer(queue.poll());
						mediaPlayer.play();
					}
					//else{
						//queue.add(hit);
						/*mediaPlayer.stop();
						mediaPlayer = new MediaPlayer(hit);
						mediaPlayer.play();
						
						*/				
					
					/*
					else{
					
					final MediaPlayer player     =  new MediaPlayer(queue.poll());
				    final MediaPlayer nextPlayer = new MediaPlayer(queue.peek());
				    player.setOnEndOfMedia(new Runnable() {
				        @Override public void run() {
				          //player.currentTimeProperty().removeListener(progressChangeListener);
				          //player.getMedia().getMetadata().removeListener(metadataChangeListener);
				          player.stop();
				          
				          nextPlayer.play();
				        }
				    });
					
				        */  
				}
			});
			return row ;
		});
		
		while(!queue.isEmpty()){
		      final MediaPlayer player     = new MediaPlayer(queue.poll());
		      final MediaPlayer nextPlayer = new MediaPlayer(queue.peek());
		      player.setOnEndOfMedia(new Runnable() {
		        @Override public void run() {
		          //player.currentTimeProperty().removeListener(progressChangeListener);
		          //player.getMedia().getMetadata().removeListener(metadataChangeListener);
		          player.stop();
		          //mediaView.setMediaPlayer(nextPlayer);
		          nextPlayer.play();
		        }
		      });
		    }

		//Makes the file names column blank, unnecessary for users to see
		//fileCol.setVisible(false);

		//ADD FUNCTIONALITY IS ONLY HERE FOR TESTING PURPOSES, WILL NOT BE AVAILABLE TO USERS IN LATER VERSIONS

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
				data.add(new Song(
						addName.getText(),
						addArtist.getText(),
						addAlbum.getText(),
						"",
						addFilePath.getText(),
						null));
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
				final ObservableList<Song> searchData =
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

						if(data.get(i).getName().toLowerCase().contains(searchTerm.getText().toLowerCase()) )
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
						if(data.get(i).getArtist().toLowerCase().contains(searchTerm.getText().toLowerCase()) )
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
						if(data.get(i).getAlbum().toLowerCase().contains(searchTerm.getText().toLowerCase()) )
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

} 