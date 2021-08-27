package com.pingponggame;;
import java.awt.*;
import java.util.Random;

public class Ball {

    private static final Random random = new Random();
    private static int INITIAL_SPEED = 2;

    private int horizontalVelocity;
    private int verticalVelocity;

    private Rectangle rectangle;

    public Ball(int xPosition, int yPosition, int xSize, int ySize) {
        this.rectangle = new Rectangle(xPosition, yPosition, xSize, ySize);
        initBall();
    }

    public void initBall() {
        int randomXDirection = random.nextInt(2);
        int randomYDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }

        setXDirection(randomXDirection * INITIAL_SPEED);

        if (randomYDirection == 0) {
            randomYDirection--;
        }

        setYDirection(randomYDirection * INITIAL_SPEED);
    }

    public void setXDirection(int randomXDirection) {
        horizontalVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        verticalVelocity = randomYDirection;
    }

    public void move() {
        rectangle.x += horizontalVelocity;
        rectangle.y += verticalVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(241, 144, 39));
        g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public boolean intersects(Rectangle rectangle) {
        return this.rectangle.intersects(rectangle);
    }

    public int getVerticalPos() {
        return rectangle.y;
    }

    public int getHorizontalPos() {
        return rectangle.x;
    }

    public int getYVelocity() {
        return verticalVelocity;
    }

    public int getXVelocity() {
        return horizontalVelocity;
    }

    public void setHorizontalVelocity(int horizontalVelocity) {
        this.horizontalVelocity = horizontalVelocity;
    }

    public void increaseHorizontalVelocity() {
        horizontalVelocity++;
    }

    public void increaseVerticalVelocity() {
        verticalVelocity++;
    }

    public void reduceVerticalVelocity() {
        verticalVelocity--;
    }
}