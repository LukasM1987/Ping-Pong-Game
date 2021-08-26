package com.pingponggame;

import java.awt.*;

public class Field {

    private Rectangle rectangle;

    public Field(int xPosition, int yPosition, int WIDTH, int HEIGHT) {
        this.rectangle = new Rectangle(xPosition, yPosition, WIDTH, HEIGHT);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(11,176, 55));
        g.fillRect(rectangle.x, rectangle.y ,rectangle.width, rectangle.height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getHorizontalPos() {
        return rectangle.y;
    }

    public void setHorizontalPos(int pos) {
        this.rectangle.x = pos;
    }
}
