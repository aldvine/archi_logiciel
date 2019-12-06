package tiers;

import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import appli.Character;
import appli.IAfficheur;

public class AfficheurClassic implements IAfficheur {

	@Override
	public void affiche(JPanel panel, Character c) {
		// clean le container
		panel.removeAll();
		
		TextArea textArea = new TextArea();
		JScrollPane scrollScreen = new JScrollPane();
		scrollScreen.setSize(panel.getSize());
		textArea.setSize(panel.getSize());
		textArea.setEditable(false);
		textArea.setText(c.toString());
		scrollScreen.setViewportView(textArea);
		panel.add(scrollScreen);
		// mise à jour de la frame
		panel.repaint();
	}
	
	@Override
	public void afficheDetail(JPanel panel,Character c1, Character c2) {
		// clean le container
		panel.removeAll();
		TextArea textArea = new TextArea();
		JScrollPane scrollScreen = new JScrollPane();
		scrollScreen.setSize(panel.getSize());
		textArea.setSize(panel.getSize());
		textArea.setEditable(false);
		textArea.setText(c1.toString() + " \n "+ c2.toString());
		scrollScreen.setViewportView(textArea);
		panel.add(scrollScreen);
		panel.repaint();
	}
	
	
}
