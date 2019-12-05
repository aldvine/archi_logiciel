package tiers;

import appli.Character;
import appli.IAfficheur;

public class AfficheurClassic implements IAfficheur {

	@Override
	public String affiche(Character c) {
		return c.toString();
	}
	
	@Override
	public String afficheDetail(Character c1, Character c2) {
		return c1.toString() + " \n "+ c2.toString();
	}
	
	
}
