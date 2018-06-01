package src.Smartphone;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Pattern 
{
	// Panels partagés à travers les classes
	JPanel panel_north = new JPanel();
	JPanel panel_south = new JPanel();
	JPanel panel_left;
	
	// Frame et Dialog partagés à travers les classes
	JFrame fmMain = new JFrame();
	JDialog dgFrame = new JDialog();
	
	
	// Boutons et labels partagés à travers les classes
	JButton btnHome;
	JButton btnRevert;
	JLabel lblTime;
	JLabel lblNotification; 
	
	// incrément timer
	private int inc = 0;
	
	/**
	 * Contructeur avec JFRAME
	 * @param panel_north 	: Emplacement nord de la fenètre
	 * @param panel_south 	: Emplacement sud de la fenètre
	 * @param fmMain 		: Fenètre abritant les options
	 */	
	public Pattern(JPanel panel_north, JPanel panel_south, JFrame fmMain) 
	{
		this.panel_south = panel_south;
		this.panel_north = panel_north;
		this.fmMain  = fmMain;
	}

	/**
	 * Contructeur avec JDIALOG
	 * @param dgFrame 	: JDialog abritant les options
	 */	
	public Pattern(JDialog dgFrame) 
	{
		this.dgFrame  = dgFrame;
	}
	
	/**
	 * Header(boolean v)
	 * Mise en place des headers des fenètres et des dialogues
	 * @param boolean v : true = JFrame / false = JDialog
	 */	
	public void Header(boolean v)
	{
		if(v) // En cas de JFrame
			fmMain.getContentPane().add(panel_north, BorderLayout.NORTH);		
		else // En cas de JDialog
			dgFrame.getContentPane().add(panel_north, BorderLayout.NORTH);	
		
		// Mise en place du layout
		panel_north.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_left = new JPanel();
		
		// Configuration des panels et labels
		panel_north.add(panel_left);
			panel_left.setBackground(new Color(0, 0, 0));
			panel_left.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			JLabel lblOperateur = new JLabel("Swisscom");
				panel_left.add(lblOperateur);
				lblOperateur.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblOperateur.setForeground(new Color(255, 255, 255));
			
			lblNotification = new JLabel("");
			panel_left.add(lblNotification);
			lblNotification.setIcon(new ImageIcon(Contact.class.getResource("/img/whatapp.PNG")));
			lblNotification.setEnabled(false);
				
		JPanel panel_right = new JPanel();
		panel_north.add(panel_right);
			panel_right.setBackground(new Color(0, 0, 0));
			panel_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
				
			JLabel lblBluetooth = new JLabel("");
				panel_right.add(lblBluetooth);
				lblBluetooth.setIcon(new ImageIcon(Contact.class.getResource("/img/bluetooth.PNG")));
			
			JLabel lblSound = new JLabel("");
			panel_right.add(lblSound);
				lblSound.setIcon(new ImageIcon(Contact.class.getResource("/img/sound.PNG")));
				
			JLabel lblWifi = new JLabel("");
			panel_right.add(lblWifi);
				lblWifi.setIcon(new ImageIcon(Contact.class.getResource("/img/wifi.PNG")));
				
			JLabel lblBatterie = new JLabel("");
				panel_right.add(lblBatterie);
				lblBatterie.setIcon(new ImageIcon(Contact.class.getResource("/img/batterie.PNG")));
			
			// Initialisation de l'heure
			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		    lblTime = new JLabel(sdf.format(cal.getTime()));
  
		    // Appel du timer
			time();
			
			// Ajout de l'heure
			panel_right.add(lblTime);
			lblTime.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblTime.setForeground(new Color(255, 255, 255));		
	}
	
	/**
	 * time()
	 * Lancemenent du timer à 1'' pour charger l'heure
	 */	
	public void time()
	{
		Timer timer = new Timer(1000, new ClockListener());
		timer.start();
    }
	
	/**
	 * Footer(boolean v, boolean level)
	 * Mise en place des footers des fenètres et des dialogues
	 * @param boolean v : true = JFrame / false = JDialog
	 * @param boolean level : true = Dernier niveau hierarchique / false = Dernier niveau hierarchique + n
	 */	
	public void Footer(boolean v, boolean level)
	{
		if(v) // En cas de fenètres
			fmMain.getContentPane().add(panel_south, BorderLayout.SOUTH);
		else // En cas de dialogues
			dgFrame.getContentPane().add(panel_south, BorderLayout.SOUTH);

		// Mise en place du panel
		panel_south.setLayout(new GridLayout(0, 2));
		panel_south.setBackground(new Color(0, 0, 0));
		panel_south.setBorder(new BevelBorder(0, Color.WHITE, Color.WHITE));

		// Mise en place des composants
		btnHome = new JButton("");
			btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnHome.setBackground(new Color(0, 2, 0, 0));
			btnHome.setOpaque(false);	
			btnHome.setBorder(null);
			btnHome.setIcon(new ImageIcon(Contact.class.getResource("/img/bw_menu.PNG")));
		panel_south.add(btnHome);
		
		btnRevert = new JButton("");
			btnRevert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRevert.setBackground(new Color(0, 2, 0, 0));
			btnRevert.setOpaque(false);			
			btnRevert.setIcon(new ImageIcon(Contact.class.getResource("/img/bw_revert.PNG")));	
		panel_south.add(btnRevert);
		
		// Initialisation des Listener
		ActionListenerFooter alf = new ActionListenerFooter();
		btnHome.addActionListener(alf);
		
		if(level) // En cas de dernier retour
			btnRevert.addActionListener(new LastActionListenerFooter());
		else // En cas de retour -1
			btnRevert.addActionListener(alf);
	}
	
	/**
	 * Scroll(JPanel in, JPanel out)
	 * Mise en place d'une scroll bar
	 * @param JPanel in : JPanel maître
	 * @param JPanel out : JPanel esclave
	 */	
	public void Scroll(JPanel in, JPanel out)
	{
		// Scroll Bar
		JScrollPane jspCenter = new JScrollPane(in);
		out.add(jspCenter, BorderLayout.CENTER);
		
		jspCenter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // Toujours en vertical
		jspCenter.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Jamais en horizontal
		jspCenter.getViewport().addChangeListener(new ScrollListener(out)); // Listener en cas de mouvement
	}
	
	/**
	 * ActionListenerFooter
	 * En cas de sélection d'un bouton
	 */	
	class ActionListenerFooter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == btnHome) // Bouton Home
			{
				fmMain.dispose(); // Fermeture de la fenètre actuelle
				dgFrame.dispose(); // Fermeture de la dialogue actuelle
				
				@SuppressWarnings("unused")
				Index i = new Index(); // Ouverture du niveau alpha
			}
			else // En cas du bouton return
			{
				fmMain.dispose();  // Fermeture de la fenètre actuelle
				dgFrame.dispose(); // Fermeture de la dialogue actuelle

				@SuppressWarnings("unused")
				Index i = new Index(); // Ouverture du niveau -1
			}
		}
	}
	
	/**
	 * ScrollListener
	 * En cas de sélection de la scrollbar (Mouvement)
	 */	
	class ScrollListener implements ChangeListener
	{
		JPanel contact = new JPanel(); 
		public ScrollListener(JPanel out) // Appel du panel en question (Maître)
		{
			contact = out;
		}
		@Override
		public void stateChanged(ChangeEvent arg0)
		{
			contact.repaint(); // Rafraichissement graphique
		}

	}

	/**
	 * LastActionListenerFooter
	 * En cas de sélection beta
	 */	
	class LastActionListenerFooter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			fmMain.dispose();	// Fermeture de la fenètre actuelle
			
			@SuppressWarnings("unused")
			Locked l = new Locked(); // Ouverture du niveau le plus bas
		}
	}
	
	/**
	 * ClockListener
	 * En cas d'appel du timer
	 */	
	class ClockListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			inc++; // Pour 5''
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        lblTime.setText(sdf.format(cal.getTime())); // Changement de l'heure
	        
	        if(inc == 5)
	        	lblNotification.setEnabled(true); // Affichage du logo what'apps
		}	        
		
	}

}
