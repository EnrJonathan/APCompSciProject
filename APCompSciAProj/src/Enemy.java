import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.tools.DocumentationTool.Location;

public class Enemy extends GameObject{
	
	private Handler handler;
	int hp = 100;
	Game game;
	Random r = new Random();
	
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
		
		for(int i = 0; i < handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			///I MADE THIS PART OF THE CODEEEEEE///
			
			if(tempObject.getId()==ID.Block) {
				if(getBoundsBig().intersects(tempObject.getBounds())) {
				//	x+=(velX*4)*-1;
				//	y+=(velY*4)*-1;   
				//	velX*=-1;                                                                     //old wall collision fix
				//	velY*=-1;
					
					
					x+=5;
					if(getBoundsBig().intersects(tempObject.getBounds())) {
						x-=5;
						y+=5;
						if(getBoundsBig().intersects(tempObject.getBounds())) {
							y-=5;
							x-=5;
							if(getBoundsBig().intersects(tempObject.getBounds())) {                //new wall collision fix
								x+=5;
								y-=5;
								}
							}
						}
					
					
					
				}
			}else {
				
				GameObject tempObject1 = handler.object.get(0);						
					
				if (x < tempObject1.getX()) {
					velX=(r.nextInt(3)+1);
				}
				if (x > tempObject1.getX()) {
					velX=-1 * (r.nextInt(3)+1);					                         //make it so that zombie follows player
					}
				if (y < tempObject1.getY()) {
					velY=(r.nextInt(3)+1);
				}
				if (y > tempObject1.getY()) {
					velY=-1 * (r.nextInt(3)+1);					
				}		
				/////////////////////////////////////////
			}     

	        		                                                 
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
		return new Rectangle (x-5,y-5,30,30);
	}

}
