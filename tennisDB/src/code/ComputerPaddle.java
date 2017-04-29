package code;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * Class for automated paddle
 */
public class ComputerPaddle{
  public int x;
  public int y;
  public final int width = 7;
  public final int height = 80;
  public int paddleSpeed = 3;
  public boolean goingUp = false;
  public boolean goingDown = false;
  Rectangle boundingBox;

  public ComputerPaddle(int x, int y){
    this.x = x;
    this.y = y;
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
  }
  /**
   * Automates the movement of the paddle to follow the y-coordinate position of the ball
   * @param game Main driver Tennis instance
   */
  public void tick(Tennis game){
    boundingBox.setBounds(this.x, this.y, this.width, this.height);

    if(game.ball.y < y && y >= 0){
      y -= paddleSpeed;
    }
    if(game.ball.y > y && y + height <= game.getHeight()){
      y += paddleSpeed;
    }
  }

  /**
   * Used strictly to display the automated paddle graphic.
   * @param graphics Main driver graphics instance
   */
  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
  }
}
