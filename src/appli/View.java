package appli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;
import javax.swing.JFileChooser;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;


public class View extends JFrame {
	 // Composants pour l'IHM
    private JPanel panModifier;
    private JPanel panAfficheur;
    private JPanel panBattle;
    private Bouton charger1;
    private Bouton afficherP1;
    private Bouton modifierP1;
    private Bouton charger2;
    private Bouton modifierP2;
    private Bouton afficherP2;
    private JScrollPane affichage;
    private JTextArea textArea;
    private JPanel panel1;
    private JLabel labelP1;
    private JPanel menuCharacter;
    private JPanel actions;
    private Bouton battleButton;;
    private JPanel panel2;
    private JLabel labelP2;
    private Bouton afficheDetailButton;
    private JLabel pluginModifier;
    private JLabel pluginAfficheur;
    private JLabel pluginBattle;
    private GridLayout gridMain;
    private JPanel afficheurJPanel;

	public View() throws Exception {
    	panModifier = new JPanel();
    	panBattle = new JPanel();
    	panAfficheur =new JPanel();
    	actions = new JPanel();
    	charger1 = new Bouton("Charger");
    	afficherP1 = new Bouton("Afficher");
    	modifierP1 = new Bouton("Modifier");
    	charger2 = new Bouton("Charger");
    	modifierP2 = new Bouton("Modifier");
    	afficherP2 = new Bouton("Afficher");
    	affichage = new javax.swing.JScrollPane();
    	textArea = new javax.swing.JTextArea();
		menuCharacter = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		labelP1 = new JLabel("Personnage 1");
		labelP2 = new JLabel("Personnage 2");
		battleButton = new Bouton("Confrontation");
		afficheDetailButton = new Bouton("Afficher le détail");
		pluginModifier = new JLabel("Plugin Modifier : ");
		pluginAfficheur = new JLabel("Plugin Afficheur : ");
		pluginBattle = new JLabel("Plugin Confrontation : ");
		// voir si mieux avec des GroupLayout
		GridLayout gridMain = new GridLayout(6, 1);
    
		this.setTitle("Plugins");
		this.setSize(1000,500);
		this.setMinimumSize(this.getSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); 	
	 	
	
	 	Border borderCharacter = BorderFactory.createTitledBorder("Les personnages");
	 	menuCharacter.setBorder(borderCharacter);
		
		Border borderP1 = BorderFactory.createTitledBorder("Personnage 1");
		panel1.setBorder(borderP1);
	 	
		panel1.add(charger1);
		panel1.add(afficherP1);
		panel1.add(modifierP1);

		
		Border borderP2 = BorderFactory.createTitledBorder("Personnage 2");
		panel2.setBorder(borderP2);
		panel2.add(charger2);
		panel2.add(afficherP2);
		panel2.add(modifierP2);

		
		// action confrontation et afficher le détail des personnages
		Border borderActions= BorderFactory.createTitledBorder("Actions");
		actions.setBorder(borderActions);
		actions.add(battleButton);
		actions.add(afficheDetailButton);
		
	 	// zone de texte visible
	 	textArea.setEditable(false);
	 	textArea.setRows(10);
	 	affichage.setViewportView(textArea);
		Border borderAffichage= BorderFactory.createTitledBorder("Informations");
		affichage.setBorder(borderAffichage);
	 	
	 	
	 	menuCharacter.add(panel1);
	 	menuCharacter.add(new JSeparator());
	 	menuCharacter.add(panel2);
	 	
	 	// titre des parties de plugins
	 	Border borderPluginModifier= BorderFactory.createTitledBorder("Plugin Modifier");
	 	panModifier.setBorder(borderPluginModifier);
	 	Border borderPluginAfficheur= BorderFactory.createTitledBorder("Plugin Afficheur");
	 	panAfficheur.setBorder(borderPluginAfficheur);
	 	Border borderPluginBattle= BorderFactory.createTitledBorder("Plugin Confrontation");
	 	panBattle.setBorder(borderPluginBattle);
	 	
	 	afficheurJPanel = new JPanel();
	 	Border borderAfficheur= BorderFactory.createTitledBorder("Afficheur");
	 	afficheurJPanel.setBorder(borderAfficheur);
	 	// ajout des partie de l'ihm
	 	this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.getContentPane().add(menuCharacter);
		this.getContentPane().add(afficheurJPanel);
		this.getContentPane().add(actions);
		this.getContentPane().add(affichage);
		this.getContentPane().add(panAfficheur);
		this.getContentPane().add(panModifier);
		this.getContentPane().add(panBattle);
		this.setVisible(true);

    }

    public JPanel getAfficheurJPanel() {
		return afficheurJPanel;
	}
    
	public void setAfficheurJPanel(JPanel afficheurJPanel) {
		this.afficheurJPanel = afficheurJPanel;
	}
	
	public JPanel getPanModifier() {
		return panModifier;
	}

	public void setPanModifier(JPanel panModifier) {
		this.panModifier = panModifier;
	}

	public JPanel getPanBattle() {
		return panBattle;
	}

	public void setPanBattle(JPanel panBattle) {
		this.panBattle = panBattle;
	}

	public Bouton getCharger1() {
		return charger1;
	}

	public void setCharger1(Bouton charger1) {
		this.charger1 = charger1;
	}

	public Bouton getAfficherP1() {
		return afficherP1;
	}

	public void setAfficherP1(Bouton afficherP1) {
		this.afficherP1 = afficherP1;
	}

	public Bouton getModifierP1() {
		return modifierP1;
	}

	public void setModifierP1(Bouton modifierP1) {
		this.modifierP1 = modifierP1;
	}

	public Bouton getCharger2() {
		return charger2;
	}

	public void setCharger2(Bouton charger2) {
		this.charger2 = charger2;
	}

	public Bouton getModifierP2() {
		return modifierP2;
	}

	public void setModifierP2(Bouton modifierP2) {
		this.modifierP2 = modifierP2;
	}

	public Bouton getAfficherP2() {
		return afficherP2;
	}

	public void setAfficherP2(Bouton afficherP2) {
		this.afficherP2 = afficherP2;
	}

	public JScrollPane getAffichage() {
		return affichage;
	}

	public void setAffichage(JScrollPane affichage) {
		this.affichage = affichage;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JLabel getLabelP1() {
		return labelP1;
	}

	public void setLabelP1(JLabel labelP1) {
		this.labelP1 = labelP1;
	}

	public JPanel getMenuCharacter() {
		return menuCharacter;
	}

	public void setMenuCharacter(JPanel menuCharacter) {
		this.menuCharacter = menuCharacter;
	}

	public JPanel getActions() {
		return actions;
	}

	public void setActions(JPanel actions) {
		this.actions = actions;
	}

	public Bouton getBattleButton() {
		return battleButton;
	}

	public void setBattleButton(Bouton battleButton) {
		this.battleButton = battleButton;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JLabel getLabelP2() {
		return labelP2;
	}

	public void setLabelP2(JLabel labelP2) {
		this.labelP2 = labelP2;
	}

	public Bouton getAfficheDetailButton() {
		return afficheDetailButton;
	}

	public void setAfficheDetailButton(Bouton afficheDetailButton) {
		this.afficheDetailButton = afficheDetailButton;
	}

	public JLabel getPluginModifier() {
		return pluginModifier;
	}

	public void setPluginModifier(JLabel pluginModifier) {
		this.pluginModifier = pluginModifier;
	}

	public JLabel getPluginAfficheur() {
		return pluginAfficheur;
	}

	public void setPluginAfficheur(JLabel pluginAfficheur) {
		this.pluginAfficheur = pluginAfficheur;
	}

	public JLabel getPluginBattle() {
		return pluginBattle;
	}

	public void setPluginBattle(JLabel pluginBattle) {
		this.pluginBattle = pluginBattle;
	}

	public GridLayout getGridMain() {
		return gridMain;
	}

	public void setGridMain(GridLayout gridMain) {
		this.gridMain = gridMain;
	}

	public void setPanAfficheur(JPanel panAfficheur) {
		this.panAfficheur = panAfficheur;
	}
	public JPanel getPanAfficheur() {
		return panAfficheur;
	}
	
	public void afficheTexte(String text) {
		textArea.append(text);
		textArea.append("\n");
		affichage.getVerticalScrollBar().setValue(affichage.getVerticalScrollBar().getMaximum());
	}
	
	public void refreshFrame() {
		this.pack();
	}
	

}
