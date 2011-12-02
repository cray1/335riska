package server;
/**
 * The Epic Chat client class.
 * @author Nik Stemm
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

import javax.swing.JOptionPane;

public class RiskiClient extends Observable{

	private String name;
	private String ip;
	private int port;
	private Socket socket;

	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;

	private Thread receivingThread;
	
	public RiskiClient(String name, String ip, Integer port) {
		// Get all the information necessary to connect to the server
		this.name = name;
		this.ip = ip;
		this.port = port;
		
		// connect to server using information given above
		try {
			socket = new Socket(ip, port);

			inStream = new ObjectInputStream(socket.getInputStream());
			outStream = new ObjectOutputStream(socket.getOutputStream());
			outStream.writeObject(name);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setChanged();
			notifyObservers("Unable to connect to server!");
		}
		
		// start new thread that receives messages
		receivingThread = new Thread(new ReceiveThread());
		receivingThread.start();
	}

	// this thread will constantly wait for new messages that it can add to the
	// list on the View's side
	private class ReceiveThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Object input = inStream.readObject();
					
					if (input instanceof String){
					String newMessage = (String) input;
					setChanged();
					notifyObservers(newMessage + "\n");
					}
				} catch (IOException e) {
					JOptionPane
							.showMessageDialog(null,
									"The connection to the server has been lost. The program will now close.");
					System.exit(0);
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getName() {
		return name;
	}


	public String getIP() {
		return ip;
	}


	public Integer getPort() {
		return port;
	}


	public void writeObject(String text) {
		try {
			String labelText;
			
				labelText = name + ": " + text;

			outStream.writeObject(labelText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
