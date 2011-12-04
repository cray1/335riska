package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import server.RiskiClient;
import server.RiskiServer;

/**
 * @author AJ Venne Created on 12:35:37 AM Nov 27, 2011
 */
public class TitleView extends MasterViewPanel {

	/**
	 * @author AJ Venne Created on 12:35:37 AM Nov 27, 2011
	 */
	private static final long serialVersionUID = -5462930111274891709L;
	private JButton hostGame;
	private JButton joinGame;
	private JButton exit;

	/**
	 * Creates a new TitleView ready to be viewed.
	 * 
	 * @author AJ Venne
	 * @param m
	 */
	public TitleView(MasterView m) {
		super(m);
		setUpLayout();
		setUpButtons();
	}

	private void setUpLayout() {
		this.setLayout(new GridLayout(3, 1));
		this.add(new JLabel(new ImageIcon("images/title.png")));
	}

	/*
	 * Add the JButtons for exiting and starting a new game.
	 */
	private void setUpButtons() {
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		
		hostGame = new JButton("Host Game");
		hostGame.addActionListener(new HostGameListener());
		buttonPanel.add(hostGame);
		
		joinGame = new JButton("Join Game");
		joinGame.addActionListener(new JoinGameListener());
		buttonPanel.add(joinGame);
		
		
		this.add(buttonPanel, BorderLayout.CENTER);
		
		exit = new JButton("Exit");
		exit.addActionListener(new ExitListener());
		this.add(exit);
	}
	//!!!!!!!!!!!
	private class HostGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String port = JOptionPane.showInputDialog(
					"Enter the port you want to listen on:", null);
			
			m.server = new RiskiServer(port);
			String name = JOptionPane.showInputDialog(
					"Enter Your name:", null);
			try{
				m.client = new RiskiClient(name, "127.0.0.1", Integer.parseInt(port));
				
			}catch (Exception e){
				m.client = new RiskiClient(name, "127.0.0.1", 4000);
			}
			m.changeView(Views.MAP, null);
			
		}

	}
	private class JoinGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.client = new RiskiClient(JOptionPane.showInputDialog("Please enter your name."), JOptionPane
					.showInputDialog("Please enter the ip address you'd like to connect to."), Integer.parseInt(JOptionPane
							.showInputDialog("Please enter the port to use.")));
			m.changeView(Views.JOIN, null);
		}

	}
	private class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}

	}

	@Override
	public String toString() {
		return "TITLE";
	}

}
