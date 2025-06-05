package com.kos0514.oop_in_java_learn.util;

/**
 * システム終了処理を抽象化するインターフェース。
 * テスト時にモック化することで、JVMの終了を防ぐことができます。
 */
public interface ExitHandler {
    /**
     * アプリケーションを終了します。
     * デフォルトでは、ステータス0（正常終了）でシステムを終了します。
     */
    void exit();
}