package appli;

import java.util.List;
import java.util.Properties;
import platform.Descriptor;
import platform.Platform;

public class Appli {

	IAfficheur monAfficheur;
	IModifier monModifier;
	List<Descriptor> listPlugins;
	Character soldat;

	public Appli() throws Exception {
	}

	public void run() throws Exception {
		this.getListOfpluginsFromConfigFile();
		this.loadPluginsAutorun();
		this.soldat = new Character("Michel", 100, 100, 50, 100, "inconnu", "normal");
	}

	public void affiche() {
		monAfficheur.affiche(this.soldat);
	}

	public void modifier() {
		monModifier.modifier(this.soldat);
	}

	public void getListOfpluginsFromConfigFile() throws Exception {
		this.listPlugins = Platform.parseConfigFiles();

	}

	public void loadPluginsAutorun() throws Exception {
		/*
		 * Methode qui charge les plugins dans le fichier de config autorun.txt. Ce
		 * fichier contient les id des plugins à charger au démarrage.
		 */

		System.out.println("Chargement des plugins de d�marrage...");
		Properties props = Platform.loadProperties("src/config/autorun/autorun.txt");
		this.loadPluginsFrom(props);
		System.out.println("Chargement des plugins de d�marrage...FIN");
	}

	public void loadPluginsFrom(Properties props) throws Exception {
		for (String idAutorun : props.stringPropertyNames()) {
			String idPlugin = props.getProperty(idAutorun);
			this.searchInListDescriptor(idPlugin);
		}

	}

	public void searchInListDescriptor(String idPlugin) throws Exception {
		for (Descriptor descriptor : this.listPlugins) {
			if (descriptor.getId().equals(idPlugin)) {
				System.out.println(idPlugin + "=" + descriptor.getId());
				this.assign(descriptor);
			}
		}
	}

	public void assign(Descriptor descriptor) throws Exception {
		String Iface = descriptor.getIface();
		switch (Iface) {
		case "appli.IAfficheur":
			this.monAfficheur = (IAfficheur) Platform.loadPlugin(descriptor);
			break;
		case "appli.IModifier":
			this.monModifier = (IModifier) Platform.loadPlugin(descriptor);
			break;
		default:
			break;
		}

	}

	// TODO generer des boutons en fonction des fichiers de plugin.
	// le clique sur le bouton entraine le chargement du plugin . Il remplace le
	// plugin deja charg� si il y en avait deja un de charg�.

}
