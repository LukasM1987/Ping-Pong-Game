package com.pingponggame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class MultiPlayer extends GUIState implements SkirmishInterface {

    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_DIAMETER = 20;
    private static final int MAX_POINTS = 11;
    private static final int GAME_MODE = 2;
    private static final File backgroundFile = new File("src/main/resources/static/pongTableBlue.jpg");
    private static final Random random = new Random();


    private BufferedImage backgroundImage;
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Scores scores =  new Scores();

    public MultiPlayer(GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        init();
    }

    @Override
    public void init() {
        newPaddles();
        newBall();
        try {
            backgroundImage = ImageIO.read(backgroundFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void newPaddles() {
        paddle1 = new Paddle(0, (GameEngine.HEIGHT / 2) - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, GAME_MODE, 1);
        paddle2 = new Paddle(GameEngine.WIDTH - PADDLE_WIDTH, (GameEngine.HEIGHT / 2) - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, GAME_MODE, 2);

    }

    @Override
    public void newBall() {
        ball = new Ball((GameEngine.WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GameEngine.HEIGHT - BALL_DIAMETER - 2) + 1, BALL_DIAMETER, BALL_DIAMETER);
    }

    public void checkBallPaddleCollision() {
        if (ball.intersects(paddle1.getRectangle())) {
            ball.setHorizontalVelocity(Math.abs(ball.getXVelocity()));

            ball.increaseHorizontalVelocity();
            if (ball.getYVelocity() > 0) {
                ball.increaseVerticalVelocity();
            } else {
                ball.reduceVerticalVelocity();
            }
            ball.setXDirection(ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }

        if (ball.intersects(paddle2.getRectangle())) {
            ball.setHorizontalVelocity(Math.abs(ball.getXVelocity()));
            ball.increaseHorizontalVelocity();
            if (ball.getYVelocity() > 0) {
                ball.increaseVerticalVelocity();
            } else {
                ball.reduceVerticalVelocity();
            }
            ball.setXDirection(-ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }
    }

    @Override
    public void checkBallFrameCollision() {
        if (ball.getVerticalPos() <= 0) {
            ball.setYDirection(-ball.getYVelocity());
        }

        if (ball.getVerticalPos() >= GameEngine.HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.getYVelocity());
        }
    }

    @Override
    public void checkPaddleFrameCollision() {

        if (paddle1.getVerticalPos() <= 0) {
            paddle1.setVerticalPos(0);
        }

        if (paddle1.getVerticalPos() >= (GameEngine.HEIGHT - PADDLE_HEIGHT)) {
            paddle1.setVerticalPos(GameEngine.HEIGHT - PADDLE_HEIGHT);
        }

        if (paddle2.getVerticalPos() <= 0) {
            paddle2.setVerticalPos(0);
        }

        if (paddle2.getVerticalPos() >= (GameEngine.HEIGHT - PADDLE_HEIGHT)) {
            paddle2.setVerticalPos(GameEngine.HEIGHT - PADDLE_HEIGHT);
        }
    }

    @Override
    public void givePlayerPoint() {
        if (ball.getHorizontalPos() + BALL_DIAMETER < 0) {
            newBall();
            newPaddles();
            scores.scorePlayer2++;
            if (scores.scorePlayer2 == MAX_POINTS) {
                checkSetWinner();
                scores.scorePlayer1 = 0;
                scores.scorePlayer2 = 0;
            }
        }

        if (ball.getHorizontalPos() > GameEngine.WIDTH) {
            newBall();
            newPaddles();
            scores.scorePlayer1++;
            if (scores.scorePlayer1 == MAX_POINTS) {
                checkSetWinner();
                scores.scorePlayer1 = 0;
                scores.scorePlayer2 = 0;
            }
        }
    }

    @Override
    public void addPlayer1Score() {
        Scores.player1Scores.add(scores.scorePlayer1);
    }

    @Override
    public void addPlayer2Score() {
        Scores.player2Scores.add(scores.scorePlayer2);
    }

    @Override
    public void checkSetWinner() {
        if (scores.scorePlayer1 == MAX_POINTS) {
            Scores.player1Scores.add(scores.scorePlayer1);
            Scores.player2Scores.add(scores.scorePlayer2);
            Scores.setWinPlayer1++;
        }

        if (scores.scorePlayer2 == MAX_POINTS) {
            Scores.player1Scores.add(scores.scorePlayer1);
            Scores.player2Scores.add(scores.scorePlayer2);
            Scores.setWinPlayer2++;
        }
    }

    @Override
    public void checkWhoWinSkirmish() {
        if (Scores.setWinPlayer1 == Sets.TWO_WINS.getSetWins() && Scores.setWinPlayer2 == Sets.ZERO_WINS.getSetWins()) {
            GUIStateManager.setStates(GUIStateManager.STATISTICS);
        } else if (Scores.setWinPlayer2 == Sets.TWO_WINS.getSetWins() && Scores.setWinPlayer1 == Sets.ZERO_WINS.getSetWins()) {
            GUIStateManager.setStates(GUIStateManager.STATISTICS);
        } else if (Scores.setWinPlayer1 == Sets.TWO_WINS.getSetWins() && Scores.setWinPlayer2 == Sets.ONE_WIN.getSetWins()) {
            GUIStateManager.setStates(GUIStateManager.STATISTICS);
        } else if (Scores.setWinPlayer2 == Sets.TWO_WINS.getSetWins() && Scores.setWinPlayer1 == Sets.ONE_WIN.getSetWins()) {
            GUIStateManager.setStates(GUIStateManager.STATISTICS);
        }
    }

    @Override
    public void update() {
        paddle1.move();
        paddle2.move();
        ball.move();
        checkPaddleFrameCollision();
        checkBallPaddleCollision();
        checkBallFrameCollision();
        givePlayerPoint();
        checkWhoWinSkirmish();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        scores.draw(g);
    }

    @Override
    public void onKeyPressed(KeyEvent key) {
        paddle1.movePaddle(key);
        paddle2.movePaddle(key);
    }

    @Override
    public void onKeyReleased(KeyEvent key) {
        paddle1.stopPaddle(key);
        paddle2.stopPaddle(key);
    }
}
