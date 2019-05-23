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

w=1000;  //1440

l=563;   //840

new Window(w,l,"GameProject",this);

start();


handler = new Handler();


handler.addObject(new Zombie(10,0));
handler.addObject(new Zombie(10,100));
handler.addObject(new Zombie(10,200));
handler.addObject(new Zombie(10,300));
handler.addObject(new Zombie(10,400));
handler.addObject(new Zombie(10,500));


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

/*
g.setColor(colorTemp = new Color(0, 255, 255));
g.fillRect(0, 0, w/2, l/2);

g.setColor(colorTemp = new Color(255, 0, 0,122));
g.fillOval(400,400,100,100);

g.setColor(colorTemp = new Color(0, 0, 255,122));
g.fillOval(450,400,100,100);
*/

g.setFont(fontTemp = new Font("LucidaSans", Font.BOLD, 30));
g.setColor(colorTemp = new Color(255,255,255,255));
g.drawString("TAX FRAUD",w/2,l/2);

handler.render(g);
///////////////////////////////////
g.dispose();
bs.show();

}

}

