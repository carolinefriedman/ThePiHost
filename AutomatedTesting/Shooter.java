import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Shooter{
  int x;
  int y;
  int health = 100;
  int width = 30;
  int height = 10;
  int moveSpeed = 3;
  boolean moveLeft = false;
  boolean moveRight = false;

  Bullets bullet;

  Rectangle boundingBox;

  //contructor
  public Shooter(int x, int y){
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    this.x = x;
    this.y = y;
    bullet = new Bullets(this);
  }

  public void tick(spaceDefender game){
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    if (moveLeft && x > 0){
      x -= moveSpeed;
    }
    if (moveRight && x < game.WIDTH - this.width){
      x += moveSpeed;
    }
    bullet.tick(game);
  }

  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
    graphics.fillRect(x + (width/2), y-2, 2, 2);
    bullet.render(graphics);
  }
}
