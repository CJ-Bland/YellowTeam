package driver;
import controllers.Jukebox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.ChangeHandler;
import view.Screen0_Main;
import view.Screen1_View;
import view.Screen2_Admin;
import view.Screen3_AdminSettings;

/**
 * The main jukemeister driver, uses the in class example to demonstrate the composite design pattern
 * 
 * @author CJ
 *@version Sprint 3
 */
public class Main extends Application implements ChangeHandler{

	/**The current stage for the application**/
	Stage stage;

	/**the root scene for this app**/
	Scene rootScene;

	/**The menu screen**/
	private static Screen0_Main screenMain;

	private static Screen1_View screenView;

	private static Screen2_Admin screenAdmin;

	private static Screen3_AdminSettings screenAdminSettings;

	public static Jukebox jukebox;

	/**Start the application
	 * @param primaryStage The main application window**/
	@Override
	public void start(Stage primaryStage) {
		try {
			this.stage = primaryStage;
			stage.setTitle("JukeMeister");

			jukebox = new Jukebox();
			
			//Make the program full screen.	
			primaryStage.setFullScreen(true);

			//The scene for the first screen.
			Scene scene = new Scene(new Pane(),800,600);				
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			this.rootScene = scene;

			//show the home screen
			home();

			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**Change the root scene **/
	private void setScreen(Pane root){
		rootScene.setRoot(root);
	}
	
	/**
	 * Main method, what runs the whole thing
	 * @param args
	 */
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
		case SCREEN2:
			if(screenAdmin == null)
				screenAdmin = new Screen2_Admin(this);
			screen = screenAdmin;
			break;
		case SCREEN3:
			if(screenAdminSettings == null)
				screenAdminSettings = new Screen3_AdminSettings(this);
			screen = screenAdminSettings;
			break;
		default:
			if(screenMain== null){
				screenMain = new Screen0_Main(this);
			}

			screen = screenMain;
		}///end switch
		
		rootScene.setRoot(screen);
	}

	/**
	 * Sets the screen to the main "splash" screen
	 */
	public void home(){
		this.changeScreen(Screen.SCREEN0);
	}
}
