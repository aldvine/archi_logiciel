package appli;


import java.util.List;

import platform.Descriptor;
import platform.Loader;
import tiers.Person;
public class Appli {
	
	IAfficheur monAfficheur; 
	
	public Appli() throws Exception {
		List<Descriptor> list = Loader.getDescriptor(IAfficheur.class);
		System.out.println(list);
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
