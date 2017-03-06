import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;

// first game. tutorial from : https://www.youtube.com/watch?v=lE18VALSCAA

public class Tennis extends Canvas implements Runnable {

  JFrame frame;

  //window sizing
  public final int WIDTH = 400;
  public final int HEIGHT = 225;

  //condense w and h into one var
  public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
  public final String TITLE = "Tennis";

  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

  //keep track of player score and computer score
  int playerScore;
  int compScore;

  //is game running?
  static boolean gameRunning = false;

  public void run(){
    while (gameRunning){
      tick();
      render();
      //this try catch slows down execution of the program so that keyboard input
      //doesnt occur so fast
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
    //gameRunning = false;
    System.exit(0);
  }

  //constructor
  public Tennis(){
    //window of game
    frame = new JFrame();

    this.setMinimumSize(gameSize);
    this.setPreferredSize(gameSize);
    this.setMaximumSize(gameSize);

    frame.add(this, BorderLayout.CENTER);
    //makes contents appear in desired frame dimensions
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //makes fram appear on screen
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setTitle(TITLE);
    frame.setLocationRelativeTo(null);

    // initiate paddle and ball instances here
  }

  public void tick(){
    //call tick for player and ball instances here
  }

  public void render(){
    //BufferStrategy is the way in which it is Buffered
    BufferStrategy bs = getBufferStrategy();
    if (bs == null){  //if there is no buffer strategy
      createBufferStrategy(3);  //set BufferStrategy to 3
      return;
    }

    Graphics g = bs.getDrawGraphics();
    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    g.setColor(Color.GREEN);
    // print scores
    g.drawString("Player: " + playerScore, 5, 15);
    g.drawString("Computer: " + compScore, getWidth() - 87, 15);

  //call render for player and ball instances here

    g.dispose();
    bs.show();
  }

  public static void main(String[] args){
    //create new game
    Tennis game = new Tennis();
    game.start();
  }
}

