import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Knife extends GameObject{
	
	private Handler handler;
	Game game;

	public Knife(int x, int y, ID id, Handler handler,int mx,int my,SpriteSheet ss,Game game) {
		super(x, y, id,ss);
		this.handler=handler;
		this.game = game;		
	}

	public void tick() {		
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillOval(x, y,60,60);
	}

	public Rectangle getBounds() {
		return new Rectangle (x,y,60,60);
	}

}
