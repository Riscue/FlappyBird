package Controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import Model.PipeData;
import Model.Texture;

public class Pipe {
	private Texture image;
	private ArrayList<PipeData> pipes = new ArrayList<PipeData>();
	private int counter = 0;
	private Random rnd = new Random();

	public Pipe(String name) {
		image = new Texture(name);
		addPipe();
	}

	public void animate() {
		for (PipeData pipe : pipes) {
			pipe.x -= 2;
		}

		if (counter++ > 200) {
			addPipe();
			counter = 0;
		}
	}

	public void addPipe() {
		// 70 410
		pipes.add(new PipeData(rnd.nextInt(250) + 70, 150));
	}

	public void draw(Graphics g) {
		for (PipeData pipe : pipes) {
			g.drawImage(image.getImage(), pipe.x, pipe.y + pipe.length, null);
			g.drawImage(image.getImage(), pipe.x, pipe.y, pipe.x + image.getWidth(), pipe.y - image.getHeight(), 0, 0,
					image.getWidth(), image.getHeight(), null);
		}
	}

	public PipeData getPipe() {
		if (pipes.size() < 1)
			return null;
		while (pipes.get(0).x < -100) {
			pipes.remove(0);
		}
		return pipes.get(0);
	}
}