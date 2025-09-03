package com.example.stringprocessor;

import com.example.stringprocessor.strategy.ProcessingStrategy;
import com.example.stringprocessor.strategy.RemovalStrategy;
import com.example.stringprocessor.strategy.ReplacementStrategy;
import com.example.stringprocessor.strategy.StrategyEnum;

/**
 * @author fuzl
 * @date 2025/9/3
 * @Description 字符串处理
 */
public class StringProcessor {
    private static final int MIN_LENGTH = 3;
    private ProcessingStrategy strategy;

    public StringProcessor() {
    }

    public StringProcessor(ProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    private void setStrategy( String mode) {
        ProcessingStrategy strategy = StrategyEnum.REPLACEMENT.getStrategy().equals(mode) ?
                new ReplacementStrategy() : new RemovalStrategy();

        this.strategy = strategy;
    }

    public String process(String input, String mode) {
        setStrategy(mode);
        return process(input);
    }

    public String process(String input) {
        if (input == null || input.length() < MIN_LENGTH) {
            return input;
        }

        String current = input;
        String previous;
        
        do {
            previous = current;
            current = processSinglePass(previous);
        } while (!current.equals(previous));

        return current;
    }

    public String processSinglePass(String str) {
        int i = 0;
        int n = str.length();

        while (i < n) {
            char current = str.charAt(i);
            int j = i + 1;
            while (j < n && str.charAt(j) == current) {
                j++;
            }
            
            if (j - i >= MIN_LENGTH) {
                return strategy.processConsecutiveChars(str, i, j);
            }
            i = j;
        }
        return str;
    }
}
