package code;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class spaceDefender extends Canvas implements Runnable{

  public final int WIDTH = 1000;
  public final int HEIGHT = 562;
  public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  static boolean gameRunning = false;
  static boolean entered = false;
  private int level = 0;
  /** dropTimer specifies the time interval at which and alien drops a bomb
  * When used in combination with the timer, having the interval = 100
  * specifies a period of .1 seconds
  */
  private int dropTimer = 100;
  Random random;
  public static Shooter player;
  public static GameIO gameIO;
  /** Number of aliens per column on screen */
  public final int alienRows = 3;
  /** Number of aliens per row on screen */
  public final int alienCols = 15;
  public Alien[][] alienMatrix = new Alien[alienRows][alienCols];

  public static int timeCount;

  /**  mysql database username */
  private static final String USERNAME = "root";
  /**  mysql database password */
  private static final String PASSWORD = "";
  /**  mysql database connection point */
  private static final String CONN_STRING = "jdbc:mysql://localhost/piproject";

  /**
   * Constructor generates the JFrame and instances of the
   * user player, the matrix of aliens, and the class for interpreting
   * keyboard input.  The nested for loops that instantiate the aliens
   * set their positions to be aligned in 3 rows of 15.
   */
  public spaceDefender(){
    JFrame frame = new JFrame();
    this.setMinimumSize(gameSize);
    this.setMaximumSize(gameSize);
    this.setPreferredSize(gameSize);
    frame.add(this, BorderLayout.CENTER);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setTitle("Space Defenders");
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

  /**
   * run is the function that the thread begins execution at.
   *
   * The reason that thread should sleep for a short amount of time in the try statement:
   * http://stackoverflow.com/questions/20634600/why-does-a-game-loop-need-to-sleep
   * Smaller increments of sleep time were tried here, but the movement of figures
   * on the screen became overly sensitive to keyboard input.
   */
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
  /** create  new thread that executes until the game ends */
  public synchronized void start(){
    this.gameRunning = true;
    new Thread(this).start();
  }

  public static synchronized void stop(){
    System.exit(0);
  }

  /**
   * tick method is used to alter attributes and variables of class
   * instances.
   */
  public void tick(){
    player.tick(this);

    /**
     * Select a randomly alien to drop a bomb every period specified by timecount
    */
    if (timeCount != 0 && timeCount % dropTimer == 0){
      int x = random.nextInt(3);
      int y = random.nextInt(10);
      this.alienMatrix[x][y].bomb.isShooting = true;
    }

    /**
     * if all aliens are dead, increase level, reset alien
     * positions, make aliens mover faster, and make bombs
     * drop more faster.
     */
    boolean allDead = true;
    for (int i = 0; i < this.alienRows; i ++){
      for (int j = 0; j < this.alienCols; j++){
        alienMatrix[i][j].tick(this);
        allDead = allDead && alienMatrix[i][j].isDead;
        if (alienMatrix[i][j].y >= player.y)
          player.health = 0;
      }
    }

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

    /**
    * Every time the player shoots 5 bullets, make the aliens approach the player.
    * Every fifth shot, numShots is incremented by 1 to keep this if statement
    * from evaluating to true every time numShots is an increment of 5.
    * This results in the need to due numShots % 6 since 1 in every 6 shots is
    * not done by the player.
    */
    if (this.player.bullet.numShots != 0 && (this.player.bullet.numShots) % 6 == 0){
      for (int i = 0; i < this.alienRows; i ++){
        for (int j = 0; j < this.alienCols; j++){
          alienMatrix[i][j].y += 20;
        }
      }
      this.player.bullet.numShots += 1;
    }
  }

  /**
   *  render method is used to render the images for the jFrame and
   *  game class instances.
   *
   * Buffer stategy is set for triple buffering
   * useful link describing multiple buffering techiques:
   * https://en.wikipedia.org/wiki/Multiple_buffering
   */
  public void render(){
    BufferStrategy buffer = getBufferStrategy();
    if (buffer == null){
      createBufferStrategy(3);
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
      for (int i = 0; i < this.alienRows; i ++){
        for (int j = 0; j < this.alienCols; j++){
          alienMatrix[i][j].render(graphics);
        }
      }
    }

    if (player.health <= 0){
      GameOver(graphics, buffer);
    }

    graphics.dispose();
    buffer.show();
  }

  /**
   *
   * dbUpdate updates high scores database with the score of the recently finished game session and the time.
   * A read from the database is then done to display the top three high scores.
   *
   * @param graphics same instance as used in spaceDefender game instance
   * @throws SQLException
   *
   */
  private void dbUpdate(Graphics graphics) throws SQLException{
    Connection conn = null;
    PreparedStatement updateStmt = null;
    PreparedStatement readStmt = null;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    LocalDateTime dateNow = LocalDateTime.now();

    try{
      conn = (Connection) DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
      String updateQuery = "insert into spaceDefendersScores (date, score) values(?, ?)";
      updateStmt = (PreparedStatement) conn.prepareStatement(updateQuery);
      updateStmt.setString(1, dateFormat.format(dateNow));
      updateStmt.setInt(2, player.bullet.playerScore);
      updateStmt.execute();
      String readQuery = "SELECT date, score FROM spaceDefendersScores ORDER BY score DESC LIMIT 3";
      readStmt = (PreparedStatement) conn.prepareStatement(readQuery);
      ResultSet highScores = readStmt.executeQuery();
      int printLocation = HEIGHT/2 + 120;
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

  /**
   *
   * Displays the game entrance screen
   * @param graphics same instance as used in spaceDefender game instance
   * @param buffer same instance as used in spaceDefender game instance
   *
   */
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

  /**
   *
   * @param graphics same instance as used in spaceDefender game instance
   * @param buffer same instance as used in spaceDefender game instance
   *
   * Displays the game over screen
   */
  public void GameOver(Graphics graphics, BufferStrategy buffer){
    graphics.setColor(Color.GREEN);
    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    graphics.setColor(Color.GREEN);
    graphics.drawString("GAME OVER", WIDTH/2 - 50, HEIGHT/2 - 30);
    graphics.drawString("Total Score: " + player.bullet.playerScore, WIDTH/2 - 60, HEIGHT/2);
    graphics.drawString("You Survived " + this.level + " levels", WIDTH/2 - 70, HEIGHT/2 + 30);
    graphics.drawString("Your High Scores:", WIDTH/2 - 70, HEIGHT/2 + 90);
    try {
		    dbUpdate(graphics);
	  } catch (SQLException e) {
		    System.err.println(e);
	  }

    graphics.dispose();
    buffer.show();
    gameRunning = false;
  }

  public static void main(String args[]) throws SQLException{
    spaceDefender game = new spaceDefender();

    ActionListener actListner = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        if (entered)
          timeCount += 1;
      }
    };
    /** increment timeCount every millisecond */
    Timer timer = new Timer(1, actListner);
    timer.start();

    game.start();
  }

}
