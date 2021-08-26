package com.pingponggame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Help extends GUIState {

    private static final File backgroundFile = new File("src/main/resources/static/pongTable.jpg");
    private static final File blackPaddle = new File("src/main/resources/static/Black-Paddle80x99.png");
    private static final File redPaddle = new File("src/main/resources/static/Red-Paddle80x99.png");
    private static final File up = new File("src/main/resources/static/UP.jpg");
    private static final File down = new File("src/main/resources/static/DOWN.jpg");
    private static final File upW = new File("src/main/resources/static/W.jpg");
    private static final File downS = new File("src/main/resources/static/S.jpg");
    private BufferedImage backgroundImage;
    private BufferedImage blackPaddleImage;
    private BufferedImage redPaddleImage;
    private BufferedImage upImage;
    private BufferedImage downImage;
    private BufferedImage upWImage;
    private BufferedImage downSImage;

    public Help(GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        init();
    }

    @Override
    public void init() {
        try {
            backgroundImage = ImageIO.read(backgroundFile);
            blackPaddleImage = ImageIO.read(blackPaddle);
            redPaddleImage = ImageIO.read(redPaddle);
            upImage = ImageIO.read(up);
            downImage = ImageIO.read(down);
            upWImage = ImageIO.read(upW);
            downSImage = ImageIO.read(downS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(blackPaddleImage, (GameEngine.WIDTH / 2) + 180, 10, null);
        g.drawImage(redPaddleImage, 180, 10, null);
        g.drawImage(upImage, (GameEngine.WIDTH / 2) + 125, 95, null);
        g.drawImage(upWImage, (GameEngine.WIDTH / 2) + 45, 95, null);
        g.drawImage(downImage, (GameEngine.WIDTH / 2) + 135, 135, null);
        g.drawImage(downSImage, (GameEngine.WIDTH / 2) + 55, 135, null);
        g.setColor(new Color(128, 0 ,0 ));
        g.setFont(new Font("Amazon", Font.PLAIN, 68));
        g.drawString("HELP",(GameEngine.WIDTH / 2) - 80, 70);
        g.setFont(new Font("Arial", Font.PLAIN, 22));
        g.setColor(new Color(128, 0 ,0 ));
        g.drawString("Move Paddle Up:         or:", (GameEngine.WIDTH / 4) + 80, 120);
        g.drawString("Move Paddle Down:         or:", (GameEngine.WIDTH / 4) + 66, 160);
        g.drawString("RULES:", (GameEngine.WIDTH / 2) - 34, 220);
        g.drawString("The game is on until one of the players wins two sets." , 170, 270);
        g.drawString("Every set is played till 11 points." , 276, 300);
        g.drawString("Points are gained if the opponent does not hit the ball." , 170, 330);
        g.setColor(Color.YELLOW);
        g.drawString("BACK", (GameEngine.WIDTH / 2) - 30, GameEngine.HEIGHT - 80);
    }

    @Override
    public void onKeyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ENTER){
            selectMenuOption();
        }
    }

    @Override
    public void onKeyReleased(KeyEvent key) {

    }

    private void selectMenuOption() {
        GUIStateManager.setStates(GUIStateManager.MAIN_MENU);
    }
}