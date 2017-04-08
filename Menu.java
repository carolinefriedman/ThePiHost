import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * Sources : https://www.youtube.com/watch?v=FdQX8sUNO-s
 */

public class Menu extends JFrame{
	private ImageIcon menuImage;
	private JLabel menuLabel;
	private Dimension menuSize = new Dimension(1000, 714);

	Menu() {
		setLayout(new FlowLayout());
		menuImage = new ImageIcon(getClass().getResource("PiHostMenuCopy.jpg"));
		menuLabel = new JLabel(menuImage);
		add(menuLabel);
	}
	
	public static void main(String args[]){
		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
		//menu.setMinimumSize(new Dimension(1000, 714));
		//menu.setMaximumSize(new Dimension(1000, 714));
		//menu.setPreferredSize(new Dimension(1000, 714));
		menu.setMinimumSize(menu.menuSize);
		menu.setMaximumSize(menu.menuSize);
		menu.setPreferredSize(menu.menuSize);
	    menu.setLocationRelativeTo(null);
	    menu.setVisible(true);
		menu.pack();
		menu.setTitle("The Pi Host");
	}
}

