package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * A tester screen based on class example
 * 
 * @author CJ
 *
 */
public class Screen1_View extends VBox{

	ChangeHandler screenChanger;
	Button home;

	public Screen1_View(ChangeHandler handler){

		screenChanger = handler;

		this.setAlignment(Pos.CENTER);//SET the alignment of the  layout.
		this.setSpacing(20);
		
		TilePane tp = new TilePane(30,30);
		tp.setAlignment(Pos.CENTER);
		tp.setMaxSize(200, 200);
		tp.setMinSize(100, 100);
		tp.setPrefSize(120, 120);

		home = new Button("Home");
		home.setOnAction(buttonHandler);
		tp.getChildren().add(home);
		
		this.getChildren().add(tp); 
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
}
