package com.pingponggame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {

    private static final int ballSpeed = 10;

    private int gameMode;
    private int playerID;
    private int verticalVelocity;
    private Rectangle rectangle;

    public Paddle(int xPosition, int yPosition, int PADDLE_WIDTH, int PADDLE_HEIGHT, int gameMode, int playerID) {
        this.gameMode = gameMode;
        this.playerID = playerID;
        this.rectangle = new Rectangle(xPosition, yPosition, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public void setYDirection(int yDirection) {
        verticalVelocity = yDirection;
    }

    public void movePaddle(KeyEvent key) {

        if (gameMode == 1) {
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
        if (gameMode == 2) {
            switch (playerID) {
                case 1:
                    if (key.getKeyCode() == KeyEvent.VK_W) {
                        setYDirection(-ballSpeed);
                    }

                    if (key.getKeyCode() == KeyEvent.VK_S) {
                        setYDirection(ballSpeed);
                    }
                    break;

                case 2:
                    if (key.getKeyCode() == KeyEvent.VK_UP) {
                        setYDirection(-ballSpeed);
                    }

                    if (key.getKeyCode() == KeyEvent.VK_DOWN) {
                        setYDirection(ballSpeed);
                    }
                    break;
            }
        }
    }

    public void stopPaddle(KeyEvent key) {

        if (gameMode == 1) {
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
        if (gameMode == 2) {
            switch (playerID) {
                case 1:
                    if (key.getKeyCode() == KeyEvent.VK_W) {
                        setYDirection(0);
                    }

                    if (key.getKeyCode() == KeyEvent.VK_S) {
                        setYDirection(0);
                    }
                    break;

                case 2:
                    if (key.getKeyCode() == KeyEvent.VK_UP) {
                        setYDirection(0);
                    }

                    if (key.getKeyCode() == KeyEvent.VK_DOWN) {
                        setYDirection(0);
                    }
                    break;
            }
        }
    }

    public void move() {
        rectangle.y += verticalVelocity;
    }

    public void draw(Graphics g) {
        if (gameMode == 2) {
            if (playerID == 1) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }
        } else {
            g.setColor(Color.BLACK);
        }
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
