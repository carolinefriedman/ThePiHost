import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.event.MouseListener;

public class spaceDefender extends Canvas implements Runnable{
  //is game running?
  static boolean gameRunning = false;

  //set dimensions of game window
  public final int WIDTH = 400;
  public final int HEIGHT = 225;
  public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);

  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

  public static Shooter player;
  public static GameIO gameIO;
  public static Alien alien;

  public int bulletCounter = 0;
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
    player = new Shooter(WIDTH/2, HEIGHT - 15/*playerheight+5*/);
  //  bullet = new Bullets(player);
    alien = new Alien(20,0);
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
    alien.tick(this);
  }

  public void render(){
    //BufferStrategy is the way in which it is Buffered
    BufferStrategy buffer = getBufferStrategy();
    if (buffer == null){  //if there is no buffer strategy
      createBufferStrategy(3);  //set BufferStrategy to 3 for triple buffering
      return;
    }

    Graphics graphics = buffer.getDrawGraphics();
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);

    player.render(graphics);
  //  bullet.render(graphics);
    alien.render(graphics);

    graphics.dispose();
    buffer.show();
  }

  public static void main(String args[]){
    spaceDefender game = new spaceDefender();
    game.start();
  }

}

