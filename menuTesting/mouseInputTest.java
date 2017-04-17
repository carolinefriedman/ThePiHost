import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class mouseInputTest implements MouseListener{
  public void mouseClicked(MouseEvent e){
  }

  public void mousePressed(MouseEvent e){
    int mouseX = e.getX();
    int mouseY = e.getY();

    //buttons
    if((mouseX >= 319) && (mouseX <= 693)){
      if ((mouseY >= 235) && (mouseY <= 304)){
        //try{
          //open 'program_name' runs program_name on mac system
    			//String cmd = "open pongGame.class";
          //try to execute cmd
    			//Process p = Runtime.getRuntime().exec(cmd);
          System.out.print("emulator button clicked\n");
    		//}
    		//catch(IOException ex){
    		//	ex.printStackTrace();
    		//}
      }
      else if ((mouseY >= 363) && (mouseY <= 431)){
            //pressed play button
            //try{
              //open 'program_name' runs program_name on mac system
        			//String cmd = "open pongGame.class";
              //try to execute cmd
        			//Process p = Runtime.getRuntime().exec(cmd);
              System.out.print("space invaders button clicked\n");
        		//}
        		//catch(IOException ex){
        		//	ex.printStackTrace();
        		//}
      }
      else if ((mouseY >= 491) && (mouseY <= 558)){
            //pressed play button
            //try{
              //open 'program_name' runs program_name on mac system
        			//String cmd = "open pongGame.class";
              //try to execute cmd
        			//Process p = Runtime.getRuntime().exec(cmd);
              System.out.print("pong button clicked\n");
        		//}
        		//catch(IOException ex){
        		//	ex.printStackTrace();
        		//}
      }
      else if ((mouseY >= 613) && (mouseY <= 680)){
            //pressed play button
            //try{
              //open 'program_name' runs program_name on mac system
        			//String cmd = "open pongGame.class";
              //try to execute cmd
        			//Process p = Runtime.getRuntime().exec(cmd);
              System.out.print("exit button clicked\n");
        		//}
        		//catch(IOException ex){
        		//	ex.printStackTrace();
        		//}
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
