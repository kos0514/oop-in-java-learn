package com.kos0514.oop_in_java_learn.service.world;

import com.kos0514.oop_in_java_learn.io.test.TestInputProvider;
import com.kos0514.oop_in_java_learn.model.world.World;
import com.kos0514.oop_in_java_learn.repository.WorldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SelectWorldServiceTest {

    @Mock
    private WorldRepository worldRepository;

    @InjectMocks
    private SelectWorldService selectWorldService;

    // テスト用のモックデータ
    private List<World> availableWorlds;
    private World testWorld1;
    private World testWorld2;
    private World testWorld3;

    @BeforeEach
    void setUp() {
        // テスト用の世界データを作成
        testWorld1 = mock(World.class);
        when(testWorld1.getName()).thenReturn("ファンタジー世界");
        when(testWorld1.getDescription()).thenReturn("魔法と剣が支配する世界");

        testWorld2 = mock(World.class);
        when(testWorld2.getName()).thenReturn("SF世界");
        when(testWorld2.getDescription()).thenReturn("科学技術が発達した未来世界");

        testWorld3 = mock(World.class);
        when(testWorld3.getName()).thenReturn("現代世界");
        when(testWorld3.getDescription()).thenReturn("現代の地球と似た世界");

        // 利用可能な世界リストを作成
        availableWorlds = List.of(testWorld1, testWorld2, testWorld3);
    }

    @Nested
    @DisplayName("selectWorld メソッドのテスト")
    class SelectWorld {

        @Test
        @DisplayName("正常系: 1番目の世界が選択される場合")
        void selectFirstWorld_returnsFirstWorld() {
            // Arrange
            // プレイヤーの入力をシミュレート（1番目の世界を選択）
            var inputProvider = new TestInputProvider()
                    .addInput("1");

            // 利用可能な世界リストを設定
            when(worldRepository.getAvailableWorlds()).thenReturn(availableWorlds);

            // Act
            var selectedWorld = selectWorldService.selectWorld(inputProvider);

            // Assert
            assertThat(selectedWorld).isEqualTo(testWorld1);
            verify(worldRepository).getAvailableWorlds();
        }

        @Test
        @DisplayName("正常系: 2番目の世界が選択される場合")
        void selectSecondWorld_returnsSecondWorld() {
            // Arrange
            // プレイヤーの入力をシミュレート（2番目の世界を選択）
            var inputProvider = new TestInputProvider()
                    .addInput("2");

            // 利用可能な世界リストを設定
            when(worldRepository.getAvailableWorlds()).thenReturn(availableWorlds);

            // Act
            var selectedWorld = selectWorldService.selectWorld(inputProvider);

            // Assert
            assertThat(selectedWorld).isEqualTo(testWorld2);
            verify(worldRepository).getAvailableWorlds();
        }

        @Test
        @DisplayName("正常系: 3番目の世界が選択される場合")
        void selectThirdWorld_returnsThirdWorld() {
            // Arrange
            // プレイヤーの入力をシミュレート（3番目の世界を選択）
            var inputProvider = new TestInputProvider()
                    .addInput("3");

            // 利用可能な世界リストを設定
            when(worldRepository.getAvailableWorlds()).thenReturn(availableWorlds);

            // Act
            var selectedWorld = selectWorldService.selectWorld(inputProvider);

            // Assert
            assertThat(selectedWorld).isEqualTo(testWorld3);
            verify(worldRepository).getAvailableWorlds();
        }

        @Test
        @DisplayName("異常系: 範囲外の選択の後に有効な選択がされる場合")
        void outOfRangeSelection_eventuallySucceeds() {
            // Arrange
            // プレイヤーの入力をシミュレート（範囲外の選択→有効な選択）
            var inputProvider = new TestInputProvider()
                    .addInputs("5", "0", "1");

            // 利用可能な世界リストを設定
            when(worldRepository.getAvailableWorlds()).thenReturn(availableWorlds);

            // Act
            var selectedWorld = selectWorldService.selectWorld(inputProvider);

            // Assert
            assertThat(selectedWorld).isEqualTo(testWorld1);
            verify(worldRepository).getAvailableWorlds();
        }

        @Test
        @DisplayName("異常系: 数値以外の入力の後に有効な選択がされる場合")
        void nonNumericInput_eventuallySucceeds() {
            // Arrange
            // プレイヤーの入力をシミュレート（数値以外の入力→有効な選択）
            var inputProvider = new TestInputProvider()
                    .addInputs("abc", "2");

            // 利用可能な世界リストを設定
            when(worldRepository.getAvailableWorlds()).thenReturn(availableWorlds);

            // Act
            var selectedWorld = selectWorldService.selectWorld(inputProvider);

            // Assert
            assertThat(selectedWorld).isEqualTo(testWorld2);
            verify(worldRepository).getAvailableWorlds();
        }
    }
}
