import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Alien{
  int x;
  int y;
  int width = 10;
  int height = 10;
  int moveSpeed = 1;
  boolean isDead = false;

  AlienBomb bomb;

  Rectangle boundingBox;

  //contructor
  public Alien(int x, int y){
    this.x = x;
    this.y = y;
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    bomb = new AlienBomb(this);


  //  boundingBox = new Rectangle(x, y, width, height);
    //boundingBox.setBounds(this.x, this.y, this.width, this.height);
  }

  public void tick(spaceDefender game){
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    x += moveSpeed;
    if (x >= game.WIDTH - width){
      moveSpeed = -moveSpeed;
      x += moveSpeed;
    }
    if (x <= 0){
      moveSpeed = -moveSpeed;
      x += moveSpeed;
    }
    this.bomb.tick(game);
  }

  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
    this.bomb.render(graphics);
  }
}

