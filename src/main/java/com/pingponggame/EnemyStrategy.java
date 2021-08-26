package com.pingponggame;

public enum EnemyStrategy {

    HARD_STRATEGY_WITHOUT_FAIL (1),
    HARD_STRATEGY_WITH_FAIL (2),
    MEDIUM_STRATEGY (3);

    private int strategyCase;

    EnemyStrategy(int strategyCase) {
        this.strategyCase = strategyCase;
    }

    public int getStrategyCase() {
        return strategyCase;
    }
}
