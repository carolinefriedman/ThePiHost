package MenuPackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * This class captures the location of the mouse upon a mouse "click".
 * Depending on the box that is selected (boxed correspond with the Menu image)
 * a different game can be opened or the menu program can be closed.
 */
public class mouseInputTest implements MouseListener{
  public void mouseClicked(MouseEvent e){
  }

  public void mousePressed(MouseEvent e){
    int mouseX = e.getX();
    int mouseY = e.getY();


    if((mouseX >= 319) && (mouseX <= 693)){
      if ((mouseY >= 235) && (mouseY <= 304)){
        try{
    			String cmd = "open Emulator/OpenEmu.app";
    			Process p = Runtime.getRuntime().exec(cmd);
    		}
    		catch(IOException ex){
    			ex.printStackTrace();
    		}
      }
      else if ((mouseY >= 363) && (mouseY <= 431)){
            try{
        			String cmd = "java -jar SpaceDefenders/SpaceDefendersGame.jar";
        			Process p = Runtime.getRuntime().exec(cmd);
        		}
        		catch(IOException ex){
        			ex.printStackTrace();
        		}
      }
      else if ((mouseY >= 491) && (mouseY <= 558)){
            try{
        			String cmd = "java -jar Tennis/TennisGame.jar";
        			Process p = Runtime.getRuntime().exec(cmd);
        		}
        		catch(IOException ex){
        			ex.printStackTrace();
        		}
      }
      else if ((mouseY >= 613) && (mouseY <= 680)){
            System.exit(0);
       }
     }
   }

   public void mouseReleased(MouseEvent e){
   }

   public void mouseEntered(MouseEvent e){
   }

   public void mouseExited(MouseEvent e){
   }
}
