import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class AlienBomb{
  int x;
  int y;
  int bombSpeed = 1;
  int width = 3;
  int height = 6;
  boolean inPlay;
  boolean isShooting = false;
  boolean moveLeft = false;
  boolean moveRight = false;
  int moveSpeed = 1;
  Rectangle boundingBox;
  boolean collision = false;

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

    if (game.cnt != 0 && game.cnt % 5 == 0){
      this.isShooting = true;
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
