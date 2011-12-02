package gui;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class JoinView extends MasterViewPanel {

	public JoinView(MasterView m) {
		super(m);
		// TODO Auto-generated constructor stub
		
	}
	private void setupLayout(){
		this.setLayout(new GridLayout(1,2));
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		JTextArea log = new JTextArea();
		
	}
}
