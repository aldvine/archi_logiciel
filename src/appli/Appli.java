package appli;


import java.io.FileReader;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;

import platform.Descriptor;
import platform.Platform;
import tiers.Person;
public class Appli {
	
	IAfficheur monAfficheur; 
	IModifier monModifier; 
	List<Descriptor> listPlugins;
	
	Character soldat ;
	
	public Appli() throws Exception {
		this.listPlugins = Platform.parseConfigFiles();
		
//		List<Descriptor> list = Platform.getDescriptor(IAfficheur.class);
//		this.monAfficheur = (IAfficheur) Platform.loadPlugin(listPlugins.get(0));
//		System.out.println(test);
		
		loadPluginsAutorun();
	
	}

	public void affiche(Character c) {
		monAfficheur.affiche(c);
	}
	public void modifier(Character c) {
		monModifier.modifier(c);
	}
	
	// Chargement des plugins qui sont dans le fichier autorun.txt , il contient les id des plugins à charger au démarrage
	public void loadPluginsAutorun() throws Exception{
		
		System.out.println("Chargement des plugins de démarrage...");
		Properties props = Platform.loadProperties("src/config/autorun/autorun.txt");
		
		// pour chaque plugin en autorun, on le charge
		for (String idAutorun : props.stringPropertyNames()) {
			String idPlugin = props.getProperty(idAutorun);
			// on le cherche dans la liste des descripteurs
			for (Descriptor descriptor : listPlugins) {
				if(descriptor.getId().equals(idPlugin)) {
					System.out.println(idPlugin+"="+descriptor.getId());
					switch (descriptor.getIface()) {
					case "appli.IAfficheur":
						this.monAfficheur = (IAfficheur) Platform.loadPlugin(descriptor);
						break;
					case "appli.IModifier":
						this.monModifier = (IModifier) Platform.loadPlugin(descriptor);
						break;
					default:
						break;
					}
					break;
				}
			}
		}
		System.out.println("Chargement des plugins de démarrage...FIN");
	}
	
	
	// TODO generer des boutons en fonction des fichiers de plugin. 
	// le clique sur le bouton entraine le chargement du plugin . Il remplace le plugin deja chargé si il y en avait deja un de chargé. 
	public void run() throws Exception{
		Fenetre f = new  Fenetre();
		f.ajoutBoutonPlugin(this.listPlugins);
		soldat = new Character("Michel", 100, 100, 50, 100, "inconnu", "normal");
		
		affiche(soldat);
		System.out.println("utilisateur du plugin de modification pour test");
		modifier(soldat);
		affiche(soldat);
	}

	
}
