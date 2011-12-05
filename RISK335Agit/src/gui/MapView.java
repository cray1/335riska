package gui;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import javax.swing.JLabel;

import baseModel.Continent;
import baseModel.Game;
import baseModel.Map;
import baseModel.Move;
import baseModel.Player;
import baseModel.Team;
import baseModel.Territory;


/**
 * MapView - the view of the map, includes a user bar, the map, and a chat area
 * @author Aj Venne
 */
@SuppressWarnings("serial")
public class MapView extends MasterViewPanel implements Observer{
	BufferedImage mapImage;
	Game game;
	Map gameMap;
	JPanel mapBox = new JPanel();
	HashMap<String, Continent> continents;
	JTextArea chatArea;
    JTextField typeArea;
	HashMap<String, Territory> territories;
	LinkedList<Territory> moveTerritories = new LinkedList<Territory>();
	ArrayList<Territory> territoryArrayList;
	public MapView(MasterView m) {
		super(m);
		setUpGame();
		setUpGUI();
		
		
	}
	
	private void setUpGUI(){
		this.setVisible(true);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		try {
			mapImage = ImageIO.read(new File("images/map2.png"));
		} 
		catch (IOException e) {
		}
		mapBox.setPreferredSize(new Dimension(mapImage.getWidth(null),mapImage.getHeight(null)));
		
		
		add(mapBox);
		
		this.addMouseListener(new mouse());
		//Adds motion listener for tooltip text
		this.addMouseMotionListener(new mouseMove());
		setUpUserBar();	
	}
	
	private void setUpUserBar(){
		JPanel userBar = new JPanel();
		userBar.setLayout(new BoxLayout(userBar, BoxLayout.X_AXIS));
		userBar.setVisible(true);
		userBar.setPreferredSize(new Dimension(mapImage.getWidth(null) -50, 200));
		
		this.add(userBar);
		JLabel turn = new JLabel("\tIt is "+ game.getActivePlayer().getTeam() +"\'s turn");
		JButton surrender = new JButton("Surrender");
		
		JButton turnInCards = new JButton("Turn in Cards");
		JButton	placeUnits = new JButton("Place Units");
		JButton nextPhase = new JButton("Next Phase");
		
		JButton attack = new JButton("Attack");
		JButton move = new JButton("Move");
		move.addActionListener(new moveButtonListener());
		JButton endTurn = new JButton("End Turn");
		
		//Main buttons
		JPanel	buttonBox = new JPanel();
		buttonBox.setLayout(new GridLayout(5, 1));
		buttonBox.add(turn);
		buttonBox.add(turnInCards);
		buttonBox.add(placeUnits);
		buttonBox.add(nextPhase);
		buttonBox.add(surrender);
		userBar.add(buttonBox);
		
		//Attack box
		JPanel attackBox = new JPanel();
		attackBox.setLayout(new GridLayout(5,1));
		attackBox.add(new JLabel(""));
		attackBox.add(attack);
		attackBox.add(move);
		attackBox.add(endTurn);
		attackBox.add(new JLabel(""));
		userBar.add(attackBox);
		
		//Cards

		JPanel cavalry = new JPanel();
		Image cavalryCard = null;
			try {
				cavalryCard = ImageIO.read(new File("images/cavalryCard.jpg"));
			} 
			catch (IOException e) {
			}
		cavalry.add(new JLabel(new ImageIcon(cavalryCard)));
		userBar.add(cavalry);
		
		
	    //Chat area
        JPanel textArea = new JPanel();
        textArea.setLayout(new BoxLayout(textArea, BoxLayout.Y_AXIS));
        chatArea = new JTextArea(2, 15);
        typeArea = new JTextField();

        
        chatArea.setLineWrap(true);
        chatArea.setEditable(false);
        typeArea.addActionListener(new retListener());
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setPreferredSize(new Dimension(150, 200));
        textArea.add(scrollPane);
        textArea.add(typeArea);
        userBar.add(textArea);
        //experiment
        
        
        
        chatArea.setText("Welcome to Risk!\nUse the buttons to the left to make your move!\nHover over any territory to view it's name!\n"+
                                        "Enter text below to talk to your fellow players, this is your chat box! \n");
        m.client.addObserver(this);
		
	}
	
	private void setUpGame(){
		game = new Game();
		game.setActivePlayer(new Player(Team.GREEN));
		gameMap = game.getMap();
		continents = gameMap.getMap();
		territories = gameMap.getMapAsStringTerritoryHashMap();
		territoryArrayList = gameMap.getTerritories();
	}
	/**
	 * Called on repaint, used to update territory labels, etc.
	 * @author Aj Venne
	 */
	
	public void paint(Graphics g){
		super.paintComponents(g);
		g.drawImage(mapImage, 0, 0, this);
		
//		TO DO: Working on making these flags appear over/on every owned territory on repaint, gonna make them small to fit new map better.
		territories.get("Alaska").setOwningTeam(Team.RED);
		
		for(int i = 0; i < territoryArrayList.size(); i++){
			
			//North America
			if(territoryArrayList.get(i).toString().equals("Alaska")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 63, 32, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 63, 32, this);
			}
			if(territoryArrayList.get(i).toString().equals("Northwest Territory")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 147, 33, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 147, 33, this);
			}
			if(territoryArrayList.get(i).toString().equals("Greenland")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 354, 3, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 354, 3, this);
			}
			if(territoryArrayList.get(i).toString().equals("Alberta")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 111, 74, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 111, 74, this);
			}
			if(territoryArrayList.get(i).toString().equals("Ontario")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 179, 77, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 179, 77, this);
			}
			if(territoryArrayList.get(i).toString().equals("Quebec")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 237, 74, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 237, 74, this);
			}
			
			if(territoryArrayList.get(i).toString().equals("Western United States")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 101, 132, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 101, 132, this);
			}
			if(territoryArrayList.get(i).toString().equals("Eastern United States")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 163, 126, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 163, 126, this);
			}
			if(territoryArrayList.get(i).toString().equals("Central America")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 105, 191, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 105, 191, this);
			}
			
			//South America
			if(territoryArrayList.get(i).toString().equals("Venezuela")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 201, 253, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 201, 253, this);
			}
			
			if(territoryArrayList.get(i).toString().equals("Peru")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 189, 296, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 189, 296, this);
			}
			if(territoryArrayList.get(i).toString().equals("Brazil")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 270, 307, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 270, 307, this);
			}
			if(territoryArrayList.get(i).toString().equals("Argentina")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 230, 401, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 230, 401, this);
			}
			
			//Africa
			if(territoryArrayList.get(i).toString().equals("North Africa")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 436,208, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 436,208, this);
			}
			if(territoryArrayList.get(i).toString().equals("Egypt")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 515 ,179, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 515,179, this);
			}
			if(territoryArrayList.get(i).toString().equals("East Africa")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 544,219, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 544,219, this);
			}
			if(territoryArrayList.get(i).toString().equals("Congo")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 511,269, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 511,269, this);
			}
			if(territoryArrayList.get(i).toString().equals("South Africa")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 514,345, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 514,345, this);
			}
			if(territoryArrayList.get(i).toString().equals("Madagascar")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 592,334, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 592, 334, this);
			}
			//EUROPE
			if(territoryArrayList.get(i).toString().equals("Iceland")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 403, 35, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 403,35, this);
			}
			if(territoryArrayList.get(i).toString().equals("Great Britain")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 438,73, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 438,73, this);
			}
			if(territoryArrayList.get(i).toString().equals("Western Europe")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 424,120, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 424,120, this);
			}
			if(territoryArrayList.get(i).toString().equals("Scandinavia")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 487,39, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 487,39, this);
			}
			if(territoryArrayList.get(i).toString().equals("Northern Europe")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 481,86, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 481,86, this);
			}
			if(territoryArrayList.get(i).toString().equals("Southern Europe")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 508,116, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 508,116, this);
			}
			if(territoryArrayList.get(i).toString().equals("Ukraine")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 555,67, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 555,67, this);
			}
			//ASIA
			if(territoryArrayList.get(i).toString().equals("Middle East")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 582,168, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 582,168, this);
			}
			if(territoryArrayList.get(i).toString().equals("Afghanistan")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 638,113, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 638,113, this);
			}
			if(territoryArrayList.get(i).toString().equals("India")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 688,177, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 688,177, this);
			}
			if(territoryArrayList.get(i).toString().equals("China")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 735,147, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 735,147, this);
			}
			if(territoryArrayList.get(i).toString().equals("Ural")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 639,57, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 639,57, this);
			}
			if(territoryArrayList.get(i).toString().equals("Siberia")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""),680,32, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 680, 32, this);
			}
			if(territoryArrayList.get(i).toString().equals("Yakutsk")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 779,46, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 779,46, this);
			}
			if(territoryArrayList.get(i).toString().equals("Kamchatka")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 856,40, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 856,40, this);
			}
			if(territoryArrayList.get(i).toString().equals("Irkutsk")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 749,73, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 749,73, this);
			}
			if(territoryArrayList.get(i).toString().equals("Mongolia")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 772,107, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 772,107, this);
			}
			if(territoryArrayList.get(i).toString().equals("Japan")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 873,135, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 873,135, this);
			}
			if(territoryArrayList.get(i).toString().equals("Siam")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""),769,203, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 769,203, this);
			}
			
			//Australia
			if(territoryArrayList.get(i).toString().equals("Indonesia")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 815,260, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 815,260, this);
			}
			if(territoryArrayList.get(i).toString().equals("New Guinea")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 911,280, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 911,280, this);
			}
			if(territoryArrayList.get(i).toString().equals("Western Australia")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 838,371, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 838,371, this);
			}
			if(territoryArrayList.get(i).toString().equals("Eastern Australia")){
				if(territoryArrayList.get(i).getOwningTeam() == null){
					g.drawImage(getFlag(""), 908,365, this);
				}
				else
					g.drawImage(getFlag(territoryArrayList.get(i).getOwningTeam().toString()), 908,365, this);
			}
			
		}
		
		
	}

	private Image getFlag(String color){
		Image flag = null;
		try {
			if(color.equals("GREEN")){
				flag = ImageIO.read(new File("images/greenflag.png"));
			}
			if(color.equals("RED")){
				flag = ImageIO.read(new File("images/redflag.png"));
			}
			if(color.equals("BLUE")){
				flag = ImageIO.read(new File("images/blueflag.png"));
			}
			if(color.equals("YELLOW")){
				flag = ImageIO.read(new File("images/yellowflag.png"));
			}
			if(color.equals("BLACK")){
				flag = ImageIO.read(new File("images/blackflag.png"));
			}
			if(color.equals("WHITE")){
				flag = ImageIO.read(new File("images/whiteflag.png"));
			}
			else if(color.equals("")){
				flag = ImageIO.read(new File("images/nomansflag.png"));
			}
		} 
		catch (IOException e) {
		}
		return flag;
	}
	
	private class moveButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			chatArea.setText(chatArea.getText() + "\nAttempting to move from ");
			addMouseListener(new moveListener());
		}
		
	}
	private void makeMove(Territory orig, Territory dest, int num){
		
		if(game.move(orig, dest, num)){
			chatArea.setText(chatArea.getText() + "Move successful!\n");
		}
		else
			chatArea.setText(chatArea.getText() + "Move failed!\n");
		
	}
	private class mouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getX() < mapImage.getWidth() && e.getY() < mapImage.getHeight()){
			System.out.println(e.getX() + "," + e.getY() + " COLOR: " + mapImage.getRGB(e.getX(), e.getY()));
			//calls helper method designed to check which area is being clicked based on coordinates and color
			
			System.out.println(getLocation(e.getX(), e.getY()));
						
//			System.out.println(territories.get(getLocation(e.getX(), e.getY())).toString());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
	
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub


		}
		
	}
	private class moveListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getX() < mapImage.getWidth() && e.getY() < mapImage.getHeight()){

			if(territories.get(getLocation(e.getX(), e.getY())).toString().equals("")){
				return;
			}
			
			moveTerritories.add(territories.get(getLocation(e.getX(), e.getY())));
			if(moveTerritories.size() == 1){
				chatArea.setText(chatArea.getText() + territories.get(getLocation(e.getX(), e.getY()).toString()) + " to ");
			}

			if(moveTerritories.size() == 2){
				
				removeMouseListener(this);
				chatArea.setText(chatArea.getText() + territories.get(getLocation(e.getX(), e.getY()).toString()) + " with ");
				
				String units = JOptionPane.showInputDialog(null, "How many units would you like to move?");
				int unitInt = 0;
					try {
						unitInt = Integer.parseInt(units);
					} catch (NumberFormatException e1) {
						units = JOptionPane.showInputDialog(null, "Not a valid input, please enter a number.");
						unitInt = Integer.parseInt(units);
					
					}
					chatArea.setText(chatArea.getText() + unitInt + " units.\n");
					makeMove(moveTerritories.pop(), moveTerritories.pop(), unitInt);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
	
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub


		}
		
	}
	private class mouseMove implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
		
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			//checking which territory, for use with tool tip which will later display owner, etc. info
			//NORTH AMERICA
			if(arg0.getX() < mapImage.getWidth() && arg0.getY() < mapImage.getHeight()){
				setToolTipText(getLocation(arg0.getX(), arg0.getY()));
			}
			else{
				setToolTipText(null);
			}
		}
		
	}
	/**
	 * Used in conjunction with the mouselistener, used to determine territory
	 * @param x - x value of current/desired point
	 * @param y - y value of current/desired point
	 * @return the name of the territory as a String
	 * @author Aj Venne
	 */
	private String getLocation(int x, int y){
		
		//NORTH AMERICA
		//Area 1
		if(mapImage.getRGB(x, y) == -8355840 && y < (mapImage.getHeight() / 5.3125)){

			return "Alaska";  //+ na.get("Alaska").getOwner().getTeam();
		}
		//Area 2
		if(mapImage.getRGB(x, y) == -256 && x < (mapImage.getWidth() / 4.066666667)){
			return "Alberta";
		}
		//Area 3
		if(mapImage.getRGB(x, y) == -128 && y > (mapImage.getHeight() / 3.4)){
			return "Central America";
		}
		//Area 4
		if(mapImage.getRGB(x, y) == -8355840 && y > (mapImage.getHeight() / 5.3125)){
			return "Eastern United States";
			
		}
		//Area 5
		if(mapImage.getRGB(x, y) == -256 && x >  (mapImage.getWidth() / 4.066666667)){
			return "Greenland";
		}
		//Area 6
		if(mapImage.getRGB(x, y) == -11513817 && y < (mapImage.getHeight() / 5.3125)){
			return "Northwest Territory";
		}
		//Area 7
		if(mapImage.getRGB(x, y) == -7039927){
			return "Ontario";
		}
		//Area 8
		if(mapImage.getRGB(x, y) == -128 && y < (mapImage.getHeight() / 3.4)){
			return "Quebec";
		}
		//Area 9
		if(mapImage.getRGB(x, y) == -11513817 && y >(mapImage.getHeight() / 5.3125)){
			return "Western United States";
		}
		
		//SOUTH AMERICA
		//Area 1
		if(mapImage.getRGB(x, y) == -65536){
			return "Argentina";
		}
		//Area 2
		if(mapImage.getRGB(x, y) == -8372160){
			return "Brazil";
		}
		//Area 3
		if(mapImage.getRGB(x, y) == -8388608){
			return "Peru";
		}
		//Area 4
		if(mapImage.getRGB(x, y) == -32640){
			return "Venezuela";
		}
		
		//AUSTRALIA
		//Area 1
		if(mapImage.getRGB(x, y) == -12582848){
			return "Eastern Australia";
		}
		//Area 2
		if(mapImage.getRGB(x, y) == -8388353){
			return "Indonesia";
		}
		//Area 3
		if(mapImage.getRGB(x, y) == -65281){
			return "New Guinea";
		}
		//Area 4
		if(mapImage.getRGB(x, y) == -8388544){
			return "Western Australia";
		}
		
		//AFRICA
		//Area 1
		if(mapImage.getRGB(x, y) == -5351680 && x < (mapImage.getWidth() / 1.694444444)){
			return "Congo";
		}
		//Area 2
		if(mapImage.getRGB(x, y) == -32768){
			return "East Africa";
		}
		//Area 3
		if(mapImage.getRGB(x, y) == -8372224 && y < (mapImage.getHeight() / 2.266666667)){
			return "Egypt";
		}
		//Area 4
		if(mapImage.getRGB(x, y) == -5351680 && x > (mapImage.getWidth() / 1.694444444)){
			return "Madagascar";
		}
		//Area 5
		if(mapImage.getRGB(x, y) == -28325){
			return "North Africa";
		}
		//Area 6
		if(mapImage.getRGB(x, y) == -8372224 && y > (mapImage.getHeight() / 2.266666667)){
			return "South Africa";
		}
		
		//EUROPE
		//Area 1
		if(mapImage.getRGB(x, y) == -16760704 && y < (mapImage.getHeight() / 4.857142857)){
			return "Great Britain";
		}
		//Area 2
		if(mapImage.getRGB(x, y) == -16776961 && y < (mapImage.getHeight() / 7.555555556)){
			return "Iceland";
		}
		//Area 3
		if(mapImage.getRGB(x, y) == -16776961 && y > (mapImage.getHeight() / 7.555555556)){
			return "Northern Europe";
		}
		//Area 4
		if(mapImage.getRGB(x, y) == -16744193 && y < (mapImage.getHeight() / 5.666666667)){
			return "Scandinavia";
		}
		//Area 5
		if(mapImage.getRGB(x, y) == -16760704 && y > (mapImage.getHeight() / 4.857142857)){
			return "Southern Europe";
		}
		//Area 6
		if(mapImage.getRGB(x, y) == -16777088){
			return "Ukraine";
		}
		//Area 7
		if(mapImage.getRGB(x, y) == -16744193 && y > (mapImage.getHeight() / 5.666666667)){
			return "Western Europe";
		}
		
		//ASIA
		//Area 1
		if(mapImage.getRGB(x, y) == -8323200 && x < (mapImage.getWidth() / 1.355555556)){
			return "Afghanistan";
		}
		//Area 2
		if(mapImage.getRGB(x, y) == -16744384 && y > (mapImage.getHeight() / 4.4)){
			return "China";
		}
		//Area 3
		if(mapImage.getRGB(x, y) == -16744320 && y > (mapImage.getHeight() / 4.689655172)){
			return "India";
		}
		//Area 4
		if(mapImage.getRGB(x, y) == -8323328 && x < (mapImage.getWidth() / 1.167464115)){
			return "Irkutsk";
		}
		//Area 5
		if(mapImage.getRGB(x, y) == -8323328 && x > (mapImage.getWidth() / 1.167464115)){
			return "Japan";
		}
		//Area 6
		if(mapImage.getRGB(x, y) == -16744384 && y < (mapImage.getHeight() / 4.657534247)){
			return "Kamchatka";
		}
		//Area 7
		if(mapImage.getRGB(x, y) == -16744448 && y > (mapImage.getHeight() / 4)){
			return "Middle East";
		}
		//Area 8
		if(mapImage.getRGB(x, y) == -16760832 && x > (mapImage.getWidth() / 1.384790011)){
			return "Mongolia";
		}
		//Area 9
		if(mapImage.getRGB(x, y) == -8323200 && x > (mapImage.getWidth() / 1.355555556)){
			return "Siam";
		}
		//Area 10
		if(mapImage.getRGB(x, y) == -16744448 && y < (mapImage.getHeight() / 4)){
			return "Siberia";
		}
		//Area 11
		if(mapImage.getRGB(x, y) == -16760832 && x < (mapImage.getWidth() / 1.384790011)){
			return "Ural";
		}
		//Area 12
		if(mapImage.getRGB(x, y) == -16744320 && y < (mapImage.getHeight() / 4.689655172)){
			return "Yakutsk";
		}
		
		return "";
		
	}
	
	
	/**
	 * 
	 * @author Aj Venne
	 */
	private class retListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
                
                        m.client.writeObject(typeArea.getText());
                        typeArea.setText("");
                        typeArea.grabFocus();
                
        }
        
	}
	@Override
	public void update(Observable arg0, Object arg1) {
        if (arg1 instanceof String){
                chatArea.append((String) arg1);
                chatArea.setCaretPosition(chatArea.getDocument().getLength());
        }
                
        
}

}
