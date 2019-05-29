import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	Handler handler;
	
	
	public Player(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	
	public void tick() {		
		x+=velX;
		y+=velY;
		
		if(handler.isUp()) velY=-3;
		else if(!handler.isDown()) velY=0;
		
		
		if(handler.isDown()) velY=3;
		else if(!handler.isUp()) velY=0;
		
		if(handler.isRight()) velX=3;
		else if(!handler.isLeft()) velX=0;
		
		if(handler.isLeft()) velX=-3;
		else if(!handler.isRight()) velX=0;
	}

	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 20, 20);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 19, 19);
	}
}
