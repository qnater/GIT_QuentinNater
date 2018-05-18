package src.Smartphone;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class Index
{
	// Vital tools
	public  JFrame index;
	private JPanel panel_north = new JPanel();
	private JPanel panel_south = new JPanel();
	private String url = "C:\\Smartphone";
	
	

	private JLabel	lblContact;
	private JLabel	lblGalerie;
	private JLabel	lblDirectory;
	
	
	/**
	 * main()
	 * Création et ouverture de la fenètre contact
	 */	
	public static void main(String[] args)
	{
		// Call du constructeur
		Index window = new Index();
		window.index.setVisible(true);
	}

	

	/**
	 * Index()
	 * Conctructeur de contact qui initialise la fenètre
	 */	
	public Index()
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
		
		index = new JFrame();
		index.setSize(480, 800); // Taille pour smartphone
		index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		index.getContentPane().setLayout(new BorderLayout(0, 0));
		index.setVisible(true);
		index.setResizable(false);
		
		// Header's & Footer's Call
		Layout contact = new Layout(panel_north, panel_south, index);
			contact.Header(true);
			contact.Footer(true, true);
   
		// == PANEL configurations == /
		
		// Contact 1st Panel
			JPanel panel_index		 = new JPanel();
			JPanel panel_directory 	 = new JPanel();
			JPanel panel_favicon 	 = new JPanel();
			
			panel_index.setLayout(new BorderLayout(0, 0));	
			index.getContentPane().add(panel_index, BorderLayout.CENTER);
			
			JLabel lblWallpaper = new JLabel();
				lblWallpaper.setIcon(new ImageIcon(Contact.class.getResource("/img/wallpaper.JPG")));
						
			
			
			
			
			panel_favicon.setLayout(new GridLayout(0,2));
			
			//panel_center.add(panel_center, new ImageIcon(Contact.class.getResource("/img/sbrowse.PNG"));		
	
			lblContact = new JLabel();
			lblContact.addMouseListener(new MouseLisenerIndex());
			lblContact.setHorizontalAlignment(SwingConstants.CENTER);
			lblContact.setIcon(new ImageIcon(Contact.class.getResource("/img/contactPage.PNG")));
			
			lblGalerie = new JLabel();
			lblGalerie.addMouseListener(new MouseLisenerIndex());
			lblGalerie.setHorizontalAlignment(SwingConstants.CENTER);
			lblGalerie.setIcon(new ImageIcon(Contact.class.getResource("/img/galeriesPage.PNG")));
			
			lblDirectory = new JLabel();
			lblDirectory.addMouseListener(new MouseLisenerIndex());
			lblDirectory.setHorizontalAlignment(SwingConstants.CENTER);
			lblDirectory.setIcon(new ImageIcon(Contact.class.getResource("/img/URL.PNG")));
	
			panel_index.add(lblWallpaper);			

			panel_index.add(panel_favicon, BorderLayout.CENTER);
			panel_index.add(panel_directory, BorderLayout.SOUTH);
			
			panel_directory.setBackground(Color.BLACK);
			panel_favicon.setBackground(Color.BLACK);
			
			panel_favicon.add(lblContact);
			panel_favicon.add(lblGalerie);			
			panel_directory.add(lblDirectory);
			
	}
	
	/**
	 * MouseLisenerIndex()
	 * En cas d'utilisation de la souris
	 */	
	class MouseLisenerIndex extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent myMouse)
		{
			if(myMouse.getSource() == lblContact)
			{
				index.dispose();
				
				@SuppressWarnings("unused")
				Contact window = new Contact(url);	
			}
			else if(myMouse.getSource() == lblGalerie)
			{
				index.dispose();
				
				@SuppressWarnings("unused")
				Galerie g = new Galerie(url);	
			}
			else if(myMouse.getSource() == lblDirectory)
			{
				try
				{
					url = JOptionPane.showInputDialog("Racine du smartphone", url);
					File check = new File(url);
					if(!check.isDirectory())
					{
						url = "C:\\Smartphone";
						lblDirectory.setText("Chemin non-valide");
						lblDirectory.setForeground(Color.RED);
					}
					else
					{
						lblDirectory.setText("");
						url = "C:\\Smartphone";			
						
					}
				}
				catch(Exception e)
				{
					url = "C:\\Smartphone";
					lblDirectory.setText("Chemin non-valide");
				}
			}
		}
	}
	
	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url) 
	{
		this.url = url;
	}
}
