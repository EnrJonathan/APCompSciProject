import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SpeedBoost extends GameObject {
	
	private BufferedImage speedBoost_image;

	public SpeedBoost(int x, int y, ID id,SpriteSheet ss) {
		super(x, y, id,ss);
		
		speedBoost_image = ss.grabImage(3, 4, 20, 20);

	}

	
	public void tick() {
		
	}

	
	public void render(Graphics g) {
		g.drawImage(speedBoost_image,x,y,null);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x,y,20,20);
	}

}