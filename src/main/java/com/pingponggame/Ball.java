package com.pingponggame;;
import java.awt.*;
import java.util.Random;

public class Ball implements BallInterface {

    private static final Random random = new Random();
    private static int initialSpeed = 2;

    public int ballHorizontalVelocity;
    public int ballVerticalVelocity;

    private Rectangle rectangle;

    public Ball(int xPosition, int yPosition, int xSize, int ySize) {
        this.rectangle = new Rectangle(xPosition, yPosition, xSize, ySize);
        initBall();
    }

    @Override
    public void initBall() {
        int randomXDirection = random.nextInt(2);
        int randomYDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }

        setXDirection(randomXDirection * initialSpeed);

        if (randomYDirection == 0) {
            randomYDirection--;
        }

        setYDirection(randomYDirection * initialSpeed);
    }

    @Override
    public void setXDirection(int randomXDirection) {
        ballHorizontalVelocity = randomXDirection;
    }

    @Override
    public void setYDirection(int randomYDirection) {
        ballVerticalVelocity = randomYDirection;
    }

    @Override
    public void move() {
        rectangle.x += ballHorizontalVelocity;
        rectangle.y += ballVerticalVelocity;
    }

    @Override
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
        return ballVerticalVelocity;
    }

    public int getXVelocity() {
        return ballHorizontalVelocity;
    }

}