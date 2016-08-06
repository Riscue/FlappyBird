package Controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import Model.Button;
import Model.Model;
import Model.PipeData;
import Model.Texture;
import View.View;

public class Controller implements ActionListener {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (model.getGameStatus()) {
		case NEWGAME:
			model.getBird().animate();
			model.getBird().flap();
			model.getGround().animate();
			break;
		case PLAYING:
			model.getPipe().animate();
			model.getGround().animate();
			model.getBird().fly();
			detectCollision();
			break;
		case FINISHED:
			model.getBird().fall();
			break;
		}

		updateScreen();
	}

	private void updateScreen() {
		view.updateScreen(model.getGameStatus(), model.getBird(), model.getPipe(), model.getHighscore(),
				model.getScore(), model.getBackground(), model.getBoard(), model.getGround(), model.getRestart());
	}

	private void detectCollision() {
		PipeData pipe = model.getPipe().getPipe();

		Bird bird = model.getBird();

		if (pipe.x + 90 < bird.getX() && !pipe.counted) {
			model.setScore(model.getScore() + 1);
			model.setHighscore(model.getScore() > model.getHighscore() ? model.getScore() : model.getHighscore());
			pipe.counted = true;
		}

		if (bird.getY() > 500) {
			model.setGameStatus(Model.GameStatus.FINISHED);
		}

		Rectangle r = new Rectangle(pipe.x - bird.getWidth() / 3, pipe.y + bird.getHeight(),
				100 + bird.getWidth() * 2 / 3, pipe.length - bird.getHeight() * 2);
		Rectangle b = new Rectangle(bird.getX(), bird.getY(), bird.getWidth() / 3, bird.getHeight());

		if (!b.intersects(r) && r.x < b.x && b.x < r.x + r.width - b.width) {
			model.setGameStatus(Model.GameStatus.FINISHED);
		}
	}

	private void initializeModel() {
		model.setGameStatus(Model.GameStatus.NEWGAME);

		model.setBackground(new Texture("Resources/background.png"));
		model.setBoard(new Texture("Resources/board.png"));

		model.setGround(new Ground("Resources/ground.png"));

		model.setRestart(new Button("Resources/restart.png"));

		model.setBird(new Bird("Resources/bird.png"));
		model.setPipe(new Pipe("Resources/pipe.png"));

		model.getRestart().setFunc(new Runnable() {
			@Override
			public void run() {
				model.setGameStatus(Model.GameStatus.NEWGAME);
			}
		});
		model.getGround().setY(256);
		model.getBoard().setY(-100);
		model.getRestart().setY(50);

		model.setTimer(new Timer(8, this));
		model.getTimer().start();
	}

	public void run() {
		initializeModel();

		view.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (model.getGameStatus() == Model.GameStatus.NEWGAME) {
						model.setGameStatus(Model.GameStatus.PLAYING);
					} else if (model.getGameStatus() == Model.GameStatus.FINISHED) {
						Button restart = model.getRestart();
						if (restart.getX() < e.getX() && e.getX() < restart.getX() + restart.getWidth()
								&& restart.getY() + 25 < e.getY()
								&& e.getY() < restart.getY() + 25 + restart.getHeight())
							NewGame();
					}
					model.getBird().up();
				}
			}
		});
	}

	private void NewGame() {
		if (model.getBird().getY() < 490)
			return;
		model.setScore(0);
		model.setBird(new Bird("Resources/bird.png"));
		model.setPipe(new Pipe("Resources/pipe.png"));
		model.setGameStatus(Model.GameStatus.NEWGAME);
	}
}