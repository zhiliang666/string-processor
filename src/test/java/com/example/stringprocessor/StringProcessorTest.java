package com.example.stringprocessor;

import cn.hutool.core.util.StrUtil;
import com.example.stringprocessor.strategy.RemovalStrategy;
import com.example.stringprocessor.strategy.ReplacementStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringProcessorTest {
    private StringProcessor removalProcessor;
    private StringProcessor replacementProcessor;

    @BeforeEach
    void setup() {
        removalProcessor = new StringProcessor(new RemovalStrategy());
        replacementProcessor = new StringProcessor(new ReplacementStrategy());
    }

    @Nested
    @DisplayName("核心功能验证")
    class CoreFunctionalityTests {
        @Test
        @DisplayName("完整处理流程验证-移除策略")
        void testFullProcessingFlowRemoval() {
            assertEquals("d", removalProcessor.process("aabcccbbad"));
        }

        @Test
        @DisplayName("完整处理流程验证-替换策略")
        void testFullProcessingFlowReplacement() {
            assertEquals("d", replacementProcessor.process("abcccbad"));
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class BoundaryConditionTests {
        @ParameterizedTest
        @ValueSource(strings = {"", "a", "ab", "aab"})
        @DisplayName("短字符串处理")
        void testShortStrings(String input) {
            assertEquals(input, removalProcessor.process(input));
            assertEquals(input, replacementProcessor.process(input));
        }

        @Test
        @DisplayName("最小可处理单元")
        void testMinimumProcessableUnit() {
            assertEquals("", removalProcessor.process("aaa"));
            assertEquals("b", replacementProcessor.process("ccc"));
        }
    }

    @Nested
    @DisplayName("特殊字符测试")
    class SpecialCharacterTests {
        @Test
        @DisplayName("字母a边界测试")
        void testCharacterABoundary() {
            assertEquals("", replacementProcessor.process("aaa"));
        }

        @Test
        @DisplayName("字母z测试")
        void testCharacterZ() {
            assertEquals("y", replacementProcessor.process("zzz"));
        }
    }

    @Nested
    @DisplayName("性能与稳定性")
    class PerformanceAndStabilityTests {
        @Test
        @DisplayName("长字符串处理性能")
        void testLongStringPerformance() {
            String longStr = StrUtil.repeat("aabbb",1000) + "ccc";
            assertTimeoutPreemptively(
                    java.time.Duration.ofMillis(500),
                    () -> removalProcessor.process(longStr)
            );
        }

        @Test
        @DisplayName("连续相同字符极限测试")
        void testMaxConsecutiveChars() {
            assertEquals("", removalProcessor.process(StrUtil.repeat("a",10000)));
        }
    }

    @Nested
    @DisplayName("策略模式验证")
    class StrategyPatternTests {
        @Test
        @DisplayName("策略切换验证")
        void testStrategySwitching() {
            String input = "aaabbb";
            StringProcessor processor = new StringProcessor(new RemovalStrategy());
            String removalResult = processor.process(input);

            processor = new StringProcessor(new ReplacementStrategy());
            String replaceResult = processor.process(input);

            assertNotEquals(removalResult, replaceResult);
        }
    }

    static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("aabcccbbad", "d", "d"),
                Arguments.of("aaabbb", "", "a")
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    @DisplayName("综合参数化测试")
    void comprehensiveParameterizedTest(String input, String expectedRemoval, String expectedReplacement) {
        assertEquals(expectedRemoval, removalProcessor.process(input));
        assertEquals(expectedReplacement, replacementProcessor.process(input));
    }
}
