package tiers;

import appli.Character;
import appli.IBattle;


public class BattleClassic implements IBattle {

	/*
	 * (non-Javadoc)
	 * fait combattre 2 personnage, certaines caractéristique impacte le combat
	 */
	@Override
	public void battle(Character c1, Character c2) {
		int degatc1 =10;
		int degatc2 =10;
		if(c1.getForce() >= c2.getForce()) {
			degatc1 = +10;
		}else {
			degatc2 = +0;
		}
		
		if(c1.getAgilite() >= c2.getAgilite()) {
			c1.setForce(c2.getForce()-10);
		}else {
			c1.setForce(c1.getForce()-10);
		}
		c1.setSante(c1.getSante()-degatc2);
		c2.setSante(c2.getSante()-degatc1);
		
	}
	
}
