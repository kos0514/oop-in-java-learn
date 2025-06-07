package com.kos0514.oop_in_java_learn.model.world;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("World クラスのテスト")
class WorldTest {

    @Nested
    @DisplayName("FantasyWorld クラスのテスト")
    class FantasyWorldTest {

        @Test
        @DisplayName("getName メソッドが正しい名前を返すこと")
        void getName_returnsCorrectName() {
            // Arrange
            var world = new FantasyWorld();

            // Act
            var name = world.getName();

            // Assert
            assertThat(name).isEqualTo("剣と魔法の世界");
        }

        @Test
        @DisplayName("getDescription メソッドが正しい説明を返すこと")
        void getDescription_returnsCorrectDescription() {
            // Arrange
            var world = new FantasyWorld();

            // Act
            var description = world.getDescription();

            // Assert
            assertThat(description).contains("ドラゴン");
            assertThat(description).contains("魔物");
            assertThat(description).contains("冒険者ギルド");
        }
    }

    @Nested
    @DisplayName("MagicTechWorld クラスのテスト")
    class MagicTechWorldTest {

        @Test
        @DisplayName("getName メソッドが正しい名前を返すこと")
        void getName_returnsCorrectName() {
            // Arrange
            var world = new MagicTechWorld();

            // Act
            var name = world.getName();

            // Assert
            assertThat(name).isEqualTo("魔導先進国");
        }

        @Test
        @DisplayName("getDescription メソッドが正しい説明を返すこと")
        void getDescription_returnsCorrectDescription() {
            // Arrange
            var world = new MagicTechWorld();

            // Act
            var description = world.getDescription();

            // Assert
            assertThat(description).isNotBlank();
        }
    }

    @Nested
    @DisplayName("CultivationWorld クラスのテスト")
    class CultivationWorldTest {

        @Test
        @DisplayName("getName メソッドが正しい名前を返すこと")
        void getName_returnsCorrectName() {
            // Arrange
            var world = new CultivationWorld();

            // Act
            var name = world.getName();

            // Assert
            assertThat(name).isEqualTo("仙人道修行");
        }

        @Test
        @DisplayName("getDescription メソッドが正しい説明を返すこと")
        void getDescription_returnsCorrectDescription() {
            // Arrange
            var world = new CultivationWorld();

            // Act
            var description = world.getDescription();

            // Assert
            assertThat(description).isNotBlank();
        }
    }

    @Nested
    @DisplayName("FullDiveGameWorld クラスのテスト")
    class FullDiveGameWorldTest {

        @Test
        @DisplayName("getName メソッドが正しい名前を返すこと")
        void getName_returnsCorrectName() {
            // Arrange
            var world = new FullDiveGameWorld();

            // Act
            var name = world.getName();

            // Assert
            assertThat(name).isEqualTo("フルダイブゲーム");
        }

        @Test
        @DisplayName("getDescription メソッドが正しい説明を返すこと")
        void getDescription_returnsCorrectDescription() {
            // Arrange
            var world = new FullDiveGameWorld();

            // Act
            var description = world.getDescription();

            // Assert
            assertThat(description).isNotBlank();
        }
    }
}
