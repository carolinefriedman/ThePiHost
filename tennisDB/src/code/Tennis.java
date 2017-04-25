package code;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;

/**
*   Tennis is the first game that our team wrote.  In learning how to write
*   a game in Java, we followed the tutorial at:
*   https://www.youtube.com/watch?v=lE18VALSCAA
*   The basic theory and flow follow what we learned in the tutorial, however,
*   modifications were made to better suite the motives of our group.
*/

public class Tennis extends Canvas implements Runnable {

  JFrame frame;

  public static PlayerPaddle player;
  public static ComputerPaddle compplayer;
  public static Ball ball;
  public static GameIO io;
  public final int WIDTH = 1000;
  public final int HEIGHT = 562;
  public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
  public final String TITLE = "Tennis";
  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  public int playerScore = 0;
  public int pScoreTracker = 0;
  public int compScore = 0;
  static boolean gameRunning = false;
  static boolean entered = false;
  
  /**
   *   mysql database connection credentials
   */
   private static final String USERNAME = "root";
   private static final String PASSWORD = "p1#h0sT";
   private static final String CONN_STRING = "jdbc:mysql://localhost/piproject";

  public Tennis(){

    frame = new JFrame();
    this.setMinimumSize(gameSize);
    this.setPreferredSize(gameSize);
    this.setMaximumSize(gameSize);
    frame.add(this, BorderLayout.CENTER);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setTitle(TITLE);
    frame.setLocationRelativeTo(null);

    player = new PlayerPaddle(10, 60);
    compplayer = new ComputerPaddle(getWidth() - 25, 60);
    ball = new Ball((getWidth()/2), (getHeight()/2));
    io = new GameIO(this);
  }

  public void run(){
    while (gameRunning){
      tick();
      render();
      try{
        /**
        * Reason that thread should sleep for a short amount of time:
        * http://stackoverflow.com/questions/20634600/why-does-a-game-loop-need-to-sleep
        * Smaller increments of sleep time were tried here, but the movement of figures
        * on the screen became overly sensitive to keyboard input.
        */
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

  public void tick(){
    player.tick(this);
    compplayer.tick(this);
    ball.tick(this);
  }

  public void render(){
    /** Set buffer stategy to triple buffering
     * useful link describing multiple buffering techiques:
     * https://en.wikipedia.org/wiki/Multiple_buffering
    */
    BufferStrategy buffStrat = getBufferStrategy();

    if (buffStrat == null){
      createBufferStrategy(3);
      return;
    }

    Graphics graphics = buffStrat.getDrawGraphics();

    if (!this.entered){
      gameEntry(graphics, buffStrat);
    }
    else{
      graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
      graphics.setColor(Color.GREEN);
      graphics.drawString("Player: " + playerScore, 5, 15);
      graphics.drawString("Computer: " + compScore, getWidth() - 87, 15);

      player.render(graphics);
      compplayer.render(graphics);
      ball.render(graphics);
    }

    /**
    *  Every time the player scores 4 points (this score interval is arbitrary,
    *  but while testing, 4 proved to be a good interval), make the ball move
    *  faster. Also allow the paddles to move faster to keep up with ball movement.
    */
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
  
  private void dbUpdate(Graphics graphics) throws SQLException{
    Connection conn = null;
    PreparedStatement updateStmt = null;
    PreparedStatement readStmt = null;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    LocalDateTime dateNow = LocalDateTime.now();

    try{
      conn = (Connection) DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
      String updateQuery = "insert into tennisScores (date, score) values(?, ?)";
      updateStmt = (PreparedStatement) conn.prepareStatement(updateQuery);
      updateStmt.setString(1, dateFormat.format(dateNow));
      updateStmt.setInt(2, playerScore);
      updateStmt.execute();
      String readQuery = "SELECT date, score FROM tennisScores ORDER BY score DESC LIMIT 3";
      readStmt = (PreparedStatement) conn.prepareStatement(readQuery);
      ResultSet highScores = readStmt.executeQuery();
      int printLocation = HEIGHT/2 + 90;
      while (highScores.next()){
          graphics.drawString("Date: " + highScores.getString(1) + ", Score: "+ highScores.getString(2), WIDTH/2 - 130, printLocation);
          printLocation += 30;
      }

    } catch (SQLException e){
      System.err.println(e);

    } finally {
      if (readStmt != null){
    	  readStmt.close();
      }
      if (updateStmt != null){
    	  updateStmt.close();
      }
      if (conn != null){
        conn.close();
      }
    }
  }

  /** Function to display the game entrance screen */
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

  /** Function to display the game over screen */
  public void GameOver(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("GAME OVER", WIDTH/2 - 50, HEIGHT/2 - 30);
    graphics.drawString("Total Score: " + playerScore, WIDTH/2 - 50, HEIGHT/2);
    graphics.drawString("Your High Scores:", WIDTH/2 - 70, HEIGHT/2 + 60);
    
    try {
		dbUpdate(graphics);
	} catch (SQLException e) {
		System.err.println(e);
	}
    
    graphics.dispose();
    buffer.show();
    gameRunning = false;
  }

  public static void main(String[] args){
    Tennis game = new Tennis();
    game.start();
  }
}
