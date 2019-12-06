package appli;

import javax.swing.JPanel;

public interface IAfficheur {
	
	// Prend en parametre un JPanel pour pouvoir modifier l'apparence du container Afficheur
	// le but est d'afficher un personnage ou autre chose si vous avez envie
	void affiche(JPanel panel , Character c);
	
	// Prend en parametre un JPanel pour pouvoir modifier l'apparence du container Afficheur
	// le but est d'afficher le detail des deux personnages qui s'affronte ou autre chose si vous avez envie :)
	void afficheDetail(JPanel panel, Character c1, Character c2);
	
	

}
