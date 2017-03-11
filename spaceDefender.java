import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.event.MouseListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class spaceDefender extends Canvas implements Runnable{
  //is game running?
  static boolean gameRunning = false;

  //set dimensions of game window
  public final int WIDTH = 600;
  public final int HEIGHT = 358;
  public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);

  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

  public static Shooter player;
  public static GameIO gameIO;
//  public static Alien alien;
//  public static Alien alien2;
//  public Alien[] alienArray = new Alien[10];
  public Alien[][] alienMatrix = new Alien[3][10];

  public static int cnt;
  static boolean entered = false;

// public int bulletCounter = 0;
  //public static Bullets bullet;

  //public static AlienBomb bomb;

  //constructor
  public spaceDefender(){
    JFrame frame = new JFrame();

    //game window dimensions
    this.setMinimumSize(gameSize);
    this.setMaximumSize(gameSize);
    this.setPreferredSize(gameSize);

    frame.add(this, BorderLayout.CENTER);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setTitle("Space Defenders");
    //set to center of screen
    frame.setLocationRelativeTo(null);
    gameIO = new GameIO(this);
    player = new Shooter(WIDTH/2, HEIGHT - 35/*playerheight+5*/);
  //  bullet = new Bullets(player);
  //  alien = new Alien(20,0);
    for (int i = 0; i < 3; i ++){
      for (int j = 0; j < 10; j++){
        alienMatrix[i][j] = new Alien(20 + (25*j), (25*i));
      }
    }
  }

  public void run(){
    while (gameRunning){
      tick();
      render();

      try{
        Thread.sleep(5);
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  public synchronized void start(){
    gameRunning = true;
    new Thread(this).start();
  }

  public static synchronized void stop(){
    System.exit(0);
  }

  public void tick(){
    player.tick(this);
  //  bullet.tick(this);
  //  alien.tick(this);
/*
    for (int i = 0; i < 10; i++){
      alienArray[i].tick(this);
    }
*/
    for (int i = 0; i < 3; i ++){
      for (int j = 0; j < 10; j++){
        alienMatrix[i][j].tick(this);
      }
    }
  }

  // display start game screen (press space to start)
  public void gameEntry(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("Press Space to start", 2, HEIGHT-5);
    graphics.dispose();
    buffer.show();
    //while(this.entered != true);
  }

  public void GameOver(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("GAME OVER", 2, HEIGHT-5);
    graphics.dispose();
    buffer.show();
    gameRunning = false;
  }

  public void render(){
    //BufferStrategy is the way in which it is Buffered
    BufferStrategy buffer = getBufferStrategy();
    if (buffer == null){  //if there is no buffer strategy
      createBufferStrategy(3);  //set BufferStrategy to 3 for triple buffering
      return;
    }

    Graphics graphics = buffer.getDrawGraphics();

    //gameEntry(graphics, buffer);

    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);

    graphics.setColor(Color.GREEN);
    graphics.drawString("Health: = " + player.health + "/100", 5, HEIGHT-10);
	  graphics.drawString("Score: = " + player.bullet.playerScore, getWidth()- 75, HEIGHT-10);

    player.render(graphics);

    for (int i = 0; i < 3; i ++){
      for (int j = 0; j < 10; j++){
        alienMatrix[i][j].render(graphics);
      }
    }
/*
    if(this.cnt == 10){
      player.health = 0;
    }
*/
    if (player.health < 0){
      GameOver(graphics, buffer);
    }

    graphics.dispose();
    buffer.show();

  }

  public static void main(String args[]){
    spaceDefender game = new spaceDefender();

    ActionListener actListner = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        cnt += 1;
      }
    };
    Timer timer = new Timer(1000, actListner);

    timer.start();

    game.start();
  }




}
