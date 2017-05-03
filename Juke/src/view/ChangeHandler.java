package view;

public interface ChangeHandler {

	/**The available screens**/
	public static enum  Screen {SCREEN0,SCREEN1, SCREEN2, SCREEN3};
	
	/**An event to handle screen changes**/
	public void changeScreen(Screen screenChoice);
	
	/**Take to the home screen**/
	public void home();
}
