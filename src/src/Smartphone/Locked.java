package src.Smartphone;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

public class Locked
{
	// Label affichant l'heure actuelle
	JLabel lblHeure;
	
	// Panel abritant l'heure et la date.
	JPanel panel_norths;
		
	// Vital tools
	public  JFrame locked;
		
	/**
	 * main()
	 * Création et ouverture de la fenètre de vérouillage
	 */	
	public static void main(String[] args)
	{
		// Call du constructeur
		Locked window = new Locked();
		window.locked.setVisible(true);
	}

	/**
	 * Index()
	 * Conctructeur de contact qui initialise la fenètre de vérouillage
	 */	
	public Locked()
	{
		initialize();
	}

	/**
	 * initialize()
	 * Initialise la fenètre et affiche l'heure et la date 
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

			// Panel primaire
			JPanel panel_locked	 = new JPanel();
			panel_locked.setLayout(new BorderLayout(0, 0));	
			locked.getContentPane().add(panel_locked, BorderLayout.CENTER);
				
			// Label du fond d'écran
			JLabel lblWallpaper = new JLabel();
			lblWallpaper.setIcon(new ImageIcon(Contact.class.getResource("/img/samsungs.JPEG")));
			panel_locked.add(lblWallpaper, BorderLayout.CENTER);	
			
			
			// Initialisation du panel temporel.
			panel_norths = new JPanel();
			panel_norths.setLayout(new BoxLayout(panel_norths, BoxLayout.Y_AXIS));
			panel_locked.add(panel_norths, BorderLayout.NORTH);

			// Initialisation des label temporels
			lblHeure = new JLabel();
			JLabel lblDate = new JLabel();

			// Attribution d'une variable calendrier
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        lblHeure.setText(sdf.format(cal.getTime()));

	        // Call et exécution du timer
	        time();
	        
	        // Mise en forme de la date
	        SimpleDateFormat date = new SimpleDateFormat("E d MMM");
	        lblDate.setText(date.format(cal.getTime()));
	        
	     
	        // Mise en forme des labels
	        lblHeure.setFont(new Font("Sans-Serif", Font.BOLD, 72));
	        lblHeure.setBackground(new Color(0,0,0,0));
	        lblHeure.setOpaque(true);
	        lblHeure.setForeground(Color.WHITE);
			panel_norths.setOpaque(true);			
			panel_norths.setBackground(Color.BLACK);			
			panel_norths.setForeground(Color.WHITE);
			lblDate.setForeground(Color.WHITE);
			lblDate.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblHeure.setAlignmentX(Component.CENTER_ALIGNMENT);
			
	        // Affichage des labels
	        panel_norths.add(lblHeure);	
	        panel_norths.add(lblDate);	
	        
	    // Dévérouillage par contact
		locked.addMouseListener(new MouseLisenerLocked());
	}
	
	/**
	 * MouseLisenerIndex
	 * En cas de contact avec la souris
	 */	
	class MouseLisenerLocked extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent myMouse)
		{
			// Fermeture de la fenètre
			locked.dispose();
			
			// Ouverture de l'index
			@SuppressWarnings("unused")
			Index i = new Index();	
		}
	}
	
	/**
	 * time()
	 * Initialisation d'un timer à 1 seconde
	 */	
	public void time()
	{
		// Initialisation d'un timer à 1 seconde
		Timer timer = new Timer(1000, new ClockListener());
		
		//Lancement du timer
		timer.start();
    }
		
	/**
	 * ClockListener
	 * En cas d'exécution du timer
	 */	
	class ClockListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			// Modification de l'heure
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        lblHeure.setText(sdf.format(cal.getTime()));
	        panel_norths.repaint();	
		}	        
		
	}

}
