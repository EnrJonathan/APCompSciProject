import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	
	private Handler handler;
	Game game;

	public Bullet(int x, int y, ID id, Handler handler,int mx,int my,SpriteSheet ss,Game game) {
		super(x, y, id,ss);
		this.handler=handler;
		this.game = game;
		
		
		velX = (mx-x)/10;
		velY = (my-y)/10;
		
		
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			
			if(tempObject.getId()==ID.Block || tempObject.getId()==ID.Chairs) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 5 + game.multy, 5 + game.multy);
	}

	public Rectangle getBounds() {
		return new Rectangle (x,y,5 + game.multy,5 + game.multy);
	}

}
