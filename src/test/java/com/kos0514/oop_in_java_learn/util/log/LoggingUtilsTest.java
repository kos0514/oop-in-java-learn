package com.kos0514.oop_in_java_learn.util.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link LoggingUtils}のテストクラス
 */
class LoggingUtilsTest {

    private Logger logger;
    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUp() {
        // SLF4Jのロガーを取得し、Logbackのロガーにキャスト
        logger = (Logger) LoggerFactory.getLogger(LoggingUtils.class);

        // テスト用のアペンダーを作成
        listAppender = new ListAppender<>();
        listAppender.start();

        // ロガーにアペンダーを追加
        logger.addAppender(listAppender);
    }

    @AfterEach
    void tearDown() {
        // テスト後にアペンダーを削除
        logger.detachAppender(listAppender);
    }

    @Nested
    @DisplayName("info メソッドのテスト")
    class Info {

        @Test
        @DisplayName("正常系: 単一のメッセージをINFOレベルでログ出力する")
        void logsSimpleMessageAtInfoLevel() {
            // Arrange
            var message = "テスト情報メッセージ";

            // Act
            LoggingUtils.info(message);

            // Assert
            var loggedEvent = listAppender.list.getFirst();
            assertThat(loggedEvent.getLevel()).isEqualTo(Level.INFO);
            assertThat(loggedEvent.getFormattedMessage()).isEqualTo(message);
        }

        @Test
        @DisplayName("正常系: フォーマット文字列と引数を使用してINFOレベルでログ出力する")
        void logsFormattedMessageAtInfoLevel() {
            // Arrange
            var format = "ユーザー {} が {} でログインしました";
            var username = "testuser";
            var time = "10:30";

            // Act
            LoggingUtils.info(format, username, time);

            // Assert
            var loggedEvent = listAppender.list.getFirst();
            assertThat(loggedEvent.getLevel()).isEqualTo(Level.INFO);
            assertThat(loggedEvent.getFormattedMessage())
                    .isEqualTo("ユーザー testuser が 10:30 でログインしました");
        }
    }

    @Nested
    @DisplayName("warn メソッドのテスト")
    class Warn {

        @Test
        @DisplayName("正常系: 単一のメッセージをWARNレベルでログ出力する")
        void logsSimpleMessageAtWarnLevel() {
            // Arrange
            var message = "テスト警告メッセージ";

            // Act
            LoggingUtils.warn(message);

            // Assert
            var loggedEvent = listAppender.list.getFirst();
            assertThat(loggedEvent.getLevel()).isEqualTo(Level.WARN);
            assertThat(loggedEvent.getFormattedMessage()).isEqualTo(message);
        }

        @Test
        @DisplayName("正常系: フォーマット文字列と引数を使用してWARNレベルでログ出力する")
        void logsFormattedMessageAtWarnLevel() {
            // Arrange
            var format = "無効な入力: {}, 期待値: {}";
            var actual = "abc";
            var expected = "数値";

            // Act
            LoggingUtils.warn(format, actual, expected);

            // Assert
            var loggedEvent = listAppender.list.getFirst();
            assertThat(loggedEvent.getLevel()).isEqualTo(Level.WARN);
            assertThat(loggedEvent.getFormattedMessage())
                    .isEqualTo("無効な入力: abc, 期待値: 数値");
        }
    }

    @Nested
    @DisplayName("printSeparator メソッドのテスト")
    class PrintSeparator {

        @Test
        @DisplayName("正常系: セパレーター文字列をINFOレベルでログ出力する")
        void logsSeparatorAtInfoLevel() {
            // Act
            LoggingUtils.printSeparator();

            // Assert
            var loggedEvent = listAppender.list.getFirst();
            assertThat(loggedEvent.getLevel()).isEqualTo(Level.INFO);
            assertThat(loggedEvent.getFormattedMessage())
                    .isEqualTo("======================================");
        }
    }

    @Nested
    @DisplayName("startPrintSeparator メソッドのテスト")
    class StartPrintSeparator {

        @Test
        @DisplayName("正常系: 空行とセパレーターをINFOレベルでログ出力する")
        void logsEmptyLineAndSeparatorAtInfoLevel() {
            // Act
            LoggingUtils.startPrintSeparator();

            // Assert
            assertThat(listAppender.list).hasSize(2);

            var firstEvent = listAppender.list.getFirst();
            assertThat(firstEvent.getLevel()).isEqualTo(Level.INFO);
            assertThat(firstEvent.getFormattedMessage()).isEmpty();

            var secondEvent = listAppender.list.get(1);
            assertThat(secondEvent.getLevel()).isEqualTo(Level.INFO);
            assertThat(secondEvent.getFormattedMessage())
                    .isEqualTo("======================================");
        }
    }

    @Nested
    @DisplayName("endPrintSeparator メソッドのテスト")
    class EndPrintSeparator {

        @Test
        @DisplayName("正常系: セパレーターと空行をINFOレベルでログ出力する")
        void logsSeparatorAndEmptyLineAtInfoLevel() {
            // Act
            LoggingUtils.endPrintSeparator();

            // Assert
            assertThat(listAppender.list).hasSize(2);

            var firstEvent = listAppender.list.getFirst();
            assertThat(firstEvent.getLevel()).isEqualTo(Level.INFO);
            assertThat(firstEvent.getFormattedMessage())
                    .isEqualTo("======================================");

            var secondEvent = listAppender.list.get(1);
            assertThat(secondEvent.getLevel()).isEqualTo(Level.INFO);
            assertThat(secondEvent.getFormattedMessage()).isEmpty();
        }
    }

    @Nested
    @DisplayName("warnInputNumber メソッドのテスト")
    class WarnInputNumber {

        @Test
        @DisplayName("正常系: 数値入力を促す警告メッセージをWARNレベルでログ出力する")
        void logsNumberInputPromptAtWarnLevel() {
            // Act
            LoggingUtils.warnInputNumber();

            // Assert
            var loggedEvent = listAppender.list.getFirst();
            assertThat(loggedEvent.getLevel()).isEqualTo(Level.WARN);
            assertThat(loggedEvent.getFormattedMessage())
                    .isEqualTo("数値を入力してください。");
        }
    }
}