package tiers;

import appli.IAfficheur;
import appli.Character;


public class AfficheurUpgraded implements IAfficheur {

	
	@Override
	public void affiche(Character c) {
		System.out.println(c+" Affichage Upgraded");
	}
	
}
