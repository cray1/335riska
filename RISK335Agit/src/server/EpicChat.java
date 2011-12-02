package server;
import javax.swing.JOptionPane;


public class EpicChat {

	public static void main(String[] args) {
		RiskiClient client = new RiskiClient(JOptionPane.showInputDialog("Please enter your name."), JOptionPane
				.showInputDialog("Please enter the ip address you'd like to connect to."), Integer.parseInt(JOptionPane
						.showInputDialog("Please enter the port to use.")));
		client.addObserver(new RiskiWindow(client));
	}
}
