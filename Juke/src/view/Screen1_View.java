package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import entity.Song;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
/**
 * A tester screen based on class example
 * 
 * @author CJ
 *
 */
public class Screen1_View extends BorderPane{

	ChangeHandler screenChanger;
	Button home;
	final ProgressBar progress = new ProgressBar();
	private ChangeListener<Duration> progressChangeListener;
	public static final int FILE_EXTENSION_LEN = 3;

	final Label currentlyPlaying = new Label();

	final HBox searchHB = new HBox();
	final HBox homeHB = new HBox();
	final AnchorPane topPane = new AnchorPane();

	static MediaPlayer mediaPlayer = null;
	private Queue<Media> queue = new LinkedList<Media>();

	public Song currentSong;
	public ImageView currentImage = new ImageView();

	Pane pane = new Pane();
	Label nameL = new Label();
	Label artistL = new Label();
	Label albumL = new Label();

	private TableView<Song> table = new TableView<Song>();

	private ObservableList<Song> data =
			FXCollections.observableArrayList();

	private final ObservableList<Song> fullData = FXCollections.observableArrayList(
			new Song("Daybreak", "Unknown", "Unknown", "","file:/C:/Daybreak.mp3", new ImageView(new Image("/img.jpg"))),
			new Song("Yakaty Sax", "Boots Randolph", "Yakaty Sax", "","file:/C:/Benny-hill-theme.mp3", null),
			new Song("Jam Funk Rhythm", "Unknown", "Unknown","","file:/C:/JamFunkRhythm-DRAFT.wav", new ImageView(new Image("/img1.jpg"))),
			new Song("Something Funky", "Unknown", "Unknown", "","file:/C:/SomthingFunkyV3b.mp3", new ImageView(new Image("/img3.jpg"))),
			new Song("C90", "Unknown", "Unknown", "","file:/C:/C90.mp3", null),
			new Song("Engine", "Unknown", "Unknown", "", "file:/C:/engine.wav", new ImageView(new Image("/img1.jpg"))),
			new Song("Slow Blues", "Unknown", "Unknown", "", "file:/C:/SlowBluesyV3.0.mp3", null)
			);

	public Screen1_View(ChangeHandler handler){			   

		screenChanger = handler;
		pane.getChildren().add(currentImage);
		currentImage.setFitWidth(115);
		currentImage.setFitHeight(115);
		currentImage.setPreserveRatio(true);

		this.addTopPanel();
		this.addCenterPanel();
		this.addBottomPanel();
		this.addLeftPanel();
		this.addRightPanel();
		this.setOnRowClick();
	}
	public void createList(){
		final List<MediaPlayer> players = new ArrayList<>();
		for (Song file : data) {
			players.add(createPlayer(file.getFileName()));
		}
		if (players.isEmpty()) {
			System.out.println("File names not valid");
			Platform.exit();
			return;
		}    
	}

	private MediaPlayer createPlayer(String mediaSource) {
		final Media media = new Media(mediaSource);
		final MediaPlayer player = new MediaPlayer(media);
		player.setOnError(new Runnable() {
			@Override public void run() {
				System.out.println("Media error occurred: " + player.getError());
			}
		});
		return player;
	}

	public void setOnRowClick(){
		table.setRowFactory( tv -> {
			TableRow<Song> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					Song rowData = row.getItem();
					System.out.println(rowData.getName().toString());

					//WILL IMPLEMENT WITH PLAYSONG AND SONGQUEUE WHEN INTERFACE IS MADE
					//Right now just plays when the song is clicked, not added to a queue

					//bip gets file name from file column		
					String bip = rowData.getFileName().toString();
					Media hit = new Media(bip);
					queue.add(hit);
					
					//update current song for now playing display
					currentSong = rowData;
					currentImage.setImage(currentSong.getArtwork().getImage());									

					if(mediaPlayer == null){						
						//mediaPlayer = new MediaPlayer(hit);
						mediaPlayer = new MediaPlayer(queue.poll());
						mediaPlayer.play();
						setCurrentlyPlaying(mediaPlayer);
					}
					else{						
						mediaPlayer.stop();
						mediaPlayer = new MediaPlayer(hit);
						mediaPlayer.play();
						setCurrentlyPlaying(mediaPlayer);
					}					
				}
			});
			return row ;
		});
	}


	public void addTopPanel(){	
		topPane.setId("Top");
		
		home = new Button("Home");	
		home.setOnAction(buttonHandler);	 				
		homeHB.getChildren().add(home);	

		final TextField searchTerm = new TextField();
		//searchTerm.setMaxWidth(.getPrefWidth());
		searchTerm.setPromptText("Search Term");

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
		searchHB.setAlignment(Pos.CENTER_RIGHT);
		topPane.getChildren().addAll(homeHB, searchHB);
		AnchorPane.setRightAnchor(searchHB, 5.0);
		AnchorPane.setTopAnchor(searchHB, 5.0);
		AnchorPane.setLeftAnchor(homeHB, 5.0);
		AnchorPane.setTopAnchor(homeHB, 5.0);
		topPane.setId("hbox");
		this.setTop(topPane);

	}


	@SuppressWarnings("unchecked")
	public void addCenterPanel(){
		table.setEditable(true);

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
		this.setCenter((table));

		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);		
		
	}

	public void addBottomPanel(){	 
		
		final VBox bottomVB = new VBox();
		//bottomVB.setStyle("-fx-font-size: 15; -fx-padding: 20; -fx-alignment: center; -fx-background-color: linear-gradient(#CCCCFF, #8888FF);");
		bottomVB.setId("hbox");
		
		final Label label = new Label("NOW PLAYING");
		label.setFont(new Font("Arial", 25));
		label.setTextFill(Color.web("#f9f9f9"));
		//label.getStyleClass().add("outline");		

		final HBox songInfo = new HBox(20);		 		

		final VBox content = new VBox(10);
		content.getChildren().setAll(		        
				nameL,
				artistL, 
				albumL		        
				);
		nameL.setTextFill(Color.web("#f9f9f9"));
		artistL.setTextFill(Color.web("#f9f9f9"));
		albumL.setTextFill(Color.web("#f9f9f9"));

		songInfo.getChildren().setAll(pane, content, progress);
		songInfo.setAlignment(Pos.CENTER);
		HBox.setHgrow(pane, Priority.ALWAYS);
		HBox.setHgrow(content, Priority.ALWAYS);
		HBox.setHgrow(progress, Priority.ALWAYS);

		bottomVB.getChildren().setAll(label, songInfo);
		bottomVB.setAlignment(Pos.CENTER);
		VBox.setMargin(label, new Insets(5, 10, 20, 10));
		this.setBottom(bottomVB);
		progress.setMaxWidth(Double.MAX_VALUE);
		//HBox.setHgrow(progress, Priority.ALWAYS);
	}

	public void addLeftPanel(){

	}

	public void addRightPanel(){

	}
	/** sets the currently playing label to the label of the new media player and updates the progress monitor. */
	private void setCurrentlyPlaying(final MediaPlayer newPlayer) {
		newPlayer.seek(Duration.ZERO);

		progress.setProgress(0);
		progressChangeListener = new ChangeListener<Duration>() {
			@Override public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
				progress.setProgress(1.0 * newPlayer.getCurrentTime().toMillis() / newPlayer.getTotalDuration().toMillis());
				progress.setStyle("-fx-accent: yellow;");
			}
		};
		newPlayer.currentTimeProperty().addListener(progressChangeListener);

		String source = newPlayer.getMedia().getSource();
		source = source.substring(0, source.length() - FILE_EXTENSION_LEN);
		source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");
		currentlyPlaying.setText("Now Playing: " + source);
		nameL.setText("NAME: " + currentSong.getName());
		artistL.setText("ARTIST: " + currentSong.getArtist());
		albumL.setText("ABLUM: " + currentSong.getAlbum());				
		}
	

	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {

			Object o = event.getSource();

			if(o == home){
				if(screenChanger != null){
					screenChanger.home();
				}
			}


		}//end event
	};

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
