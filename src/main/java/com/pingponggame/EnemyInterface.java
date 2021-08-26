package com.pingponggame;

import java.awt.*;

public interface EnemyInterface {

    void initEnemy();
    void setYDirection(int randomYDirection);
    void draw(Graphics g);
}
