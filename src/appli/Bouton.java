package appli;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Bouton extends JButton implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;

    public Bouton(String str) {
        super(str);
        this.name = str;
        this.addMouseListener(this);
    }

    public void mousePressed(MouseEvent event) {

    }

    public void mouseClicked(MouseEvent event) {
        // Inutile d'utiliser cette m�thode ici
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}