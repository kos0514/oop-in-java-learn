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
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}