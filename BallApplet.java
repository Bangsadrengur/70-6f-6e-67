import java.applet.*;
import java.awt.*;
import java.net.*;

public class BallApplet extends Applet implements Runnable {
    AudioClip bounce;
    int x_pos=30;
    int y_pos=100;
    int x_speed=1;
    int radius=20;
    int appletsize_x=300;
    int appletsize_y=300;

    private Image dbImage;
    private Graphics dbg;

    public void init() {
        setBackground(Color.blue);
        bounce=getAudioClip(getCodeBase(),"Sound.wav");
    }
    public void start() {
        Thread th = new Thread(this);
        th.start();
    }
    public void stop() {}
    public void destroy() {}
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        while(true) {
            //if(x_pos>appletsize_x-radius) {
            //    x_speed=-1;
            //    bounce.play();
            //}
            if(x_pos>(appletsize_x+radius)) {
                x_pos=-20;
                bounce.play();
            }
            //else if(x_pos<radius) {
            //   x_speed+=1;
            //   bounce.play();
            //}
            x_pos+=x_speed;
            repaint();
            try {
                Thread.sleep(20);
            }
            catch(InterruptedException ex) {
                // do nothing
            }
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x_pos-radius,y_pos-radius,2*radius,2*radius);
    }
    public void update(Graphics g) {
        if(dbImage==null) {
            dbImage=createImage(this.getSize().width,this.getSize().height);
            dbg=dbImage.getGraphics();
        }
        dbg.setColor(getBackground());
        dbg.fillRect(0,0,this.getSize().width,this.getSize().height);
        dbg.setColor(getForeground());
        paint(dbg);
        g.drawImage(dbImage,0,0,this);
    }
}
