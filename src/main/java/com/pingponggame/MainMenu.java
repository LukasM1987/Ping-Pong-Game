package com.pingponggame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;


public class MainMenu extends GUIState {


    private int currentChoice = 0;
    private static final String[] options = {"NEW GAME", "     HELP", "      EXIT"};
    private static final File backgroundFile = new File("src/main/resources/static/pongMenu.jpg");
    private static final File pongLogo = new File("src/main/resources/static/logo.png");
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
            g.drawString(options[i], (GameEngine.WIDTH / 2) - 80, 260 + i * 30);
        }
    }

    private void selectMenuOption(){
        if(currentChoice == 0){
            GUIStateManager.setStates(GUIStateManager.SET_DIFFICULTY);
        }

        if(currentChoice == 1){
            GUIStateManager.setStates(GUIStateManager.HELP);
        }

        if(currentChoice == 2){
            exitGame();
        }
    }

    @Override
    public void onKeyPressed(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            selectMenuOption();
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
