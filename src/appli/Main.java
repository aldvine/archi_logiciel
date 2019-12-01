package appli;

public class Main {

	// 1 extract method du sysout --> affiche()
	// 2 Create class Afficheur
	// 3 d�placer la m�thode affiche() -> Afficheur
	// 4 add Field Afficheur to Appli
	// 5 source -> generateDelegateMethod affiche()
	// 6 Sur Afficheur clic droit -> refactor -> Extrat interface
	public static void main(String[] args) throws Exception {
		Appli app = new Appli();
		Fenetre ihm = new Fenetre(app);
		ihm.start();
	}

}
