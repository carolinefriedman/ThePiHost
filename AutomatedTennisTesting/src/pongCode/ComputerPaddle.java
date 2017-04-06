package pongCode;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class ComputerPaddle{
  public int x;
  public int y;
  //size of paddles
  public int width = 5;
  public int height = 40;

  public int paddleSpeed = 3;

  Rectangle boundingBox;

  public boolean goingUp = false;
  public boolean goingDown = false;

  public ComputerPaddle(int x, int y){
    this.x = x;
    this.y = y;

    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
  }

  public void tick(Tennis game){
    boundingBox.setBounds(this.x, this.y, this.width, this.height);

    if(game.ball.y < y && y >= 0){
      y -= paddleSpeed;
    }
    if(game.ball.y > y && y + height <= game.getHeight()){
      y += paddleSpeed;
    }
  }

  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
  }
}
