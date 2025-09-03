package com.example.stringprocessor.strategy;

import cn.hutool.core.util.StrUtil;

/**
 * @author fuzl
 * @date 2025/9/3
 * @Description 替换重复字符
 */
public class ReplacementStrategy implements ProcessingStrategy {
    @Override
    public String processConsecutiveChars(String str, int start, int end) {
        char replacement = (char)(str.charAt(start) - 1);
        String replacementStr = String.valueOf(replacement);
        if (replacement > 'z' || replacement < 'a') {
            replacementStr = "";
        }
        String result = str.substring(0, start) + replacementStr + str.substring(end);

        String oldStr = str.substring(start,end);
        System.out.println(StrUtil.format("-> {}, {} is replaced by {} ", result, oldStr, replacementStr));
        return result;
    }
}
