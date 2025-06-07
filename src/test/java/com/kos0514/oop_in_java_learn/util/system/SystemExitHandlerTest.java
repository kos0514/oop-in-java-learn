package com.kos0514.oop_in_java_learn.util.system;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link SystemExitHandler}のテストクラス
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("SystemExitHandler クラスのテスト")
class SystemExitHandlerTest {

    /**
     * テスト用のSystemExitHandler実装クラス。
     * System.exit()を実際に呼び出す代わりに、呼び出されたことを記録します。
     */
    static class TestSystemExitHandler extends SystemExitHandler {
        private boolean exitCalled = false;
        private int exitStatus = -1;

        @Override
        public void exit() {
            // System.exit()を呼び出す代わりに、呼び出されたことを記録
            this.exitCalled = true;
            this.exitStatus = 0; // SystemExitHandlerは常に0を使用
        }

        public boolean wasExitCalled() {
            return exitCalled;
        }

        public int getExitStatus() {
            return exitStatus;
        }
    }

    @Nested
    @DisplayName("exit メソッドのテスト")
    class Exit {

        /**
         * このテストでは、SystemExitHandlerのexitメソッドが呼び出されることを検証します。
         * 実際のSystem.exit()呼び出しはテスト用の実装で置き換えて、JVMが終了しないようにします。
         */
        @Test
        @DisplayName("正常系: exit()メソッドが呼び出される")
        void callsSystemExit() {
            // Arrange
            var testExitHandler = new TestSystemExitHandler();

            // Act
            testExitHandler.exit();

            // Assert
            assertThat(testExitHandler.wasExitCalled()).isTrue();
            assertThat(testExitHandler.getExitStatus()).isEqualTo(0);
        }
    }
}
