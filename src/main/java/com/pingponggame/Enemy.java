package com.pingponggame;

import java.awt.*;
import java.util.Random;

public class Enemy implements EnemyInterface {

    private static final Random random = new Random();
    private static final int speed = 3;
    private static final double MULTIPLY_ENEMY_MOVE = 2.5;

    private int enemyVerticalVelocity;
    private Rectangle rectangle;

    public Enemy(int xPosition, int yPosition, int PADDLE_WIDTH, int PADDLE_HEIGHT) {
        this.rectangle = new Rectangle(xPosition, yPosition, PADDLE_WIDTH, PADDLE_HEIGHT);
        initEnemy();
    }
    @Override
    public void initEnemy() {
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * speed);
    }

    @Override
    public void setYDirection(int randomYDirection) {
        enemyVerticalVelocity = randomYDirection;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public int moveStrategyEasy() {
        return rectangle.y += enemyVerticalVelocity;
    }

    public int moveStrategyMedium() {
        return rectangle.y += enemyVerticalVelocity * MULTIPLY_ENEMY_MOVE;
    }

    public int getVerticalPos() {
        return rectangle.y;
    }

    public void setVerticalPos(int pos) {
        rectangle.y = pos;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getVelocity() {
        return enemyVerticalVelocity;
    }
}
