package pongCode;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball{
  public int x;
  public int y;
  public int size = 10;
  public int speed = 2;

  public int velocityX;
  public int velocityY;

  Rectangle boundingBox;

  public Ball(int x, int y){
    this.x = x;
    this.y = y;

    //sets bounds on hit box for ball
    boundingBox = new Rectangle(x, y, size, size);
    boundingBox.setBounds(this.x, this.y, this.size, this.size);

    velocityX = speed;
    velocityY = speed;
  }

  public void tick(Tennis game){
    boundingBox.setBounds(this.x, this.y, this.size, this.size);
    //if there is a collosion with end of screen
    //if ball hits left wall
    if(x <= 0){
      velocityX = speed;
      game.compScore++;
    }
    //if ball hits right wall
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
