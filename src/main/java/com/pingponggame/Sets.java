package com.pingponggame;

public enum Sets {

    ZERO_WINS (0),
    ONE_WIN (1),
    TWO_WINS (2);

    private int setWins;

    Sets(int setWins) {
        this.setWins = setWins;
    }

    public int getSetWins() {
        return setWins;
    }
}
