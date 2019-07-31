
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MedKit extends GameObject {
	
	private BufferedImage med_image;

	public MedKit(int x, int y, ID id,SpriteSheet ss) {
		super(x, y, id,ss);
		
		med_image = ss.grabImage(3, 2, 20, 20);

	}

	
	public void tick() {
		
	}

	
	public void render(Graphics g) {
		g.drawImage(med_image,x,y,null);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x,y,20,20);
	}

}
