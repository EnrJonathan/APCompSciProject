
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable{

private static final long serialVersionUID = 1L;
private boolean isRunning = false;
private Thread thread;
private Handler handler;
int w;
int l;


public static void main (String args[]) {
new Game();
}


public Game() {
w=1275;  //1400
l=800;   //800
new Window(w,l,"GameProject",this);
start();
handler = new Handler();
this.addKeyListener(new KeyInput(handler));

handler.addObject(new Player(50,450,ID.Player, handler));
handler.addObject(new Zombie(10,450,ID.Zombie));
}



private void start() {
isRunning = true;
thread = new Thread(this);
thread.start();
}



private void stop() {
isRunning = false;
try {
thread.join();
} catch (InterruptedException e) {
e.printStackTrace();
}
}


//the run(), render(), & tick() method is from Notch (the creator of minecraft)

public void run() {

  this.requestFocus();

  long lastTime = System.nanoTime();

  double amountOfTicks = 60.0;

  double ns = 1000000000 / amountOfTicks;

  double delta = 0;

  long timer = System.currentTimeMillis();

  int frames = 0;

  while (isRunning) {

  long now = System.nanoTime();

  delta += (now - lastTime) /ns;

  lastTime = now;

  while(delta >= 1) {

  tick();

  //updates++;

  delta--;

  }

  render();

  frames++;

  if (System.currentTimeMillis() - timer > 1000) {

timer += 1000;

frames = 0;

  }

  }

  stop();

}


public void tick() {
handler.tick();
}


public void render() {
Color colorTemp;
Font fontTemp;
BufferStrategy bs = this.getBufferStrategy();
if(bs == null) {
this.createBufferStrategy(3);
return;
}


Graphics g = bs.getDrawGraphics();
///////////////////////////////////
g.setColor(colorTemp = new Color(20, 20, 20));
g.fillRect(0, 0, w, l);

handler.render(g);
///////////////////////////////////
g.dispose();
bs.show();
}
}

