package com.pingponggame;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class GUIState {

    protected GUIStateManager GUIStateManager;

    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract void onKeyPressed(KeyEvent key);
    public abstract void onKeyReleased(KeyEvent key);
}
