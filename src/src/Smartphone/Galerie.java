package src.Smartphone;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Galerie
{
	// Vital tools
	private JFrame frame;
	private JPanel panel_contacts = new JPanel();
	
	// Header's & Footer's Panel
	private	JPanel panel_north = new JPanel();
	private	JPanel panel_south = new JPanel();

	private JPanel panel_tools;
	
	// Directory of the contact's picture
	private String url = "C:\\Smartphone";
	
	private JLabel lblTools; 
	private JLabel lblDelete;
	private JLabel lblModifier;
	private JLabel lblComeBack;
	
	// JDialogue Contact (Add-Modify)
	private JDialog dgFrame;
	
	/**
	 * main()
	 * Création et ouverture de la fenètre contact
	 */	
	public static void main(String[] args)
	{
		// Call du constructeur
		Galerie window = new Galerie();
		window.frame.setVisible(true);
	}

	

	/**
	 * Contact()
	 * Conctructeur de contact qui initialise la fenètre
	 */	
	public Galerie()
	{
		initialize();
	}
	public Galerie(String url)
	{
		this.url = url;
		initialize();
		
	}

	/**
	 * initialize()
	 * Initialise la fenètre et affiche les contacts enregistrés
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
		
		
		// Header's & Footer's Call
		Layout galeries = new Layout(panel_north, panel_south, frame);
			galeries.Header(true);
			galeries.Footer(true, false);

		// Contact 1st Panel
		JPanel panel_center = new JPanel();
		frame.getContentPane().add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		panel_center.setBackground(SystemColor.BLACK);
		
		// Contact 3rd Panel
		panel_tools = new JPanel();
		panel_tools.setPreferredSize(new Dimension(10, 100));
		panel_center.add(panel_tools, BorderLayout.NORTH);
		panel_tools.setBackground(SystemColor.scrollbar);
		panel_tools.setLayout(new GridLayout(1, 3));
			
		
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
			panel_contacts.setAutoscrolls(true);
			panel_contacts.setBackground(Color.BLACK);
			
			String s = ""; int x = 0;
			for (int i = 0; i < galerie.length; i++) 
			{
				s = galerie[i].getAbsolutePath();
				if(pictureCheck(s))
					x++;
			}

			int height = (int)(x/3)+1;	
			try
			{
				panel_contacts.setLayout(new GridLayout(height, 3));
			}
			catch (Exception e) 
			{
				
			}
			
			
			lblTools = new JLabel("Galerie");
			lblTools.setForeground(Color.BLACK);
			lblTools.setFont(new Font("Monotype Corsiva", Font.BOLD, 69));
			lblTools.setHorizontalAlignment(SwingConstants.CENTER);
			panel_tools.add(lblTools);
			
			

		galeries.Scroll(panel_contacts, panel_center);
	 
		// Save file call
		file(url, "show");
		
		frame.addWindowListener(new FrameListener());
		frame.repaint();
	}



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
	 * String url 		- Choix du dossier des contacts
	 * String action 	- Appel le mode création (vide) ou modification (avec infos) 
	 */	
	private void file(String url, String action) 
	{
		File directory = new File(url);
		panel_contacts.removeAll();
		panel_contacts.repaint();	

		if(directory.isDirectory())
		{
			File[] contatcs = directory.listFiles();
			String s;
			for (int i = 0; i < contatcs.length; i++) 
			{
				JLabel lblPic = new JLabel();
				if(contatcs[i].isFile())
				{
					s = contatcs[i].getAbsolutePath();
					if(pictureCheck(s))
					{
						ImageIcon imageIcon;
						imageIcon = new ImageIcon(s); // load the image to a imageIcon
						Image image = imageIcon.getImage(); // transform it 
						Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
						lblPic.setIcon(new ImageIcon(newimg)); // transform it back	
						panel_contacts.add(lblPic);
						
						lblPic.addMouseListener(new GalerieMouseListener());
					}
				}					
			}
		}
		else
			System.out.println("Erreur - L'URL donnée n'est pas un dossier valide.");		
	}
	
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
	
	class GalerieMouseListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent myMouse)
		{
			if(myMouse.getSource() == lblTools)
			{
				System.out.println("Modifier");
			}
			else if(myMouse.getSource() == lblComeBack)
			{
				panel_tools.removeAll();
					lblTools.setText("Galerie");
					lblTools.setForeground(Color.BLACK);
					lblTools.setFont(new Font("Monotype Corsiva", Font.BOLD, 69));
					lblTools.setHorizontalAlignment(SwingConstants.CENTER);
				panel_tools.repaint();
			}
			else if(myMouse.getSource() == lblDelete)
			{
				panel_tools.removeAll();
				panel_tools.repaint();
			}
			else if(myMouse.getSource() == lblModifier)
			{
				panel_tools.removeAll();
				panel_tools.repaint();
			}
			else
			{
				JLabel objectLbl = (JLabel) myMouse.getSource();
							
				panel_tools.removeAll();
					tools_beta_add(objectLbl.getIcon());
				panel_tools.repaint();
			}
		}
	}
	
	/**
	 * tools_beta_add()
	 * Initialise les outils secondaires
	 * @param icon 
	 */	
	private void tools_beta_add(Icon icon)
	{
		JLabel lblLooking = new JLabel();
		lblLooking.setIcon(icon);

		lblLooking.setForeground(Color.BLACK);
		lblLooking.setFont(new Font("Monotype Corsiva", Font.BOLD, 0));
		lblLooking.setHorizontalAlignment(SwingConstants.CENTER);
		panel_tools.add(lblLooking);

		// ADD OPTIONS
		lblModifier = new JLabel("");
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifier.setIcon(new ImageIcon(Contact.class.getResource("/img/modify.PNG")));
		panel_tools.add(lblModifier);

		// SHOW CONTACT
		lblDelete = new JLabel("");
		panel_tools.add(lblDelete);		
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setIcon(new ImageIcon(Contact.class.getResource("/img/delete.PNG")));
		
		// FAVORIS CONTACT
		lblComeBack = new JLabel("");
		panel_tools.add(lblComeBack);		
		lblComeBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblComeBack.setIcon(new ImageIcon(Contact.class.getResource("/img/comeback.PNG")));

		GalerieMouseListener mlc = new GalerieMouseListener();
		lblModifier.addMouseListener(mlc);
		lblDelete.addMouseListener(mlc);
		lblComeBack.addMouseListener(mlc);
		
	}	
}
