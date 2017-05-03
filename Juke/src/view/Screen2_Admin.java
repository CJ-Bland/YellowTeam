package view;

import driver.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * The view that allows the admin to login
 * 
 * @author Simon
 * (key press implementation added by CJ)
 */
public class Screen2_Admin extends VBox {

	ChangeHandler screenChanger;
	Button login;
	Button home;
	Label thePassword;
	PasswordField pswd;

	/**
	 * Constructor of the class
	 * 
	 * @param handler
	 */
	public Screen2_Admin(ChangeHandler handler){

		screenChanger = handler;

		this.setAlignment(Pos.CENTER);//SET the alignment of the  layout.
		this.setSpacing(20);

		TilePane tp = new TilePane(30,30);
		tp.setAlignment(Pos.CENTER);
		tp.setMaxSize(200, 200);
		tp.setMinSize(100, 100);
		tp.setPrefSize(120, 120);

		thePassword = new Label();
		thePassword.setText("Password:");
		tp.getChildren().add(thePassword);

		pswd = new PasswordField();
		pswd.setPromptText("password");
		tp.getChildren().add(pswd);

		login = new Button("Login");
		login.setOnAction(buttonHandler);
		tp.getChildren().add(login);

		home = new Button("Home");
		home.setOnAction(buttonHandler);
		tp.getChildren().add(home);

		tp.setStyle("-fx-font-size: 40px");

		this.getChildren().add(tp); 
		
		//Listen for keypress
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {                    
                    case ENTER:   
                    	if (pswd.getText().equals(Main.jukebox.getPassword())) {
        					screenChanger.changeScreen(ChangeHandler.Screen.SCREEN3);
        					pswd.setText("");
        				} else {
        					Alert alert = new Alert(AlertType.INFORMATION);
        					alert.setTitle("Password incorrect");
        					alert.setHeaderText("Error");
        					alert.setContentText("Password incorrect");
        					alert.showAndWait();
        				}
                    	break;
                }
            }
        });
	}

	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {

			Object o = event.getSource();

			if (o == home) {
				if(screenChanger != null){
					screenChanger.home();
					pswd.setText("");
				}
			} else if (o == login) {
				if (pswd.getText().equals(Main.jukebox.getPassword())) {
					screenChanger.changeScreen(ChangeHandler.Screen.SCREEN3);
					pswd.setText("");
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Password incorrect");
					alert.setHeaderText("Error");
					alert.setContentText("Password incorrect");
					alert.showAndWait();
				}
			}

		}//end event
	};

}
