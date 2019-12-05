package tiers;

import appli.Character;
import appli.IModifier;


public class ModifierClassic implements IModifier {

	/*
	 * (non-Javadoc)
	 * Modifier le personnage pour que sa vie augmente de 10
	 */
	@Override
	public void modifier(Character c) {
		c.setSante(c.getSante()+10);
	}
	
}
