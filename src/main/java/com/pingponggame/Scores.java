package com.pingponggame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Scores {

    public static final List<Integer> player1Scores = new ArrayList<>();
    public static final List<Integer> player2Scores = new ArrayList<>();

    public static int setWinPlayer1;
    public static int setWinPlayer2;

    public int scorePlayer1;
    public int scorePlayer2;

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawString(scorePlayer1 / 10 + String.valueOf(scorePlayer1 % 10), (GameEngine.WIDTH / 2) - 85, 50);
        g.drawString(scorePlayer2 / 10 + String.valueOf(scorePlayer2 % 10), (GameEngine.WIDTH / 2) + 20, 50);
        g.drawString(String.valueOf(setWinPlayer1), (GameEngine.WIDTH / 2) - 52, GameEngine.HEIGHT -  12);
        g.drawString(String.valueOf(setWinPlayer2), (GameEngine.WIDTH / 2) + 20, GameEngine.HEIGHT - 12);
    }
}