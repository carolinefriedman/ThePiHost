package AlienTesting;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class AlienBomb{
  public int x;
  public int y;
  public int bombSpeed = 1;
  public int width = 3;
  public int height = 6;
  public boolean inPlay;
  public boolean isShooting = false;
  public boolean moveLeft = false;
  public boolean moveRight = false;
  public int moveSpeed = 1;
  Rectangle boundingBox;
  public boolean collision = false;

  public AlienBomb(Alien alien){
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    this.x = alien.x + (alien.width / 2);
    this.y = alien.y;
    boolean inPlay = true;
  }

//  public void tick(spaceDefender game, Shooter shooter){
  public void tick(spaceDefender game, Alien alien){

  //    this.x = shooter.x;

//    if (isShooting && (inPlay == true)){

    //if (game.cnt != 0 && game.cnt % 20 == 0){
    //  this.isShooting = true;
    //}
    if(!isShooting){
      y = alien.y;
    }
    boundingBox.setBounds(this.x, this.y, this.width, this.height);

    if (y > game.player.y){
      x = alien.x + (alien.width / 2);
      y = alien.y;
      isShooting = false;
      collision = false;
    }

    if(isShooting){
      y += bombSpeed;
    }

    else{
      x = alien.x + (alien.width / 2);
    }

    collide(game);

  }

  private void collide(spaceDefender game){
    if(boundingBox.intersects(game.player.boundingBox)){
      collision = true;
    }

    if (collision == true){
      game.player.health -= 1;
      collision = false;
    }
  }

  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
  }
}