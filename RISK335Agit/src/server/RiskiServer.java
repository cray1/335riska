package server;

/**
 * The Epic Chat server class.
 * @author Nik Stemm
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import baseModel.Move;

public class RiskiServer {

	private ServerSocket serverSocket;
	private LinkedBlockingQueue<ClientManager> removal;
	private LinkedList<ClientManager> clientManagers;
	private int port;
	Thread serverThread;
	public RiskiServer(String port) {
		new LinkedBlockingDeque<String>();
		clientManagers = new LinkedList<ClientManager>();
		removal = new LinkedBlockingQueue<ClientManager>();
		
		try{
			this.port = Integer.parseInt(port);
		}
		catch(Exception e){
			System.out.println("Not a valid port. Defaulting to 4000.");
			this.port = 4000;
		}
		 new ServerManager();
		 
		
		 
	}

	
	

	

	// every client gets its own object to be maintained in a list. Within this
	// object is a thread that handles all of the information that is received
	// on the input stream - which corresponds to an EpicClient's output stream.
	private class ClientManager {
		private String name;
		private String team;
		private Socket socket;
		private ObjectInputStream inStream;
		private ObjectOutputStream outStream;
		Thread getMessagesThread;

		private ClientManager(Socket sock) {
			socket = sock;

			try {
				outStream = new ObjectOutputStream(socket.getOutputStream());
				inStream = new ObjectInputStream(socket.getInputStream());
				try {
					name = (String) inStream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			getMessagesThread = new Thread(new getThread());
			getMessagesThread.start();

		}

		// this thread constantly waits for a new message from the client. When
		// such a message is received, it then has every other ClientManager to
		// send that message out to their clients.
		
		private class getThread implements Runnable {

			@Override
			public void run() {
				while (true) {
					try {
						Object input = inStream.readObject();
						String message;
						if (input instanceof String) {
							message = (String) input;

							for (ClientManager manager : clientManagers) {
								if (!message.contains("/w"))
									manager.sendS(message);
								else if (message.contains("/w " + name)) {
									String temp1, temp2;
									Scanner buffer = new Scanner(message);
									temp1 = buffer.next();
									temp2 = "";
									while (buffer.hasNext()) {
										String pass = buffer.next();
										if (pass.contains("/w"))
											buffer.next();
										if (!pass.contains("/w"))
											temp2 += pass + " ";
									}

									manager.sendS("[Whisper] " + temp1 + " "
											+ temp2);
								}
							}
						}
						else if (input instanceof Move)
							sendM((Move) input);

					} catch (IOException e) {
						System.out.println("A client has disconnected.");
						try {
							ClientManager.this.socket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						break;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					while (!removal.isEmpty()) {
						clientManagers.remove(removal.poll());
					}
				}
			}
		}

		public void sendS(String message) {

			try {
				outStream.writeObject(message);

			} catch (IOException e) {
				removal.add(ClientManager.this);
			}
		}

		public void sendM(Move mov) {

			try {
				outStream.writeObject(mov);

			} catch (IOException e) {
				removal.add(ClientManager.this);
			}
		}
	}
	private class ServerManager {



		private ServerManager() {
			

			serverThread = new Thread(new servThread());
			serverThread.start();

		}

		// this thread constantly waits for a new message from the client. When
		// such a message is received, it then has every other ClientManager to
		// send that message out to their clients.
		private class servThread implements Runnable {

			@Override
			public void run() {
				try {
					// start listening on given port

					try {
						serverSocket = new ServerSocket(port);
					} catch (IOException ioe) {
						System.out.println(" Invalid port. Defaulting to 4000.");
						serverSocket = new ServerSocket(4000);
					}
					System.out.println("Waiting for a connection...");
					// always wait for more connections. When a new client new connects,
					// add its thread to the list called clients, and start a new thread
					// for it.
					while (true) {
						Socket socket = serverSocket.accept();
						System.out.println("A new client has connected.");
						ClientManager newManager = new ClientManager(socket);
						clientManagers.add(newManager);
					}

				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}





	}
}

