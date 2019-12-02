package appli;

import appli.Character;

public interface IAfficheur {

	// retourne une chaine de caractere pour afficher un personnage

	String affiche(Character c);
	
	// retourne une chaine de caractere à afficher donnant de détail des personnages
	String afficheDetail(Character c1, Character c2);

}