package Controller;

import java.awt.Graphics;

import Model.Texture;

public class Ground extends Texture {
	private int animationIndex;

	public Ground(String t) {
		super(t);
	}

	public void animate() {
		animationIndex = (animationIndex + 2) % getWidth();
	}

	@Override
	public void draw(Graphics g) {

		for (int i = 0; i < 480 + getWidth(); i += getWidth()) {
			g.drawImage(getImage(), i - animationIndex, getY(), null);
		}

	}
}