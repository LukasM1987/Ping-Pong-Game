package com.pingponggame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Statistics extends GUIState {

    private static final File backgroundFile = new File("src/main/resources/static/pongTable.jpg");
    private static final File blackPaddle = new File("src/main/resources/static/Black-Paddle80x99.png");
    private static final File redPaddle = new File("src/main/resources/static/Red-Paddle80x99.png");

    private BufferedImage backgroundImage;
    private BufferedImage blackPaddleImage;
    private BufferedImage redPaddleImage;

    public Statistics(GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        init();
    }

    @Override
    public void init() {
        try {
            backgroundImage = ImageIO.read(backgroundFile);
            blackPaddleImage = ImageIO.read(blackPaddle);
            redPaddleImage = ImageIO.read(redPaddle);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        drawGraphics(g);
        drawSkirmishResult(g);
        drawMenuOption(g);
    }

    private void drawGraphics(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(blackPaddleImage, (GameEngine.WIDTH / 2) + 195, 10, null);
        g.drawImage(redPaddleImage, 165, 10, null);
    }

    @Override
    public void onKeyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ENTER){
            selectMenuOption();
            Scores.setWinPlayer2 = 0;
            Scores.setWinPlayer1 = 0;
            Scores.player1Scores.clear();
            Scores.player2Scores.clear();
            DifficultyMenu.difficultyPercent = 10;
            MainMenu.getMode().clear();
            MainMenu.setCurrentChoice(0);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent key) {

    }

    private void selectMenuOption() {
        GUIStateManager.setStates(GUIStateManager.MAIN_MENU);
    }

    private void drawSkirmishResult(Graphics g) {
        g.setColor(new Color(128, 0 ,0 ));
        g.setFont(new Font("Amazon", Font.PLAIN, 68));
        g.drawString("STATISTICS",(GameEngine.WIDTH / 2) - 190, 70);
        g.setFont(new Font("Arial", Font.PLAIN, 22));
        g.setColor(Color.WHITE);
        if (MainMenu.getMode().contains(1)) {
            multiPlayerResult(g);
        } else if (MainMenu.getMode().contains(0)) {
            singlePlayerResult(g);
        }
    }

    private void singlePlayerResult(Graphics g) {
        g.drawString("Player 1 sets win: " + Scores.setWinPlayer1, (GameEngine.WIDTH / 2) - 290, (GameEngine.HEIGHT / 2) - 16);
        g.drawString("Computer sets win: " + Scores.setWinPlayer2, (GameEngine.WIDTH / 2) + 100, (GameEngine.HEIGHT / 2) - 16);
        for (int i = 0; i < Scores.player1Scores.size(); i++) {
            g.drawString("Set " + (i + 1) + ": " + Scores.player1Scores.get(i), (GameEngine.WIDTH / 2) - 236, (GameEngine.HEIGHT / 2) + 14 + (i * 24));
        }
        for (int i = 0; i < Scores.player2Scores.size(); i++) {
            g.drawString("Set " + (i + 1) + ": " + Scores.player2Scores.get(i), (GameEngine.WIDTH / 2) + 156, (GameEngine.HEIGHT / 2) + 14 + (i * 24));
        }
    }

    private void multiPlayerResult(Graphics g) {
        g.drawString("Player 1 sets win: " + Scores.setWinPlayer1, (GameEngine.WIDTH / 2) - 290, (GameEngine.HEIGHT / 2) - 16);
        g.drawString("Player 2 sets win: " + Scores.setWinPlayer2, (GameEngine.WIDTH / 2) + 100, (GameEngine.HEIGHT / 2) - 16);
        for (int i = 0; i < Scores.player1Scores.size(); i++) {
            g.drawString("Set " + (i + 1) + ": " + Scores.player1Scores.get(i), (GameEngine.WIDTH / 2) - 236, (GameEngine.HEIGHT / 2) + 14 + (i * 24));
        }
        for (int i = 0; i < Scores.player2Scores.size(); i++) {
            g.drawString("Set " + (i + 1) + ": " + Scores.player2Scores.get(i), (GameEngine.WIDTH / 2) + 156, (GameEngine.HEIGHT / 2) + 14 + (i * 24));
        }
    }

    private void drawMenuOption(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 22));
        g.setColor(Color.YELLOW);
        g.drawString("BACK TO   MAIN MENU", (GameEngine.WIDTH / 2) - 110, GameEngine.HEIGHT - 80);
    }
}
