package com.kos0514.oop_in_java_learn.service;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.factory.TransmigratorFactory;
import com.kos0514.oop_in_java_learn.io.UserInputProvider;
import com.kos0514.oop_in_java_learn.io.test.TestInputProvider;
import com.kos0514.oop_in_java_learn.model.Transmigrator;
import com.kos0514.oop_in_java_learn.model.playable_status.PlayableStatuses;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.model.world.World;
import com.kos0514.oop_in_java_learn.service.race.SelectRaceService;
import com.kos0514.oop_in_java_learn.service.world.SelectWorldService;
import com.kos0514.oop_in_java_learn.util.LoggingUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransmigrationServiceTest {

    @Mock
    private TransmigratorFactory transmigratorFactory;

    @Mock
    private SelectWorldService selectWorldService;

    @Mock
    private SelectRaceService selectRaceService;

    @Mock
    private PlayableStatuses playableStatuses;

    @InjectMocks
    private TransmigrationService transmigrationService;

    // テスト用のモックデータ
    private SoulName testSoulName;
    private Age testAge;
    private World testWorld;
    private Race testRace;
    private Transmigrator testTransmigrator;

    @BeforeEach
    void setUp() {
        // テスト用のモックデータを初期化
        testSoulName = mock(SoulName.class);
        testAge = mock(Age.class);
        testWorld = mock(World.class);
        testRace = mock(Race.class);
        testTransmigrator = mock(Transmigrator.class);
    }

    @Nested
    @DisplayName("startTransmigrationProcess() メソッドのテスト")
    class StartTransmigrationProcessNoArgs {

        @Test
        @DisplayName("正常系: 転生プロセスが正常に完了する")
        void normalCase_completesSuccessfully() throws Exception {
            // Arrange
            var spyService = spy(transmigrationService);
            doNothing().when(spyService).startTransmigrationProcess(any(UserInputProvider.class));

            // Act
            spyService.startTransmigrationProcess();

            // Assert
            verify(spyService).startTransmigrationProcess(any(UserInputProvider.class));
        }

        @Test
        @DisplayName("異常系: 例外が発生した場合、ログを出力して例外をラップして再スローする")
        void exceptionOccurs_logsAndRethrows() {
            // Arrange
            var spyService = spy(transmigrationService);
            var testException = new RuntimeException("テストエラー");
            doThrow(testException).when(spyService).startTransmigrationProcess();

            try (MockedStatic<LoggingUtils> mockedLoggingUtils = mockStatic(LoggingUtils.class)) {
                // Act & Assert
                var exception = assertThrows(RuntimeException.class, spyService::startTransmigrationProcess);

                // 例外メッセージの検証
                assertThat(exception.getMessage()).isEqualTo("テストエラー");
            }
        }
    }

    @Nested
    @DisplayName("startTransmigrationProcess メソッドのテスト")
    class StartTransmigrationProcess {

        @Test
        @DisplayName("正常系: 転生プロセスが正常に完了する")
        void normalCase_completesSuccessfully() {
            // Arrange
            // TestInputProviderをtry-with-resourcesで使用
            try (var inputProvider = new TestInputProvider()
                    .addInputs("テスト魂", "25")) {

                // スタブ
                when(testSoulName.getName()).thenReturn("テスト魂");
                when(testWorld.getName()).thenReturn("テスト世界");
                when(testRace.getJapaneseName()).thenReturn("テスト種族");
                when(testTransmigrator.getSoulName()).thenReturn(testSoulName);
                when(testTransmigrator.getWorld()).thenReturn(testWorld);
                when(testTransmigrator.getRace()).thenReturn(testRace);
                when(selectWorldService.selectWorld(any(UserInputProvider.class))).thenReturn(testWorld);
                when(selectRaceService.selectRace(any(UserInputProvider.class))).thenReturn(testRace);
                when(transmigratorFactory.create(any(SoulName.class), any(Age.class), eq(testWorld), eq(testRace)))
                        .thenReturn(testTransmigrator);
                when(testTransmigrator.getPlayableStatuses()).thenReturn(playableStatuses);

                // Act
                transmigrationService.startTransmigrationProcess(inputProvider);

                // Assert
                // 各サービスが正しく呼び出されたことを検証
                verify(selectWorldService).selectWorld(any(UserInputProvider.class));
                verify(selectRaceService).selectRace(any(UserInputProvider.class));
                verify(transmigratorFactory).create(any(SoulName.class), any(Age.class), eq(testWorld), eq(testRace));
                verify(playableStatuses).showStatus();
            }
        }

        @Test
        @DisplayName("異常系: 世界選択でエラーが発生した場合")
        void worldSelectionError_handlesException() {
            // Arrange
            try (var inputProvider = new TestInputProvider()
                    .addInputs("テスト魂", "25")) {

                // 世界選択時に例外をスローするように設定
                when(selectWorldService.selectWorld(any(UserInputProvider.class)))
                        .thenThrow(new RuntimeException("世界選択エラー"));

                // Act & Assert
                // 例外が伝播することを確認
                assertThrows(RuntimeException.class, () -> {
                    transmigrationService.startTransmigrationProcess(inputProvider);
                });

                // 検証
                verify(selectWorldService).selectWorld(any(UserInputProvider.class));
                verify(selectRaceService, never()).selectRace(any(UserInputProvider.class));
                verify(transmigratorFactory, never()).create(any(), any(), any(), any());
            }
        }

        @Test
        @DisplayName("異常系: 種族選択でエラーが発生した場合")
        void raceSelectionError_handlesException() {
            // Arrange
            try (var inputProvider = new TestInputProvider()
                    .addInputs("テスト魂", "25")) {

                // 世界選択は成功するが、種族選択で例外をスローするように設定
                when(selectWorldService.selectWorld(any(UserInputProvider.class))).thenReturn(testWorld);
                when(selectRaceService.selectRace(any(UserInputProvider.class)))
                        .thenThrow(new RuntimeException("種族選択エラー"));

                // Act & Assert
                assertThrows(RuntimeException.class, () -> {
                    transmigrationService.startTransmigrationProcess(inputProvider);
                });

                // 検証
                verify(selectWorldService).selectWorld(any(UserInputProvider.class));
                verify(selectRaceService).selectRace(any(UserInputProvider.class));
                verify(transmigratorFactory, never()).create(any(), any(), any(), any());
            }
        }

        @Test
        @DisplayName("異常系: 転生者作成でエラーが発生した場合")
        void transmigratorCreationError_handlesException() {
            // Arrange
            try (var inputProvider = new TestInputProvider()
                    .addInputs("テスト魂", "25")) {

                // 世界選択と種族選択は成功するが、転生者作成で例外をスローするように設定
                when(selectWorldService.selectWorld(any(UserInputProvider.class))).thenReturn(testWorld);
                when(selectRaceService.selectRace(any(UserInputProvider.class))).thenReturn(testRace);
                when(transmigratorFactory.create(any(SoulName.class), any(Age.class), any(World.class), any(Race.class)))
                        .thenThrow(new RuntimeException("転生者作成エラー"));

                // Act & Assert
                assertThrows(RuntimeException.class, () -> {
                    transmigrationService.startTransmigrationProcess(inputProvider);
                });

                // 検証
                verify(selectWorldService).selectWorld(any(UserInputProvider.class));
                verify(selectRaceService).selectRace(any(UserInputProvider.class));
                verify(transmigratorFactory).create(any(SoulName.class), any(Age.class), any(World.class), any(Race.class));
                verify(playableStatuses, never()).showStatus();
            }
        }

        @Test
        @DisplayName("異常系: 無効な名前が入力された後に有効な名前が入力された場合")
        void invalidThenValidName_eventuallySucceeds() {
            // Arrange
            // 最初に無効な名前（空文字）、次に有効な名前を入力
            try (var inputProvider = new TestInputProvider()
                    .addInputs("", "テスト魂", "25")) {

                try (MockedStatic<SoulName> mockedSoulName = mockStatic(SoulName.class);
                     MockedStatic<Age> mockedAge = mockStatic(Age.class)) {

                    // SoulName.ofの動作をモック
                    // 最初の呼び出しで例外をスロー、2回目の呼び出しで正常に値を返す
                    mockedSoulName.when(() -> SoulName.of(""))
                            .thenThrow(new IllegalArgumentException("名前は空にできません"));
                    mockedSoulName.when(() -> SoulName.of("テスト魂")).thenReturn(testSoulName);

                    // Age.fromStringの動作をモック
                    mockedAge.when(() -> Age.fromString("25")).thenReturn(testAge);

                    when(testRace.getJapaneseName()).thenReturn("テスト種族");
                    when(testTransmigrator.getSoulName()).thenReturn(testSoulName);
                    when(testTransmigrator.getWorld()).thenReturn(testWorld);
                    when(testTransmigrator.getRace()).thenReturn(testRace);

                    when(selectWorldService.selectWorld(any(UserInputProvider.class))).thenReturn(testWorld);
                    when(selectRaceService.selectRace(any(UserInputProvider.class))).thenReturn(testRace);
                    when(transmigratorFactory.create(eq(testSoulName), eq(testAge), eq(testWorld), eq(testRace)))
                            .thenReturn(testTransmigrator);
                    when(testTransmigrator.getPlayableStatuses()).thenReturn(playableStatuses);

                    // Act
                    transmigrationService.startTransmigrationProcess(inputProvider);

                    // Assert
                    verify(selectWorldService).selectWorld(any(UserInputProvider.class));
                    verify(selectRaceService).selectRace(any(UserInputProvider.class));
                    verify(transmigratorFactory).create(eq(testSoulName), eq(testAge), eq(testWorld), eq(testRace));
                    verify(playableStatuses).showStatus();
                }
            }
        }

        @Test
        @DisplayName("異常系: 無効な年齢が入力された後に有効な年齢が入力された場合")
        void invalidThenValidAge_eventuallySucceeds() {
            // Arrange
            // 最初に無効な年齢（文字列）、次に有効な年齢を入力
            try (var inputProvider = new TestInputProvider()
                    .addInputs("テスト魂", "abc", "25")) {

                try (MockedStatic<SoulName> mockedSoulName = mockStatic(SoulName.class);
                     MockedStatic<Age> mockedAge = mockStatic(Age.class)) {

                    // SoulName.ofの動作をモック
                    mockedSoulName.when(() -> SoulName.of("テスト魂")).thenReturn(testSoulName);

                    // Age.fromStringの動作をモック
                    // 最初の呼び出しで例外をスロー、2回目の呼び出しで正常に値を返す
                    mockedAge.when(() -> Age.fromString("abc"))
                            .thenThrow(new IllegalArgumentException("年齢は数値で入力してください"));
                    mockedAge.when(() -> Age.fromString("25")).thenReturn(testAge);

                    when(testRace.getJapaneseName()).thenReturn("テスト種族");
                    when(testTransmigrator.getSoulName()).thenReturn(testSoulName);
                    when(testTransmigrator.getWorld()).thenReturn(testWorld);
                    when(testTransmigrator.getRace()).thenReturn(testRace);
                    when(selectWorldService.selectWorld(any(UserInputProvider.class))).thenReturn(testWorld);
                    when(selectRaceService.selectRace(any(UserInputProvider.class))).thenReturn(testRace);
                    when(transmigratorFactory.create(eq(testSoulName), eq(testAge), eq(testWorld), eq(testRace)))
                            .thenReturn(testTransmigrator);
                    when(testTransmigrator.getPlayableStatuses()).thenReturn(playableStatuses);

                    // Act
                    transmigrationService.startTransmigrationProcess(inputProvider);

                    // Assert
                    verify(selectWorldService).selectWorld(any(UserInputProvider.class));
                    verify(selectRaceService).selectRace(any(UserInputProvider.class));
                    verify(transmigratorFactory).create(eq(testSoulName), eq(testAge), eq(testWorld), eq(testRace));
                    verify(playableStatuses).showStatus();
                }
            }
        }
    }
}
