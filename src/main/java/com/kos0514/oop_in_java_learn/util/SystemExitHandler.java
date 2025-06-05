package com.kos0514.oop_in_java_learn.util;

import org.springframework.stereotype.Component;

/**
 * ExitHandlerインターフェースの実装クラス。
 * 実際にSystem.exit()を呼び出してJVMを終了させます。
 */
@Component
public class SystemExitHandler implements ExitHandler {
    
    @Override
    public void exit() {
        // Default exit with status 0
        System.exit(0);
    }
}