package AlienTesting;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class spaceDefender extends Canvas implements Runnable{
  //is game running?
  public static boolean gameRunning = false;
  public int level = 1;
  public int dropTimer = 100;

  //set dimensions of game window
  public final int WIDTH = 600;
  public final int HEIGHT = 358;
  public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);

  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

  public static Shooter player;
  public static GameIO gameIO;

  public Alien[][] alienMatrix = new Alien[3][10];

  public static int cnt;
  public static boolean entered = false;
  Random random;
  public int x;
  public int y;
  
  public boolean allDead2 = false;

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
    random = new Random();
  //  bullet = new Bullets(player);

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
    if (cnt != 0 && cnt % dropTimer == 0){
      x = random.nextInt(3);
      y = random.nextInt(10);
      this.alienMatrix[x][y].bomb.isShooting = true;
    }

    for (int i = 0; i < 3; i ++){
      for (int j = 0; j < 10; j++){
        alienMatrix[i][j].tick(this);
        if (this.cnt != 0 && (this.cnt % 500) == 0){
          alienMatrix[i][j].y += 10;
        }
      }
    }

  }

  // display start game screen (press space to start)
  public void gameEntry(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("Press Space to start", WIDTH/2 - 100, HEIGHT/2 - 30);
    graphics.drawString("Controls:", WIDTH/2 - 100, HEIGHT/2 + 0);
    graphics.drawString("   Move Left: Left Arrow", WIDTH/2 - 100, HEIGHT/2 + 15);
    graphics.drawString("   Move Right: Right Arrow", WIDTH/2 - 100, HEIGHT/2 + 30);
    graphics.drawString("   Shoot: Space Bar", WIDTH/2 - 100, HEIGHT/2 + 45);
    graphics.dispose();
    buffer.show();
    //while(this.entered != true);
  }

  public void GameOver(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("GAME OVER", WIDTH/2 - 50, HEIGHT/2 - 30);
    graphics.drawString("Total Score: " + player.bullet.playerScore, WIDTH/2 - 60, HEIGHT/2);
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

    if (!this.entered){
      gameEntry(graphics, buffer);
    }

    else{
      graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);

      graphics.setColor(Color.GREEN);
      graphics.drawString("Health: = " + player.health + "/100", 5, HEIGHT-10);
  	  graphics.drawString("Score: = " + player.bullet.playerScore, getWidth()- 75, HEIGHT-10);

      player.render(graphics);

      boolean allDead = true;
      for (int i = 0; i < 3; i++){
        for (int j = 0; j < 10; j++){
          alienMatrix[i][j].render(graphics);
          allDead = allDead && alienMatrix[i][j].isDead;
        }
      }

      // if all aliens are dead, increase level,
      // reset alien positions, and make bombs drop
      // more frequently

      if (allDead2 == true){
        this.level++;
        for (int i = 0; i < 3; i ++){
          for (int j = 0; j < 10; j++){
            alienMatrix[i][j].isDead = false;
            alienMatrix[i][j].x = 20 + (25*j);
            alienMatrix[i][j].y = 25 * i;
            alienMatrix[i][j].moveSpeed += 1;
            dropTimer -= 20;
            allDead = false;
          }
        }
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
        if (entered)
          cnt += 1;
      }
    };
    Timer timer = new Timer(1, actListner);

    timer.start();

    game.start();
  }




}
