package com.kos0514.oop_in_java_learn.io;

/**
 * ユーザー入力を提供するインターフェース
 */
public interface UserInputProvider extends AutoCloseable {
    /**
     * ユーザーからの入力を1行読み取ります
     * @return 入力された文字列
     */
    String readLine();
}
