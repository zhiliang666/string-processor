package com.example.stringprocessor.strategy;

import cn.hutool.core.util.StrUtil;

/**
 * @author fuzl
 * @date 2025/9/3
 * @Description 移除重复字符
 */
public class RemovalStrategy implements ProcessingStrategy {
    @Override
    public String processConsecutiveChars(String str, int start, int end) {
        String result = str.substring(0, start) + str.substring(end);

        System.out.println(StrUtil.format("-> {} ", result));
        return result;
    }
}
