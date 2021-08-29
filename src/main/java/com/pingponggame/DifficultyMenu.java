package com.pingponggame;;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class DifficultyMenu extends GUIState {

    private static final String[] menuOptions = {"START", " BACK"};
    private static final File pongLogo = new File("src/main/resources/static/logo.png");
    private static final File decrementFile = new File("src/main/resources/static/Decrement.jpg");
    private static final File incrementFile = new File("src/main/resources/static/Increment.jpg");
    private static final int MIN_PERCENT = 9;
    private static final int MAX_PERCENT = 101;
    private static final int GUI_MIN_PERCENT = 10;
    private static final int GUI_MAX_PERCENT = 100;

    public static int difficultyPercent = 10;

    private int currentChoiceMenu = 0;

    private BufferedImage incrementImage;
    private BufferedImage decrementImage;
    private BufferedImage logoImage;

    public DifficultyMenu(GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        init();
    }


    @Override
    public void init() {

        try {
            logoImage = ImageIO.read(pongLogo);

            incrementImage = ImageIO.read(incrementFile);
            decrementImage = ImageIO.read(decrementFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        setGraphics(g);
        setMenuOptions(g);
    }

    private void setGraphics(Graphics g) {
        g.setColor(new Color(11,176, 55));
        g.fillRect(0, 0, GameEngine.WIDTH, GameEngine.HEIGHT);
        g.drawImage(logoImage, (GameEngine.WIDTH / 2) - (logoImage.getWidth() / 2), 15, null);
        g.drawImage(decrementImage, (GameEngine.WIDTH / 2) - 120, GameEngine.HEIGHT / 2 + 14, null);
        g.drawImage(incrementImage, (GameEngine.WIDTH / 2) + 60, GameEngine.HEIGHT / 2 + 14, null);
        g.setFont(new Font("Consolas", Font.PLAIN, 32));
        g.setColor(Color.WHITE);
        g.drawString("SET GAME DIFFICULTY", (GameEngine.WIDTH / 2) - 160, GameEngine.HEIGHT / 2);
        g.drawString(difficultyPercent + "%", (GameEngine.WIDTH / 2) - 24, GameEngine.HEIGHT / 2 + 40);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
    }

    private void setMenuOptions(Graphics g) {
        for (int i = 0; i < menuOptions.length; i++) {
            if (i == currentChoiceMenu) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(menuOptions[i], (GameEngine.WIDTH / 2) - 50, (GameEngine.HEIGHT - 80) + i * 30);
        }
    }

    private void selectMenuOption(){
        if(currentChoiceMenu == 0){
            GUIStateManager.setStates(GUIStateManager.SINGLE_PLAYER);
        }

        if(currentChoiceMenu == 1){
            GUIStateManager.setStates(GUIStateManager.MAIN_MENU);
            MainMenu.getMode().clear();
            difficultyPercent = 0;
            currentChoiceMenu = 0;
        }
    }

    @Override
    public void onKeyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            selectMenuOption();
        }
        if (key.getKeyCode() == KeyEvent.VK_UP || key.getKeyCode() == KeyEvent.VK_W) {
            currentChoiceMenu--;
            if (currentChoiceMenu == -1) {
                currentChoiceMenu = menuOptions.length - 1;
            }
        }
        if (key.getKeyCode() == KeyEvent.VK_DOWN || key.getKeyCode() == KeyEvent.VK_S) {
            currentChoiceMenu++;
            if (currentChoiceMenu == menuOptions.length) {
                currentChoiceMenu = 0;
            }
        }

        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            difficultyPercent--;
            if (difficultyPercent <= MIN_PERCENT) {
                difficultyPercent = GUI_MIN_PERCENT;
            }
        }
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
            difficultyPercent++;
            if (difficultyPercent == MAX_PERCENT) {
                difficultyPercent = GUI_MAX_PERCENT;
            }
        }
    }

    @Override
    public void onKeyReleased(KeyEvent key) {

    }
}
