import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.tools.DocumentationTool.Location;

public class Enemy extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	int choose = 0;
	int hp = 100;
	Game game;
	
	private BufferedImage enemy_image;


	public Enemy(int x, int y, ID id, Handler handler,Game game,SpriteSheet ss) {
		super(x, y, id,ss);
		this.handler = handler;
		this.game=game;
		
		enemy_image = ss.grabImage(2, 1, 20, 20);

	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		choose = r.nextInt(10);
		
		
		for(int i = 0; i < handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
		/*	if(tempObject.getId()==ID.Block) {
				if(getBoundsBig().intersects(tempObject.getBounds())) {
					x+=(velX*2)*-1;
					y+=(velY*2)*-1;                                                            //make it so that zombi follows player
					velX*=-1;
					velY*=-1;
				}else if (choose == 0) {
					velX = (r.nextInt(2 - -2)+ -2);
					velY = (r.nextInt(2 - -2)+ -2);	
			}   */  


	        		                                                 
			if(tempObject.getId()==ID.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
				hp-=50;
				handler.removeObject(tempObject);
				}
			}		
		}
		if(hp <= 0) {
			handler.removeObject(this);
		game.enemiesLeft--;
		}

	}

	public void render(Graphics g) {
		g.drawImage(enemy_image,x,y,null);

		
		
	}

	public Rectangle getBounds() {
		return new Rectangle (x,y,20,20);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle (x-10,y-10,50,50);
	}

}
