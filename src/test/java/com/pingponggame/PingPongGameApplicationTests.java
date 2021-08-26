package com.pingponggame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PingPongGameApplicationTests {

    @Test
    void enemyStrategyTrueTest() {
        //When
        Skirmish skirmish = new Skirmish(null);

        int enemyMistake = 30;
        int setedDificulty = 60;

        //Then
        boolean result = skirmish.setRoundDifficulty(enemyMistake, setedDificulty);

        //Given
        Assertions.assertTrue(result);
    }

    @Test
    void enemyStrategyFalseTest() {
        //When
        Skirmish skirmish = new Skirmish(null);

        int enemyMistake = 76;
        int setedDificulty = 60;

        //Then
        boolean result = skirmish.setRoundDifficulty(enemyMistake, setedDificulty);

        //Given
        Assertions.assertFalse(result);
    }

    @Test
    void enemyLowerEdgeTest() {
        //When
        Skirmish skirmish = new Skirmish(null);

        //Then
        int result  = skirmish.enemyIntersectsLowerFrameEdge();

        //Given
        Assertions.assertEquals(422, result);
    }

    @Test
    void enemyUpperEdgeTest() {
        //When
        Skirmish skirmish = new Skirmish(null);

        //Then
        int result  = skirmish.enemyIntersectsUpperFrameEdge();

        //Given
        Assertions.assertEquals(39, result);
    }
}