package appli;

public class Main {

	//1 extract method du sysout --> affiche() 
	//2 Create class Afficheur
	//3 déplacer la méthode affiche() -> Afficheur
	//4 add Field Afficheur to Appli
	//5 source -> generateDelegateMethod affiche()
	//6 Sur Afficheur clic droit -> refactor -> Extrat interface
	public static void main(String[] args) throws Exception {
		
		Appli a  = new Appli();
		a.run();
	}
	
}
