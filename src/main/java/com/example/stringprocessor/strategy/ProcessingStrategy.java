package com.example.stringprocessor.strategy;

/**
 * @author fuzl
 * @date 2025/9/3
 * @Description 处理策略
 */
public interface ProcessingStrategy {
    /**
     * 处理连续的字符
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    String processConsecutiveChars(String str, int start, int end);
}
