package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.Optional;

import entity.Song;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
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
/**
 * A tester screen based on class example
 * 
 * @author CJ
 *
 */
public class Screen1_View extends BorderPane{

	ChangeHandler screenChanger;
	Button home;
	final HBox searchHB = new HBox();
	final HBox bottomPane = new HBox();
	
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

	public Screen1_View(ChangeHandler handler){

		screenChanger = handler;				
		
		this.addTopPanel();
		this.addCenterPanel();
		this.addBottomPanel();
		this.addLeftPanel();
		this.addRightPanel();
		
	}
	
	public void addTopPanel(){
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
		searchHB.getStyleClass().add("hbox");
		
		this.setTop(searchHB);
		
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
	        //Create buttons and listeners
	        home = new Button("Home");	
	        home.setOnAction(buttonHandler);

	        //place buttons on panel that is added at the Bottom.	   
	        bottomPane.setId("South");
	        bottomPane.setSpacing(10.0);
	        bottomPane.setAlignment(Pos.CENTER);
	        bottomPane.getChildren().add(home);

	        //Style the bottom panel
	        bottomPane.getStyleClass().add("hbox");
	        
	        //Add panel to the bottom.
	        this.setBottom(bottomPane);	        
	        
	    }
	 
	 public void addLeftPanel(){
			
		}
	 
	 public void addRightPanel(){
			
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
