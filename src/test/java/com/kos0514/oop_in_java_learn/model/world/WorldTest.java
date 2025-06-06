package com.kos0514.oop_in_java_learn.model.world;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WorldTest {

    @Nested
    class FantasyWorldTest {

        @Test
        void getName_returnsCorrectName() {
            // Arrange
            var world = new FantasyWorld();

            // Act
            var name = world.getName();

            // Assert
            assertThat(name).isEqualTo("剣と魔法の世界");
        }

        @Test
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
    class MagicTechWorldTest {

        @Test
        void getName_returnsCorrectName() {
            // Arrange
            var world = new MagicTechWorld();

            // Act
            var name = world.getName();

            // Assert
            assertThat(name).isEqualTo("魔導先進国");
        }

        @Test
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
    class CultivationWorldTest {

        @Test
        void getName_returnsCorrectName() {
            // Arrange
            var world = new CultivationWorld();

            // Act
            var name = world.getName();

            // Assert
            assertThat(name).isEqualTo("仙人道修行");
        }

        @Test
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
    class FullDiveGameWorldTest {

        @Test
        void getName_returnsCorrectName() {
            // Arrange
            var world = new FullDiveGameWorld();

            // Act
            var name = world.getName();

            // Assert
            assertThat(name).isEqualTo("フルダイブゲーム");
        }

        @Test
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