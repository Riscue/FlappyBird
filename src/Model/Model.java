package Model;

import javax.swing.Timer;

import Controller.Bird;
import Controller.Ground;
import Controller.Pipe;

public class Model {
	public static enum GameStatus {
		NEWGAME, PLAYING, FINISHED
	}
	private GameStatus gameStatus;
	private Bird bird;
	private Pipe pipe;
	private int highscore = 0;
	private int score = 0;

	private Texture background, board;
	private Ground ground;
	private Button restart;

	private Timer timer;

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Bird getBird() {
		return bird;
	}

	public void setBird(Bird bird) {
		this.bird = bird;
	}

	public Pipe getPipe() {
		return pipe;
	}

	public void setPipe(Pipe pipe) {
		this.pipe = pipe;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Texture getBackground() {
		return background;
	}

	public void setBackground(Texture background) {
		this.background = background;
	}

	public Texture getBoard() {
		return board;
	}

	public void setBoard(Texture board) {
		this.board = board;
	}

	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}

	public Button getRestart() {
		return restart;
	}

	public void setRestart(Button restart) {
		this.restart = restart;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer2) {
		this.timer = timer2;
	}
}