import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Bullets{
  int x;
  int y;
  int bulletSpeed = 3;
  int width = 3;
  int height = 3;
  int score = 0;
  boolean inPlay;
  boolean isShooting = false;
  boolean moveLeft = false;
  boolean moveRight = false;
  int moveSpeed;
  Rectangle boundingBox;
  boolean collision = false;

  public Bullets(Shooter shooter){
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    this.x = shooter.x + (shooter.width / 2);
    this.y = shooter.y;
    this.moveSpeed = shooter.moveSpeed;
    boolean inPlay = true;
  }

  public void tick(spaceDefender game){
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    if (y < 0 || collision == true){
      x = game.player.x + (game.player.width / 2);
      y = game.player.y;
      isShooting = false;
      collision = false;
    }

    if(isShooting){
      y -= bulletSpeed;
    }

    if (moveLeft && isShooting == false && x > 0){
      x -= moveSpeed;
    }

    if (moveRight && isShooting == false && x < game.WIDTH - this.width){
      x += moveSpeed;
    }

    collide(game);
  }

  private void collide(spaceDefender game){
/*
    for (int i = 0; i < 10; i ++){
      if(boundingBox.intersects(game.alienArray[i].boundingBox)){
        game.alienArray[i].isDead = true;
        game.alienArray[i].x = -50;
      }
    }
*/
    for (int i =0; i < 3; i ++){
      for (int j = 0; j < 10; j ++){
        if(boundingBox.intersects(game.alienMatrix[i][j].boundingBox)){
          game.alienMatrix[i][j].isDead = true;
          game.alienMatrix[i][j].x = -50;
          collision = true;
          score ++;
        }
      }
    }
  }

  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
  }
}
