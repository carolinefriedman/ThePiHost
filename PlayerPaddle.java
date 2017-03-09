import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class PlayerPaddle{
  int x;
  int y;
  //size of paddles
  int width = 5;
  int height = 40;
  int paddleSpeed = 3;

  //for making a hitbox for paddle
  Rectangle boundingBox;

  boolean moveUp = false;
  boolean moveDown = false;

  public PlayerPaddle(int x, int y){
    this.x = x;
    this.y = y;

    //sets bounds on hit box for player paddle
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
  }

  public void tick(Tennis game){
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    if(moveUp && y > 0){
      y -= paddleSpeed;
    }
    if(moveDown && y < game.getHeight() - height){
      y += paddleSpeed;
    }
  }

  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
  }
}

