package com.kos0514.oop_in_java_learn.model.value;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SoulIdTest {

    @Nested
    class NewId {
        
        @Test
        void createsInstanceWithRandomUuid() {
            // Act
            var soulId = SoulId.newId();
            
            // Assert
            assertThat(soulId).isNotNull();
            assertThat(soulId.getId()).isNotNull();
        }
        
        @Test
        void createsUniqueInstances() {
            // Act
            var soulId1 = SoulId.newId();
            var soulId2 = SoulId.newId();
            
            // Assert
            assertThat(soulId1).isNotEqualTo(soulId2);
            assertThat(soulId1.getId()).isNotEqualTo(soulId2.getId());
        }
    }
    
    @Nested
    class Of {
        
        @Test
        void validId_createsInstance() {
            // Arrange
            var uuid = UUID.randomUUID();
            
            // Act
            var soulId = SoulId.of(uuid);
            
            // Assert
            assertThat(soulId).isNotNull();
            assertThat(soulId.getId()).isEqualTo(uuid);
        }
        
        @Test
        void nullId_throwsException() {
            // Act & Assert
            assertThatThrownBy(() -> SoulId.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("魂IDにnullは指定できません");
        }
    }
    
    @Nested
    class EqualsAndHashCode {
        
        @Test
        void sameId_areEqual() {
            // Arrange
            var uuid = UUID.randomUUID();
            var id1 = SoulId.of(uuid);
            var id2 = SoulId.of(uuid);
            
            // Act & Assert
            assertThat(id1).isEqualTo(id2);
            assertThat(id1.hashCode()).isEqualTo(id2.hashCode());
        }
        
        @Test
        void differentIds_areNotEqual() {
            // Arrange
            var id1 = SoulId.of(UUID.randomUUID());
            var id2 = SoulId.of(UUID.randomUUID());
            
            // Act & Assert
            assertThat(id1).isNotEqualTo(id2);
        }
    }
}