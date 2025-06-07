package com.kos0514.oop_in_java_learn.io;

import java.util.Scanner;

/**
 * 標準入力からユーザー入力を提供する実装
 */
public class SystemInputProvider implements UserInputProvider {
    private final Scanner scanner;

    public SystemInputProvider() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        // 次の行があるかどうか確認してから読み込む
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        // 入力が空の場合は空文字列を返す
        return "";
    }

    @Override
    public void close() {
        scanner.close();
    }
}