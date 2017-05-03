package view;

import driver.Main;
import entity.Song;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * The view that allows the admin to change settings
 * 
 * @author Simon and CJ
 */
public class Screen3_AdminSettings extends VBox {

	ChangeHandler screenChanger;
	GridPane grid;
	Button save;
	Button home;
	Label newPasswordLabel;
	Label newPasswordConfirmLabel;
	Label oneCreditPriceLabel;
	Label fiveCreditsPriceLabel;
	Label displayVenueNameLabel;
	Label displayMessageLabel;
	TextField newPassword;
	TextField newPasswordConfirm;
	TextField oneCreditPrice;
	TextField fiveCreditsPrice;
	TextField displayMessage;
	RadioButton venueNameYes;
	RadioButton venueNameNo;
	RadioButton messageYes;
	RadioButton messageNo;
	ToggleGroup groupVenue;
	ToggleGroup groupMessage;

	/**
	 * Constructor of the class
	 * 
	 * @param handler
	 */
	public Screen3_AdminSettings(ChangeHandler handler){
		VBox box = new VBox();
		Label title = new Label("ADMIN SETTINGS");
		title.setTextFill(Color.web("#f9f9f9"));

		title.setStyle("-fx-font-size:35px; -fx-padding: 50px;");

		screenChanger = handler;

		this.setAlignment(Pos.CENTER);//SET the alignment of the  layout.
		this.setSpacing(20);

		//Create Grid
		grid = new GridPane();
		grid.setHgap(5.0);
		grid.setVgap(5.0);
		//grid.setAlignment(Pos.CENTER);

		//Creating labels
		addLabel(newPasswordLabel, "New PIN:",0,0,1,1);
		addLabel(newPasswordConfirmLabel, "Confirm New PIN:",0,1,1,1);
		addLabel(oneCreditPriceLabel, "Set Price for one credit:",0,2,1,1);
		addLabel(fiveCreditsPriceLabel, "Set Price for 5 credits:",0,3,1,1);
		addLabel(displayVenueNameLabel, "Display the venue name?",0,4,1,1);
		addLabel(displayMessageLabel, "Set Message",0,5,1,1);

		//Creating textfields
		//addTextField helper method was not working properly, did it the long way and it works now

		//addTextField(newPassword, "New PIN",1,0,2,1);
		newPassword = new TextField();
		newPassword.setPromptText("New PIN");
		newPassword.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(newPassword, 1, 0, 2, 1);

		//addTextField(newPasswordConfirm, "Confirm PIN",1,1,2,1);
		newPasswordConfirm = new TextField();
		newPasswordConfirm.setPromptText("Confirm PIN");
		newPasswordConfirm.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(newPasswordConfirm, 1, 1, 2, 1);

		//addTextField(oneCreditPrice, "1 Price",1,2,2,1);
		oneCreditPrice = new TextField();
		oneCreditPrice.setPromptText("1 Price");
		oneCreditPrice.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(oneCreditPrice, 1, 2, 2, 1);

		//addTextField(fiveCreditsPrice, "5 Price",1,3,2,1);
		fiveCreditsPrice = new TextField();
		fiveCreditsPrice.setPromptText("5 Price");
		fiveCreditsPrice.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(fiveCreditsPrice, 1, 3, 2, 1);

		//addTextField(displayMessage, "Set Message",1,5,2,1);
		displayMessage = new TextField();
		displayMessage.setPromptText("Set Message");
		displayMessage.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(displayMessage, 1, 5, 2, 1);

		//Creating checkbox
		groupVenue = new ToggleGroup();
		venueNameYes = new RadioButton("Yes");
		venueNameYes.setStyle("-fx-text-fill: #f9f9f9;");
		venueNameYes.setToggleGroup(groupVenue);
		venueNameNo = new RadioButton("No");
		venueNameNo.setStyle("-fx-text-fill: #f9f9f9;");
		venueNameNo.setToggleGroup(groupVenue);
		groupMessage = new ToggleGroup();
		messageYes = new RadioButton("Yes");
		messageYes.setStyle("-fx-text-fill: #f9f9f9;");
		messageYes.setToggleGroup(groupMessage);
		messageNo = new RadioButton("No");
		messageNo.setStyle("-fx-text-fill: #f9f9f9;");
		messageNo.setToggleGroup(groupMessage);

		if (Main.jukebox.isDisplayMessage())
			messageYes.setSelected(true);
		else
			messageNo.setSelected(false);
		if (Main.jukebox.isDisplayVName())
			venueNameYes.setSelected(true);
		else
			venueNameNo.setSelected(false);
		// Adding them to the grid
		grid.add(venueNameYes,1,4,1,1);
		grid.add(venueNameNo,2,4,1,1);
		grid.add(messageYes,1,6,1,1);
		grid.add(messageNo,2,6,1,1);

		//Creating buttons
		//addButton helper method was not working properly, did it the long way and it works now

		//addButton(save, "Save",1,7,1,1);	
		//addButton(home, "Back",2,7,1,1);
		save = new Button("Save");
		save.setOnAction(buttonHandler);
		save.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(save, 1, 7, 1, 1);

		home = new Button("Back");
		home.setOnAction(buttonHandler);
		home.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(home, 2, 7, 1, 1);

		//Grid constraints for row sizing
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100/8);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(100/8);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(100/8);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(100/8);
		RowConstraints row5 = new RowConstraints();
		row5.setPercentHeight(100/8);
		RowConstraints row6 = new RowConstraints();
		row6.setPercentHeight(100/8);
		RowConstraints row7 = new RowConstraints();
		row7.setPercentHeight(100/8);
		RowConstraints row8 = new RowConstraints();
		row8.setPercentHeight(100/8);

		//Grid constraints for column resizing
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(60);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(20);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(20);

		grid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7);
		grid.getColumnConstraints().addAll(col1, col2, col3);

		grid.setStyle("-fx-padding: 50px; -fx-font-size:25px;");

		//Added text fields for adding song functionality
		
		final TextField addName = new TextField();
		addName.setPromptText("Name");
		addName.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		//addName.setMaxWidth(nameCol.getPrefWidth());

		final TextField addArtist = new TextField();
		//addArtist.setMaxWidth(artistCol.getPrefWidth());
		addArtist.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		addArtist.setPromptText("Artist");

		final TextField addAlbum = new TextField();
		addAlbum.setPromptText("Album");
		addAlbum.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		//addAlbum.setMaxWidth(nameCol.getPrefWidth());

		final TextField addFilePath = new TextField();
		//addFilePath.setMaxWidth(fileCol.getPrefWidth());
		addFilePath.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		addFilePath.setPromptText("File Path");

		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//if the fields are full, then it will add. Prevents adding empty rows
				if(!addName.getText().isEmpty() && !addArtist.getText().isEmpty() 
						&& !addAlbum.getText().isEmpty() && !addFilePath.getText().isEmpty()){
					Screen1_View.fullData.add(new Song(
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
				else{
					System.out.println("Must have fields, cannot be blank");
				}
			}			
		});
		VBox addSongs = new VBox(10);
		Label dis = new Label("ADD NEW SONG");
		dis.setTextFill(Color.web("#f9f9f9"));
		dis.setStyle("-fx-font-size:25px;");
		VBox.setMargin(addSongs, new Insets(10, 10, 10, 10));
		addSongs.setAlignment(Pos.CENTER);
		
		HBox addhb = new HBox();
		addhb.getChildren().addAll(addName, addArtist, addAlbum, addFilePath, addButton);
		addhb.setSpacing(5);
		addhb.setAlignment(Pos.CENTER);
		addSongs.getChildren().addAll(dis, addhb);
		
		box.getChildren().addAll(title, addSongs, grid);
		box.setAlignment(Pos.CENTER);
		VBox.setMargin(box, new Insets(30, 10, 10, 10));
		this.getChildren().add(box); 
	}

	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {

			Object o = event.getSource();

			if (o == home) {
				System.out.println("is home");				
				if(screenChanger != null){
					screenChanger.changeScreen(ChangeHandler.Screen.SCREEN2);
					newPassword.setText("");
					newPasswordConfirm.setText("");
				}
			} else if (o == save) {
				System.out.println("is save");
				System.out.println("New pin is: " + newPasswordConfirm.getText());
				if (!newPasswordConfirm.getText().equals(""))
					if (newPassword.getText().equals(newPasswordConfirm.getText()))
						Main.jukebox.setPassword(newPasswordConfirm.getText());
					else
						infoBox("Passwords are different", "Error", "Error");
				if (!oneCreditPrice.getText().equals(""))
					Main.jukebox.setOneCreditPrice(Integer.parseInt(oneCreditPrice.getText()));
				if (!fiveCreditsPrice.getText().equals(""))
					Main.jukebox.setFiveCreditsPrice(Integer.parseInt(fiveCreditsPrice.getText()));
				if (!displayMessage.getText().equals(""))
					Main.jukebox.setMessage(displayMessage.getText());
				if (venueNameYes.isSelected())
					Main.jukebox.setDisplayVName(true);
				else
					Main.jukebox.setDisplayVName(false);
				if (messageYes.isSelected())
					Main.jukebox.setDisplayMessage(true);
				else
					Main.jukebox.setDisplayMessage(false);
			}

		}//end event
	};

	/**
	 * Method that creates a label and add it to the grid
	 * 
	 * @param label
	 * @param value
	 * @param colIndex
	 * @param rowIndex
	 * @param colSpan
	 * @param rowSpan
	 */
	public void addLabel(Label label, String value, int colIndex, int rowIndex, int colSpan, int rowSpan) {
		label = new Label();
		label.setText(value);
		label.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(label, colIndex, rowIndex, colSpan, rowSpan);
		label.setTextFill(Color.web("#f9f9f9"));
	}

	/**
	 * Method that creates a textfield
	 * 
	 * @param textField
	 * @param value
	 * @param colIndex
	 * @param rowIndex
	 * @param colSpan
	 * @param rowSpan
	 */
	public void addTextField(TextField textField, String value, int colIndex, int rowIndex, int colSpan, int rowSpan) {
		textField = new TextField();
		textField.setPromptText(value);
		textField.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(textField, colIndex, rowIndex, colSpan, rowSpan);
	}

	/**
	 * Method that creates a button
	 * 
	 * @param button
	 * @param value
	 * @param colIndex
	 * @param rowIndex
	 * @param colSpan
	 * @param rowSpan
	 */
	public void addButton(Button button, String value, int colIndex, int rowIndex, int colSpan, int rowSpan) {
		button = new Button(value);
		button.setOnAction(buttonHandler);
		button.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		grid.add(button, colIndex, rowIndex, colSpan, rowSpan);

	}

	/**
	 * Method used to show an info box
	 * 
	 * @param infoMessage
	 * @param titleBar
	 * @param headerMessage
	 */
	public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

}
