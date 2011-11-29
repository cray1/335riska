package gui;
//
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author AJ Venne Created on 8:09:32 PM Nov 26, 2011
 */
public class MapView extends MasterViewPanel {
	/**
	 * @author AJ Venne Created on 8:10:07 PM Nov 26, 2011
	 * 
	 */
	private static final long serialVersionUID = 6109177940814871611L;
	BufferedImage buffImage;

	/**
	 * @param m
	 * @author AJ Venne Created on 8:09:28 PM Nov 26, 2011
	 * 
	 */
	public MapView(MasterView m) {
		super(m);
		this.setVisible(true);
		// this.setLayout(new FlowLayout());
		Image mapImage = new ImageIcon("images/map.png").getImage();
		buffImage = new BufferedImage(mapImage.getWidth(null),
				mapImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics g = buffImage.getGraphics();
		g.drawImage(mapImage, 0, 0, null);
		this.add(new JLabel(new ImageIcon(buffImage)));
		this.addMouseListener(new mouse());
	}

	/**
	 * @author AJ Venne Created on 8:09:32 PM Nov 26, 2011
	 */
	private class mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// System.out.println(e.getX() + "," + e.getY() + " COLOR: " +
			// buffImage.getRGB(e.getX(), e.getY()));

			// NORTH AMERICA
			// Area 1
			if ((buffImage.getRGB(e.getX(), e.getY()) == -8355840)
					&& (e.getY() < 128))
				System.out.println("North America Area 1");
			// Area 2
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -256)
					&& (e.getX() < 300))
				System.out.println("North America Area 2");
			// Area 3
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -128)
					&& (e.getY() > 200))
				System.out.println("North America Area 3");
			// Area 4
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -8355840)
					&& (e.getY() > 128))
				System.out.println("North America Area 4");
			// Area 5
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -256)
					&& (e.getX() > 300))
				System.out.println("North America Area 5");
			// Area 6
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -11513817)
					&& (e.getY() < 128))
				System.out.println("North America Area 6");
			// Area 7
			else if (buffImage.getRGB(e.getX(), e.getY()) == -7039927)
				System.out.println("North America Area 7");
			// Area 8
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -128)
					&& (e.getY() < 200))
				System.out.println("North America Area 8");
			// Area 9
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -11513817)
					&& (e.getY() > 128))
				System.out.println("North America Area 9");

			// SOUTH AMERICA
			// Area 1
			else if (buffImage.getRGB(e.getX(), e.getY()) == -65536)
				System.out.println("South America Area 1");
			// Area 2
			else if (buffImage.getRGB(e.getX(), e.getY()) == -8372160)
				System.out.println("South America Area 2");
			// Area 3
			else if (buffImage.getRGB(e.getX(), e.getY()) == -8388608)
				System.out.println("South America Area 3");
			// Area 4
			else if (buffImage.getRGB(e.getX(), e.getY()) == -32640)
				System.out.println("South America Area 4");

			// AUSTRALIA
			// Area 1
			else if (buffImage.getRGB(e.getX(), e.getY()) == -12582848)
				System.out.println("Australia Area 1");
			// Area 2
			else if (buffImage.getRGB(e.getX(), e.getY()) == -8388353)
				System.out.println("Australia Area 2");
			// Area 3
			else if (buffImage.getRGB(e.getX(), e.getY()) == -65281)
				System.out.println("Australia Area 3");
			// Area 4
			else if (buffImage.getRGB(e.getX(), e.getY()) == -8388544)
				System.out.println("Australia Area 4");

			// AFRICA
			// Area 1
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -5351680)
					&& (e.getX() < 720))
				System.out.println("Africa Area 1");
			// Area 2
			else if (buffImage.getRGB(e.getX(), e.getY()) == -32768)
				System.out.println("Africa Area 2");
			// Area 3
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -8372224)
					&& (e.getY() < 300))
				System.out.println("Africa Area 3");
			// Area 4
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -5351680)
					&& (e.getX() > 720))
				System.out.println("Africa Area 4");
			// Area 5
			else if (buffImage.getRGB(e.getX(), e.getY()) == -28325)
				System.out.println("Africa Area 5");
			// Area 6
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -8372224)
					&& (e.getY() > 300))
				System.out.println("Africa Area 6");
			// EUROPE
			// Area 1
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16760704)
					&& (e.getY() < 140))
				System.out.println("Europe Area 1");
			// Area 2
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16776961)
					&& (e.getY() < 90))
				System.out.println("Europe Area 2");
			// Area 3
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16776961)
					&& (e.getY() > 90))
				System.out.println("Europe Area 3");
			// Area 4
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16744193)
					&& (e.getY() < 120))
				System.out.println("Europe Area 4");
			// Area 5
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16760704)
					&& (e.getY() > 140))
				System.out.println("Europe Area 5");
			// Area 6
			else if (buffImage.getRGB(e.getX(), e.getY()) == -16777088)
				System.out.println("Europe Area 6");
			// Area 7
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16744193)
					&& (e.getY() > 120))
				System.out.println("Europe Area 7");

			// ASIA
			// Area 1
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -8323200)
					&& (e.getX() < 900))
				System.out.println("Asia Area 1");
			// Area 2
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16744384)
					&& (e.getY() > 145))
				System.out.println("Asia Area 2");
			// Area 3
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16744320)
					&& (e.getY() > 145))
				System.out.println("Asia Area 3");
			// Area 4
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -8323328)
					&& (e.getX() < 1045))
				System.out.println("Asia Area 4");
			// Area 5
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -8323328)
					&& (e.getX() > 1045))
				System.out.println("Asia Area 5");
			// Area 6
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16744384)
					&& (e.getY() < 146))
				System.out.println("Asia Area 6");
			// Area 7
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16744448)
					&& (e.getY() > 170))
				System.out.println("Asia Area 7");
			// Area 8
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16760832)
					&& (e.getX() > 881))
				System.out.println("Asia Area 8");
			// Area 9
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -8323200)
					&& (e.getX() > 900))
				System.out.println("Asia Area 9");
			// Area 10
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16744448)
					&& (e.getY() < 170))
				System.out.println("Asia Area 10");
			// Area 11
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16760832)
					&& (e.getX() < 881))
				System.out.println("Asia Area 11");
			// Area 12
			else if ((buffImage.getRGB(e.getX(), e.getY()) == -16744320)
					&& (e.getY() < 145))
				System.out.println("Asia Area 12");

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

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

}
