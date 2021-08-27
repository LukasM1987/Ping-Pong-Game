package com.pingponggame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {

    private static final int ballSpeed = 10;

    private int verticalVelocity;
    private Rectangle rectangle;

    public Paddle(int xPosition, int yPosition, int PADDLE_WIDTH, int PADDLE_HEIGHT) {
        rectangle = new Rectangle(xPosition, yPosition, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public void setYDirection(int yDirection) {
        verticalVelocity = yDirection;
    }

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

    public void move() {
        rectangle.y += verticalVelocity;
    }

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