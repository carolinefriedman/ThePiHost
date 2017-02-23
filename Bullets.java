import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Bullets{
  int x;
  int y;
  int bulletSpeed = 5;
  int width = 3;
  int height = 3;
  boolean inPlay;
  boolean isShooting = false;
  boolean moveLeft = false;
  boolean moveRight = false;
  int moveSpeed = 1;
  Rectangle boundingBox;

  public Bullets(Shooter shooter){
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    this.x = shooter.x + (shooter.width / 2);
    this.y = shooter.y;
    boolean inPlay = true;
  }

  public void tick(spaceDefender game){
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    if (y < 0){
      x = game.player.x + (game.player.width / 2);
      y = game.player.y;
      isShooting = false;
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
    if(boundingBox.intersects(game.alien.boundingBox)){
      game.alien.isDead = true;
      game.alien.x = -50;
    }
  }

  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
  }
}

