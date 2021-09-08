package com.pingponggame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainMenu extends GUIState {

    private static final String[] options = {"SINGLE PLAYER", " MULTI PLAYER", "        HELP", "         EXIT"};
    private static final File backgroundFile = new File("src/main/resources/static/pongMenu.jpg");
    private static final File pongLogo = new File("src/main/resources/static/logo.png");
    private static final List<Integer> gameMode = new ArrayList<>();

    private static int currentChoice = 0;

    private BufferedImage backgroundImage;
    private BufferedImage logoImage;

    public MainMenu(GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        init();
    }
    public void init(){
        try {
            backgroundImage = ImageIO.read(backgroundFile);
            logoImage = ImageIO.read(pongLogo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(){

    }

    public void draw(Graphics g){
        drawGraphics(g);
        drawMenuOptions(g);
    }

    public static int setCurrentChoice(int choice) {
        return currentChoice = choice;
    }

    public static List<Integer> getMode() {
        return gameMode;
    }

    private void drawGraphics(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(logoImage, (GameEngine.WIDTH / 2) - (logoImage.getWidth() / 2), 15, null);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
    }

    private void drawMenuOptions(Graphics g) {
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], (GameEngine.WIDTH / 2) - 110, 250 + i * 30);
        }
    }

    private void addMode(int choice) {
        gameMode.add(choice);
    }

    private int selectMenuOption(){
        if(currentChoice == 0){
            GUIStateManager.setStates(GUIStateManager.SET_DIFFICULTY);
        }

        if (currentChoice == 1) {
            GUIStateManager.setStates(GUIStateManager.MULTI_PLAYER);
        }

        if(currentChoice == 2){
            GUIStateManager.setStates(GUIStateManager.HELP);
        }

        if(currentChoice == 3){
            exitGame();
        }
        return currentChoice;
    }

    @Override
    public void onKeyPressed(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            addMode(selectMenuOption());
        }

        if (key.getKeyCode() == KeyEvent.VK_UP || key.getKeyCode() == KeyEvent.VK_W) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }

        if (key.getKeyCode() == KeyEvent.VK_DOWN || key.getKeyCode() == KeyEvent.VK_S) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void onKeyReleased(KeyEvent key) {

    }

    private void exitGame() {
        System.exit(0);
    }
}
