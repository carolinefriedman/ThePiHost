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

  public static PlayerPaddle player;
  public static ComputerPaddle compplayer;
  public static Ball ball;
  public static GameIO io;

  //window sizing
  public final int WIDTH = 1000;
  public final int HEIGHT = 562;

  //condense w and h into one var
  public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
  public final String TITLE = "Tennis";

  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

  //keep track of player score and computer score
  public int playerScore = 0;
  public int pScoreTracker = 0;
  public int compScore = 0;

  //is game running?
  static boolean gameRunning = false;
  static boolean entered = false;

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
    player = new PlayerPaddle(10, 60);
    compplayer = new ComputerPaddle(getWidth() - 25, 60);
    ball = new Ball((getWidth()/2), (getHeight()/2));
    io = new GameIO(this);
  }

  public void tick(){
    //call tick for player and ball instances here
    player.tick(this);
    compplayer.tick(this);
    ball.tick(this);
  }

  public void gameEntry(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("Welcome to Tennis!", WIDTH/2 - 200, HEIGHT/2 - 150);
    graphics.drawString("Score points by hitting the ball with your paddle (right paddle).", WIDTH/2 - 200, HEIGHT/2 - 75);
    graphics.drawString("Dont miss! Otherwise the computer scores a point.", WIDTH/2 - 200, HEIGHT/2 - 60);
    graphics.drawString("See how many points you can score before the computer scores 10 points", WIDTH/2 - 200, HEIGHT/2 - 45);
    graphics.drawString("Controls:", WIDTH/2 - 200, HEIGHT/2 + 25);
    graphics.drawString("\t\t\tMove Paddle Up: Up Arrow", WIDTH/2 - 200, HEIGHT/2 + 50);
    graphics.drawString("\t\t\tMove Paddle Down: Down Arrow", WIDTH/2 - 200, HEIGHT/2 + 75);
    graphics.drawString("CLICK ON SCREEN AND THEN PRESS THE SPACEBAR TO PLAY", WIDTH/2 - 200, HEIGHT/2 + 150);
    graphics.dispose();
    buffer.show();
  }

  public void GameOver(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("GAME OVER", WIDTH/2 - 50, HEIGHT/2 - 30);
    graphics.drawString("Total Score: " + playerScore, WIDTH/2 - 50, HEIGHT/2);
    graphics.dispose();
    buffer.show();
    gameRunning = false;
  }

  public void render(){
    //BufferStrategy is the way in which it is Buffered
    BufferStrategy buffStrat = getBufferStrategy();
    if (buffStrat == null){  //if there is no buffer strategy
      createBufferStrategy(3);  //set BufferStrategy to 3
      return;
    }

    Graphics graphics = buffStrat.getDrawGraphics();

    if (!this.entered){
      gameEntry(graphics, buffStrat);
    }

    else{
      graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
      graphics.setColor(Color.GREEN);
      // print scores
      graphics.drawString("Player: " + playerScore, 5, 15);
      graphics.drawString("Computer: " + compScore, getWidth() - 87, 15);

    //call render for player and ball instances here
      player.render(graphics);
      compplayer.render(graphics);
      ball.render(graphics);
    }

    if (this.pScoreTracker % 4 == 0 && this.pScoreTracker > 0){
      ball.speed += 1;
      this.compplayer.paddleSpeed += 1;
      this.player.paddleSpeed += 1;
      this.pScoreTracker = 0;
    }

    if (this.compScore >= 10){
      GameOver(graphics, buffStrat);
    }

    graphics.dispose();
    buffStrat.show();
  }

  public static void main(String[] args){
    //create new game
    Tennis game = new Tennis();
    game.start();
  }
}
