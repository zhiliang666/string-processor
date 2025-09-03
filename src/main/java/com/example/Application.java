package com.example;

import com.example.stringprocessor.StringProcessor;

/**
 * @author fuzl
 * @date 2025/9/3
 * @Description 入口类
 */
public class Application {
    private final static int StringProcessorParamsLength = 2;
    public static void main(String[] args) {
        if (args.length < StringProcessorParamsLength) {
            System.out.println("Usage: <input_string> <mode:remove|replace>");
            return;
        }

        StringProcessor  processor = new StringProcessor();
        String result = processor.process(args[0], args[1]);
    }
}
