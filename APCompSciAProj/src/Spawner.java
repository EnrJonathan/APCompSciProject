import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Spawner extends GameObject {
	
	
	
	private Handler handler;
	Game game;
	private BufferedImage spawner_image;

	public Spawner(int x, int y, ID id,Handler handler, Game game,SpriteSheet ss) {
		super(x, y, id,ss);
		this.handler = handler;
		this.game=game;
		spawner_image = ss.grabImage(1, 2, 20, 20);

	}

	
	public void tick() {
		for(int i = 0; i < handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ID.Spawner && game.myTimer == 4.5 && game.enemiesLeft < 20) {
				handler.addObject(new Enemy(tempObject.x+5, tempObject.y+5,ID.Enemy,handler,game,ss));
				game.enemiesLeft++;
			}

			if(tempObject.getId()==ID.Spawner && game.myTimer == 9.0 && game.enemiesLeft < 20) {
				handler.addObject(new Enemy(tempObject.x+5, tempObject.y+5,ID.Enemy,handler,game,ss));
				game.enemiesLeft++;
			}
		}
			
		

	}

	
	public void render(Graphics g) {
		g.drawImage(spawner_image,x,y,null);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x,y,20,20);
	}

}
