import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.Random;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class spaceDefender extends Canvas implements Runnable{
  //is game running?
  static boolean gameRunning = false;
  private int level = 1;
  private int dropTimer = 100;

  //set dimensions of game window
  public final int WIDTH = 1000;
  public final int HEIGHT = 562;
  public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);

  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

  public static Shooter player;
  public static GameIO gameIO;

  public final int alienRows = 3;
  public final int alienCols = 15;
  public Alien[][] alienMatrix = new Alien[alienRows][alienCols];

  public static int timeCount;
  static boolean entered = false;
  Random random;

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
    player = new Shooter(WIDTH/2, HEIGHT - 35);
    random = new Random();

    for (int i = 0; i < this.alienRows; i ++){
      for (int j = 0; j < this.alienCols; j++){
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

    if (timeCount != 0 && timeCount % dropTimer == 0){
      int x = random.nextInt(3);
      int y = random.nextInt(10);
      this.alienMatrix[x][y].bomb.isShooting = true;
    }

    for (int i = 0; i < this.alienRows; i ++){
      for (int j = 0; j < this.alienCols; j++){
        alienMatrix[i][j].tick(this);
        if (alienMatrix[i][j].y >= player.y)
          player.health = 0;
      }
    }

    if (this.player.bullet.numShots != 0 && (this.player.bullet.numShots) % 6 == 0){
      for (int i = 0; i < this.alienRows; i ++){
        for (int j = 0; j < this.alienCols; j++){
          alienMatrix[i][j].y += 20;
        }
      }
      this.player.bullet.numShots += 1;
    }

  }

  // display start game screen (press space to start)
  public void gameEntry(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("Welcome to Space Defenders!", WIDTH/2 - 150, HEIGHT/2 - 60);
    graphics.drawString("Defend the universe from evil aliens", WIDTH/2 - 170, HEIGHT/2 - 35);
    graphics.drawString("Controls:", WIDTH/2 - 150, HEIGHT/2 + 25);
    graphics.drawString("\t\t\tMove Left: Left Arrow", WIDTH/2 - 150, HEIGHT/2 + 50);
    graphics.drawString("\t\t\tMove Right: Right Arrow", WIDTH/2 - 150, HEIGHT/2 + 75);
    graphics.drawString("\t\t\tShoot: Space Bar", WIDTH/2 - 150, HEIGHT/2 + 100);
    graphics.drawString("CLICK ON SCREEN AND THEN PRESS SPACEBAR TO START", WIDTH/2 - 230, HEIGHT/2 + 200);
    graphics.dispose();
    buffer.show();
  }

  public void GameOver(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("GAME OVER", WIDTH/2 - 50, HEIGHT/2 - 30);
    graphics.drawString("Total Score: " + player.bullet.playerScore, WIDTH/2 - 60, HEIGHT/2);
    graphics.drawString("You Survived " + this.level + " levels", WIDTH/2 - 70, HEIGHT/2 + 30);
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
      for (int i = 0; i < this.alienRows; i ++){
        for (int j = 0; j < this.alienCols; j++){
          alienMatrix[i][j].render(graphics);
          allDead = allDead && alienMatrix[i][j].isDead;
        }
      }

      // if all aliens are dead, increase level,
      // reset alien positions, and make bombs drop
      // more frequently
      if (allDead == true){
        this.level++;
        for (int i = 0; i < this.alienRows; i ++){
          for (int j = 0; j < this.alienCols; j++){
            alienMatrix[i][j].isDead = false;
            alienMatrix[i][j].x = 20 + (25*j);
            alienMatrix[i][j].y = 25 * i;
            alienMatrix[i][j].moveSpeed = 2;
            alienMatrix[i][j].bomb.bombSpeed++;
            dropTimer = 50;
            allDead = false;
          }
        }
      }

    }

    if (player.health <= 0){
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
          timeCount += 1;
      }
    };
    Timer timer = new Timer(1, actListner);

    timer.start();

    game.start();
  }




}
