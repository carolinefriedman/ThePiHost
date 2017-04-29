package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *  This class is for the bullet that belongs to the shooter (player of the game).
 */
public class Bullets{
  public int x;
  public int y;
  public int bulletSpeed = 12;
  public final int width = 3;
  public final int height = 3;
  public int playerScore = 0;
  public boolean inPlay;
  public boolean isShooting = false;
  public boolean moveLeft = false;
  public boolean moveRight = false;
  public int moveSpeed;
  Rectangle boundingBox;
  boolean collision = false;
  public int numShots = 1;

  /**
   * @param shooter instance of shooter class that bullet shall belong to
   */
  public Bullets(Shooter shooter){
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    this.x = shooter.x + (shooter.width / 2);
    this.y = shooter.y;
    this.moveSpeed = shooter.moveSpeed;
    boolean inPlay = true;
  }

  /**
   * tick method for bullet class keeps the bullet behind the shooter until the user wants to fire the bullet.
   * Once the bullet is either off of the top of the screen or has collided with an alien, the position of the bullet
   * is repositioned behind the shooter.
   *
   * @param game Driver instance of the game
   *
   */
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

	/**
	 * Detects collisions between fired bullet and alien.
	 * If a collision occurs, the alien is declared dead and is moved off of the screen until the next level.
	 * @param game Driver instance of the game
	 */
  private void collide(spaceDefender game){
    for (int i = 0; i < game.alienRows; i ++){
      for (int j = 0; j < game.alienCols; j++){
        if(boundingBox.intersects(game.alienMatrix[i][j].boundingBox)){
          game.alienMatrix[i][j].isDead = true;
          game.alienMatrix[i][j].x = -50;
          game.alienMatrix[i][j].y = 0;
          collision = true;
          playerScore ++;
        }
      }
    }

  }

  /**
   * Strictly for displaying bullet instance on screen
   *
   * @param graphics same instance as used in spaceDefender game instance
   */
  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
  }
}
