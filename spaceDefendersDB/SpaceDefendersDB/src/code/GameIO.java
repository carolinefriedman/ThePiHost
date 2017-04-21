package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameIO implements KeyListener{

  public GameIO(spaceDefender game){
    game.addKeyListener(this);
  }

  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT){
      spaceDefender.player.moveLeft = true;
      spaceDefender.player.bullet.moveLeft = true;
    }

    if (key == KeyEvent.VK_RIGHT){
      spaceDefender.player.moveRight = true;
      spaceDefender.player.bullet.moveRight = true;
    }

    if (key == KeyEvent.VK_SPACE){
      spaceDefender.player.bullet.isShooting = true;
      spaceDefender.player.bullet.numShots += 1;
      spaceDefender.entered = true;
    }
  }

  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT){
      spaceDefender.player.moveLeft = false;
      spaceDefender.player.bullet.moveLeft = false;
    }

    if (key == KeyEvent.VK_RIGHT){
      spaceDefender.player.moveRight = false;
      spaceDefender.player.bullet.moveRight = false;
    }
  }

  public void keyTyped(KeyEvent e){
  }
}
