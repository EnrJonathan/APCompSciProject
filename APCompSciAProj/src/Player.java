import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		if(handler.isUp()) {
			game.inRangeForAmmo = "";
			velY=-3;
		}
		else if(!handler.isDown()) velY=0;
		
		if(handler.isDown()) {
			game.inRangeForAmmo = "";
			velY=3;
		}
		else if(!handler.isUp())velY=0;
		
		if(handler.isRight()) {
			game.inRangeForAmmo = "";
			velX=3;
		}
		else if(!handler.isLeft()) velX=0;
		
		if(handler.isLeft()) {
			game.inRangeForAmmo = "";
			velX=-3;
		}
		else if(!handler.isRight()) velX=0;
		
		
		//reloading
		if(handler.isReload()) {
			int temp = (game.mag - 12) * -1;
			if(game.xtraAmmo >= temp) {
				game.xtraAmmo -= temp;
				game.mag += temp;
			}else{
				game.mag += game.xtraAmmo;
				game.xtraAmmo = 0;
			}
		}
		
		
		//interaction for ammo or escape
		if(handler.isInteract()) {               // for ammo
			if(game.inRangeForAmmo == "Press E to buy Ammo (Cost:150)") {
				if(game.points >= 150) {
					game.xtraAmmo = 36;
					game.points -= 150;
				}
				
			}else if(true) {              // for escape
				
			}else {
				
			}

		}
		
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
							if(getBounds().intersects(tempObject.getBounds())) {      //new wall collision fix
								x+=3;
								y-=3;
								}
							}
						}
					
					
					
					
				}	
			}
			
			if(tempObject.getId() == ID.Crate) {
				if (getBounds().intersects(tempObject.getBounds()) && game.xtraAmmo < 36) {
					game.inRangeForAmmo = "Press E to buy Ammo (Cost:150)";
				}
				if(game.xtraAmmo == 36) {
					game.inRangeForAmmo = "";
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
