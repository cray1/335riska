package gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;


import javax.swing.JLabel;

import baseModel.Continent;
import baseModel.Game;
import baseModel.Map;



public class MapView extends MasterViewPanel{
	BufferedImage buffImage;
	Game newGame;
	Map gameMap;
	HashMap<String, Continent> continents;
	public MapView(MasterView m) {
		super(m);
		
		setUpGUI();
	
		
		
	}
	private void setUpGUI(){
		this.setVisible(true);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Image mapImage = new ImageIcon("images/map.png").getImage();
		buffImage = new BufferedImage(mapImage.getWidth(null),mapImage.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		
//		Image whiteFlag = new ImageIcon("images/whiteFlag.png").getImage();
		Graphics g = buffImage.getGraphics();
	    g.drawImage(mapImage, 0, 0, null);

	    this.add(new JLabel(new ImageIcon(buffImage)));
		this.addMouseListener(new mouse());
		//Adds motion listener for tooltip text
		this.addMouseMotionListener(new mouseMove());
		
		//NOTE
		//Currently a bug that needs to be worked out: Buffered Image and displayed image are not in exact spot when this is added, hard to track since
		//the buffered is invisible...
		
		//adding a user bar for cards, buttons, etc
//		JPanel userBar = new JPanel();
//		this.add(userBar);
//		userBar.setLocation(0,680);
//		userBar.setVisible(true);
//		//adding buttons
//		JButton cards = new JButton("Cards");
//		JButton quit = new JButton("Quit Game");
//		userBar.add(cards);
//		userBar.add(quit);
		
	}
	private class mouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getX() < 1227 && e.getY() < 628){
			System.out.println(e.getX() + "," + e.getY() + " COLOR: " + buffImage.getRGB(e.getX(), e.getY()));
			//calls helper method designed to check which area is being clicked based on coordinates and color
			System.out.println(getLocation(e.getX(), e.getY()));
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
			if(arg0.getX() < 1227 && arg0.getY() < 628){
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
		if(buffImage.getRGB(x, y) == -8355840 && y < 128){
			return "Alaska";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -256 && x < 300){
			return "North America Area 2";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -128 && y > 200){
			return "North America Area 3";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -8355840 && y > 128){
			return "North America Area 4";
			
		}
		//Area 5
		if(buffImage.getRGB(x, y) == -256 && x > 300){
//			continents.get("nAmerica").getChildren();
			return "Greenland";
		}
		//Area 6
		if(buffImage.getRGB(x, y) == -11513817 && y < 128){
			return "North America Area 6";
		}
		//Area 7
		if(buffImage.getRGB(x, y) == -7039927){
			return "North America Area 7";
		}
		//Area 8
		if(buffImage.getRGB(x, y) == -128 && y < 200){
			return "North America Area 8";
		}
		//Area 9
		if(buffImage.getRGB(x, y) == -11513817 && y > 128){
			return "North America Area 9";
		}
		
		//SOUTH AMERICA
		//Area 1
		if(buffImage.getRGB(x, y) == -65536){
			return "South America Area 1";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -8372160){
			return "South America Area 2";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -8388608){
			return "South America Area 3";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -32640){
			return "South America Area 4";
		}
		
		//AUSTRALIA
		//Area 1
		if(buffImage.getRGB(x, y) == -12582848){
			return "Australia Area 1";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -8388353){
			return "Australia Area 2";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -65281){
			return "Australia Area 3";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -8388544){
			return "Australia Area 4";
		}
		
		//AFRICA
		//Area 1
		if(buffImage.getRGB(x, y) == -5351680 && x < 720){
			return "Africa Area 1";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -32768){
			return "Africa Area 2";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -8372224 && y < 300){
			return "Africa Area 3";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -5351680 && x > 720){
			return "Africa Area 4";
		}
		//Area 5
		if(buffImage.getRGB(x, y) == -28325){
			return "Africa Area 5";
		}
		//Area 6
		if(buffImage.getRGB(x, y) == -8372224 && y > 300){
			return "Africa Area 6";
		}
		//EUROPE
		//Area 1
		if(buffImage.getRGB(x, y) == -16760704 && y < 140){
			return "Europe Area 1";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -16776961 && y < 90){
			return "Europe Area 2";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -16776961 && y > 90){
			return "Europe Area 3";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -16744193 && y < 120){
			return "Europe Area 4";
		}
		//Area 5
		if(buffImage.getRGB(x, y) == -16760704 && y > 140){
			return "Europe Area 5";
		}
		//Area 6
		if(buffImage.getRGB(x, y) == -16777088){
			return "Europe Area 6";
		}
		//Area 7
		if(buffImage.getRGB(x, y) == -16744193 && y > 120){
			return "Europe Area 7";
		}
		
		//ASIA
		//Area 1
		if(buffImage.getRGB(x, y) == -8323200 && x < 900){
			return "Asia Area 1";
		}
		//Area 2
		if(buffImage.getRGB(x, y) == -16744384 && y > 145){
			return "Asia Area 2";
		}
		//Area 3
		if(buffImage.getRGB(x, y) == -16744320 && y > 145){
			return "Asia Area 3";
		}
		//Area 4
		if(buffImage.getRGB(x, y) == -8323328 && x < 1045){
			return "Asia Area 4";
		}
		//Area 5
		if(buffImage.getRGB(x, y) == -8323328 && x > 1045){
			return "Asia Area 5";
		}
		//Area 6
		if(buffImage.getRGB(x, y) == -16744384 && y < 146){
			return "Asia Area 6";
		}
		//Area 7
		if(buffImage.getRGB(x, y) == -16744448 && y > 170){
			return "Asia Area 7";
		}
		//Area 8
		if(buffImage.getRGB(x, y) == -16760832 && x > 881){
			return "Asia Area 8";
		}
		//Area 9
		if(buffImage.getRGB(x, y) == -8323200 && x > 900){
			return "Asia Area 9";
		}
		//Area 10
		if(buffImage.getRGB(x, y) == -16744448 && y < 170){
			return "Asia Area 10";
		}
		//Area 11
		if(buffImage.getRGB(x, y) == -16760832 && x < 881){
			return "Asia Area 11";
		}
		//Area 12
		if(buffImage.getRGB(x, y) == -16744320 && y < 145){
			return "Asia Area 12";
		}
		
		return null;
		
	}
	

}
