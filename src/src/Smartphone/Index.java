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
	
	// URL primaire
	private String url = "C:\\Smartphone\\url";
	
	// Labels utilisés comme outils
	private JLabel	lblContact;
	private JLabel	lblGalerie;
	private JLabel	lblDirectory;
	
	/**
	 * main()
	 * Création et ouverture de la fenètre Index
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
	 * Initialise la fenètre et affiche les outils primaires de l'index
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
		
		// Appel de l'Header et du Footer
		Pattern contact = new Pattern(panel_north, panel_south, index);
			contact.Header(true);
			contact.Footer(true, true);
   
		// == PANEL configurations == /
		
			// Initialisation des panels
			JPanel panel_index		 = new JPanel();
			JPanel panel_directory 	 = new JPanel();
			JPanel panel_favicon 	 = new JPanel();
		
			// Configuration des layout
			panel_index.setLayout(new BorderLayout(0, 0));	
			index.getContentPane().add(panel_index, BorderLayout.CENTER);
			
			// Mise en place d'un graphisme de 2 colones
			panel_favicon.setLayout(new GridLayout(0,2));
			
			// Configuration du label des contacts
			lblContact = new JLabel();
			lblContact.addMouseListener(new MouseLisenerIndex());
			lblContact.setHorizontalAlignment(SwingConstants.CENTER);
			lblContact.setIcon(new ImageIcon(Contact.class.getResource("/img/contact_sam.PNG")));
			
			
			// Configuration du label de la galerie
			lblGalerie = new JLabel();
			lblGalerie.addMouseListener(new MouseLisenerIndex());
			lblGalerie.setHorizontalAlignment(SwingConstants.CENTER);
			lblGalerie.setIcon(new ImageIcon(Contact.class.getResource("/img/galerie_sam.PNG")));
			
			
			// Configuration du label de l'url
			lblDirectory = new JLabel();
			lblDirectory.addMouseListener(new MouseLisenerIndex());
			lblDirectory.setHorizontalAlignment(SwingConstants.CENTER);
			lblDirectory.setIcon(new ImageIcon(Contact.class.getResource("/img/directory_sam.PNG")));
	
			
			// Mise en place des panels
			panel_index.add(panel_favicon, BorderLayout.CENTER);
			panel_index.add(panel_directory, BorderLayout.SOUTH);
			
			// Attribution des fonds
			panel_directory.setBackground(Color.BLACK);
			panel_favicon.setBackground(Color.BLACK);
			
			
			// Ajout des labels dans les panels
			panel_favicon.add(lblContact);
			panel_favicon.add(lblGalerie);			
			panel_directory.add(lblDirectory);

			// Vérification du chemin enregistré.
			try 
			{
				File fUrl = new File("C:\\Smartphone\\url"); // Dossier de config.
				if(!fUrl.exists())
					fUrl.mkdirs();
				
				File fLink = new File(fUrl, "url.txt"); // Fichier de config.
				if(!fLink.exists() || !fLink.isFile())
				{
					fLink.createNewFile();
					// Flux de base (pour voir)
					FileWriter flowfile = new FileWriter(fLink);
					// Flux Tampon (englobe le flux de base).
					BufferedWriter 	bwrite= new BufferedWriter(flowfile);
					bwrite.write(url);
					bwrite.close();
				}
				
				// Flux de base (pour voir)
				FileReader flowfile = new FileReader(fLink); // Lecture du fichier
				
				// Flux Tampon (englobe le flux de base).
				BufferedReader 	bread= new BufferedReader(flowfile); 
				url = bread.readLine();
				setUrl(url); // Attribution de l'url
				bread.close();
			} 
			catch (IOException e) 
			{
				new Log(e.getMessage(), "Index : Set Url File (Directory Path)", "Index");  // Génération du log
			}
			
	}
	
	/**
	 * MouseLisenerIndex
	 * En cas d'utilisation de la souris
	 */	
	class MouseLisenerIndex extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent myMouse)
		{
			// Clique sur le label des contacts
			if(myMouse.getSource() == lblContact)
			{
				// Fermeture de la fenètre index
				index.dispose();
				
				// Ouverture des contacts avec l'url
				@SuppressWarnings("unused")
				Contact window = new Contact(url);	
			}
			
			// Clique sur le label de la galerie
			else if(myMouse.getSource() == lblGalerie)
			{
				// Fermeture de la fenètre index
				index.dispose();
				
				// Ouverture de la galerie avec l'url
				@SuppressWarnings("unused")
				Galerie g = new Galerie(url);	
			}
			
			// Clique sur le label de l'url
			else if(myMouse.getSource() == lblDirectory)
			{
				try
				{
					// Changement de l'url -> Choix de l'arborescence
					url = JOptionPane.showInputDialog("Racine du smartphone", getUrl());
					
					// Attribution du changement
					setUrl(url);
					
					
					// Ecriture du nouveau lien
					File check = new File(url);
					if(!check.isDirectory())
					{
						url = getUrl();
						lblDirectory.setText("Chemin non-valide");
						lblDirectory.setForeground(Color.RED);
						setUrl(url);
					}
					else
					{
						lblDirectory.setText("");
						url = getUrl();		
						
					}
					
						try 
						{
							File fLink = new File("C:\\Smartphone\\url", "url.txt");
							
							// Flux de base (pour voir)
							FileWriter flowfile = new FileWriter(fLink);
							
							// Flux Tampon (englobe le flux de base).
							BufferedWriter 	bwrite= new BufferedWriter(flowfile);
							
							bwrite.write(url);
								setUrl(url);
							bwrite.close();
						} 
						catch (IOException e) 
						{
							new Log(e.getMessage(), "Index : Change Path (Bad Path)", "Index");  // Génération du log
							lblDirectory.setText("");
							url = getUrl();
						}					
				}
				catch(Exception e)
				{
					new Log(e.getMessage(), "Index : Change Path (Come Back)", "Index");  // Génération du log
					lblDirectory.setText("");
					url = getUrl();
				}
				
				try
				{
					if(getUrl().isEmpty())
					{
						setUrl("C:\\Smartphone");
					}
				}
				catch (Exception e) 
				{
					new Log(e.getMessage(), "Index : Change Path (Empty)", "Index");  // Génération du log
					setUrl("C:\\Smartphone");
				}
			}
		}
	}
	
	/**
	 * getUrl()
	 * @param String url : Chemin absolu des configurations.
	 * Appel de l'url 
	 */	
	public String getUrl()
	{
		return url;
	}

	/**
	 * setUrl()
	 * @param String url : Chemin absolu des configurations.
	 * Attribution de l'url 
	 */	
	public void setUrl(String url) 
	{
		this.url = url;
	}
}
