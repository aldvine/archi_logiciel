package appli;

public interface IAfficheur {
	
	// retourne une chaine de caractere pour afficher un personnage

	String affiche(Character c);
	
	// retourne une chaine de caractere � afficher donnant de d�tail des personnages
	String afficheDetail(Character c1, Character c2);
	
	

}
