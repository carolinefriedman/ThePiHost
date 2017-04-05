package AlienTesting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameIO implements KeyListener{

  public GameIO(spaceDefender game){
    game.addKeyListener(this);
  }

  public void key_Pressed(int event){
    int key = event;

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
      spaceDefender.entered = true;
    }
/*
    if (key == KeyEvent.VK_B){
      spaceDefender.alienMatrix[1][1].bomb.isShooting = true;
    }
*/
  }

  public void key_Released(int x){
    int key = x;

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

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}