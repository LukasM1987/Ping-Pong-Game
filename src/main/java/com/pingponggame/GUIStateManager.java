package com.pingponggame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GUIStateManager {


    private final List<GUIState> gamesStates = new ArrayList<>();

    public static final int MAIN_MENU = 0;
    public static final int HELP = 1;
    public static final int SET_DIFFICULTY = 2;
    public static final int SINGLE_PLAYER = 3;
    public static final int MULTI_PLAYER = 4;
    public static final int STATISTICS = 5;

    private int currentState;

    public GUIStateManager() {
        currentState = MAIN_MENU;
        gamesStates.add(new MainMenu(this));
        gamesStates.add(new Help(this));
        gamesStates.add(new DifficultyMenu(this));
        gamesStates.add(new SinglePlayer(this));
        gamesStates.add(new MultiPlayer(this));
        gamesStates.add(new Statistics(this));
    }

    public void setStates(int state) {
        setCurrentState(state);
        gamesStates.get(getCurrentState()).init();
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public void update() {
        gamesStates.get(currentState).update();
    }


    public void draw(Graphics g) {
        gamesStates.get(getCurrentState()).draw(g);
    }

    public void keyPressed(KeyEvent key) {
        gamesStates.get(getCurrentState()).onKeyPressed(key);
    }


    public void keyReleased(KeyEvent key) {
        gamesStates.get(getCurrentState()).onKeyReleased(key);
    }
}
