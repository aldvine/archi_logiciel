package tiers;

import appli.IModifier;
import appli.Character;


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
