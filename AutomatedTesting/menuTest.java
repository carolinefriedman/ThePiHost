import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.event.MouseListener;

public class menuTest extends Canvas implements Runnable{
    //initializing dimesnison on canvas
    public final int WIDTH = 400;
    public final int HEIGHT = 225;

    public final Dimension menuSize = new Dimension(WIDTH, HEIGHT);

    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public menuTest(){

      //creating window of game

      //crating instance of JFrame
      JFrame frame = new JFrame();

      //Setting attributes of window frame:

      //Set sizing constaints.  The following restricts the window
      //size to 'menuSize'
      this.setMinimumSize(menuSize);
      this.setPreferredSize(menuSize);
      this.setMaximumSize(menuSize);

      frame.add(this, BorderLayout.CENTER);
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setTitle("Menu Test");
      //set to center of screen
      frame.setLocationRelativeTo(null);

      //allow mouse I/O in frame by creating instance of mouseInputTest
      this.addMouseListener(new mouseInputTest());
    }

    //is the menu running?
    static boolean menuRunning = false;

    //thread enters run when it starts
    public void run(){
      while (menuRunning){
        render();
      }
    }

    public synchronized void start(){
      menuRunning = true;
      new Thread(this).start();
    }

    public static synchronized void stop(){
      System.exit(0);
    }

    public void render(){
      //BufferStrategy is the way in which it is Buffered
      BufferStrategy buffer = getBufferStrategy();
      if (buffer == null){  //if there is no buffer strategy
        this.createBufferStrategy(3);  //set BufferStrategy to 3 for triple buffering
        return;
      }

      Graphics graphics = buffer.getDrawGraphics();
      graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);

      //draw button for mouse input
      graphics.setColor(Color.GREEN);
      graphics.fillRect(150, 85, 100, 50);

      //label button with string
      graphics.setColor(Color.BLACK);
      graphics.drawString("Play Pong", (getWidth()/2)-30, (getHeight()/2));

      graphics.dispose();
      buffer.show();
    }

    public static void main(String[] args){
      //create new menue instance
      menuTest menu = new menuTest();
      //run start() function, where a new thread is started and
      //graphic rendering begins
      menu.start();
    }
}

