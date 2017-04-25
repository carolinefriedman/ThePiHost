package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball{
  public int x;
  public int y;
  public int size = 13;
  public int speed = 2;

  public int velocityX;
  public int velocityY;

  private int intersectionHits = 0;

  Rectangle boundingBox;

  public Ball(int x, int y){
    this.x = x;
    this.y = y;
    boundingBox = new Rectangle(x, y, size, size);
    boundingBox.setBounds(this.x, this.y, this.size, this.size);
    velocityX = speed;
    velocityY = speed;
  }

  public void tick(Tennis game){
    boundingBox.setBounds(this.x, this.y, this.size, this.size);

    if(x <= 0){
      velocityX = speed;
      game.compScore++;
    }

    else if (x + size >= game.getWidth()){
      velocityX = -speed;
      game.playerScore++;
    }

    if (y <= 0){
      velocityY = speed;
    }
    else if (y + size >= game.getHeight()){
      velocityY = -speed;
    }

    x += velocityX;
    y += velocityY;

    paddleCollide(game);
  }

  private void paddleCollide(Tennis game){
    if(boundingBox.intersects(game.player.boundingBox)){
      velocityX = speed;

      /**
       * Each collision increases intersectionHits by three
       * due to rendering of collision happening multiple times.
       * Therefore, if intersectionHits modulo 3 == 0, a single
       * collision occured and player score is increased by one
       */
      this.intersectionHits += 1;
      if (this.intersectionHits % 3 == 0 && this.intersectionHits != 0){
        game.playerScore += 1;
        game.pScoreTracker += 1;
        this.intersectionHits = 0;
      }
    }

    else if (boundingBox.intersects(game.compplayer.boundingBox)){
      velocityX = -speed;
    }
  }

  public void render(Graphics g){
    g.setColor(Color.GREEN);
    g.fillOval(x, y, size, size);
  }
}
