package com.pingponggame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GUIStateManager {


    private static final List<GUIState> gamesStates = new ArrayList<>();
    public static int currentState;
    public static final int MAIN_MENU = 0;
    public static final int HELP = 1;
    public static final int SET_DIFFICULTY = 2;
    public static final int SKIRMISH = 3;
    public static final int STATISTICS = 4;

    public GUIStateManager() {
        currentState = MAIN_MENU;
        gamesStates.add(new MainMenu(this));
        gamesStates.add(new Help(this));
        gamesStates.add(new DifficultyMenu(this));
        gamesStates.add(new Skirmish(this));
        gamesStates.add(new Statistics(this));
    }

    public void setStates(int state) {
        currentState = state;
        gamesStates.get(currentState).init();
    }

    public void update() {
        gamesStates.get(currentState).update();
    }


    public void draw(Graphics g) {
        gamesStates.get(currentState).draw(g);
    }

    public void keyPressed(KeyEvent key) {
        gamesStates.get(currentState).onKeyPressed(key);
    }


    public void keyReleased(KeyEvent key) {
        gamesStates.get(currentState).onKeyReleased(key);
    }
}
