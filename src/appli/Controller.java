package appli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import appli.Character;
import appli.View;
import platform.Descriptor;
import platform.Platform;

public class Controller {
	private View view;
	private Character personnageOne;
	private Character personnageTwo;
	private List<Descriptor> listPlugins;
	IAfficheur monAfficheur;
	IModifier monModifier;
	IBattle monBattle;

	public Controller(View view, Character personnageOne, Character personnageTwo) {
		super();
		this.view = view;
		this.personnageOne = personnageOne;
		this.personnageTwo = personnageTwo;
	}

	// initialisation de la fenetre
	public void initView() throws Exception {
		this.view.getCharger1().addActionListener(new BoutonGenerateCharacter());
		this.view.getCharger2().addActionListener(new BoutonGenerateCharacter());
		this.view.getAfficherP1().addActionListener(new BoutonAfficherPersonnage());
		this.view.getAfficherP2().addActionListener(new BoutonAfficherPersonnage());
		this.view.getModifierP1().addActionListener(new BoutonModifierPersonnage());
		this.view.getModifierP2().addActionListener(new BoutonModifierPersonnage());
		this.view.getBattleButton().addActionListener(new BoutonBattlePersonnage());
		this.view.getAfficheDetailButton().addActionListener(new BoutonDetailPersonnage());
		this.getListOfpluginsFromConfigFile();
		this.loadPluginsAutorun();
		this.genereBouton();

	}

	public void getListOfpluginsFromConfigFile() throws Exception {
		this.listPlugins = Platform.parseConfigFiles();

	}

	public void loadPluginsAutorun() throws Exception {
		/*
		 * Methode qui charge les plugins dans le fichier de config autorun.txt. Ce
		 * fichier contient les id des plugins à charger au démarrage.
		 */
		view.afficheTexte("Chargement des plugins de démarrage...");
		Properties props = Platform.loadProperties("src/config/autorun/autorun.txt");
		this.loadPluginsFrom(props);
		view.afficheTexte("FIN Chargement des plugins de démarrage");
		view.afficheTexte("---------------------------------------");
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
				this.assign(descriptor);
			}
		}
	}

	// assigne le plugin au bon objet
	public void assign(Descriptor descriptor) throws Exception {
		String Iface = descriptor.getIface();
		switch (Iface) {
		case "appli.IAfficheur":
			IAfficheur pluginAfficher = (IAfficheur) loadPlugin(descriptor);
			monAfficheur = pluginAfficher;
			break;
		case "appli.IModifier":
			IModifier pluginModifier = (IModifier) loadPlugin(descriptor);
			monModifier = pluginModifier;
			break;
		case "appli.IBattle":
			IBattle pluginBattle = (IBattle) loadPlugin(descriptor);
			monBattle = pluginBattle;
			break;
		default:
			break;
		}
	}

	// chargement du plugin via Platform et affichage des logs dans la partie informations
	public Object loadPlugin(Descriptor descriptor) throws Exception {
		Object plugin;
		if (descriptor.getStatut() == "loaded") {
			view.afficheTexte("Plugin " + descriptor.getName() + " déjà chargé");
		} else {
			view.afficheTexte("Chargement du plugin " + descriptor.getName());
		}

		// chargement via la plateforme
		plugin = Platform.loadPlugin(descriptor);
		if (plugin != null && descriptor.getStatut() == "loaded") {
			view.afficheTexte("Plugin " + descriptor.getName() + " activé");
		} else if (descriptor.getStatut() == "fail_interface") {
			view.afficheTexte("Erreur lors du chargement du plugin " + descriptor.getName()
					+ ", l'interface " + descriptor.getIface() + " à implementé n'existe pas.");
		}else if (descriptor.getStatut() == "fail_class") {
				view.afficheTexte("Erreur lors du chargement du plugin " + descriptor.getName()
						+ ", la classe " + descriptor.getClassName()+" n'existe pas.");
		}else if (descriptor.getStatut() == "fail_implement") {
			view.afficheTexte("Erreur lors du chargement du plugin "+descriptor.getName()+", il n'implemente pas l'interface "+ descriptor.getIface() );
		}else {
			view.afficheTexte("Une erreur inconnue est survenue.");
		}
		view.afficheTexte("---------------------------------------");
		return plugin;
	}

	// generation des boutons de plugins
	public void genereBouton() {
		for (Descriptor d : this.listPlugins) {
			Bouton bouton = new Bouton(d.getName());
			String iface = d.getIface();
			switch (iface) {
			case "appli.IAfficheur":
				bouton.addActionListener(new BoutonChargementPlugin(d.getId()));
				view.getPanAfficheur().add(bouton);
				break;
			case "appli.IModifier":
				bouton.addActionListener(new BoutonChargementPlugin(d.getId()));
				view.getPanModifier().add(bouton);
				break;
			case "appli.IBattle":
				bouton.addActionListener(new BoutonChargementPlugin(d.getId()));
				view.getPanBattle().add(bouton);
				break;
			default:
				break;
			}
			view.pack();
		}
	}

	// appel de la méthode affiche du plugin Afficheur
	public void affichePersonnage(Character c) {
		if (monAfficheur == null) {
			view.afficheTexte("Aucun afficheur n'est chargé !");
		} else {
			if (c == null) {
				view.afficheTexte("Le personnage n'est pas chargé !");
			} else {
//				view.refreshFrame();
				monAfficheur.affiche(view.getAfficheurJPanel(), c);
				
			}
		}
		view.refreshFrame();

	}

	// affiche les infos des deux personnages
	public void afficheDetail() {
		if (monAfficheur == null) {
			view.afficheTexte("Aucun afficheur n'est chargé !");
		} else {
			if (personnageOne == null) {
				view.afficheTexte("Le personnage 1 n'est pas chargé !");
			} else if (personnageTwo == null) {
				view.afficheTexte("Le personnage 2 n'est pas chargé !");
			} else {
				monAfficheur.afficheDetail(view.getAfficheurJPanel(),personnageOne, personnageTwo);
			}
		}
	}

	public Character generateCharacter(String name) {
		//Nombre entre 10 et 150
		Random randomGenerator = new Random();
		ArrayList<Integer> nb = new ArrayList<Integer>();
		int randomInt ;
		Character c = new Character();
		for (int i = 0; i < 4; i++) {
			randomInt = randomGenerator.nextInt(150); 
			nb.add(((randomInt + 9) / 10 ) * 10);	
		}

		return new Character(name, nb.get(0), nb.get(1), nb.get(2), nb.get(3), "inconnu", "normal");
	}

	// appel de la méthode modifier du plugin Modifier
	public void modifier(Character c) {
		if (monModifier == null) {
			view.afficheTexte("Aucun plugin Modifier n'est chargé !");
		} else {
			if (c == null) {
				view.afficheTexte("Le personnage n'est pas chargé !");
			} else {
				monModifier.modifier(c);
				view.afficheTexte("Le personnage a été modifié ! ");
			}
		}

	}

	// appel de la méthode battle du plugin Battle
	public void battle() {
		if (monBattle == null) {
			view.afficheTexte("Aucun plugin Battle n'est chargé !");
		} else {
			if (personnageOne == null) {
				view.afficheTexte("Le personnage 1 n'est pas chargé !");
			} else if (personnageTwo == null) {
				view.afficheTexte("Le personnage 2 n'est pas chargé !");
			} else {
				monBattle.battle(personnageOne, personnageTwo);
				view.afficheTexte("Une confrontation a eu lieu ! ");
			}
		}

		// scroll automatique vers le bas pour l'affichage
	}

	class BoutonChargementPlugin implements ActionListener {
		String id;

		BoutonChargementPlugin(String id) {
			this.id = id;
		}

		public void actionPerformed(ActionEvent arg0) {
			try {
				searchInListDescriptor(this.id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	class BoutonBattlePersonnage implements ActionListener {
		BoutonBattlePersonnage() {
		}
		public void actionPerformed(ActionEvent arg0) {
			battle();
		}
	}

	class BoutonGenerateCharacter implements ActionListener {
		BoutonGenerateCharacter() {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == view.getCharger1()) {
				personnageOne = generateCharacter("Player 1");
			}
			if (arg0.getSource() == view.getCharger2())
				personnageTwo = generateCharacter("Player 2");
			view.afficheTexte("Génération d'un personnage");
//			affichePersonnage(nouveauPersonnage);
		}
	}

	class BoutonModifierPersonnage implements ActionListener {
		BoutonModifierPersonnage() {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == view.getModifierP1())
				modifier(personnageOne);
			if (arg0.getSource() == view.getModifierP2())
				modifier(personnageTwo);
		}
	}

	class BoutonAfficherPersonnage implements ActionListener {
		BoutonAfficherPersonnage() {
		}

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == view.getAfficherP1())
				affichePersonnage(personnageOne);
			if (arg0.getSource() == view.getAfficherP2())
				affichePersonnage(personnageTwo);
		}
	}
	
	class BoutonDetailPersonnage implements ActionListener {
		BoutonDetailPersonnage() {
		}
		public void actionPerformed(ActionEvent arg0) {
			afficheDetail();
		}
	}
}
