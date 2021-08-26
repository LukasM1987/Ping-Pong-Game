package com.pingponggame;

public interface SkirmishInterface {

    void newPaddles();
    void newBall();
    boolean setRoundDifficulty(int enemyMistake, int setedDificulty);
    void enemyStrategy();
    void checkBallPaddleCollision();
    void checkBallFrameCollision();
    void checkPaddleFrameCollision();
    void givePlayerPoint();
    void giveEnemyPoint();
    void checkSetWinner();
    void checkWhoWinSkirmish();
    void addPlayer1Score();
    void addPlayer2Score();
    int enemyIntersectsUpperFrameEdge();
    int enemyIntersectsLowerFrameEdge();

}
