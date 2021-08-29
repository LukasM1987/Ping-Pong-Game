package com.pingponggame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PingPongGameApplicationTests {

    @Test
    void enemyStrategyTrueTest() {
        //Given
        SinglePlayer singlePlayer = new SinglePlayer(null);

        int enemyMistake = 30;
        int setDifficulty = 60;

        //When
        boolean result = singlePlayer.setRoundDifficulty(enemyMistake, setDifficulty);

        //Then
        Assertions.assertTrue(result);
    }

    @Test
    void enemyStrategyFalseTest() {
        //Given
        SinglePlayer singlePlayer = new SinglePlayer(null);

        int enemyMistake = 76;
        int setDifficulty = 60;

        //When
        boolean result = singlePlayer.setRoundDifficulty(enemyMistake, setDifficulty);

        //Then
        Assertions.assertFalse(result);
    }

    @Test
    void enemyLowerEdgeTest() {
        //Given
        SinglePlayer singlePlayer = new SinglePlayer(null);

        //When
        int result  = singlePlayer.enemyIntersectsLowerFrameEdge();

        //Then
        Assertions.assertEquals(422, result);
    }

    @Test
    void enemyUpperEdgeTest() {
        //Given
        SinglePlayer singlePlayer = new SinglePlayer(null);

        //When
        int result  = singlePlayer.enemyIntersectsUpperFrameEdge();

        //Then
        Assertions.assertEquals(39, result);
    }
}