package src.Smartphone;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Galerie
{
	// Outil de base
	private JFrame frame;
	private JPanel panel_galeries = new JPanel();
	
	// Panneau
	private	JPanel panel_north = new JPanel();
	private	JPanel panel_south = new JPanel();
	private JPanel panel_tools;

	// Dossier photo
	private String url = "C:\\Smartphone";
	// String du nom de la photo et de son chemin absolu
	private String addingPath;
	
	// Label utilisé comme un bouton
	private JLabel lblDelete, lblComeBack, lblContGal;	
	private JLabel lblTools = new JLabel("Galerie");; 
	private JLabel lblPic = new JLabel();
	private JLabel lblAdding = new JLabel();
	
	private Variables v = new Variables();
	
	// JDialogue (Add-Modify)
	private JDialog dgFrame;
	
	/**
	 * main()
	 * Création et ouverture de la fenètre galerie
	 */	
	public static void main(String[] args)
	{
		// Call du constructeur
		Galerie window = new Galerie();
		window.frame.setVisible(true);
	}

	

	/**
	 * Galerie()
	 * Conctructeur de contact qui initialise la fenètre
	 */	
	public Galerie()
	{
		initialize();
	}
	
	/**
	 * Galerie()
	 * @param String url : URL du dossier photo
	 * Conctructeur de contact qui initialise la fenètre avec l'url classique
	 */	
	public Galerie(String url)
	{
		this.url = url;
		initialize();
		
	}

	/**
	 * initialize()
	 * Initialise la fenètre et affiche les photographies enregistrés
	 */	
	private void initialize()
	{
		// == FRAME configurations == /
		frame = new JFrame();
		frame.setSize(480, 800); // Taille pour smartphone
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);
		frame.setResizable(false);
		
		
		// Header's & Footer's 
		Pattern galeries = new Pattern(panel_north, panel_south, frame);
			galeries.Header(true);
			galeries.Footer(true, false);

		// Galerie 1er Panel
		JPanel panel_center = new JPanel();
		frame.getContentPane().add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		panel_center.setBackground(SystemColor.BLACK);
		
		// Galerie 3ème Panel
		panel_tools = new JPanel();
		panel_tools.setPreferredSize(new Dimension(10, 100));
		panel_center.add(panel_tools, BorderLayout.NORTH);
		panel_tools.setBackground(Color.BLACK);
		panel_tools.setLayout(new GridLayout(1, 3));
			
		panel_center.add(panel_galeries, BorderLayout.CENTER);

		// Création d'une arborescence pour abriter les contacts
		File directory = new File(url);
		if(!directory.exists())
		{
			if(!directory.isDirectory())
				directory.mkdir();
		}
   
		// == PANEL configurations == /
			
			// Height of the GridLayout
			File[] galerie = directory.listFiles();			
		
			// Contact 2sd Panel
			panel_galeries.setBackground(Color.BLACK);
			
			String s = ""; int x = 0;
			for (int i = 0; i < galerie.length; i++) 
			{
				s = galerie[i].getAbsolutePath();
				if(pictureCheck(s))
					x++;
			}

			// Calcul du nombre de ligne du grid
			int height = (int)(x/3)+1;	
			
			try
			{
				panel_galeries.setLayout(new GridLayout(height, 3));
			}
			catch (Exception e) 
			{
				
			}
			
		
		// Appel des outils primaires
		tools_alpha_add();
			
			
		// Appel de la scroll bar
		galeries.Scroll(panel_galeries, panel_center);
	 
		// Affichage des photos
		file(url, "show");
		
		// Appel du FrameListener
		frame.addWindowListener(new FrameListener());
		
		frame.repaint();
	}


	/**
	 * DialogFrameListener
	 * En cas de fermeture du Dialog
	 */	
	class DialogFrameListener extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			int reponse = JOptionPane.showConfirmDialog(null,"Voulez-vous réellement quitter sans sauver ?");
			if(reponse == JOptionPane.YES_OPTION)
			{
				dgFrame.dispose();
			}			
		}
	}
	
	/**
	 * DialogFrameListener
	 * En cas de fermeture de la Frame
	 */	
	class FrameListener extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			int reponse = JOptionPane.showConfirmDialog(null,"Voulez-vous réellement quitter sans sauver ?");
			if(reponse == JOptionPane.YES_OPTION)
			{
				frame.dispose();				
				@SuppressWarnings("unused")
				Index i = new Index();
			}			
		}
	}
	
	/**
	 * file(String url, String action) 
	 * @param String url 		- Choix du dossier des contacts
	 * @param String action 	- Appel le mode création (vide) ou modification (avec infos) 
	 */	
	private void file(String url, String action) 
	{
		File directory = new File(url);
		panel_galeries.removeAll();
		
		if(directory.isDirectory())
		{
			File[] contatcs = directory.listFiles();
			ImageIcon imageIcon;
			Image image, newimg;

			for (int i = 0; i < contatcs.length; i++) 
			{
				try
				{
					File myFile = contatcs[i];
					
					lblPic = new JLabel();
					lblPic.setFont(new Font("Monotype Corsiva", Font.BOLD, 0));
					lblPic.setHorizontalAlignment(SwingConstants.CENTER);
					lblPic.setText(myFile.getPath());
										
					if(contatcs[i].isFile())
					{
						if(pictureCheck(contatcs[i].getAbsolutePath()))
						{
							imageIcon = new ImageIcon(contatcs[i].getAbsolutePath()); // load the image to a imageIcon
							image = imageIcon.getImage(); // transform it 
							newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
							
							lblPic.setIcon(new ImageIcon(newimg)); // transform it back	
								
							panel_galeries.add(lblPic);
							panel_galeries.repaint();
							lblPic.addMouseListener(new GalerieMouseListener());
							
							
						}	
					}
				}
				catch(Exception e)
				{
					System.out.println("Picture Name");
				}			
			}
		}
		else
			System.out.println("Erreur - L'URL donnée n'est pas un dossier valide.");		
	
		panel_galeries.repaint();
	}
	
	/**
	 * pictureCheck
	 * Vérifie que le fichier est une image
	 * @param String path : Chemin de l'image à vérifier
	 */	
	public boolean pictureCheck(String path)
	{
		String sub3, sub4;
		
		sub3 = path.substring(path.length()-3, path.length());
		sub4 = path.substring(path.length()-4, path.length());
		
		if(sub3.equals("JPG")||sub3.equals("jpg")||sub3.equals("png")||sub3.equals("PNG")||sub3.equals("png")||sub3.equals("gif")||sub3.equals("GIF")||sub4.equals("JPEG")||sub4.equals("jpeg"))
			return true;
		else
			return false;
	}
	
	
	/**
	 * GalerieMouseListener
	 * En cas de sélection d'un label
	 */	
	class GalerieMouseListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent myMouse)
		{
			if(myMouse.getSource() == lblContGal)
			{				
				@SuppressWarnings("unused")
				Contact c = new Contact(v.getPic(), true);
			}
			else if(myMouse.getSource() == lblComeBack)
			{	
				tools_alpha_add();
			}
			else if(myMouse.getSource() == lblDelete)
			{
				fileUnique(url, v.getName(), "delete");
				frame.dispose();
				@SuppressWarnings("unused")
				Galerie g = new Galerie();
				panel_galeries.repaint();
			}
			else if(myMouse.getSource() == lblAdding)
			{
					try // En cas d'annulation
		            {
			          JFileChooser chooser = new JFileChooser();
					    chooser.setCurrentDirectory(new java.io.File(url));
			            chooser.setDialogTitle("Browse the folder to process");
			            chooser.setAcceptAllFileFilterUsed(false);
			            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif"));
				           
			            chooser.showOpenDialog(null);
			            addingPath = chooser.getSelectedFile().toString();
			            
			            String install = "cmd /C xcopy \"" + addingPath +"\" /E /Q /I \"" + url + "\"";
						Runtime.getRuntime().exec(install);	
					
						file(url, "show");
							
			        }					
					catch (IOException e1)
		            {
						JOptionPane.showMessageDialog(frame,"Error - Failed Path\nDirectory Fail (Erreur C002)", "Failed Path", JOptionPane.ERROR_MESSAGE);  
					}
					catch (NullPointerException e2)
		            {
					}
			
					panel_galeries.repaint();
					
					frame.dispose();
					
					@SuppressWarnings("unused")
					Galerie g = new Galerie();
		           
			}

			else 
			{
				JLabel objectLbl = (JLabel) myMouse.getSource();				
				v.setName(objectLbl.getText());
				v.setPic(objectLbl.getText());
				
				panel_tools.removeAll();
					tools_beta_add(objectLbl.getIcon());
				panel_tools.repaint();
			}
		}
	}
	
	/**
	 * GalerieMouseListener
	 * En cas de sélection d'un label d'option
	 */	
	class GalerieMouseListener2 extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent myMouse)
		{
			try 
			{
				String install = "cmd /C start "+ v.getName();
				Runtime.getRuntime().exec(install);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}	
		}
	}
	
	
	/**
	 * tools_beta_add()
	 * Initialise les outils secondaires
	 * @param icon : Icone à afficher
	 */	
	private void tools_beta_add(Icon icon)
	{

		ImageIcon imageIcon;
		Image image, newimg;
		JLabel lblLooking = new JLabel();
	
	
		
		imageIcon = ((ImageIcon) icon);
		image = imageIcon.getImage(); // transform it 
		newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		lblLooking.setIcon((new ImageIcon(newimg)));

		lblLooking.setForeground(Color.BLACK);
		lblLooking.setFont(new Font("Monotype Corsiva", Font.BOLD, 0));
		lblLooking.setHorizontalAlignment(SwingConstants.CENTER);

		panel_tools.add(lblLooking);

		
		// SHOW CONTACT
		lblDelete = new JLabel("");
		panel_tools.add(lblDelete);		
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setIcon(new ImageIcon(Contact.class.getResource("/img/crossSubmit.PNG")));
		
		// CONTACT GALERIE CONTACT
		lblContGal = new JLabel("");
		panel_tools.add(lblContGal);		
		lblContGal.setHorizontalAlignment(SwingConstants.CENTER);
		lblContGal.setIcon(new ImageIcon(Contact.class.getResource("/img/contactgalerieSubmit.PNG")));

		// BACK CONTACT
		lblComeBack = new JLabel("");
		panel_tools.add(lblComeBack);		
		lblComeBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblComeBack.setIcon(new ImageIcon(Contact.class.getResource("/img/backSubmit.PNG")));

				
		GalerieMouseListener mlc = new GalerieMouseListener();
		GalerieMouseListener2 mlc2 = new GalerieMouseListener2();
		lblDelete.addMouseListener(mlc);
		lblComeBack.addMouseListener(mlc);
		lblContGal.addMouseListener(mlc);
		lblLooking.addMouseListener(mlc2);
		
	}	
	
	/**
	 * tools_alpha_add()
	 * Initialise les outils primaires
	 * @param icon 
	 */	
	private void tools_alpha_add()
	{
		
		panel_tools.removeAll();
		
		lblTools.setForeground(Color.WHITE);
		lblTools.setFont(new Font("Monotype Corsiva", Font.BOLD, 69));
		lblTools.setHorizontalAlignment(SwingConstants.CENTER);
		panel_tools.add(lblTools);
		
		// ADD OPTIONS
		lblAdding.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdding.setIcon(new ImageIcon(Contact.class.getResource("/img/addSubmit.PNG")));
		GalerieMouseListener mlc = new GalerieMouseListener();
		lblAdding.addMouseListener(mlc);
		
		
		panel_tools.add(lblAdding);
		
		panel_tools.repaint();
	}

	/**
	 * fileUnique(String url, String fileName, String mode)
	 * @param String url 		- Choix du dossier des contacts
	 * @param String fileName	- Name of the contact to use
	 * @param String mode	 	- Appel le mode création (vide) ou modification (avec infos) 
	 */	
	private void fileUnique(String url, String fileName, String mode) 
	{
		File directory = new File(url);
			if(directory.isDirectory())
			{
				File[] contatcs = directory.listFiles();
				
				for (int i = 0; i < contatcs.length; i++) 
				{
					if(contatcs[i].isFile())
					{
						if(contatcs[i].getPath().equals(fileName))
						{
							if(mode.equals("delete"))
							{
								contatcs[i].delete();
							}
							else
							{
								System.out.println("Erreur mode");
							}
						}	
					}					
				}
			}
		
	}
}
