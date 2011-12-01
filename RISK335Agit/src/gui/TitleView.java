package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * @author AJ Venne Created on 12:35:37 AM Nov 27, 2011
 */
public class TitleView extends MasterViewPanel {

	/**
	 * @author AJ Venne Created on 12:35:37 AM Nov 27, 2011
	 */
	private static final long serialVersionUID = -5462930111274891709L;
	private JButton newGame;
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
		add(new JLabel(new ImageIcon("images/title.png")));
	}

	/*
	 * Add the JButtons for exiting and starting a new game.
	 */
	private void setUpButtons() {
		newGame = new JButton("New Game");
		newGame.addActionListener(new NewGameListener());
		this.add(newGame);
		exit = new JButton("Exit");
		exit.addActionListener(new ExitListener());
		this.add(exit);
	}

	private class NewGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.MAP, null);
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
