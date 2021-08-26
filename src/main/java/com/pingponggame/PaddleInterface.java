package com.pingponggame;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface PaddleInterface {
    void setYDirection(int yDirection);
    void movePaddle(KeyEvent key);
    void stopPaddle(KeyEvent key);
    void move();
    void draw(Graphics g);
}
