import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class DropTimer{
  Toolkit toolkit;
  Timer timer;

  public DropTimer(int seconds){
    toolkit = Toolkit.getDefaultToolKit();
    timer = new Timer();
    timer.schedule(new DropTask(), seconds*100);
  }

  class DropTask extends TimerTask{
    public void run(){
      System.out.println("Time");
      System.exit(0);
    }
  }

  public static void main(String args[]){
    new DropTask(5);
  }
}
