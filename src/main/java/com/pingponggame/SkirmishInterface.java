package com.pingponggame;

public interface SkirmishInterface {

    void newPaddles();
    void newBall();
    void givePlayerPoint();
    void checkSetWinner();
    void checkPaddleFrameCollision();
    void checkBallPaddleCollision();
    void checkBallFrameCollision();
    void addPlayer1Score();
    void addPlayer2Score();
    void checkWhoWinSkirmish();

}
