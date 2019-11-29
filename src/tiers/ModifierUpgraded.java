package tiers;

import appli.IModifier;
import appli.Character;


public class ModifierUpgraded implements IModifier {

	/*
	 * (non-Javadoc)
	 * Modifier le personnage pour que sa vie augmente de 10 et sa force de 10
	 */
	@Override
	public void modifier(Character c) {
		c.setForce(c.getForce()+10);
		c.setSante(c.getSante()+10);
	}
	
}
