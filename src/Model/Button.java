package Model;

import java.awt.Graphics;

public class Button extends Texture {
	private Runnable r;

	public Button(String t, Runnable r) {
		super(t);
		this.r = r;
	}

	public Button(String t) {
		this(t, null);
	}

	public void run() {
		if (r != null) {
			r.run();
		}
	}

	public void setFunc(Runnable r) {
		this.r = r;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), null);
	}
}