package appli;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import platform.Descriptor;

public class Fenetre extends JFrame {

  private JPanel pan = new JPanel();
  private Appli appli;

  public Fenetre(Appli appli) {
    this.appli = appli;
    this.setTitle("Plugins");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    // Ajout du bouton � notre content pane
    this.setContentPane(pan);
    this.setVisible(true);
  }

  public void start() {
    try {
      this.appli.run();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.genereBoutonFrom();
  }

  public void genereBoutonFrom() {

    for (Descriptor d : this.appli.listPlugins) {

      JButton bouton = new Bouton(d.getName());
      String iface = d.getIface();

      if(iface.equals("appli.IAfficheur")){
        bouton.addActionListener(new BoutonListenerAfficher(d.getId()));
      }
      else{
        bouton.addActionListener(new BoutonListenerModifier(d.getId()));

      }
      pan.add(bouton);
      
      this.setContentPane(pan);
    }
  }

  class BoutonListenerAfficher implements ActionListener {
    String id;

    BoutonListenerAfficher(String id) {
      this.id = id;
    }

    public void actionPerformed(ActionEvent arg0) {
      try {
        appli.searchInListDescriptor(this.id);
        appli.affiche();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }

  class BoutonListenerModifier implements ActionListener {
    String id;

    BoutonListenerModifier(String id) {
      this.id = id;
    }

    public void actionPerformed(ActionEvent arg0) {
      try {
        appli.searchInListDescriptor(this.id);
        appli.modifier();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}