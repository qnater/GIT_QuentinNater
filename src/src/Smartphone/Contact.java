package src.Smartphone;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Contact
{
	// Outils vitauy
	private JFrame frame;
	private JPanel panel_contacts = new JPanel();
	
	// Tableau de l'alphabet
	private char[] first = new char[26];
	
	// Labels outils utilisés comme des boutons
	private JLabel lblAdd;
	private JLabel lblContact;
	private JLabel lblFavoris;	
	private JLabel lblDelete;
	private JLabel lblModifier;
	private JLabel lblComeBack;
	private JLabel lblPic;
	
	// Panels du Header et du Footer
	private	JPanel panel_north = new JPanel();
	private	JPanel panel_south = new JPanel();
	
	private JPanel panel_adding;
	private JPanel panel_add;
	private JPanel panel_tools;
	
	// URL primaire des fichiers de configurations 
	private String url = "C:\\Smartphone";
	
	// Boutons de sauvegarde ou d'annulation
	private JButton submitSave;
	private JButton submitCancel;
	//private JButton submitReset;
	
	// Browser
	private JLabel sPicBrowse;
	private JLabel sSongBrowse;
	
	// Supression du text dans les textboxes
	private JLabel demail;
	private JLabel dtelephone;
	private JLabel dportable;
	
	// JDialogue Contact (Ajout-Modification)
	private JDialog dgFrame;
	
	// Appel des autres classes
	private boolean t;
	private String imageContact;
	private int height = -1;
	private int inc = 0;
	
	// Textbox (infos contacts)
	private JTextField tName = new JTextField(19);	
	private JTextField tPicture = new JTextField(16);	
	private JTextField tLastname = new JTextField(19);	
	private JTextField tNumero = new JTextField(16);	
	private JTextField tNumeroP = new JTextField(16);	
	private JTextField tEmail = new JTextField(16);	
	private JTextField tProfession = new JTextField(19);	
	private JTextField tOrganisation = new JTextField(19);	
	private JTextField tWeb = new JTextField(19);	
	private JTextField tDateNaissance = new JTextField(19);	
	private JTextField tSonnerie = new JTextField(16);	
	private JTextField tSurnom = new JTextField(19);
	private JCheckBox cFavoris = new JCheckBox();

	// Appel des variables
	private Variables v = new Variables();
	
	/**
	 * main()
	 * Création et ouverture de la fenètre contact
	 */	
	public static void main(String[] args)
	{
		// Call du constructeur
		Contact window = new Contact();
		window.frame.setVisible(true);
	}

	/**
	 * Contact()
	 * Conctructeur de contact qui initialise la fenètre
	 */	
	public Contact()
	{
		initialize();
	}
	
	/**
	 * Contact()
	 * @param String url : Lien du dossier contenant les fichiers
	 * Conctructeur de contact qui initialise la fenètre
	 */	
	public Contact(String url)
	{
		this.url = url;
		initialize();	
	}
	
	
	/**
	 * Contact() - Ouverture d'une page simplifiée pour ajouter une photo de profil
	 * @param String pic : Lien du dossier contenant les fichiers
	 * @param boolean t : Activation du mode
	 * Conctructeur de contact qui initialise la fenètre
	 */	
	public Contact(String pic, String url, boolean t) 
	{
		v.setPic(pic);
		this.url = url;
		this.t = t;
		imageContact = pic;
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
		
		
		// Appel de l'Header et du Footer
		Pattern contact = new Pattern(panel_north, panel_south, frame);
			contact.Header(true);
			contact.Footer(true, false);

		
		// Création d'une arborescence pour abriter les contacts
		File directory = new File(url);
		
		if(!directory.isDirectory())
		{
			JOptionPane.showMessageDialog(null, "L'URL donnée non-valide... Redirection vers un dossier valide", "Information URL", 1);
			url = "C:\\Smartphone";
			directory = new File(url);
			
		}
		// == PANEL configurations == /
		
			// Initialisation du panel principal
			JPanel panel_center = new JPanel();
			frame.getContentPane().add(panel_center, BorderLayout.CENTER);
			panel_center.setLayout(new BorderLayout(0, 0));
			panel_center.setBackground(SystemColor.BLACK);
			
			// Configuration du panel des contacts
			panel_contacts.setAutoscrolls(true);
			panel_contacts.setBackground(Color.BLACK);
						
			// Taille du GridLayout
			File[] contatcs = directory.listFiles();			
			
			height=0; 
			for (int i = 0; i < contatcs.length; i++) 
			{
				if(contatcs[i].getAbsolutePath().substring(contatcs[i].getAbsolutePath().length()-3, contatcs[i].getAbsolutePath().length()).equals("txt"))
					height++;
			}

						
		// Configuration du panel des outils
		panel_tools = new JPanel();
		panel_tools.setPreferredSize(new Dimension(10, 100));
		panel_center.add(panel_tools, BorderLayout.NORTH);
		panel_tools.setBackground(Color.BLACK);
		panel_tools.setLayout(new GridLayout(1, 3));
		
		// Mise en place de la scroll bar
		contact.Scroll(panel_contacts, panel_center);
		
		// Appel des outils primaires
		tools_alpha_add();
	 
		// Lecuture des fichiers de configuration des contacts
		file(url, "show");
		
		// En cas d'extinction de la fenètre
		frame.addWindowListener(new FrameListener());
		
		frame.repaint();
	}

	/**
	 * AddContact() 
	 * Initialise les données à zéro
	 */	
	private void AddContact() 
	{
		// Initialise les informations des textboxes
		setInfos("set");
	}
	
	/**
	 * ChangeContact(String mode) 
	 * @param String mode - Appel le mode création (vide) ou modification (avec infos)
	 * Permet d'afficher la fenètre des informations d'un contact
	 */	
	private void ChangeContact(String mode) 
	{
		// Initialisation d'un JDialog
		dgFrame = new JDialog();
		dgFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		dgFrame.setSize(480, 800);
		dgFrame.setVisible(true);
		dgFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
		// Mise en place de l'Header et du Footer, pour les JDialog
		Pattern adding = new Pattern(dgFrame);
		adding.Header(false);
		adding.Footer(false, false);
		
		// Initialisation des panels
		panel_adding 	= new JPanel();
		panel_add 		= new JPanel();	
		JPanel panel_save = new JPanel();
		dgFrame.add(panel_save, BorderLayout.SOUTH);
		panel_save.setLayout(new GridLayout(0, 2));

		// Initialisation des boutons
		submitSave = new JButton("Save");
		submitCancel = new JButton("Cancel");
		panel_save.add(submitSave);
		panel_save.add(submitCancel);
		
		// Initialisation de l'action listener
		ButtonActionListener bal = new ButtonActionListener();
		submitSave.addActionListener(bal);
		submitCancel.addActionListener(bal);
		
		// Ajout des panels dans le dialog	
		dgFrame.add(panel_adding, BorderLayout.CENTER);
			panel_adding.setLayout(new BorderLayout(0, 0));
			panel_adding.add(panel_add, BorderLayout.CENTER);
		
		// Initialisation de la sroll bar
		panel_adding.setAutoscrolls(true);
		adding.Scroll(panel_add, panel_adding);
		panel_add.setLayout(new GridLayout(13, 2));
		panel_add.setBackground(SystemColor.scrollbar);
		
		// En cas de modification, lecture du fichier contact
		if(mode.equals("modify"))
			setInfos("modify");
		else // Autrement ajout de données
			AddContact();
		
		// En cas d'interruption de la dialog (fermeture)
		dgFrame.addWindowListener(new DialogFrameListener());
	}

	/**
	 * fileWriter(String fileName) 
	 * Écriture d'un nouveau contact dans un fichier de configuration
	 * @param String fileName : Nom de l'image en question
	 */	
	private void fileWriter(String fileName) 
	{
		// Création d'un directory avec l'url enregistré.
		File directory = new File(url);
		
		// Si l'url est valide
		if(directory.isDirectory())
		{
			try 
			{
				File myNewFile; // Initialisation d'un fichier
				
				// Ecriture du fichier en question, selon le nom donné
				if(fileName.equals(""))	// Ajout normal		
				{
					myNewFile = new File(directory, tName.getText()+".txt");
					tName.setText(tName.getText().substring(0, 1).toUpperCase()+tName.getText().substring(1, tName.getText().length()));
				}
				else // Photo de la galerie
					myNewFile = new File(directory, v.getName() +".txt");
				
				myNewFile.createNewFile();
								
				FileWriter 		wFile 	= new FileWriter(myNewFile);					
				BufferedWriter 	bFile 	= new BufferedWriter(wFile);
				
				
				// Vérification de la checkbox favori
				if(cFavoris.isSelected())
					v.setFavoris("1");
				else
					v.setFavoris("0");
				
				
				if(fileName.equals("")) // Ecriture des variables à partir des textboxes
					bFile.write("smart.conf;"+tName.getText()+";"+tPicture.getText()+";"+tLastname.getText()+";"+tNumero.getText()+";"+tNumeroP.getText()+";"+tEmail.getText()+";"+tProfession.getText()+";"+tOrganisation.getText()+";"+tWeb.getText()+";"+tDateNaissance.getText()+";"+tSonnerie.getText()+";"+tSurnom.getText()+";"+v.getFavoris()+";");
				else // Ecriture des variables à partir du fichier
					bFile.write("smart.conf;"+v.getName()+";"+fileName+";"+v.getLastname()+";"+v.getTelephone()+";"+v.getPortable()+";"+v.getEmail()+";"+v.getProfession()+";"+v.getOrganisation()+";"+v.getWeb()+";"+v.getDateNaissance()+";"+v.getSong()+";"+v.getNickname()+";"+v.getFavoris()+";");
				
					
				bFile.close();
				
				// Fermeture de la dialogs
				if(fileName.equals(""))
					dgFrame.dispose();
				
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (NullPointerException npe)
			{
				System.out.println("Erreur avec les données des textboxes");
				npe.printStackTrace();
			}
		}
		else
		{
			System.out.println("Le répertoire d'écriture n'est pas un dossier...");
		}
	}	
	
	/**
	 * setInfos(...)
	 * @param String mode : mode utilisé (Set = Initialisation | get = Utilisation)
	 * Initialise les textboxes de configurations avec les informations des fichiers et les affiche
	 */	
	private void setInfos(String mode) 
	{
		// En cas d'ajout d'un contact
		if(mode.equals("set"))
		{
			v.setName("name");
			v.setPic("-");	v.setLastname("-");	v.setTelephone("-"); v.setPortable("-");
			v.setEmail("-"); v.setProfession("-"); v.setOrganisation("-");	v.setWeb("-");
			v.setDateNaissance("-"); v.setSong("-"); v.setNickname("-"); v.setFavoris("-");
		}
		
		// Vérification des touches utilisées
		ContactKeyListener myKey = new ContactKeyListener();
		

	// Initialisation des textboxes et labels selon le mode et l'état avec vérification de touches	
		tName.setText(v.getName());
			JLabel	lName = new JLabel("Forname");
			tName.addKeyListener(myKey);
		if(!mode.equals("set"))
			tName.setEditable(false);
		else
			tName.setEditable(true);
		
		tPicture.setText(v.getPic());
			tPicture.addKeyListener(myKey);
			JLabel	lPicture = new JLabel("Picture");
			sPicBrowse = new JLabel();
			sPicBrowse.addMouseListener(new MouseLisenerContact());
			sPicBrowse.setIcon(new ImageIcon(Contact.class.getResource("/img/sbrowse.PNG")));
			
		tLastname.setText(v.getLastname());
			tLastname.addKeyListener(myKey);
			JLabel	lLastname = new JLabel("Lastname");
				
		tNumero.setText(v.getTelephone());
			tNumero.addKeyListener(myKey);
			JLabel lNumero = new JLabel("Numéro de téléphone");
			dtelephone = new JLabel();
			dtelephone.setHorizontalAlignment(SwingConstants.LEFT);
			dtelephone.setIcon(new ImageIcon(Contact.class.getResource("/img/sdelete.PNG")));
			dtelephone.addMouseListener(new MouseLisenerContact());
		
			
		tNumeroP.setText(v.getPortable());
			tNumeroP.addKeyListener(myKey);
			JLabel	lNumeroP = new JLabel("Numéro de portable");
			dportable = new JLabel();
			dportable.setHorizontalAlignment(SwingConstants.LEFT);
			dportable.setIcon(new ImageIcon(Contact.class.getResource("/img/sdelete.PNG")));
			dportable.addMouseListener(new MouseLisenerContact());
		
		
		tEmail.setText(v.getEmail());
			tEmail.addKeyListener(myKey);
			JLabel	lEmail = new JLabel("Email");
			demail = new JLabel();
			demail.setHorizontalAlignment(SwingConstants.LEFT);
			demail.setIcon(new ImageIcon(Contact.class.getResource("/img/sdelete.PNG")));
			demail.addMouseListener(new MouseLisenerContact());
			
		tProfession.setText(v.getProfession());
			tProfession.addKeyListener(myKey);
			JLabel lProfession = new JLabel("Profession");
				
		tOrganisation.setText(v.getOrganisation());
			tOrganisation.addKeyListener(myKey);
			JLabel lOrganisation = new JLabel("Organisation");
				
		tWeb.setText(v.getWeb());
			tWeb.addKeyListener(myKey);
			JLabel	lWeb = new JLabel("Site Web");
				
		tDateNaissance.setText(v.getDateNaissance());
			tDateNaissance.addKeyListener(myKey);
			JLabel lDateNaissance = new JLabel("Date de Naissance");
			
		tSonnerie.setText(v.getSong());
			tSonnerie.addKeyListener(myKey);
			JLabel	lSong = new JLabel("Ringston");
			sSongBrowse = new JLabel();
			sSongBrowse.addMouseListener(new MouseLisenerContact());
			sSongBrowse.setIcon(new ImageIcon(Contact.class.getResource("/img/sbrowse.PNG")));

		tSurnom.setText(v.getNickname());
			tSurnom.addKeyListener(myKey);
			JLabel lSurnom  = new JLabel("Surnom");
				
		JLabel lFavoris  = new JLabel("Favoris");;
			cFavoris.setOpaque(false);
	
		// Mise en place de labels pour la bonne harmonie graphique
		JPanel p1  = new JPanel();	p1.setOpaque(false);
		JPanel p2  = new JPanel();	p2.setOpaque(false);
		JPanel p3  = new JPanel();	p3.setOpaque(false);
		JPanel p4  = new JPanel();	p4.setOpaque(false);
		JPanel p5  = new JPanel();	p5.setOpaque(false);
		JPanel p6  = new JPanel();	p6.setOpaque(false);
		JPanel p7  = new JPanel();	p7.setOpaque(false);
		JPanel p8  = new JPanel();	p8.setOpaque(false);
		JPanel p9  = new JPanel();	p9.setOpaque(false);
		JPanel p10  = new JPanel();	p10.setOpaque(false);
		JPanel p11  = new JPanel();	p11.setOpaque(false);
		JPanel p12  = new JPanel();	p12.setOpaque(false);
		JPanel sp13  = new JPanel();sp13.setOpaque(false);
		JPanel sp14  = new JPanel();sp14.setOpaque(false);

		
		// Attribution des labels et des outils
		panel_add.add(lName); panel_add.add(p1);
			p1.add(tName);
						
		panel_add.add(lPicture); panel_add.add(p2);
			p2.add(tPicture);
			p2.add(sPicBrowse);
		
		panel_add.add(lLastname); panel_add.add(p3);
		p3.add(tLastname);
		
		panel_add.add(lNumero);	panel_add.add(p4);
			p4.add(tNumero);
			p4.add(dtelephone);
					
		panel_add.add(lNumeroP); panel_add.add(p5);
			p5.add(tNumeroP);
			p5.add(dportable);
						
		panel_add.add(lEmail); panel_add.add(p6);
			p6.add(tEmail);
			p6.add(demail);
			
		panel_add.add(lProfession);	panel_add.add(p7);
		p7.add(tProfession);
				
		panel_add.add(lOrganisation); panel_add.add(p8);
		p8.add(tOrganisation);
		
		panel_add.add(lWeb); panel_add.add(p9);
		p9.add(tWeb);
		
		panel_add.add(lDateNaissance);	panel_add.add(p10);
		p10.add(tDateNaissance);
		
		panel_add.add(lSong); panel_add.add(p11);
			p11.add(tSonnerie);
			p11.add(sSongBrowse);
		
		panel_add.add(lSurnom);	panel_add.add(p12);
		p12.add(tSurnom);
		
		panel_add.add(lFavoris);
		panel_add.add(cFavoris);
	}

	/**
	 * showContact()
	 * Affiche les princpales informations des contacts enregistrés
	 */	
	private void showContact()
	{
		// Prend la première lettre du nom du contact
		char c = v.getName().substring(0, 1).charAt(0); 
		
		// Codex ASCII 
		int codex = ((int)(c))-65;
				
		// Initialisation du mouse listener
		MouseLisenerContact mlcLbl = new MouseLisenerContact();

		try
		{
			if(first[codex] != '"') // Si la lettre n'est toujours pas utilisée
			{
				// Mise en titre de cette lettre
				JLabel title = new JLabel(v.getName().substring(0, 1));
				panel_contacts.add(title);
				
				title.setOpaque(true);
				title.setBackground(new Color(230, 230, 230, 20));
				
				title.setForeground(SystemColor.control);
				title.setFont(new Font("Tahoma", Font.BOLD, 20));
				
				// Supression de la lettre dans le tableau
				first[codex] = '"';
				
				if(inc==0)
					height++; // Augmentation du layout pour chaque entête
			}
			
		}
		catch (ArrayIndexOutOfBoundsException a) 
		{
			System.out.println("Nom non-conforme");
		}
		
		try
		{	
			panel_contacts.setLayout(new GridLayout(height,0));
		}
		catch (Exception e) 
		{
			System.out.println("Problème de redimentionnement du layout");
		}
		// Label abritant le nom et la photo du contacts
		lblPic = new JLabel(v.getName());

		// Ajout du contact
		panel_contacts.add(lblPic);
		lblPic.setForeground(SystemColor.control);
		
		File f = new File(v.getPic());
		
		ImageIcon imageIcon;
		
		// Si l'image est un fichier
		if(f.isFile())
		{
			imageIcon = new ImageIcon(v.getPic()); // Appel de l'image en question
		}
		else
		{
			imageIcon = (new ImageIcon(Contact.class.getResource("/img/default.PNG"))); // Mise en place d'une image par default
		}
		Image image = imageIcon.getImage(); // Casting
		Image newimg = image.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH); // Mise en place s
		lblPic.setIcon(new ImageIcon(newimg)); // Casting retour
	
		
		// Chaque contact peut être actionné avec la souris
		lblPic.addMouseListener(mlcLbl);
	}

	/**
	 * tools_alpha_add()
	 * Initialise les outils primaires
	 */	
	private void tools_alpha_add()
	{
		// ADD OPTIONS
		lblAdd = new JLabel("");
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setIcon(new ImageIcon(Contact.class.getResource("/img/addSubmit.PNG")));
		panel_tools.add(lblAdd);

		// SHOW CONTACT
		lblContact = new JLabel("");
		panel_tools.add(lblContact);		
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setIcon(new ImageIcon(Contact.class.getResource("/img/contactSubmit.PNG")));
		
		// FAVORIS CONTACT
		lblFavoris = new JLabel("");
		panel_tools.add(lblFavoris);		
		lblFavoris.setHorizontalAlignment(SwingConstants.CENTER);
		lblFavoris.setIcon(new ImageIcon(Contact.class.getResource("/img/favorisSubmit.PNG")));
		
		// Transformation des labels en boutons
		MouseLisenerContact mlc = new MouseLisenerContact();
		lblAdd.addMouseListener(mlc);
		lblContact.addMouseListener(mlc);
		lblFavoris.addMouseListener(mlc);
		
	}
	
	/**
	 * tools_beta_add()
	 * Initialise les outils secondaires
	 * @param icon : Image symbolique a afficher 
	 */	
	private void tools_beta_add(Icon icon)
	{
		// Affichage du nom et de l'icone du contact sélectionné
		JLabel lblLooking = new JLabel(v.getName());
		lblLooking.setIcon(icon);

		lblLooking.setForeground(Color.BLACK);
		lblLooking.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		lblLooking.setHorizontalAlignment(SwingConstants.CENTER);
		panel_tools.add(lblLooking);

		// ADD OPTIONS
		lblModifier = new JLabel("");
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifier.setIcon(new ImageIcon(Contact.class.getResource("/img/modifySubmit.PNG")));
		panel_tools.add(lblModifier);

		// SHOW CONTACT
		lblDelete = new JLabel("");
		panel_tools.add(lblDelete);		
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setIcon(new ImageIcon(Contact.class.getResource("/img/crossSubmit.PNG")));
		
		// FAVORIS CONTACT
		lblComeBack = new JLabel("");
		panel_tools.add(lblComeBack);		
		lblComeBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblComeBack.setIcon(new ImageIcon(Contact.class.getResource("/img/backSubmit.PNG")));

		// Transformation des labels en boutons
		MouseLisenerContact mlc = new MouseLisenerContact();
		lblModifier.addMouseListener(mlc);
		lblDelete.addMouseListener(mlc);
		lblComeBack.addMouseListener(mlc);
		
	}
	
	/**
	 * DialogFrameListener()
	 * En cas de fermeture d'une JDialog
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
	 * FrameListener()
	 * En cas de fermeture d'une JFrame
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
				
				// Ouverture de la fenètre hierarchiquement plus faible
				Index i = new Index();
			}			
		}
	}
	
	
	/**
	 * ButtonActionListener
	 * En cas d'enclenchement d'un bouton
	 */	
	class ButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// En cas de sauvegarde d'une modification au contact
			if(e.getSource() == submitSave)
			{
				fileWriter("");	 // Ecriture de la modification
				
				panel_contacts.removeAll();
				panel_contacts.repaint();
				
				// Augmentation du layout pour chaque ajout
				height = height+2;
				file(url, "show"); // Affichage des contacts
			}
			else
			{	
				// En cas de fermeture (revenir en arrière)
				int reponse = JOptionPane.showConfirmDialog(null,"Voulez-vous réellement quitter sans sauver ?");
			
				if(reponse == JOptionPane.YES_OPTION)
				{
					dgFrame.dispose();
				}
			}
			
		}	
	}

	
	/**
	 * ContactKeyListener
	 * En cas d'enclenchement du clavier
	 */	
	class ContactKeyListener extends KeyAdapter
	{
		// En cas de l'utilisation du ;
		@Override
		public void keyTyped(KeyEvent e) 
		{
			// En cas d'utilisation du ;
			if(e.getKeyChar() == ';')
				e.setKeyChar(',');
			
			
			// En cas d'utilisation de caractères spéciaux
			int ascii = (int)(e.getKeyChar());			
			if((ascii > 31  && ascii < 65 || ascii > 122) )
			{
				JOptionPane.showMessageDialog(null, e.getKeyChar() + "  : Caractère non-valide", "Caractère non-valide", 1);
				e.setKeyChar('a');
			}
		}
	}
	
	/**
	 * MouseLisenerContact
	 * En cas d'enclenchement de la souris
	 */	
	class MouseLisenerContact extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent myMouse)
		{

			// En cas d'appel depuis la galerie
			if(t)
			{
				// Modification du contact choisi avec l'image choisie
				JLabel objectLbl = (JLabel) myMouse.getSource();
				v.setName(objectLbl.getText());	
				fileUnique(url, v.getName(), imageContact);
				
				frame.dispose();
			}
			else
			{
				// En cas d'appel du bouton add
				if(myMouse.getSource() == lblAdd)
				{
					ChangeContact("adding"); // Affiche la dialog d'ajout
				}
				// En cas d'appel du bouton favori
				else if(myMouse.getSource() == lblFavoris)
				{
					file(url, "favoris"); // affiche les favoris
				}
				// En cas d'appel du bouton contact
				else if(myMouse.getSource() == lblContact)
				{
					file(url, "show"); // Affiche les contacts
				}
				// En cas d'appel du bouton comeback				
				else if(myMouse.getSource() == lblComeBack)
				{
					panel_tools.removeAll();
						tools_alpha_add(); // Appel les outils primaires
					panel_tools.repaint();
				}
				// En cas d'appel du bouton delete				
				else if(myMouse.getSource() == lblDelete)
				{
					fileUnique(url, v.getName(), "delete"); // Supprime le contact en question
					height = height-2;
					
					panel_tools.removeAll();
						tools_alpha_add(); // Appel les outils primaires
					panel_tools.repaint();
				
					file(url, "show"); // Affiche les contacts
				}
				// En cas d'appel du bouton modifier
				else if(myMouse.getSource() == lblModifier)
				{
					fileUnique(url, v.getName(), "modify"); // Identifie le contact à modifier et modification
					
					panel_tools.removeAll();
						tools_alpha_add(); // Appel des outils primaires
					panel_tools.repaint();
	
					file(url, "show"); // Affichage des contacts
				}
				// En cas d'appel du browser d'image
				else if(myMouse.getSource() == sPicBrowse)
				{
					// Initialise le FileChooser
				    JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File(url+"\\img\\"));
		            chooser.setDialogTitle("Browse the folder to process");
		            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif"));
				    chooser.setAcceptAllFileFilterUsed(false);
		            
		            try // En cas d'annulation
		            {
			            chooser.showOpenDialog(null);
			            v.setPic(chooser.getSelectedFile().toString());
			            tPicture.setText(v.getPic()); 
		            }
		            catch (Exception e)
		            {
						
					}
				}
				// En cas d'appel du browser de chansons
				else if(myMouse.getSource() == sSongBrowse)
				{
					// Initialise le FileChooser
				    JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File(url+"\\songs\\"));
		            chooser.setDialogTitle("Browse the folder to process");
		            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		            chooser.setAcceptAllFileFilterUsed(false);
		            try // En cas d'annulation
		            {
			            chooser.showOpenDialog(null);
			            v.setSong(chooser.getSelectedFile().toString());
			            tSonnerie.setText(v.getSong()); 
		            }
		            catch (Exception e)
		            {
						
					}
				}
				
				// En cas d'appel du bouton supression email
				else if(myMouse.getSource() == demail)
				{
					tEmail.setText("-");
				}
				// En cas d'appel du bouton supression portable				
				else if(myMouse.getSource() == dportable)
				{
					tNumeroP.setText("-");	
				}
				// En cas d'appel du bouton supression telephone				
				else if(myMouse.getSource() == dtelephone)
				{
					tNumero.setText("-");	
				}
				// En cas d'appel d'un contact				
				else
				{
					JLabel objectLbl = (JLabel) myMouse.getSource(); // Identifie le contact
					v.setName(objectLbl.getText()); // Initialisation du nom

					panel_tools.removeAll();
						tools_beta_add(objectLbl.getIcon()); // Appel les outils secondaires.
					panel_tools.repaint();
				}
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
		try
		{
			File directory = new File(url); // Dossier de l'url primaire
			
			panel_contacts.removeAll();
			panel_contacts.repaint();	
			
			// Création d'un tableau alphabétique
			char c = 'A';
			for (int x = 0; x <= 25; x++) 
			{
				first[x] = c; c++;
			}
			
			if(directory.isDirectory())
			{
				// Liste des fichiers du dossier
				File[] contatcs = directory.listFiles();
				
				for (int i = 0; i < contatcs.length; i++) 
				{
					// Vérification du txt
					if(contatcs[i].getAbsolutePath().substring(contatcs[i].getAbsolutePath().length()-3, contatcs[i].getAbsolutePath().length()).equals("txt"))
					{
						if(contatcs[i].isFile())
						{
							// Initialisation des variables selon le fichier
							setVariablesByFile(contatcs[i]);
							
							if(action.equals("show")) // mode : show
							{
								showContact(); // Affiche les contacts
							}
							else if(action.equals("favoris")) // mode : favoris
							{						
								if(v.getFavoris().equals("1"))
									showContact(); // Affiche les contacts favoris
							}						
						}
					}
				}
				inc++;
			}
			else
				System.out.println("Erreur - L'URL donnée n'est pas un dossier valide.");		
		}
		catch(Exception e)
		{
				// Erreur de lecture du fichier
		}
	}
	
	/**
	 * fileUnique(String url, String fileName, String mode)
	 * @param String url 		- Choix du dossier des contacts
	 * @param String fileName	- Name of the contact to use
	 * @param String mode	 	- Appel le mode création (vide) ou modification (avec infos) 
	 */	
	private void fileUnique(String url, String fileName, String mode) 
	{
		File directory = new File(url); // Dossier de l'url primaire
			if(directory.isDirectory())
			{
				// Liste des fichiers du dossier
				File[] contatcs = directory.listFiles();
				
				for (int i = 0; i < contatcs.length; i++) 
				{
					if(contatcs[i].isFile())
					{
						// Identifiation du fichier de configuration selon le contact
						if(contatcs[i].getName().equals(fileName+".txt"))
						{
							if(mode.equals("modify")) // mode : modify
							{	
								setVariablesByFile(contatcs[i]); // Appel des variables du contact
								ChangeContact("modify"); // Appel la dialog avec les informations
							}
							else if(mode.equals("delete")) // mode : delete
							{
								contatcs[i].delete(); // supprimme le contact
	
							}
							else // mode : -
							{
								setVariablesByFile(contatcs[i]); // Appel des variables du contact (Changement de la photo)
								fileWriter(mode); // Ecris la contact avec la nouvelle photo.
							}
						}
					}					
				}
			}
		
	}
	
	/**
	 * setVariablesByFile(File myFileToSet)
	 * @param File myFileToSet	- File to use
	 * Set all variables of the contact's file
	 */
	private void setVariablesByFile(File myFileToSet)
	{
		// Incrémentation des distances entre chaque variable de la chaîne result
		int inc, inc_save; String result; String convert;

		try 
		{
			FileReader 		rFile 	= new FileReader(myFileToSet);					
			BufferedReader 	bFile 	= new BufferedReader(rFile);
			
			// Décortique result grâce aux ";" et appel le setter de chaque variable.
			
			
			result 		= bFile.readLine();
			
			if(result.substring(0, 10).equals("smart.conf"))
			{
				
				inc			= result.indexOf(";", 12);
				convert 	= result.substring(11, inc); 
				convert		= convert.substring(0, 1).toUpperCase()+convert.substring(1, convert.length());
				v.setName(convert);
								
				inc_save	= result.indexOf(";", inc+1);
				v.setPic(result.substring(inc+1, inc_save));
				
				inc			= result.indexOf(";", inc_save+1);
				v.setLastname(result.substring(inc_save+1, inc));
				
				inc_save 	= result.indexOf(";", inc+1);
				v.setTelephone(result.substring(inc+1, inc_save));
				
				inc			= result.indexOf(";", inc_save+1);
				v.setPortable(result.substring(inc_save+1, inc));
				
				inc_save 	= result.indexOf(";", inc+1);
				v.setEmail(result.substring(inc+1, inc_save));
				
				inc			= result.indexOf(";", inc_save+1);
				v.setProfession(result.substring(inc_save+1, inc));
				
				inc_save 	= result.indexOf(";", inc+1);
				v.setOrganisation(result.substring(inc+1, inc_save));
				
				inc			= result.indexOf(";", inc_save+1);
				v.setWeb(result.substring(inc_save+1, inc));
				
				inc_save 	= result.indexOf(";", inc+1);
				v.setDateNaissance(result.substring(inc+1, inc_save));
				
				inc			= result.indexOf(";", inc_save+1);
				v.setSong(result.substring(inc_save+1, inc));
				
				inc_save 	= result.indexOf(";", inc+1);
				v.setNickname(result.substring(inc+1, inc_save));
				
				inc			= result.indexOf(";", inc_save+1);
				v.setFavoris(result.substring(inc_save+1, inc));
			}
			
			bFile.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException io)
		{
			io.printStackTrace();
		}
		catch (ArrayIndexOutOfBoundsException io)
		{
			io.printStackTrace();
		}
	}
}
