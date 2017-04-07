import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.ChangeHandler;
import view.Screen0_Main;
import view.Screen1_View;

/**
 * A tester driver with rudimentary UI based on class example
 * 
 * @author CJ
 *
 */
public class CJ_TestDriver extends Application implements ChangeHandler{

	/**The current stage for the application**/
	Stage stage;
	
	/**the root scene for this app**/
	Scene rootScene;
	
	/**The menu screen**/
	private static Screen0_Main screenMain;
	
	private static Screen1_View screenView;
	
	//========================================================================
		/**Start our application
		 * @param primaryStage The main application window**/
		//=========================================================================
		@Override
		public void start(Stage primaryStage) {
			try {
				this.stage = primaryStage;
				
				//Make the program full screen.	
				primaryStage.setFullScreen(true);
							
				//The scene for the first screen.
				Scene scene = new Scene(new Pane(),800,600);
				this.rootScene = scene;
				
				//show the home screen
				home();
				
				
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		/**Change the root scene for this app**/
		private void setScreen(Pane root){
	    	rootScene.setRoot(root);
	    }
		public static void main(String[] args) {
			launch(args);
		}
		
		/**The implementation of the ScreenChangeHandler interface
		    * @param screenChoice The screen chosen.
		    */
			//=====================================================================
			@Override
			public void changeScreen(ChangeHandler.Screen screenChoice) {
				Pane screen;
				   
				   switch(screenChoice){
					   case SCREEN1:
						   if(screenView == null)
							   screenView = new Screen1_View(this);
						   screen = screenView;
						   break;
						 default:
							  if(screenMain== null){
								   screenMain = new Screen0_Main(this);
							  }
						
							   screen = screenMain;
				   }///end switch
				rootScene.setRoot(screen);
			}
			
			public void home(){
				this.changeScreen(Screen.SCREEN0);
			}
}
