package appli;


import tiers.IAfficheur;
import tiers.Loader;
import tiers.Person;
public class Appli {
	
	IAfficheur monAfficheur; 
	
	public Appli() throws Exception {
		this.monAfficheur = (IAfficheur) Loader.load(IAfficheur.class, "afficheur");
	}

	public void affiche(Person p) {
		monAfficheur.affiche(p);
	}

	public void run(){
		Person p = new Person("Toto","Nantes",12);// b
		p.setAge(p.getAge()+1);
		affiche(p);
	}
	
}
