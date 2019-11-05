package tiers;

public class Afficheur implements IAfficheur {

	
	
	/* (non-Javadoc)
	 * @see tiers.IAfficheur#affiche(tiers.Person)
	 */
	@Override
	public void affiche(Person p) {
		System.out.println(p);
	}
	
}
