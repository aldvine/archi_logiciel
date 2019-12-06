package appli;

import appli.Character;
import appli.Controller;
import appli.View;
public class App {

	public static void main(String[] args) throws Exception {
		Character p1 = null;
		Character p2 = null;
		View view = new View();
		Controller controller = new Controller(view, p1, p2);
		controller.initView();

	}

}
