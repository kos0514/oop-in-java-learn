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

    /**
     * ログに開始用のセパレーターを表示します。
     * このメソッドは視覚的な区切りのため、1行の空行を出力した後にセパレーターを表示します。
     * セクションの開始部分で呼び出すことを想定しています。
     */
    public static void startPrintSeparator() {
        log.info("");
        printSeparator();
    }

    /**
     * ログに終了用のセパレーターを表示します。
     * このメソッドは視覚的な区切りのため、セパレーターを表示した後に1行の空行を出力します。
     * セクションの終了部分で呼び出すことを想定しています。
     */
    public static void endPrintSeparator() {
        printSeparator();
        log.info("");
    }

    /**
     * 数値入力が必要な場合に、ユーザーが非数値を入力した際の警告メッセージを表示します。
     * 入力検証のエラーハンドリングで使用することを想定しています。
     */
    public static void warnInputNumber() {
        log.warn("数値を入力してください。");
    }
}
