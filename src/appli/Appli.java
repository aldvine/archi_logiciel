package appli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import platform.Descriptor;
import platform.Platform;

public class Appli extends JFrame {

	// Pour le fonctionnement de l'application
	IAfficheur monAfficheur;
	IModifier monModifier;
	IBattle monBattle;
	List<Descriptor> listPlugins;
	Character p1; // personnage 1 
	Character p2; // personnage 2
	
	// Composants pour l'IHM
	private JPanel panModifier = new JPanel();
	private JPanel panAfficheur = new JPanel();
	private JPanel panBattle = new JPanel();
	
	Bouton charger1  = new Bouton("Charger Personnage 1") ;
 	Bouton afficherP1  = new Bouton("Afficher P1") ;
 	Bouton modifierP1  = new Bouton("Modifier P1") ;
 	
	Bouton charger2  = new Bouton("Charger Personnage 2") ;
 	Bouton modifierP2  = new Bouton("Modifier P2") ;
 	Bouton afficherP2  = new Bouton("Afficher P2") ;
 	
 	JScrollPane affichage = new javax.swing.JScrollPane();
 	JTextArea textArea = new javax.swing.JTextArea();
	
	// chargement de la fenetre d'execution

	public Appli() throws Exception {
		
		this.setTitle("Plugins");
		this.setSize(1000,500);
		this.setMinimumSize(this.getSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); 	
	 	
		
	 	JPanel menuCharacter = new JPanel();
		menuCharacter.setLayout(new GridLayout(2,2));
		
		JPanel panel1 = new JPanel();
	 	
	 	JLabel labelP1 = new JLabel("Personnage 1");
	 	panel1.add(labelP1);
		panel1.add(charger1);
		panel1.add(afficherP1);
		panel1.add(modifierP1);
		charger1.addActionListener(new BoutonGenerateCharacter());
		afficherP1.addActionListener(new BoutonAfficherPersonnage());
		modifierP1.addActionListener(new BoutonModifierPersonnage());
		
		JPanel panel2 = new JPanel();
		JLabel labelP2 = new JLabel("Personnage 2");
		panel2.add(labelP2);
		panel2.add(charger2);
		panel2.add(afficherP2);
		panel2.add(modifierP2);
		charger2.addActionListener(new BoutonGenerateCharacter());
		afficherP2.addActionListener(new BoutonAfficherPersonnage());
		modifierP2.addActionListener(new BoutonModifierPersonnage());
		
		JPanel actions = new JPanel();
		
		Bouton battleButton  = new Bouton("Confrontation") ;
		battleButton.addActionListener(new BoutonBattlePersonnage());
		actions.add(battleButton);
		
		Bouton afficheDetailButton  = new Bouton("Afficher le détail") ;
		afficheDetailButton.addActionListener(new BoutonDetailPersonnage());
		actions.add(afficheDetailButton);
		
	 	// zone de texte
	 	textArea.setEditable(false);
	 	affichage.setViewportView(textArea);
	 	
	 	
	 	menuCharacter.add(panel1);
	 	menuCharacter.add(panel2);
	 	JLabel pluginModifier = new JLabel("Plugin Modifier : ");
	 	panModifier.add(pluginModifier);
	 	JLabel pluginAfficheur = new JLabel("Plugin Afficheur : ");
	 	panAfficheur.add(pluginAfficheur);
	 	JLabel pluginBattle = new JLabel("Plugin Confrontation : ");
	 	panBattle.add(pluginBattle);

	 	// voir si mieux avec des GroupLayout
	 	GridLayout gridMain = new GridLayout(6,1);
	 	this.getContentPane().setLayout(gridMain); 
		this.getContentPane().add(menuCharacter);
		this.getContentPane().add(actions);
		this.getContentPane().add(affichage);
		this.getContentPane().add(panModifier);
		this.getContentPane().add(panAfficheur);
		this.getContentPane().add(panBattle);
		this.setVisible(true);
	}

	public void run() throws Exception {
		
		this.getListOfpluginsFromConfigFile();
		this.loadPluginsAutorun();
		this.genereBoutonFrom();
//		this.p1 = new Character("Michel", 100, 100, 50, 100, "inconnu", "normal");
	}

	public void afficheTexte(String text) {

		textArea.append(text);
		textArea.append("\n");
		// scroll automatique vers le bas pour l'affichage
		affichage.getVerticalScrollBar().setValue(affichage.getVerticalScrollBar().getMaximum());
	}
	
	public void affichePersonnage(Character c) {
		if(monAfficheur ==null) {
			afficheTexte("Aucun afficheur n'est chargé !");
		}else {
			if(c == null) {
				textArea.append("Le personnage n'est pas chargé !");
			}else {
				textArea.append(monAfficheur.affiche(c));
			}
		}
	
		textArea.append("\n");
		// scroll automatique vers le bas pour l'affichage
		affichage.getVerticalScrollBar().setValue(affichage.getVerticalScrollBar().getMaximum());
	}
	
	// affiche les infos des deux personnages
	public void afficheDetail() {
		if(monAfficheur ==null) {
			afficheTexte("Aucun afficheur n'est chargé !");
		}else {
			if(p1 == null) {
				textArea.append("Le personnage 1 n'est pas chargé !");
			}else if(p2 == null){
				textArea.append("Le personnage 2 n'est pas chargé !");	
			}else {
				textArea.append(monAfficheur.afficheDetail(p1, p2));
			}
		}
		textArea.append("\n");
		// scroll automatique vers le bas pour l'affichage
		affichage.getVerticalScrollBar().setValue(affichage.getVerticalScrollBar().getMaximum());
	}

	public void modifier(Character c) {
		if(monModifier ==null) {
			afficheTexte("Aucun plugin Modifier n'est chargé !");
		}else {
			if(c == null) {
				afficheTexte("Le personnage n'est pas chargé !");
			}else {
				monModifier.modifier(c);
				afficheTexte("Le personnage a été modifié ! ");
			}
		}
		
	}
	
	public void battle() {
		if(monBattle ==null) {
			afficheTexte("Aucun plugin Battle n'est chargé !");
		}else {
			if(p1 == null) {
				afficheTexte("Le personnage 1 n'est pas chargé !");
			}else if(p2 == null){
				afficheTexte("Le personnage 2 n'est pas chargé !");	
			}else {
				monBattle.battle(p1, p2);
				afficheTexte("Une confrontation a eu lieu ! ");
			}
		}
		
		// scroll automatique vers le bas pour l'affichage
	}

	public void getListOfpluginsFromConfigFile() throws Exception {
		this.listPlugins = Platform.parseConfigFiles();

	}
	

	public void loadPluginsAutorun() throws Exception {
		/*
		 * Methode qui charge les plugins dans le fichier de config autorun.txt. Ce
		 * fichier contient les id des plugins à charger au démarrage.
		 */
		afficheTexte("Chargement des plugins de démarrage...");
		Properties props = Platform.loadProperties("src/config/autorun/autorun.txt");
		this.loadPluginsFrom(props);
		afficheTexte("FIN Chargement des plugins de démarrage");
		afficheTexte("---------------------------------------");
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
//				System.out.println(idPlugin + "=" + descriptor.getId());
				this.assign(descriptor);
			}
		}
	}

	public void assign(Descriptor descriptor) throws Exception {
		String Iface = descriptor.getIface();
		switch (Iface) {
		case "appli.IAfficheur":
			this.monAfficheur = (IAfficheur) loadPlugin(descriptor);
			break;
		case "appli.IModifier":
			this.monModifier = (IModifier) loadPlugin(descriptor);
			break;
		case "appli.IBattle":
			this.monBattle = (IBattle) loadPlugin(descriptor);
			break;
		default:
			break;
		}
	}
	
	// chargement du plugin via la plateforme et affichage des messages de réussites/echec dans l'ihm
	public Object loadPlugin(Descriptor descriptor) throws Exception{
		Object plugin ;
		if(descriptor.getStatut() == "loaded") {
			afficheTexte("Plugin "+descriptor.getName()+" déjà chargé");
		}else {
			afficheTexte("Chargement du plugin "+descriptor.getName());
		}
		
		// chargement via la plateforme
		plugin = Platform.loadPlugin(descriptor);
		
		if(!plugin.equals(null) && descriptor.getStatut() == "loaded") {
			afficheTexte("Plugin "+descriptor.getName()+" activé");
		}else if(descriptor.getStatut() == "fail") {
			afficheTexte("Erreur lors du chargement du plugin "+descriptor.getName()+", il n'implemente pas l'interface "+ descriptor.getIface() );
		}
		afficheTexte("---------------------------------------");
		return plugin;			
	}
	
	public Character generateCharacter() {
		return new Character("Michel", 100, 100, 50, 100, "inconnu", "normal");
	}

	// TODO generer des boutons en fonction des fichiers de plugin.
	// le clique sur le bouton entraine le chargement du plugin . Il remplace le
	// plugin deja charg� si il y en avait deja un de charg�.
	
	public void genereBoutonFrom() {
		for (Descriptor d : this.listPlugins) {
			JButton bouton = new Bouton(d.getName());
			String iface = d.getIface();
			switch (iface) {
			case "appli.IAfficheur":
				bouton.addActionListener(new BoutonChargementPlugin(d.getId()));
				this.panAfficheur.add(bouton);
//				this.setContentPane(this.panAfficheur);
				break;
			case "appli.IModifier":
				bouton.addActionListener(new BoutonChargementPlugin(d.getId()));
				this.panModifier.add(bouton);
//				this.setContentPane(this.panModifier);
				break;
			case "appli.IBattle":
				bouton.addActionListener(new BoutonChargementPlugin(d.getId()));
				this.panBattle.add(bouton);
//				this.setContentPane(this.panModifier);
				break;
			default:
				break;
			}
			pack();
		}
	}
	

	// bouton pour generer les personnages
	class BoutonGenerateCharacter implements ActionListener {
		BoutonGenerateCharacter() {
		}
		public void actionPerformed(ActionEvent arg0) {
			Character c = generateCharacter();
			if(arg0.getSource() == charger1)
				p1 = c;
			if(arg0.getSource() == charger2)
				p2 = c;
			afficheTexte("Génération d'un personnage");
			affichePersonnage(c);
		}
	}
	
	class BoutonAfficherPersonnage implements ActionListener {
		BoutonAfficherPersonnage() {
		}
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == afficherP1)
				affichePersonnage(p1);
			if(arg0.getSource() == afficherP2)
				affichePersonnage(p2);
		}
	}
	
	class BoutonModifierPersonnage implements ActionListener {
		BoutonModifierPersonnage() {
		}
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == modifierP1)
				modifier(p1);
			if(arg0.getSource() == modifierP2)
				modifier(p2);
		}
	}
	
	class BoutonBattlePersonnage implements ActionListener {
		BoutonBattlePersonnage() {
		}
		public void actionPerformed(ActionEvent arg0) {
			battle();
		}
	}
	class BoutonDetailPersonnage implements ActionListener {
		BoutonDetailPersonnage() {
		}
		public void actionPerformed(ActionEvent arg0) {
			afficheDetail();
		}
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


}
