import java.awt.BorderLayout;

import java.awt.Canvas;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.EventQueue;

import java.awt.Font;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.image.BufferStrategy;

import javax.swing.AbstractAction;

import javax.swing.ActionMap;

import javax.swing.InputMap;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.KeyStroke;

import javax.swing.Timer;

import javax.swing.UIManager;

import javax.swing.UnsupportedLookAndFeelException;

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


//handler.addObject(new Zombie(10,3));



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


/////////////background
g.setColor(colorTemp = new Color(52, 53, 54));
///////////////////////


/////////walls
g.setColor(colorTemp = new Color(82, 11, 63));
g.fillRect(-100, 0, 400,400 );
g.fillRect(-100, 575, 400,375 );
g.fillRect(450,450 , 50,75 );
g.fillRect(450,375 , 10,225 );
g.fillRect(0, 0, 750,100 );
g.fillRect(700, 0, 50,175 );
g.fillOval(500, 30, 250,250 );

((Graphics2D) g).rotate(Math.toRadians(25));
g.fillRect(950,-180,25,200);
g.fillRect(930, -130, 20,125 );
((Graphics2D) g).rotate(Math.toRadians(-50));
g.fillRect(625,850,25,200);
g.fillRect(605, 900, 20,125 );
((Graphics2D) g).rotate(Math.toRadians(25));

g.fillRect(650, 725, 225,125 );
g.fillRect(1240, 300, 125,175 );
g.fillRect(1220, 720, 125,225 );
g.fillRect(1150, 0, 200,175 );
g.fillRect(1100, 75, 200,75 );

g.fillRect(860, 140,40,40 );
g.fillRect(820, 60,30,30 );

g.fillRect(0,0,6,l );
g.fillRect(0, 0,w,6 );        //borders of map
g.fillRect(w-6,0,6,l );
g.fillRect(0,l-26,w,6 ); //fix(not alligned with border)
/////////////



////g.setFont(fontTemp = new Font("LucidaSans", Font.BOLD, 30));
//g.setColor(colorTemp = new Color(255,255,255,255));
//g.drawString("TAX FRAUD",w/2,l/2);

handler.render(g);
///////////////////////////////////
g.dispose();
bs.show();
}
}

