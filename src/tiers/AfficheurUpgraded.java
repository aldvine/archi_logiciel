package tiers;

import appli.Character;
import appli.IAfficheur;


public class AfficheurUpgraded implements IAfficheur {

	
	@Override
	public String affiche(Character c) {
		return c.toString()+" Affichage Upgraded";
	}
	
	@Override
	public String afficheDetail(Character c1, Character c2) {
		return "*****-----Detail-----***** \n "+c1.toString() + " \n "+ c2.toString();
	}
	
}
