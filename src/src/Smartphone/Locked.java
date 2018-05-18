package src.Smartphone;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Locked
{
	// Vital tools
	public  JFrame locked;
	/**
	 * main()
	 * Création et ouverture de la fenètre contact
	 */	
	public static void main(String[] args)
	{
		// Call du constructeur
		Locked window = new Locked();
		window.locked.setVisible(true);
	}

	/**
	 * Index()
	 * Conctructeur de contact qui initialise la fenètre
	 */	
	public Locked()
	{
		initialize();
	}

	/**
	 * initialize()
	 * Initialise la fenètre et affiche les contacts enregistrés
	 */	
	private void initialize()
	{
		// == FRAME configurations == /
		
		locked = new JFrame();
		locked.setSize(480, 800); // Taille pour smartphone
		locked.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		locked.getContentPane().setLayout(new BorderLayout(0, 0));
		locked.setVisible(true);
		locked.setResizable(false);
		
	
			JPanel panel_locked		 = new JPanel();
			
			panel_locked.setLayout(new BorderLayout(0, 0));	
			locked.getContentPane().add(panel_locked, BorderLayout.CENTER);
			
			JLabel lblWallpaper = new JLabel();
				lblWallpaper.setIcon(new ImageIcon(Contact.class.getResource("/img/samsung.JPEG")));
						
			panel_locked.add(lblWallpaper);	
			
			
		
		locked.addMouseListener(new MouseLisenerIndex());
	}
	
	class MouseLisenerIndex extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent myMouse)
		{
			locked.dispose();
			
			@SuppressWarnings("unused")
			Index i = new Index();	
		}
	}
}
