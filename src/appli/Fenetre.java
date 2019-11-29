package appli;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import platform.Descriptor;

public class Fenetre extends JFrame{
  private JPanel pan = new JPanel();
  private JButton bouton = new JButton("Mon bouton");

  public Fenetre(){
    this.setTitle("Plugins");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    //Ajout du bouton à notre content pane
    pan.add(bouton);
    this.setContentPane(pan);
    this.setVisible(true);
  }       
  
  public void ajoutBoutonPlugin(List<Descriptor> plugins) {
	  
	  for (Descriptor d : plugins) {
		  JButton bouton = new JButton(d.getName());
		  pan.add(bouton);
		  this.setContentPane(pan);
	  }
	  
  }
}