package gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

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
import baseModel.Team;
import baseModel.Territory;



public class MapView extends MasterViewPanel implements Observer{
	BufferedImage buffImage;
	Game newGame;
	Map gameMap;
	JPanel mapBox = new JPanel();
	HashMap<String, Continent> continents;
	JTextArea chatArea ;
	JTextField typeArea ;
	HashMap<String, Territory> territories;
	Image mapImage;
	public MapView(MasterView m) {
		super(m);
		setUpGUI();
		setUpGame();
		
	}
	private void setUpGUI(){
		this.setVisible(true);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		mapImage = new ImageIcon("images/map2.png").getImage();
		buffImage = new BufferedImage(mapImage.getWidth(null),mapImage.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		
		
		mapBox.setPreferredSize(new Dimension(mapImage.getWidth(null),mapImage.getHeight(null)));
		
		
		Graphics g = buffImage.getGraphics();
	    g.drawImage(mapImage, 0, 0, mapBox);
//	    
//	   this.add(new JLabel(new ImageIcon(buffImage)));
	   mapBox.add(new JLabel(new ImageIcon(buffImage)));
	   mapBox.add(new JLabel(new ImageIcon("images/map2.png")));
	   
	   this.add(mapBox);
	   
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
		JLabel turn = new JLabel("\tIt is "+ "BLANK" +"\'s turn");
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
		newGame = new Game();
		gameMap = newGame.getMap();
		continents = gameMap.getMap();
		territories = gameMap.getMapAsStringTerritoryHashMap();
		
	}
	
	public void paintComponent(Graphics g){
			
		//TO DO: Working on making these flags appear over/on every owned territory on repaint, gonna make them small to fit new map better.
		territories.get("Alaska").setOwningTeam(Team.GREEN);
		if(territories.get("Alaska").getOwningTeam() != null){
//			
//			char[] name = territories.get("Alaska").getOwner().toString().toCharArray();
//			g.drawChars(name, 0, 5, 317, 178);
			mapBox.add(new JLabel(territories.get("Alaska").getOwningTeam().toString()));
		}
			
		
		

	}
	private class moveButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			System.out.print("To be implemented...");
//			JOptionPane.showMessageDialog(null, "Choose your move!");
			
			
		}
		
	}
	private class mouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getX() < buffImage.getWidth() && e.getY() < buffImage.getHeight()){
			System.out.println(e.getX() + "," + e.getY() + " COLOR: " + buffImage.getRGB(e.getX(), e.getY()));
			//calls helper method designed to check which area is being clicked based on coordinates and color
			
//			System.out.println(getLocation(e.getX(), e.getY()));
						
			System.out.println(territories.get(getLocation(e.getX(), e.getY())).toString());
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
			if(arg0.getX() < buffImage.getWidth() && arg0.getY() < buffImage.getHeight()){
				setToolTipText(getLocation(arg0.getX(), arg0.getY()));
			}
			else{
				setToolTipText(null);
			}
		}
		
	}
	//Helper method mentioned above for determining location
	private String getLocation(int x, int y){
		
		//NORTH AMERICA
		//Area 1
		if(buffImage.getRGB(x, y) == -8355840 && y < (buffImage.getHeight() / 5.3125)){

			return "Alaska";  //+ na.get("Alaska").getOwner().getTeam();
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -256 && x < (buffImage.getWidth() / 4.066666667)){
			return "Alberta";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -128 && y > (buffImage.getHeight() / 3.4)){
			return "Central America";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -8355840 && y > (buffImage.getHeight() / 5.3125)){
			return "Eastern United States";
			
		}
		//Area 5
		if(buffImage.getRGB(x, y) == -256 && x >  (buffImage.getWidth() / 4.066666667)){
			return "Greenland";
		}
		//Area 6
		if(buffImage.getRGB(x, y) == -11513817 && y < (buffImage.getHeight() / 5.3125)){
			return "Northwest Territory";
		}
		//Area 7
		if(buffImage.getRGB(x, y) == -7039927){
			return "Ontario";
		}
		//Area 8
		if(buffImage.getRGB(x, y) == -128 && y < (buffImage.getHeight() / 3.4)){
			return "Quebec";
		}
		//Area 9
		if(buffImage.getRGB(x, y) == -11513817 && y >(buffImage.getHeight() / 5.3125)){
			return "Western United States";
		}
		
		//SOUTH AMERICA
		//Area 1
		if(buffImage.getRGB(x, y) == -65536){
			return "Argentina";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -8372160){
			return "Brazil";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -8388608){
			return "Peru";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -32640){
			return "Venezuela";
		}
		
		//AUSTRALIA
		//Area 1
		if(buffImage.getRGB(x, y) == -12582848){
			return "Eastern Australia";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -8388353){
			return "Indonesia";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -65281){
			return "New Guinea";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -8388544){
			return "Western Australia";
		}
		
		//AFRICA
		//Area 1
		if(buffImage.getRGB(x, y) == -5351680 && x < (buffImage.getWidth() / 1.694444444)){
			return "Congo";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -32768){
			return "East Africa";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -8372224 && y < (buffImage.getHeight() / 2.266666667)){
			return "Egypt";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -5351680 && x > (buffImage.getWidth() / 1.694444444)){
			return "Madagascar";
		}
		//Area 5
		if(buffImage.getRGB(x, y) == -28325){
			return "North Africa";
		}
		//Area 6
		if(buffImage.getRGB(x, y) == -8372224 && y > (buffImage.getHeight() / 2.266666667)){
			return "South Africa";
		}
		
		//EUROPE
		//Area 1
		if(buffImage.getRGB(x, y) == -16760704 && y < (buffImage.getHeight() / 4.857142857)){
			return "Great Britain";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -16776961 && y < (buffImage.getHeight() / 7.555555556)){
			return "Iceland";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -16776961 && y > (buffImage.getHeight() / 7.555555556)){
			return "Northern Europe";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -16744193 && y < (buffImage.getHeight() / 5.666666667)){
			return "Scandinavia";
		}
		//Area 5
		if(buffImage.getRGB(x, y) == -16760704 && y > (buffImage.getHeight() / 4.857142857)){
			return "Southern Europe";
		}
		//Area 6
		if(buffImage.getRGB(x, y) == -16777088){
			return "Ukraine";
		}
		//Area 7
		if(buffImage.getRGB(x, y) == -16744193 && y > (buffImage.getHeight() / 5.666666667)){
			return "Western Europe";
		}
		
		//ASIA
		//Area 1
		if(buffImage.getRGB(x, y) == -8323200 && x < (buffImage.getWidth() / 1.355555556)){
			return "Afghanistan";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -16744384 && y > (buffImage.getHeight() / 4.4)){
			return "China";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -16744320 && y > (buffImage.getHeight() / 4.689655172)){
			return "India";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -8323328 && x < (buffImage.getWidth() / 1.167464115)){
			return "Irkutsk";
		}
		//Area 5
		if(buffImage.getRGB(x, y) == -8323328 && x > (buffImage.getWidth() / 1.167464115)){
			return "Japan";
		}
		//Area 6
		if(buffImage.getRGB(x, y) == -16744384 && y < (buffImage.getHeight() / 4.657534247)){
			return "Kamchatka";
		}
		//Area 7
		if(buffImage.getRGB(x, y) == -16744448 && y > (buffImage.getHeight() / 4)){
			return "Middle East";
		}
		//Area 8
		if(buffImage.getRGB(x, y) == -16760832 && x > (buffImage.getWidth() / 1.384790011)){
			return "Mongolia";
		}
		//Area 9
		if(buffImage.getRGB(x, y) == -8323200 && x > (buffImage.getWidth() / 1.355555556)){
			return "Siam";
		}
		//Area 10
		if(buffImage.getRGB(x, y) == -16744448 && y < (buffImage.getHeight() / 4)){
			return "Siberia";
		}
		//Area 11
		if(buffImage.getRGB(x, y) == -16760832 && x < (buffImage.getWidth() / 1.384790011)){
			return "Ural";
		}
		//Area 12
		if(buffImage.getRGB(x, y) == -16744320 && y < (buffImage.getHeight() / 4.689655172)){
			return "Yakutsk";
		}
		
		return "";
		
	}
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
