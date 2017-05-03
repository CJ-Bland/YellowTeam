package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The "splash" screen which is the jukemeisters main display screen
 * Switches to admin log in through keyboard commands, and to the user view through clicking
 * 
 * @author CJ
 *
 */
public class Screen0_Main extends VBox{

	ChangeHandler screenChanger;
	Button test;

	public Screen0_Main(ChangeHandler handler){

		screenChanger = handler;
		
		this.setAlignment(Pos.BOTTOM_CENTER);//SET the alignment of the  layout.
		this.setSpacing(20);//Spacing between components.
		
		final Label label = new Label("JUKEMEISTER");
		//label.setFont(new Font("Bungee Shade", 50));
		label.setFont(new Font("Arial", 50));
		label.setTextFill(Color.web("#f9f9f9"));
		
		
		TilePane tp = new TilePane(30,30);
		tp.setAlignment(Pos.CENTER);
		tp.setMaxSize(200, 200);
		tp.setMinSize(100, 100);
		tp.setPrefSize(120, 120);

		test = new Button("");
		test.setStyle("-fx-background-color: #262626;");
		test.setOnAction(buttonHandler);
		tp.getChildren().add(test);
		VBox.setMargin(test, new Insets(10, 10, 10, 10));
		
		String image = "/splash.png";
		this.setStyle("-fx-background-image: url('" + image + "'); " +
		           "-fx-background-position: center center; " +
		           "-fx-background-repeat: stretch;");
		         
		
		this.getChildren().add(tp);
		
		//Listen for keypress
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DELETE:   
                    	System.out.println("delete pressed");                	
                    	break;
                    case ENTER:   
                    	screenChanger.changeScreen(ChangeHandler.Screen.SCREEN2);
                    	System.out.println("enter pressed");
                    	break;
                }
            }
        });
        
        this.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse click detected! " + mouseEvent.getSource());
                screenChanger.changeScreen(ChangeHandler.Screen.SCREEN1);
            }
        });
	}

	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {

			Object o = event.getSource();

			if(o == test){
				screenChanger.changeScreen(ChangeHandler.Screen.SCREEN1);
			}

		}
	};

}
