package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Class for user controlled paddle.
 */
public class PlayerPaddle{
  public int x;
  public int y;
  public final int width = 7;
  public final int height = 80;
  public int paddleSpeed = 3;
  /** moveUp assigned to true if player pressed up arrow */
  public boolean moveUp = false;
  /** moveDown assigned to true if player pressed down arrow */
  public boolean moveDown = false;
  public Rectangle boundingBox;

  public PlayerPaddle(int x, int y){
    this.x = x;
    this.y = y;
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
  }
  /**
   * This method allows for movement of the user controlled paddle.
   * Movement is bounded by the screen dimensions.
   *
   * @param game Main driver Tennis instance
   */
  public void tick(Tennis game){
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    if(moveUp && y > 0){
      y -= paddleSpeed;
    }

    if(moveDown && y < game.HEIGHT - this.height){
      y += paddleSpeed;
    }
  }

  /**
   * Used strictly to display the player paddle graphic.
   * @param graphics Main driver graphics instance
   */
  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
  }
}
