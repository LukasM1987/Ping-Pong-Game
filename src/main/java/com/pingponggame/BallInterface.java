package com.pingponggame;;

import java.awt.*;

public interface BallInterface {
    void initBall();
    void setXDirection(int randomXDirection);
    void setYDirection(int randomYDirection);
    void move();
    void draw(Graphics g);
}
