import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Escape extends GameObject {
	
	private BufferedImage escape_image;

	public Escape(int x, int y, ID id,SpriteSheet ss) {
		super(x, y, id,ss);
		
		escape_image = ss.grabImage(3, 1, 20, 20);

	}

	
	public void tick() {
		
	}

	
	public void render(Graphics g) {
		g.drawImage(escape_image,x,y,null);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x,y,20,20);
	}

}