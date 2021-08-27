package com.pingponggame;

import java.awt.*;
import java.util.Random;

public class Enemy  {

    private static final Random random = new Random();
    private static final int SPEED = 3;
    private static final double MULTIPLY_MOVE = 2.5;

    private int enemyVerticalVelocity;
    private Rectangle rectangle;

    public Enemy(int xPosition, int yPosition, int PADDLE_WIDTH, int PADDLE_HEIGHT) {
        this.rectangle = new Rectangle(xPosition, yPosition, PADDLE_WIDTH, PADDLE_HEIGHT);
        initEnemy();
    }

    public void initEnemy() {
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * SPEED);
    }

    public void setYDirection(int randomYDirection) {
        enemyVerticalVelocity = randomYDirection;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public int moveStrategyEasy() {
        return rectangle.y += enemyVerticalVelocity;
    }

    public int moveStrategyMedium() {
        return rectangle.y += enemyVerticalVelocity * MULTIPLY_MOVE;
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
