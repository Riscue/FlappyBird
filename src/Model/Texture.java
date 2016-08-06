package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.net.URL;

import javax.swing.ImageIcon;

public class Texture {
	private ImageIcon image;
	private int x, y;

	public Texture(String imageName) {
		URL imgURL = getClass().getClassLoader().getResource(imageName);
		if (imgURL != null) {
			image = new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + imageName);
		}
		x = getCenter().x;
		y = getCenter().y;
	}

	public Image getImage() {
		return image.getImage();
	}

	public int getWidth() {
		return image.getIconWidth();
	}

	public int getHeight() {
		return image.getIconHeight();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x += x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y += y;
	}

	public Point getLocation() {
		return new Point(x, y);
	}

	public void setLocation(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public void setLocation(Point l) {
		this.x += l.x;
		this.y += l.y;
	}

	public void draw(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), null);
	}

	private Point getCenter() {
		int c_X = (480 - getWidth()) / 2;
		int c_Y = (640 - getHeight()) / 2;
		return new Point(c_X, c_Y);
	}
}