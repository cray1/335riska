package server;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class RiskiWindow  extends JFrame implements Observer {

	private static final long serialVersionUID = 5690326605708216038L;
	
	private JTextArea log;
	private JTextField inputBox;
	private JScrollPane logScroll;
	private JButton sendButton;
	private RiskiClient client;
	
	public RiskiWindow(RiskiClient client){
		this.client = client;
		client.addObserver(this);
		
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 700);

		log = new JTextArea();
		log.setSize(780, 590);
		log.setLocation(5, 5);
		log.append(client.getName() + " has connected on " + client.getIP() + ", port " + client.getPort() + "\n");
		log.setEditable(false);
		log.setVisible(true);

		logScroll = new JScrollPane(log);
		logScroll.setSize(780, 590);
		logScroll.setLocation(5, 5);
		logScroll.setVisible(true);

		inputBox = new JTextField();
		inputBox.setSize(690, 25);
		inputBox.setLocation(5, 610);
		inputBox.addActionListener(new SendListener());
		inputBox.setVisible(true);

		sendButton = new JButton();
		sendButton.setText("Send");
		sendButton.setSize(75, 25);
		sendButton.setLocation(700, 610);
		sendButton.addActionListener(new SendListener());
		sendButton.setVisible(true);

		this.add(logScroll);
		this.add(inputBox);
		this.add(sendButton);
		this.setVisible(true);
		inputBox.grabFocus();

		this.setTitle(client.getName() + "'s Epic Chat");
	}

	// this is the listener for sending messages to the server
	private class SendListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			client.writeObject(inputBox.getText());
			inputBox.setText("");
			inputBox.grabFocus();
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		log.append((String) arg1);
		log.setCaretPosition(log.getDocument().getLength());
	}
}
