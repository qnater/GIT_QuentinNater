package src.Smartphone;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Contact
{
	// Vital tools
	private JFrame frame;
	private JPanel panel_contacts = new JPanel();
	
	// Alphabet array
	private char[] first = new char[26];
	
	// Labels Tools (Use like submit)
	private JLabel lblAdd;
	private JLabel lblContact;
	private JLabel lblFavoris;	
	private JLabel lblDelete;
	private JLabel lblModifier;
	private JLabel lblComeBack;
	private JLabel lblPic;
	
	// Header's & Footer's Panel
	private	JPanel panel_north = new JPanel();
	private	JPanel panel_south = new JPanel();
	
	// JPanel (Page de modificatio), d'ajout et d'outils
	private JPanel panel_adding;
	private JPanel panel_add;
	private JPanel panel_tools;
	
	// Directory of the contact's picture
	private String url = "C:\\Smartphone";
	
	// Addin' / Modifin' Dialog Form
	private JButton submitSave;
	private JButton submitCancel;
	//private JButton submitReset;
	
	// Browser
	private JLabel sPicBrowse;
	private JLabel sSongBrowse;
	
	// Delete text from textbox
	private JLabel demail;
	private JLabel dtelephone;
	private JLabel dportable;
	
	// JDialogue Contact (Add-Modify)
	private JDialog dgFrame;
	
	private boolean t;
	private String imageContact;
	
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
	 * Contact()
	 * @param boolean t : Lien du dossier contenant les fichiers
	 * Conctructeur de contact qui initialise la fenètre
	 */	
	public Contact(String pic, boolean t) 
	{
		v.setPic(pic);
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
		
		
		// Header's & Footer's Call
		Pattern contact = new Pattern(panel_north, panel_south, frame);
			contact.Header(true);
			contact.Footer(true, false);

		
		// Création d'une arborescence pour abriter les contacts
		File directory = new File(url);

		// == PANEL configurations == /
		
		// Contact 1st Panel
			JPanel panel_center = new JPanel();
			frame.getContentPane().add(panel_center, BorderLayout.CENTER);
			panel_center.setLayout(new BorderLayout(0, 0));
			panel_center.setBackground(SystemColor.BLACK);
			
			// Height of the GridLayout
			File[] contatcs = directory.listFiles();			
		
			// Contact 2sd Panel
			panel_contacts.setAutoscrolls(true);
			panel_contacts.setBackground(Color.BLACK);
			
			int height = contatcs.length*2;			
			try
			{
				if(height < 10)
					height = 10;
				
				panel_contacts.setLayout(new GridLayout(height, 1));
			}
			catch (Exception e) 
			{
				
			}
			
		// Contact 3rd Panel
		panel_tools = new JPanel();
		panel_tools.setPreferredSize(new Dimension(10, 100));
		panel_center.add(panel_tools, BorderLayout.NORTH);
		panel_tools.setBackground(Color.BLACK);
		panel_tools.setLayout(new GridLayout(1, 3));
		
		contact.Scroll(panel_contacts, panel_center);
		
		//if(!t)
		tools_alpha_add();
	 
		// Save file call
		file(url, "show");
		
		frame.addWindowListener(new FrameListener());
		frame.repaint();
	}


	/**
	 * AddContact() 
	 * Initialise les données à zéro
	 */	
	private void AddContact() 
	{
		setInfos("set");
	}
	
	/**
	 * ChangeContact(String mode) 
	 * @param String mode - Appel le mode création (vide) ou modification (avec infos)
	 * Permet d'afficher la fenètre des informations d'un contact
	 */	
	private void ChangeContact(String mode) 
	{
		dgFrame = new JDialog();
		dgFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		dgFrame.setSize(480, 800);
		dgFrame.setVisible(true);
		dgFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
		Pattern adding = new Pattern(dgFrame);
		adding.Header(false);
		adding.Footer(false, false);
		
		panel_adding 	= new JPanel();
		panel_add 		= new JPanel();
		
		JPanel panel_save = new JPanel();

		dgFrame.add(panel_save, BorderLayout.SOUTH);


		panel_save.setLayout(new GridLayout(0, 2));
		
		submitSave = new JButton("Save");
		submitCancel = new JButton("Cancel");
		
		panel_save.add(submitSave);
		panel_save.add(submitCancel);
		
		ButtonActionListener bal = new ButtonActionListener();
		submitSave.addActionListener(bal);
		submitCancel.addActionListener(bal);
		
		dgFrame.add(panel_adding, BorderLayout.CENTER);
			panel_adding.setLayout(new BorderLayout(0, 0));
			panel_adding.add(panel_add, BorderLayout.CENTER);
		
		panel_adding.setAutoscrolls(true);
		
		adding.Scroll(panel_add, panel_adding);
		panel_add.setLayout(new GridLayout(13, 2));
		panel_add.setBackground(SystemColor.scrollbar);
		
		if(mode.equals("modify"))
			setInfos("modify");
		else
			AddContact();
		
		dgFrame.addWindowListener(new DialogFrameListener());
	}

	/**
	 * fileWriter() 
	 * Écriture d'un nouveau contact dans un fichier de configuration
	 * @param fileName 
	 */	
	private void fileWriter(String fileName) 
	{
		File directory = new File(url);
		
		if(directory.isDirectory())
		{
			try 
			{
				File myNewFile;
				if(fileName.equals(""))					
					myNewFile = new File(directory, tName.getText()+".txt");
				else
					myNewFile = new File(directory, v.getName() +".txt");
				
				myNewFile.createNewFile();
				
				FileWriter 		wFile 	= new FileWriter(myNewFile);					
				BufferedWriter 	bFile 	= new BufferedWriter(wFile);
				
				if(cFavoris.isSelected())
					v.setFavoris("1");
				else
					v.setFavoris("0");
				
				if(fileName.equals(""))
					bFile.write(tName.getText()+";"+tPicture.getText()+";"+tLastname.getText()+";"+tNumero.getText()+";"+tNumeroP.getText()+";"+tEmail.getText()+";"+tProfession.getText()+";"+tOrganisation.getText()+";"+tWeb.getText()+";"+tDateNaissance.getText()+";"+tSonnerie.getText()+";"+tSurnom.getText()+";"+v.getFavoris()+";");
				else
					bFile.write(v.getName()+";"+fileName+";"+v.getLastname()+";"+v.getTelephone()+";"+v.getPortable()+";"+v.getEmail()+";"+v.getProfession()+";"+v.getOrganisation()+";"+v.getWeb()+";"+v.getDateNaissance()+";"+v.getSong()+";"+v.getNickname()+";"+v.getFavoris()+";");
				
					
				bFile.close();
				
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
		if(mode.equals("set"))
		{
			v.setName("name");
			v.setPic("-");	v.setLastname("-");	v.setTelephone("-"); v.setPortable("-");
			v.setEmail("-"); v.setProfession("-"); v.setOrganisation("-");	v.setWeb("-");
			v.setDateNaissance("-"); v.setSong("-"); v.setNickname("-"); v.setFavoris("-");
		}
		
		ContactKeyListener myKey = new ContactKeyListener();
		
		
			
		tName.setText(v.getName());
			JLabel	lName = new JLabel("Forname");
			tName.addKeyListener(myKey);
				
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
	 * showContact(int i, String substring, String name, String pic, String url)
	 * Affiche les princpales informations des contacts enregistrés
	 */	
	private void showContact()
	{
		char c = v.getName().substring(0, 1).charAt(0); 
		
		int codex = ((int)(c))-65;

		
		MouseLisenerContact mlcLbl = new MouseLisenerContact();
		
		try
		{
			if(first[codex] != '"')
			{
				JLabel title = new JLabel(v.getName().substring(0, 1));
				panel_contacts.add(title);
				
				title.setOpaque(true);
				title.setBackground(new Color(230, 230, 230, 20));
				
				title.setForeground(SystemColor.control);
				title.setFont(new Font("Tahoma", Font.BOLD, 20));
				first[codex] = '"';
			}
		}
		catch (ArrayIndexOutOfBoundsException a) 
		{
			System.out.println("Nom non conforme");
		}
		
		lblPic = new JLabel(v.getName());

		panel_contacts.add(lblPic);
		lblPic.setForeground(SystemColor.control);
		
		File f = new File(v.getPic());
		
		ImageIcon imageIcon;
		
		if(f.isFile())
		{
			imageIcon = new ImageIcon(v.getPic()); // load the image to a imageIcon
		}
		else
		{
			imageIcon = (new ImageIcon(Contact.class.getResource("/img/default.PNG")));
		}
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		lblPic.setIcon(new ImageIcon(newimg)); // transform it back
	
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
			if(e.getSource() == submitSave)
			{
				fileWriter("");	
				
				panel_contacts.removeAll();
				panel_contacts.repaint();
				
				file(url, "show");
			}
			else
			{	
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


		@Override
		public void keyTyped(KeyEvent e) 
		{
			if(e.getKeyChar() == ';')
				e.setKeyChar(',');
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
			if(t)
			{
				JLabel objectLbl = (JLabel) myMouse.getSource();
				v.setName(objectLbl.getText());	
				fileUnique(url, v.getName(), imageContact);
				
				frame.dispose();
			}
			else
			{
				
				if(myMouse.getSource() == lblAdd)
				{
					ChangeContact("adding");
				}
				else if(myMouse.getSource() == lblFavoris)
				{
					file(url, "favoris");
				}
				else if(myMouse.getSource() == lblContact)
				{
					file(url, "show");
				}
				else if(myMouse.getSource() == lblComeBack)
				{
					panel_tools.removeAll();
						tools_alpha_add();
					panel_tools.repaint();
				}
				else if(myMouse.getSource() == lblDelete)
				{
					fileUnique(url, v.getName(), "delete");
					
					panel_tools.removeAll();
						tools_alpha_add();
					panel_tools.repaint();
				
					file(url, "show");
				}
				else if(myMouse.getSource() == lblModifier)
				{
					fileUnique(url, v.getName(), "modify");
					
					panel_tools.removeAll();
						tools_alpha_add();
					panel_tools.repaint();
	
					file(url, "show");
				}
				else if(myMouse.getSource() == sPicBrowse)
				{
					
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
				else if(myMouse.getSource() == sSongBrowse)
				{
					
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
				else if(myMouse.getSource() == demail)
				{
					tEmail.setText("-");
				}
				else if(myMouse.getSource() == dportable)
				{
					tNumeroP.setText("-");	
				}
				else if(myMouse.getSource() == dtelephone)
				{
					tNumero.setText("-");	
				}
				else
				{
					JLabel objectLbl = (JLabel) myMouse.getSource();
					v.setName(objectLbl.getText());
					
					panel_tools.removeAll();
						tools_beta_add(objectLbl.getIcon());
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
			File directory = new File(url);
			
			panel_contacts.removeAll();
			panel_contacts.repaint();	
			
			char c = 'A';
			for (int x = 0; x <= 25; x++) 
				first[x] = c; c++;
			
			if(directory.isDirectory())
			{
				File[] contatcs = directory.listFiles();
				
				for (int i = 0; i < contatcs.length; i++) 
				{
					if(contatcs[i].getAbsolutePath().substring(contatcs[i].getAbsolutePath().length()-3, contatcs[i].getAbsolutePath().length()).equals("txt"))
					{
						if(contatcs[i].isFile())
						{
							setVariablesByFile(contatcs[i]);
							
							if(action.equals("show"))
							{
								showContact();
							}
							else if(action.equals("favoris"))
							{						
								if(v.getFavoris().equals("1"))
									showContact();
							}						
						}
					}
				}
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
		File directory = new File(url);
			if(directory.isDirectory())
			{
				File[] contatcs = directory.listFiles();
				
				for (int i = 0; i < contatcs.length; i++) 
				{
					if(contatcs[i].isFile())
					{
						if(contatcs[i].getName().equals(fileName+".txt"))
						{
							if(mode.equals("modify"))
							{	
								setVariablesByFile(contatcs[i]);
								ChangeContact("modify");
							}
							else if(mode.equals("delete"))
							{
								contatcs[i].delete();
							}
							else
							{
								setVariablesByFile(contatcs[i]);
								fileWriter(mode);
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
		int inc, inc_save; String result;

		try 
		{
			FileReader 		rFile 	= new FileReader(myFileToSet);					
			BufferedReader 	bFile 	= new BufferedReader(rFile);
				
				result 		= bFile.readLine();
				
				inc			= result.indexOf(";", 0);
				v.setName(result.substring(0, inc));
								
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
	}
}
