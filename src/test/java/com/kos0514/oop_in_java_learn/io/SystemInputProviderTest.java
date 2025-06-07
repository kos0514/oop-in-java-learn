package com.kos0514.oop_in_java_learn.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("SystemInputProvider クラスのテスト")
class SystemInputProviderTest {

    // Store the original System.in to restore it after tests
    private final InputStream originalSystemIn = System.in;

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }

    @Nested
    @DisplayName("readLine メソッドのテスト")
    class ReadLine {

        @Test
        @DisplayName("System.in からの入力を返すこと")
        void returnsInputFromSystemIn() {
            // Arrange
            String testInput = "test input";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            var provider = new SystemInputProvider();

            // Act
            String result = provider.readLine();

            // Assert
            assertThat(result).isEqualTo(testInput);

            // Clean up
            provider.close();
        }

        @Test
        @DisplayName("空の入力を処理できること")
        void handlesEmptyInput() {
            // Arrange
            String testInput = "";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            var provider = new SystemInputProvider();

            // Act
            String result = provider.readLine();

            // Assert
            assertThat(result).isEmpty();

            // Clean up
            provider.close();
        }

        @Test
        @DisplayName("複数行の入力を処理できること")
        void handlesMultipleLines() {
            // Arrange
            String testInput = "line1\nline2\nline3";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            var provider = new SystemInputProvider();

            // Act
            String firstLine = provider.readLine();
            String secondLine = provider.readLine();
            String thirdLine = provider.readLine();

            // Assert
            assertThat(firstLine).isEqualTo("line1");
            assertThat(secondLine).isEqualTo("line2");
            assertThat(thirdLine).isEqualTo("line3");

            // Clean up
            provider.close();
        }
    }

    @Nested
    @DisplayName("close メソッドのテスト")
    class Close {

        @Test
        @DisplayName("Scanner を正しく閉じること")
        void closesScanner() {
            // This test uses a mock Scanner to verify that close() is called

            // Create a custom SystemInputProvider with a mock Scanner
            class TestableSystemInputProvider extends SystemInputProvider {
                private final Scanner mockScanner;

                public TestableSystemInputProvider(Scanner mockScanner) {
                    // Call the parent constructor to initialize fields
                    super();
                    // Use reflection to replace the scanner field with our mock
                    try {
                        var scannerField = SystemInputProvider.class.getDeclaredField("scanner");
                        scannerField.setAccessible(true);
                        scannerField.set(this, mockScanner);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to set mock scanner", e);
                    }
                    this.mockScanner = mockScanner;
                }
            }

            // Arrange
            var mockScanner = mock(Scanner.class);
            var provider = new TestableSystemInputProvider(mockScanner);

            // Act
            provider.close();

            // Assert
            verify(mockScanner).close();
        }
    }
}
