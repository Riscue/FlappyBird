package Controller;

import java.awt.Graphics;
import java.awt.Graphics2D;

import Model.Texture;

public class Bird extends Texture {

	private final int JUMP_SPEED = 5;
	private final int GRAVITY = 1;
	private int animationIndex = 1, animationCount;
	private boolean DIRECTION = true;
	private int lastY;
	private int speed = 0;
	private double angle = 0;
	private int GRAVITY_COUNT = 0;
	private double angleVelo;

	public Bird(String bird) {
		super(bird);
		lastY = super.getY();
	}

	public void animate() {
		if (getY() < lastY - 50) {
			DIRECTION = false;
		} else if (getY() > lastY) {
			DIRECTION = true;
		}

		setY(DIRECTION ? -1 : 1);
	}

	public void flap() {
		if (animationCount == 0) {
			animationIndex = (animationIndex + 1) % 3;
		}
		animationCount = (animationCount + 1) % 10;
	}

	public void fly() {
		flap();
		if (speed < JUMP_SPEED) {
			if (GRAVITY_COUNT++ % 6 == 0) {
				speed += GRAVITY;
			}
			if (angle < 25)
				angle += angleVelo;
		} else
			rotate();
		setY(speed);
	}

	public void rotate() {
		if (angle > -90)
			angle -= 5;
	}

	// jumping
	public void up() {
		speed = -JUMP_SPEED;
		angleVelo = (25 - angle) / 25;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.translate(getX() + getWidth() / 6, getY() + getHeight() / 2);
		g2d.rotate(Math.toRadians(-angle));
		g2d.translate(-getWidth() / 6, -getHeight() / 2);
		g2d.drawImage(getImage(), 0, 0, getWidth() / 3, getHeight(), animationIndex * getWidth() / 3, 0,
				(animationIndex + 1) * getWidth() / 3, getHeight(), null);
		g2d.translate(getWidth() / 6, getHeight() / 2);
		g2d.rotate(Math.toRadians(angle));
		g2d.translate(-getX() - getWidth() / 6, -getY() - getHeight() / 2);
	}

	public void fall() {
		angle = -90;
		if (getY() < 501) {
			setY(10);
		}
	}
}
