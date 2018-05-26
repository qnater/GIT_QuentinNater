package src.Smartphone;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

public class Locked
{
	
	JLabel lblHeure;
	JPanel panel_norths;
	
	
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

			JPanel panel_locked	 = new JPanel();
			panel_locked.setLayout(new BorderLayout(0, 0));	
			locked.getContentPane().add(panel_locked, BorderLayout.CENTER);
				
			JLabel lblWallpaper = new JLabel();
			lblWallpaper.setIcon(new ImageIcon(Contact.class.getResource("/img/samsungs.JPEG")));
			panel_locked.add(lblWallpaper, BorderLayout.CENTER);	
			
			panel_norths = new JPanel();
			

			panel_norths.setLayout(new BoxLayout(panel_norths, BoxLayout.Y_AXIS));
			
			panel_locked.add(panel_norths, BorderLayout.NORTH);

			lblHeure = new JLabel();
			JLabel lblDate = new JLabel();

			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        lblHeure.setText(sdf.format(cal.getTime()));

	        time();
	        
	        SimpleDateFormat date = new SimpleDateFormat("E d MMM");
	        lblDate.setText(date.format(cal.getTime()));
	        
	     
			
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
			
			

			
			
	        
	        panel_norths.add(lblHeure);	
	        panel_norths.add(lblDate);	
	        
	       
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
	
	
	public void time()
	{
		Timer timer = new Timer(1000, new ClockListener());
		timer.start();
    }
	class ClockListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        lblHeure.setText(sdf.format(cal.getTime()));
	        panel_norths.repaint();	
		}	        
		
	}
}
