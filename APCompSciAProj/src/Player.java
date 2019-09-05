import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

	Handler handler;
	Game game;
	double knifeTime;
	
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
		if (game.win == false && game.lose == false) {
		if(handler.isUp()) {
			game.inRange = "";
			velY=-2 - game.speedBoost;
		}
		else if(!handler.isDown()) velY=0;
		
		if(handler.isDown()) {
			game.inRange = "";
			velY=2 + game.speedBoost;
		}
		else if(!handler.isUp())velY=0;
		
		if(handler.isRight()) {
			game.inRange = "";
			velX=2 + game.speedBoost;
		}
		else if(!handler.isLeft()) velX=0;
		
		if(handler.isLeft()) {
			game.inRange = "";
			velX=-2 - game.speedBoost;
		}
		else if(!handler.isRight()) velX=0;
	}
		
		
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
		
		//knifing
				if(handler.isKnife()) {
				 knifeTime = game.myTimer;
					for(int i = 0; i < handler.object.size();i++) {
						GameObject tempObject = handler.object.get(i);
						
						if(tempObject.getId()==ID.Player) {
							handler.addObject(new Knife(tempObject.getX()-20, tempObject.getY()-20,ID.Knife,handler,x,y,ss,game));
						}
						if(tempObject.getId()==ID.Knife) {
							                                    //add some sort of timer to remove the knife
							handler.removeObject(tempObject);

						}
					}                               
				}
		
		//restarting
				if(handler.isReset() && (game.win == true||game.lose == true)) {
				x = 160;
				y = 128;
				game.multy = 1;
				game.speedBoost = 0;
				game.wave = 1;
				game.hp = 100;
				game.points = 0;
				game.lose = false;
				game.win = false;
				game.mag = 12;
				game.xtraAmmo = 12;
				game.enemiesLeft = 0;
				game.zombiesKilled = 0;
				game.myTimer = 0.0;
				game.inRange = "";
				}
				
				
		//interaction for ammo or escape
		if(handler.isInteract()) {                                                     // for ammo
			if(game.inRange == "Press E to buy Ammo (Cost:150)") {
				if(game.points >= 150 && game.xtraAmmo < 36) {
					game.xtraAmmo = 36;
					game.points -= 150;
				}
			}else if(game.inRange == "Press E to buy your Freedom (Cost:5000)") {              // for escape later on
				if(game.points >= 5000) {
				game.inRange = "";
				game.points -= 5000;
				game.win = true;
				x = 160;
				y = 128;
				}
			}else if(game.inRange == "Press E to buy Damage Perk (Cost:1000)"){          //for damage perk
				if(game.points >= 1000 && !(game.ifBoughtDamagePerk == game.wave)) {
					game.multy++;
					game.points-=1000;
					game.ifBoughtDamagePerk = game.wave;
					}
			}else if(game.inRange == "Press E to buy Speed Boost (Cost:750)"){            //for Speed Boost
				if(game.points >= 750 && !(game.ifBoughtSpeedBoost == game.wave)) {
					game.speedBoost++;
					game.points-=1000;
					game.ifBoughtSpeedBoost = game.wave;
					}
			}else if(game.inRange == "Press E to heal yourself(Cost:200)"){             //for med-kits
				if(game.points >= 200  && game.hp < 100) {
					game.hp = 100;
					game.points -= 200;
				}
			}
			
		}
		
		
		
		
		
		if(game.zombiesKilled >= (game.wave*5) && game.enemiesLeft == 0) {
			game.zombiesKilled = 0;                                               //waves
			game.wave++;
			
			
		}
		
		
		
		
		
		

		
	}
	
	
	
	
	
	private void collision() {
		for (int i = 0; i < handler.object.size();i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block|| tempObject.getId()==ID.Chairs) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					//x += velX * -1;
					//y += velY * -1;
					
					
					
					x+=2 + game.speedBoost;
					if(getBounds().intersects(tempObject.getBounds())) {
						x-=2 + game.speedBoost;
						y+=2 + game.speedBoost;
						if(getBounds().intersects(tempObject.getBounds())) {
							y-=2 + game.speedBoost;
							x-=2 + game.speedBoost;
							if(getBounds().intersects(tempObject.getBounds())) {      //new wall collision fix
								x+=2 + game.speedBoost;
								y-=2 + game.speedBoost;
								}
							}
						}
					
					
					
					
				}	
			}
			
			if(tempObject.getId() == ID.Crate) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(game.xtraAmmo == 36) {
						game.inRange = "You have Max Ammo";
					}else {
						game.inRange = "Press E to buy Ammo (Cost:150)";
					}
				}
			}
			
			if(tempObject.getId() == ID.Escape) {
				if (getBounds().intersects(tempObject.getBounds())) {
					game.inRange = "Press E to buy your Freedom (Cost:5000)";
				}
			}
			
			if(tempObject.getId() == ID.MedKit) {
				if (getBounds().intersects(tempObject.getBounds())) {
					game.inRange = "Press E to heal yourself(Cost:200)";
				}
			}
			if(tempObject.getId() == ID.DamagePerk) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(game.ifBoughtDamagePerk == game.wave) {
						game.inRange = "Out Of order till next round";
					}else{
						if(game.multy == 10) {
							game.inRange = "You've reached max damage!";
						}else {
						game.inRange = "Press E to buy Damage Perk (Cost:1000)";
						}

					}
				}
			}
			if(tempObject.getId() == ID.SpeedBoost) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(game.ifBoughtSpeedBoost == game.wave) {
						game.inRange = "Out Of order till next round";
					}else{
						if(game.speedBoost == 2) {
							game.inRange = "You've reached max speed!";
						}else {
						game.inRange = "Press E to buy Speed Boost (Cost:750)";
						}

					}
				}
			}
				
			if(tempObject.getId() == ID.Enemy) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					game.hp-=2;                                         //damage to player
					if(game.hp <= 0) {
						game.lose = true;
						x = 160;
						y = 128;
					}
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
