package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * A tester scene based on class example 
 * 
 * @author CJ
 *
 */
public class Screen0_Main extends VBox{

	ChangeHandler screenChanger;
	Button test;

	public Screen0_Main(ChangeHandler handler){

		screenChanger = handler;
		this.setAlignment(Pos.CENTER);//SET the alignment of the  layout.
		this.setSpacing(20);//Spacing between components.

		Label lbl = new Label("Welcome to JukeMeister");
		//lbl.getStyleClass().add("screenTitle");
		this.getChildren().add(lbl);

		TilePane tp = new TilePane(30,30);
		tp.setAlignment(Pos.CENTER);
		tp.setMaxSize(200, 200);
		tp.setMinSize(100, 100);
		tp.setPrefSize(120, 120);

		test = new Button("Test");
		test.setOnAction(buttonHandler);
		tp.getChildren().add(test);

		this.getChildren().add(tp);
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
