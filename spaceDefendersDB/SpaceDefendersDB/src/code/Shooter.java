package code;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * This is the class that controls the shooter (which is controled by the user).
 */
public class Shooter{
  public int x;
  public int y;
  public int health = 100;
  public final int width = 30;
  public final int height = 10;
  public int moveSpeed = 3;
  /** moveLeft is set to true user presses the left arrow key */
  public boolean moveLeft = false;
  /** moveRight is set to true user presses the right arrow key */
  public boolean moveRight = false;

  Bullets bullet;
  Rectangle boundingBox;
/**
 *
 * @param x x coordinate of the shooter
 * @param y y coordinate of the shooter
 */
  public Shooter(int x, int y){
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    this.x = x;
    this.y = y;
    bullet = new Bullets(this);
  }

  /**
   * Method for responding to user input and moving the shooter accordingly.
   * @param game Driver instance of the game
   */
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

  /**
   * render method for shooter class is strictly for displaying graphics of shooter figure
   * @param graphics same instance as used in spaceDefender game instance
   */
  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
    graphics.fillRect(x + (width/2), y-2, 2, 2);
    bullet.render(graphics);
  }
}
