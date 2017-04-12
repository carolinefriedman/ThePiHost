import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class mouseInputTest implements MouseListener{
  public void mouseClicked(MouseEvent e){
  }

  public void mousePressed(MouseEvent e){
    int mouseX = e.getX();
    int mouseY = e.getY();
    //play button
    if((mouseY >= 85) && (mouseY <= 135)){
        //pressed play button
        try{
          //open 'program_name' runs program_name on mac system
    			String cmd = "open pongGame.class";
          //try to execute cmd
    			Process p = Runtime.getRuntime().exec(cmd);
    		}
    		catch(IOException ex){
    			ex.printStackTrace();
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

