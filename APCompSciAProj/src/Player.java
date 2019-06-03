import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

	Handler handler;
	Game game;
	
	private BufferedImage player_image;
	
	public Player(int x, int y, ID id,Handler handler, Game game,SpriteSheet ss) {
		super(x, y, id,ss);
		this.handler = handler;
		this.game=game;
		
		player_image = ss.grabImage(2, 2, 20, 20);
	}

	
	public void tick() {		
		x+=velX;
		y+=velY;
		collision();
		
		
		//movement
		if(handler.isUp()) velY=-3;
		else if(!handler.isDown()) velY=0;
		
		if(handler.isDown()) velY=3;
		else if(!handler.isUp()) velY=0;
		
		if(handler.isRight()) velX=3;
		else if(!handler.isLeft()) velX=0;
		
		if(handler.isLeft()) velX=-3;
		else if(!handler.isRight()) velX=0;
		
	}
	
	
	private void collision() {
		for (int i = 0; i < handler.object.size();i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					//x += velX * -1;
					//y += velY * -1;
					
					
					
					x+=3;
					if(getBounds().intersects(tempObject.getBounds())) {
						x-=3;
						y+=3;
						if(getBounds().intersects(tempObject.getBounds())) {
							y-=3;
							x-=3;
							if(getBounds().intersects(tempObject.getBounds())) {                //new wall collision fix
								x+=3;
								y-=3;
								}
							}
						}
					
					
					
					
				}	
			}
			
			if(tempObject.getId() == ID.Crate) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					game.ammo = 25;
				}	
			}
			
			if(tempObject.getId() == ID.Enemy) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					game.hp-=4;
				}	
			}
		}
	}

	
	public void render(Graphics g) {
		g.drawImage(player_image,x,y,null);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 20);
	}
}
