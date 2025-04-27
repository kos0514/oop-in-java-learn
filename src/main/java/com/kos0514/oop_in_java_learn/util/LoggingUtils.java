package com.kos0514.oop_in_java_learn.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * ロギングに関するユーティリティメソッドを提供するクラス。
 */
@Slf4j
@UtilityClass
public class LoggingUtils {
    
    /**
     * 転生プロセスのセパレーターを表示します。
     */
    public static void printSeparator() {
        log.info("======================================");
    }
}