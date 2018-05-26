package src.Smartphone;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Pattern 
{
	JPanel panel_north = new JPanel();
	JPanel panel_south = new JPanel();
	JFrame fmMain = new JFrame();
	JDialog dgFrame = new JDialog();
	
	JPanel panel_left;

	JButton btnHome;
	JButton btnRevert;
	JLabel lblTime;
	JLabel lblNotification; 
	
	
	private int inc = 0;
	
	/**
	 * Contructor
	 * @param panel_north 	: Emplacement nord de la fenètre
	 * @param panel_south 	: Emplacement sud de la fenètre
	 * @param fmMain 		: Fenètre en question
	 */	
	public Pattern(JPanel panel_north, JPanel panel_south, JFrame fmMain) 
	{
		this.panel_south = panel_south;
		this.panel_north = panel_north;
		this.fmMain  = fmMain;
	}

	
	public Pattern(JDialog dgFrame) 
	{
		this.dgFrame  = dgFrame;
	}
	
	public void Header(boolean v)
	{

		
		
		if(v)
			fmMain.getContentPane().add(panel_north, BorderLayout.NORTH);		
		else
			dgFrame.getContentPane().add(panel_north, BorderLayout.NORTH);	
		
		panel_north.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_left = new JPanel();
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
			
			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		    lblTime = new JLabel(sdf.format(cal.getTime()));
  
			time();
			
				panel_right.add(lblTime);
				lblTime.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblTime.setForeground(new Color(255, 255, 255));		
	}
	
	public void time()
	{
		Timer timer = new Timer(1000, new ClockListener());
		timer.start();
    }
	

	public void Footer(boolean v, boolean level)
	{
		if(v)
			fmMain.getContentPane().add(panel_south, BorderLayout.SOUTH);

		else
			dgFrame.getContentPane().add(panel_south, BorderLayout.SOUTH);

			panel_south.setLayout(new GridLayout(0, 2));
			panel_south.setBackground(new Color(0, 0, 0));
			panel_south.setBorder(new BevelBorder(0, Color.WHITE, Color.WHITE));

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
		
		ActionListenerFooter alf = new ActionListenerFooter();
		btnHome.addActionListener(alf);
		
		if(level)
			btnRevert.addActionListener(new LastActionListenerFooter());
		
		else
			btnRevert.addActionListener(alf);
	}
	
	
	public void Scroll(JPanel in, JPanel out)
	{
		// Scroll Bar
		JScrollPane jspCenter = new JScrollPane(in);
		out.add(jspCenter, BorderLayout.CENTER);
		jspCenter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspCenter.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	}
	
	class ActionListenerFooter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == btnHome)
			{
				fmMain.dispose();
				dgFrame.dispose();
				
				@SuppressWarnings("unused")
				Index i = new Index();
			}
			else
			{
				fmMain.dispose();	
				dgFrame.dispose();

				@SuppressWarnings("unused")
				Index i = new Index();
			}
		}
	}
	
	class LastActionListenerFooter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			fmMain.dispose();	
			
			@SuppressWarnings("unused")
			Locked l = new Locked();
		}
	}
	
	class ClockListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			inc++;
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        lblTime.setText(sdf.format(cal.getTime()));
	        
	        if(inc == 5)
	        	lblNotification.setEnabled(true);
	        
		}	        
		
	}

}
