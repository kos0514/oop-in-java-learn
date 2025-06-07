package com.kos0514.oop_in_java_learn.util.log;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * ロギングに関するユーティリティメソッドを提供するクラス。
 */
@Slf4j
@UtilityClass
public class LoggingUtils {

    /**
     * 一般的な情報ログを出力します。
     *
     * @param message 出力するメッセージ
     */
    public static void info(String message) {
        log.info(message);
    }

    /**
     * 一般的な情報ログを出力します（フォーマット対応）。
     *
     * @param format フォーマット文字列
     * @param args   フォーマット内のプレースホルダに対応する引数
     */
    public static void info(String format, Object... args) {
        log.info(format, args);
    }

    /**
     * 警告ログを出力します。
     *
     * @param message 出力するメッセージ
     */
    public static void warn(String message) {
        log.warn(message);
    }

    /**
     * 警告ログを出力します（フォーマット対応）。
     *
     * @param format フォーマット文字列
     * @param args   フォーマット内のプレースホルダに対応する引数
     */
    public static void warn(String format, Object... args) {
        log.warn(format, args);
    }


    /**
     * 転生プロセスのセパレーターを表示します。
     */
    public static void printSeparator() {
        info("======================================");
    }

    /**
     * ログに開始用のセパレーターを表示します。
     * このメソッドは視覚的な区切りのため、1行の空行を出力した後にセパレーターを表示します。
     * セクションの開始部分で呼び出すことを想定しています。
     */
    public static void startPrintSeparator() {
        info("");
        printSeparator();
    }

    /**
     * ログに終了用のセパレーターを表示します。
     * このメソッドは視覚的な区切りのため、セパレーターを表示した後に1行の空行を出力します。
     * セクションの終了部分で呼び出すことを想定しています。
     */
    public static void endPrintSeparator() {
        printSeparator();
        info("");
    }

    /**
     * 数値入力が必要な場合に、ユーザーが非数値を入力した際の警告メッセージを表示します。
     * 入力検証のエラーハンドリングで使用することを想定しています。
     */
    public static void warnInputNumber() {
        warn("数値を入力してください。");
    }
}
