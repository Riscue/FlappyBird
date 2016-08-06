package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Bird;
import Controller.Ground;
import Controller.Pipe;
import Model.Button;
import Model.Model.GameStatus;
import Model.Texture;

public class View extends JFrame {
	private static final long serialVersionUID = -8215973777232473220L;
	private JPanel panel;

	private GameStatus gameStatus;
	private Bird bird;
	private Pipe pipe;
	private int highscore = 0;
	private int score = 0;

	private Texture background, board;
	private Ground ground;
	private Button restart;

	public View() {
		setSize(480, 640);
		setVisible(true);
		setResizable(false);
		setFocusable(true);
		setTitle("Flappy Bird");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			private static final long serialVersionUID = 8237064153083890636L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (gameStatus == null || bird == null || pipe == null || background == null || board == null
						|| ground == null || restart == null)
					return;

				background.draw(g);
				bird.draw(g);
				pipe.draw(g);

				if (gameStatus == GameStatus.FINISHED) {
					board.draw(g);
					restart.draw(g);
					g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, g.getFont().getSize() + 20));
					g.drawString(score + "", 235, 210);
					g.drawString(highscore + "", 235, 270);
				} else if (gameStatus == GameStatus.PLAYING) {
					g.setColor(Color.WHITE);
					g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, g.getFont().getSize() + 40));
					g.drawString(score + "", 220, 100);
				}

				ground.draw(g);
			}
		};
		getContentPane().add(panel);
	}

	public void updateScreen(GameStatus gameStatus, Bird bird, Pipe pipe, int highscore, int score, Texture background,
			Texture board, Ground ground, Button restart) {
		this.gameStatus = gameStatus;
		this.bird = bird;
		this.pipe = pipe;
		this.highscore = highscore;
		this.score = score;
		this.background = background;
		this.board = board;
		this.ground = ground;
		this.restart = restart;

		panel.repaint();
	}
}
