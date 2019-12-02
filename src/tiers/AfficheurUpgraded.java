package tiers;

import appli.IAfficheur;
import appli.Character;


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
