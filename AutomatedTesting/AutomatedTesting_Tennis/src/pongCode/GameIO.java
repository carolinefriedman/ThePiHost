package pongCode;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameIO implements KeyListener{

  public GameIO(Tennis tennis){
    tennis.addKeyListener(this);
  }

  public void keyPressed(KeyEvent event){
    int keyCode = event.getKeyCode();

    if(keyCode == KeyEvent.VK_W){
        Tennis.player.moveUp = true;
    }

    if(keyCode == KeyEvent.VK_S){
        Tennis.player.moveDown = true;
    }

  }

  public void keyReleased(KeyEvent event){
    int keyCode = event.getKeyCode();

    if(keyCode == KeyEvent.VK_W){
        Tennis.player.moveUp = false;
    }

    if(keyCode == KeyEvent.VK_S){
        Tennis.player.moveDown = false;
    }
  }

  public void keyTyped(KeyEvent event){
  }

}
