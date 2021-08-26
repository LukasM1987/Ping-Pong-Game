package com.pingponggame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle implements PaddleInterface {

    private static final int ballSpeed = 10;

    private int paddleVerticalVelocity;
    private Rectangle rectangle;

    public Paddle(int xPosition, int yPosition, int PADDLE_WIDTH, int PADDLE_HEIGHT) {
        rectangle = new Rectangle(xPosition, yPosition, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    @Override
    public void setYDirection(int yDirection) {
        paddleVerticalVelocity = yDirection;
    }

    @Override
    public void movePaddle(KeyEvent key) {

        if (key.getKeyCode() == KeyEvent.VK_W) {
            setYDirection(-ballSpeed);
        }

        if (key.getKeyCode() == KeyEvent.VK_S) {
            setYDirection(ballSpeed);
        }

        if (key.getKeyCode() == KeyEvent.VK_UP) {
            setYDirection(-ballSpeed);
        }

        if (key.getKeyCode() == KeyEvent.VK_DOWN) {
            setYDirection(ballSpeed);
        }
    }

    @Override
    public void stopPaddle(KeyEvent key) {

        if (key.getKeyCode() == KeyEvent.VK_W) {
            setYDirection(0);
        }

        if (key.getKeyCode() == KeyEvent.VK_S) {
            setYDirection(0);
        }

        if (key.getKeyCode() == KeyEvent.VK_UP) {
            setYDirection(0);
        }

        if (key.getKeyCode() == KeyEvent.VK_DOWN) {
            setYDirection(0);
        }
    }

    @Override
    public void move() {
        rectangle.y += paddleVerticalVelocity;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(rectangle.x, rectangle.y ,rectangle.width, rectangle.height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getVerticalPos() {
        return rectangle.y;
    }

    public void setVerticalPos(int pos) {
        rectangle.y = pos;
    }
}