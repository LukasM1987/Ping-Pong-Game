package com.pingponggame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SinglePlayer extends GUIState implements SkirmishInterface {

    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_DIAMETER = 20;
    private static final int MAX_POINTS = 11;
    private static final int FIELD_HEIGHT = 124;
    private static final int FIELD_WIDTH = 1;
    private static final int GAME_MODE = 1;
    private static final int BALL_VERTICAL_POSITION_INDEX = 0;
    private static final int SPEED_UP_BALL_WHEN_DIFFICULTY_IS_HIGHER_THEN_55 = 55;
    private static final List<Integer> ballVerticalYPosition = new ArrayList<>();
    private static final File backgroundFile = new File("src/main/resources/static/pongTable.jpg");
    private static final Random random = new Random();
    private static final Field field = new Field((GameEngine.WIDTH / 4) * 3 - 20, GameEngine.HEIGHT / 2 - FIELD_HEIGHT / 2, FIELD_WIDTH, FIELD_HEIGHT);

    private int ballTouchPaddle;
    private int randomEnemyMistakeSecondStrategy;
    private int randomEnemyMistake;
    private int randomDifficultyStrategy;
    private BufferedImage backgroundImage;
    private Paddle player;
    private Enemy enemy;
    private Ball ball;
    private Scores scores;

    public SinglePlayer(GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        this.scores = new Scores();
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
    public void update() {
        player.move();
        ball.move();
        enemyStrategy();
        checkBallFrameCollision();
        checkPaddleFrameCollision();
        checkBallPaddleCollision();
        givePlayerPoint();
        giveEnemyPoint();
        checkSetWinner();
        checkWhoWinSkirmish();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        field.draw(g);
        player.draw(g);
        enemy.draw(g);
        ball.draw(g);
        scores.draw(g);
    }

    @Override
    public void onKeyPressed(KeyEvent key) {
        player.movePaddle(key);
    }

    @Override
    public void onKeyReleased(KeyEvent key) {
        player.stopPaddle(key);
    }

    @Override
    public void checkPaddleFrameCollision() {
        if (player.getVerticalPos() <= 0) {
            player.setVerticalPos(0);
        }

        if (player.getVerticalPos() >= GameEngine.HEIGHT - PADDLE_HEIGHT) {
            player.setVerticalPos(GameEngine.HEIGHT - PADDLE_HEIGHT);
        }
    }

    @Override
    public void checkBallPaddleCollision() {
        checkPlayerCollision();
        checkEnemyCollision();
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

    public int enemyIntersectsUpperFrameEdge() {
        return PADDLE_HEIGHT / 2 - BALL_DIAMETER / 2 - 1;
    }

    public int enemyIntersectsLowerFrameEdge() {
        return GameEngine.HEIGHT - PADDLE_HEIGHT / 2 - BALL_DIAMETER / 2  + 2;
    }

    public boolean setRoundDifficulty(int enemyMistake, int setDifficulty) {
        if (enemyMistake < setDifficulty) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void newPaddles() {
        player = new Paddle(0, (GameEngine.HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, GAME_MODE, 0);
        enemy = new Enemy(GameEngine.WIDTH - PADDLE_WIDTH, (GameEngine.HEIGHT / 2) - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        randomEnemyStrategy();
    }

    @Override
    public void newBall() {
        ball = new Ball((GameEngine.WIDTH / 2) - (BALL_DIAMETER / 2), (GameEngine.HEIGHT / 2 - BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER);
        ballTouchPaddle = 0;
        clearBallVerticalPositionDifficultFailStrategy();
    }

    @Override
    public void givePlayerPoint() {
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
    public void checkSetWinner() {
        if (scores.scorePlayer1 == MAX_POINTS) {
            addPlayer1Score();
            addPlayer2Score();
            Scores.setWinPlayer1++;
        }

        if (scores.scorePlayer2 == MAX_POINTS) {
            addPlayer1Score();
            addPlayer2Score();
            Scores.setWinPlayer2++;
        }
    }

    public void addPlayer1Score() {
        Scores.player1Scores.add(scores.scorePlayer1);
    }

    public void addPlayer2Score() {
        Scores.player2Scores.add(scores.scorePlayer2);
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

    private void giveEnemyPoint() {
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
    }

    private void randomEnemyStrategy() {
        randomEnemyMistake = random.nextInt(100) + 1;
        randomDifficultyStrategy = random.nextInt(3) + 1;
        randomEnemyMistakeSecondStrategy = random.nextInt(3) + 5;
    }

    private void checkEnemyCollision() {
        if (ball.intersects(enemy.getRectangle())) {
            ball.setHorizontalVelocity(Math.abs(ball.getXVelocity()));
            if (DifficultyMenu.difficultyPercent >= SPEED_UP_BALL_WHEN_DIFFICULTY_IS_HIGHER_THEN_55) {
                ball.increaseHorizontalVelocity();
                if (ball.getYVelocity() > 0) {
                    ball.increaseVerticalVelocity();
                } else {
                    ball.reduceVerticalVelocity();
                }
            }
            ball.setXDirection(-ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }
    }

    private void checkPlayerCollision() {
        if (ball.intersects(player.getRectangle())) {
            ball.setHorizontalVelocity(Math.abs(ball.getXVelocity()));
            if (DifficultyMenu.difficultyPercent >= SPEED_UP_BALL_WHEN_DIFFICULTY_IS_HIGHER_THEN_55) {
                ball.increaseHorizontalVelocity();
                if (ball.getYVelocity() > 0) {
                    ball.increaseVerticalVelocity();
                } else {
                    ball.reduceVerticalVelocity();
                }
            }
            ball.setXDirection(ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }
    }

    private void enemyEasyStrategy() {
        enemy.setVerticalPos(enemy.moveStrategyEasy());
        if (enemy.getVerticalPos() <= 0) {
            enemy.setYDirection(-enemy.getVelocity());
        }
        if (enemy.getVerticalPos() >= enemyStrategyStopPosition()) {
            enemy.setYDirection(-enemy.getVelocity());
        }
    }

    private void enemyMediumStrategy() {
        enemy.setVerticalPos(enemy.moveStrategyMedium());
        if (enemy.getVerticalPos() <= 0) {
            enemy.setYDirection(-enemy.getVelocity());
        }
        if (enemy.getVerticalPos() >= enemyStrategyStopPosition()) {
            enemy.setYDirection(-enemy.getVelocity());
        }
    }

    private void enemyDifficultStrategyWithFail() {
        if (DifficultyMenu.difficultyPercent >= SPEED_UP_BALL_WHEN_DIFFICULTY_IS_HIGHER_THEN_55) {
            if (ball.getVerticalPos() <= enemyIntersectsUpperFrameEdge() || ball.getVerticalPos() >= enemyIntersectsLowerFrameEdge()) {
                enemy.setYDirection(0);
            } else {
                enemy.setVerticalPos(enemyDifficultStrategyFailVerticalPosition());
                addValueWhenBallTouchPlayer();
                addBallVerticalPosition();
            }
            setEnemyMistakeVerticalPosition();
            clearBallVerticalPosition();
            setFieldPosition();
        } else {
            enemyMediumStrategy();
        }
    }

    private void clearBallVerticalPosition() {
        if (ball.getHorizontalPos() < stopEnemyLine()) {
        clearBallVerticalPositionDifficultFailStrategy();

        }
    }

    private void setFieldPosition() {
        if (ball.getHorizontalPos() >= stopEnemyLine()) {
            field.setHorizontalPos(temporaryFieldPosition());
        } else {
            field.setHorizontalPos(basicFieldPosition());
        }
    }

    private void setEnemyMistakeVerticalPosition() {
        if (ball.getHorizontalPos() >= stopEnemyLine() && !ballVerticalYPosition.isEmpty()) {
            enemy.setVerticalPos(enemyStopPosition(BALL_VERTICAL_POSITION_INDEX));
        }
    }

    private void addBallVerticalPosition() {
        if (ballTouchPaddle >= randomEnemyMistakeSecondStrategy && ball.intersects(field.getRectangle())) {
            ballVerticalYPosition.add(ball.getVerticalPos());
        }
    }

    private void addValueWhenBallTouchPlayer() {
        if (ball.intersects(player.getRectangle())) {
            ballTouchPaddle++;
        }
    }

    private void enemyDifficultStrategy() {
        if (ball.getVerticalPos() <= enemyIntersectsUpperFrameEdge() || ball.getVerticalPos() >= enemyIntersectsLowerFrameEdge()) {
            enemy.setYDirection(0);
        } else {
            enemy.setVerticalPos(enemyDifficultStrategyFailVerticalPosition());
        }
    }

    private void enemyStrategy() {

        if (setRoundDifficulty(randomEnemyMistake, DifficultyMenu.difficultyPercent)) {
            if (randomDifficultyStrategy == EnemyStrategy.HARD_STRATEGY_WITHOUT_FAIL.getStrategyCase()) {
                enemyDifficultStrategy();
            } else if (randomDifficultyStrategy == EnemyStrategy.HARD_STRATEGY_WITH_FAIL.getStrategyCase()) {
                enemyDifficultStrategyWithFail();
            } else if (randomDifficultyStrategy == EnemyStrategy.MEDIUM_STRATEGY.getStrategyCase()) {
                enemyMediumStrategy();
            }
        } else {
            enemyEasyStrategy();
        }
    }

    private void clearBallVerticalPositionDifficultFailStrategy() {
        ballVerticalYPosition.clear();
    }

    private int enemyDifficultStrategyFailVerticalPosition() {
        return ball.getVerticalPos() - (PADDLE_HEIGHT / 2) + (BALL_DIAMETER / 2);
    }

    private int temporaryFieldPosition() {
        return GameEngine.WIDTH / 2 + 10;
    }

    private int basicFieldPosition() {
        return (GameEngine.WIDTH / 4) * 3 - 20;
    }

    private int stopEnemyLine() {
        return (GameEngine.WIDTH / 4 * 3) - 40;
    }

    private int enemyStopPosition(int index) {
        return ballVerticalYPosition.get(index) - PADDLE_HEIGHT / 2 + BALL_DIAMETER / 2;
    }

    private int enemyStrategyStopPosition() {
        return GameEngine.HEIGHT - PADDLE_HEIGHT;
    }
}