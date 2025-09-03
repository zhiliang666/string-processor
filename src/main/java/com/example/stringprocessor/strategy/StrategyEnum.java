package com.example.stringprocessor.strategy;

/**
 * @author fuzl
 * @date 2025/9/3
 * @Description 策略枚举
 */
public enum StrategyEnum {
    REMOVAL("remove"),
    REPLACEMENT("replace");

    public String getStrategy() {
        return strategy;
    }

    private String strategy;

    StrategyEnum(String  value) {
        this.strategy = value;
    }
}
