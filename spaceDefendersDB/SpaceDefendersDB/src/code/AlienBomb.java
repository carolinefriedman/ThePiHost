package code;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

/** This class is used to control a bomb that each alien can try to drop onto the shooter (player)*/
public class AlienBomb{
  public int x;
  public int y;
  public int bombSpeed = 1;
  public final int width = 3;
  public final int height = 6;
  /** If value is true, a bomb is currently in motion.  Value is false otherwise. */
  public boolean isShooting = false;
  public boolean moveLeft = false;
  public boolean moveRight = false;
  public int moveSpeed = 1;
  Rectangle boundingBox;
  /** Boolean variable specifying if there was a collision between the player (shooter) and the bomb*/
  boolean collision = false;

  /**
   * A bomb is positioned directly behind each alien.
   *
   * @param alien Instance of Alien that newly constructed alien bomb will belong to
   */
  public AlienBomb(Alien alien){
    boundingBox = new Rectangle(x, y, width, height);
    boundingBox.setBounds(this.x, this.y, this.width, this.height);
    this.x = alien.x + (alien.width / 2);
    this.y = alien.y;
  }

/**
 * tick actuates the movement of the bomb.
 * As long as the bomb isn't scheduled to drop, it maintains position behind the alien.
 * If bomb drops below the shooter, its position is reset to behind the alien
 *
 * @param game Driver instance of the game
 * @param alien Instance of Alien that alien bomb belongs to
 */
  public void tick(spaceDefender game, Alien alien){
    if(!isShooting){
      y = alien.y;
    }

    boundingBox.setBounds(this.x, this.y, this.width, this.height);

    if (y > game.player.y){
      x = alien.x + (alien.width / 2);
      y = alien.y;
      isShooting = false;
      collision = false;
    }

    if(isShooting){
      y += bombSpeed;
    }

    else{
      x = alien.x + (alien.width / 2);
    }

    collide(game);

  }

  /**
   * collide method is to determine if there is a collision between the alien bomb and the shooter.
   *
   * @param game Driver instance of the game
   *
   */
  private void collide(spaceDefender game){
    if(boundingBox.intersects(game.player.boundingBox)){
      collision = true;
    }

    if (collision == true){
      game.player.health -= 1;
      collision = false;
    }
  }

	/**
	 * Strictly for displaying graphics associated with AlienBomb instance
	 * @param graphics Driver instance of the game
	 */
  public void render(Graphics graphics){
    graphics.setColor(Color.GREEN);
    graphics.fillRect(x, y, width, height);
  }
}
