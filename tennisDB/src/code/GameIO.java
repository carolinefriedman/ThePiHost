package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Allows for user control over the player paddle */
public class GameIO implements KeyListener{

  public GameIO(Tennis tennis){
    tennis.addKeyListener(this);
  }

  /**
   *  Set moveUp to true if player pressed up arrow.
   *  Set moveDown to true if player pressed down arrow.
   *  Allow user to pass the game entrance screen by pressing spacebar.
   */
  public void keyPressed(KeyEvent event){
    int keyCode = event.getKeyCode();

    if(keyCode == KeyEvent.VK_UP){
        Tennis.player.moveUp = true;
    }

    if(keyCode == KeyEvent.VK_DOWN){
        Tennis.player.moveDown = true;
    }

    if(keyCode == KeyEvent.VK_SPACE){
        Tennis.entered = true;
    }

  }

  /**
   * Once user releases a key, respond accordingly to keep the player paddle from constantly moving.
   */
  public void keyReleased(KeyEvent event){
    int keyCode = event.getKeyCode();

    if(keyCode == KeyEvent.VK_UP){
        Tennis.player.moveUp = false;
    }

    if(keyCode == KeyEvent.VK_DOWN){
        Tennis.player.moveDown = false;
    }

  }

  public void keyTyped(KeyEvent event){
  }

}
