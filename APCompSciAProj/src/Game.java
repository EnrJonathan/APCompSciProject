
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

private static final long serialVersionUID = 1L;
private boolean isRunning = false;
private Thread thread;
private Handler handler;
private Camera camera;
private SpriteSheet ss;

private BufferedImage level = null;
private BufferedImage sprite_sheet = null;
private BufferedImage floor = null;

//damage multiplyer
public int multy = 1;



// amount of ammo that the player starts with
public int mag = 12;
public int xtraAmmo = 12;

// range to interact
public String inRange = "";

//loosing and winning switch
public boolean win = false;
public boolean lose = false;


//health of player
public int hp = 100;


//point system
public int points = 0;


////inGameTimer
public double myTimer = 0;





//enemies left
public int enemiesLeft = 0;
public int wave = 1;
public int zombiesKilled = 0;    // when it reaches 20, start new wave

public static void main (String args[]) {
new Game();
}


public Game() {
	new Window(1000,563,"GameProject",this);
	start();


	handler = new Handler();
	camera = new Camera(0,0);
	this.addKeyListener(new KeyInput(handler));


	BufferedImageLoader loader = new BufferedImageLoader();
	level = loader.loadImage("/game_level.png");
	sprite_sheet = loader.loadImage("/bullet.png");
	
	ss = new SpriteSheet(sprite_sheet);
	floor = ss.grabImage(1,3,32,32);
	
	this.addMouseListener(new MouseInput(handler,camera,this,ss));

	
	loadLevel(level);
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
  
  
  
  if(myTimer >= 9.5) myTimer = 0.0;
else   myTimer += 0.015;                                                        //timer counter for spawners
  

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
	
	for(int i = 0;i < handler.object.size();i++) {
		if(handler.object.get(i).getId() == ID.Player) {
			camera.tick(handler.object.get(i));
		}
	}
	
	
handler.tick();
}


public void render() {
BufferStrategy bs = this.getBufferStrategy();
if(bs == null) {
this.createBufferStrategy(3);
return;
}


Graphics g = bs.getDrawGraphics();
Graphics2D g2d = (Graphics2D) g;
Font waveFont = new Font(Font.SANS_SERIF, Font.PLAIN, 50);
Font ammoFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
Font pointsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
///////////////////////////////////

g2d.translate(-camera.getX(),-camera.getY());
for(int xx =0; xx<30*72;xx+=32) {
	for(int yy =0; yy<30*72;yy+=32) {
		g.drawImage(floor,xx,yy,null);
	}
}
handler.render(g);
g2d.translate(camera.getX(),camera.getY());


//health bar
g.setColor(Color.gray);
g.fillRect(5, 5, 200, 32);
g.setColor(Color.green);
g.fillRect(5, 5, hp*2, 32);
g.setColor(Color.black);
g.drawRect(5, 5, 200, 32);


//can buy ammo
	g.setColor(Color.white);
	g.drawString(inRange,420,281);
	
//timer display
		//g.setColor(Color.white);
		//g.drawString("Time: " + myTimer,420,100);

//points display
g.setColor(Color.white);
g.setFont(ammoFont);
g.drawString("Pts: " + points,5,475);
g.setColor(Color.white);


//wave display
g.setColor(Color.white);
g.setFont(waveFont);
g.drawString("Wave: " + wave,5,525);
g.setColor(Color.white);




//ammo display
g.setColor(Color.white);
g.setFont(ammoFont);
g.drawString("Ammo: "+ mag + "/" + xtraAmmo,770,520);
g.setColor(Color.white);

//shows # of enemies left
//g.setColor(Color.white);
//g.drawString("Zombies Left: " + enemiesLeft,5,200);
//g.drawString("Zombies Killed: " + zombiesKilled,5,250);


//loosing screen
if(lose) {
		g.setColor(Color.red);
		g.fillRect(0,0,1000,563);
		g.setColor(Color.white);
		g.drawString("Press the \"Space\" key to restart", 400, 400);
		Font f = new Font("Dialog", Font.PLAIN, 100);
		g.setFont(f);
		g.drawString("YOU ARE DEAD", 100, 300);
		hp = 100;
		xtraAmmo=0;
		mag=0;
	}
//winning screen
if(win) {
		g.setColor(Color.green);
		g.fillRect(0,0,1000,563);
		g.setColor(Color.white);
		g.drawString("Press the \"Space\" key to restart", 400, 400);
		Font f = new Font("Dialog", Font.PLAIN, 100);
		g.setFont(f);
		g.drawString("YOU WON !!!", 200, 300);
		xtraAmmo=0;
		hp = 100;
		mag=0;
		}


//no ammo display
if(mag==0 && xtraAmmo==0 && win == false && lose == false) {
	g.setColor(Color.red);
	g.drawString("No Ammo",500,300);
}

///////////////////////////////////
g.dispose();
bs.show();
}



//level loader
private void loadLevel(BufferedImage image) {
	int w = image.getWidth();
	int h = image.getHeight();
	
	for (int xx=0; xx<w;xx++) {
		for(int yy=0;yy<h;yy++) {
			int pixel = image.getRGB(xx,yy);
			int red = (pixel >> 16) & 0xff;
			int green = (pixel >> 8) & 0xff;
			int blue = (pixel) & 0xff;
			
			if(green == 7 && red == 6 && blue == 255)
				handler.setFirst(new Player(xx*32, yy*32, ID.Player, handler,this,ss));
			
			if(green == 255 && red == 0 && blue == 255)
				handler.addObject(new Crate(xx*32, yy*32, ID.Crate,ss));
			
			if(green == 0 && red == 255 && blue == 0)
				handler.addObject(new MedKit(xx*32, yy*32, ID.MedKit,ss));
			
			if(green == 91 && red ==12 && blue == 172)                                
				handler.addObject(new MedKit(xx*32, yy*32, ID.DamagePerk,ss));            
			
			if(green == 255 && red == 243 && blue == 0)
				handler.addObject(new Spawner(xx*32, yy*32, ID.Spawner, handler,this,ss));
			
			if(green == 0 && red == 255 && blue == 249)
				handler.addObject(new Escape(xx*32, yy*32, ID.Escape,ss));
			
			if(red == 0 && green == 255 && blue == 0)
				handler.addObject(new Block(xx*32, yy*32, ID.Block,ss));
			
			if(red == 245 && green == 0 && blue == 255)
				handler.addObject(new Chairs(xx*32, yy*32, ID.Chairs,ss));
			

		}
	}
}





}

