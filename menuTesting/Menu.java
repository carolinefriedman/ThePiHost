package MenuPackage;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * Helpful source : https://www.youtube.com/watch?v=FdQX8sUNO-s
 */
/**
 * This class displays the Main menu picture.
 * The mouse listener gives the static buttons functionality for opening games and closing the program.
 */
public class Menu extends JFrame{
	private ImageIcon menuImage;
	private JLabel menuLabel;
	private Dimension menuSize = new Dimension(1000, 714);

	/**
	 * Displays image on JFrame and adds a label to the image
	 */
	Menu() {
		setLayout(new FlowLayout());
		menuImage = new ImageIcon(getClass().getResource("PiHostMenuCopy.jpg"));
		menuLabel = new JLabel(menuImage);
		add(menuLabel);
		addMouseListener(new mouseInputTest());
	}
	/**
	 * Sets JFrame attributes.
	 */
	public static void main(String args[]){
		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
		menu.setMinimumSize(menu.menuSize);
		menu.setMaximumSize(menu.menuSize);
		menu.setPreferredSize(menu.menuSize);
	  menu.setLocationRelativeTo(null);
	  menu.setVisible(true);
		menu.pack();
		menu.setTitle("The Pi Host");
	}
}
